## ---------------------------------------------------------------------------
## diary-tracker Makefile
## ---------------------------------------------------------------------------
## Usage examples:
##   make dev-run            run app locally against dev postgres container
##   make prod-run           build + run full prod stack via compose
##   make test               run tests (spins up Testcontainers, no manual db needed)
##   make db-migrate ENV=dev migrate the dev database (flyway)
##   make db-clean ENV=dev   drop + recreate the dev database, then migrate
## ---------------------------------------------------------------------------

# Default env if ENV= not passed explicitly
ENV ?= dev

# Resolve which env file and compose file to use based on ENV
ENV_FILE := .env.$(ENV)
COMPOSE_FILE := compose.$(ENV).yaml

# Load the chosen .env file's vars into make itself (so $(DB_NAME) etc. work below)
ifneq (,$(wildcard $(ENV_FILE)))
	include $(ENV_FILE)
	export
endif

# Set DOCKER_HOST to the podman socket for the current user, so that podman compose works with testcontainers
DOCKER_HOST := unix:///run/user/$(shell id -u)/podman/podman.sock

.PHONY: help dev-run prod-run test db-migrate db-clean db-up db-down check-env

help:
	@echo "Available targets:"
	@echo "  make dev-run               start dev postgres + run app locally via maven"
	@echo "  make prod-run               build and run full prod stack via compose"
	@echo "  make test                   run test suite"
	@echo "  make db-migrate ENV=dev|prod   run flyway migrations against target db"
	@echo "  make db-clean   ENV=dev|prod   drop, recreate, and re-migrate target db"
	@echo "  make db-up      ENV=dev|prod   start just the postgres container"
	@echo "  make db-down    ENV=dev|prod   stop the postgres container"

check-env:
	@if [ ! -f "$(ENV_FILE)" ]; then \
		echo "Missing $(ENV_FILE) — copy .env.example and fill in values."; \
		exit 1; \
	fi

## ---------------------------------------------------------------------------
## Run targets
## ---------------------------------------------------------------------------

dev-run: check-env
	@$(MAKE) ENV=dev db-up
	@echo "Waiting for postgres to be healthy..."
	@until podman exec diary-postgres-dev pg_isready -U $(DB_USERNAME) -d $(DB_NAME) >/dev/null 2>&1; do sleep 1; done
	@$(MAKE) ENV=dev db-migrate
	@export $$(grep -v '^#' $(ENV_FILE) | xargs) && \
		mvn spring-boot:run -Dspring-boot.run.profiles=dev

prod-run: check-env
	@echo "Building and starting full prod stack..."
	podman compose -f compose.prod.yaml --env-file .env.prod up --build

test:
	@echo "Running tests (Testcontainers manages its own throwaway db)..."
	DOCKER_HOST=$(DOCKER_HOST) mvn test

## ---------------------------------------------------------------------------
## Database targets
## ---------------------------------------------------------------------------

db-up: check-env
	podman compose -f $(COMPOSE_FILE) --env-file $(ENV_FILE) up -d postgres

db-down: check-env
	podman compose -f $(COMPOSE_FILE) --env-file $(ENV_FILE) stop postgres

db-migrate: check-env
	@echo "Running Flyway migrations against $(ENV) database..."
	mvn flyway:migrate \
		-Dflyway.url=jdbc:postgresql://localhost:5432/$(DB_NAME) \
		-Dflyway.user=$(DB_USERNAME) \
		-Dflyway.password=$(DB_PASSWORD) \
		-Dflyway.locations=classpath:db/migration

db-clean: check-env
	@echo "WARNING: this will drop and recreate the $(ENV) database."
	@read -p "Continue? [y/N] " confirm && [ "$$confirm" = "y" ] || exit 1
	podman exec diary-postgres-$(ENV) psql -U $(DB_USERNAME) -d postgres \
		-c "DROP DATABASE IF EXISTS $(DB_NAME);"
	podman exec diary-postgres-$(ENV) psql -U $(DB_USERNAME) -d postgres \
		-c "CREATE DATABASE $(DB_NAME);"
	@$(MAKE) ENV=$(ENV) db-migrate
	@echo "$(ENV) database cleaned and migrated."

