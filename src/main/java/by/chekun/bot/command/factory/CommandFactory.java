package by.chekun.bot.command.factory;

import by.chekun.bot.command.Command;

@FunctionalInterface
public interface CommandFactory {
    Command findCommand(String action);
}
