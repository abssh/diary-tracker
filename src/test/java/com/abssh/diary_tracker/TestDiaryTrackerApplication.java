package com.abssh.diary_tracker;

import org.springframework.boot.SpringApplication;

public class TestDiaryTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.from(DiaryTrackerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
