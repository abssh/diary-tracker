#!/usr/bin/env bash
# set_prompt.sh — set PS1 with a "for:(topic)" suffix, current session only.
#
# Usage:
#   source set_prompt.sh <topic>
#
# Must be SOURCED (not executed) so PS1 changes apply to your current shell.
# Auto-detects bash vs zsh via $SHELL.

set_prompt_main() {
    local topic="$1"

    if [ -z "$topic" ]; then
        echo "Usage: source set_prompt.sh <topic>" >&2
        return 1
    fi

    case "$SHELL" in
        */zsh)
            if [ -z "$ZSH_VERSION" ]; then
                echo "Detected \$SHELL=zsh but this isn't running in zsh. Source it from a zsh session." >&2
                return 1
            fi
            # Base prompt (git_prompt_info removed) + bold magenta/red topic suffix
            export PS1='%(?:%{%}%1{➜%} :%{%}%1{➜%} ) %{%}%c%{%} %B%F{magenta}for:(%f%F{red}'"$topic"'%f%F{magenta})%f%b '
            ;;
        */bash)
            if [ -z "$BASH_VERSION" ]; then
                echo "Detected \$SHELL=bash but this isn't running in bash. Source it from a bash session." >&2
                return 1
            fi
            # Base prompt + bold magenta/red topic suffix using ANSI escapes
            export PS1='\u@\h:\w\$ \[\033[1;35m\]for:(\[\033[1;31m\]'"$topic"'\[\033[1;35m\])\[\033[0m\] '
            ;;
        *)
            echo "Unsupported shell: $SHELL (only bash and zsh are supported)" >&2
            return 1
            ;;
    esac
}

# Detect if sourced vs executed
( return 0 2>/dev/null )
if [ $? -eq 0 ]; then
    # Sourced — apply directly to current shell
    set_prompt_main "$1"
else
    # Executed directly — can't affect parent shell, so just print instructions
    echo "This script must be sourced to change your current shell's prompt." >&2
    echo "Run instead:" >&2
    echo "  source ${BASH_SOURCE:-$0} \"$1\"" >&2
    exit 1
fi