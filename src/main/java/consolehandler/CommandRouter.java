package consolehandler;

import consolehandler.commands.Command;
import consolehandler.commands.AboutCommand;

import java.util.Locale;

public class CommandRouter {
    private final Command aboutCommand = new AboutCommand();
    public String route(String input) {
        String normalizedInput = input.trim().toLowerCase();
        switch (normalizedInput) {
            case "/about":
                return aboutCommand.execute();
            default:
                return "Неизвестная команда. Попробуйте /about";
        }
    }
}
