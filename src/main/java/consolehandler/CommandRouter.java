//package consolehandler;
//
//import consolehandler.commands.Command;
//import consolehandler.commands.AboutCommand;
//
//import java.util.Locale;
//
//public class CommandRouter {
//    private final Command aboutCommand = new AboutCommand();
//    public String route(String input) {
//        String normalizedInput = input.trim().toLowerCase();
//        switch (normalizedInput) {
//            case "/about":
//                return aboutCommand.execute();
//            default:
//                return "Неизвестная команда. Попробуйте /about";
//        }
//    }
//}

package consolehandler;

import consolehandler.commands.Command;
import consolehandler.commands.AboutCommand;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandRouter {

    private final Command aboutCommand = new AboutCommand();
    private final Deque<Command> stack = new ArrayDeque<>();

    public String route(String input) {
        String normalized = input.trim().toLowerCase();

        // === Внутри подменю ===
        if (!stack.isEmpty()) {
            if (normalized.equals("/back") || normalized.equals("назад")) {
                stack.pop();
                return stack.isEmpty()
                        ? "↩️ Главное меню. Введите /about."
                        : stack.peek().execute();
            }

            try {
                int choice = Integer.parseInt(normalized);
                Command current = stack.peek();
                String result = current.handleChoice(choice);

                if (result.equals("__EXIT__")) {
                    stack.pop();
                    return stack.isEmpty()
                            ? "↩️ Главное меню. Введите /about."
                            : stack.peek().execute();
                }

                // Если выбранный пункт открывает вложенное подменю (AboutCommand)
                if (current instanceof AboutCommand && choice >= 1 && choice <= 3) {
                    Command sub = ((AboutCommand) current).getSubCommand(choice);
                    if (sub != null && sub.isInteractive()) {
                        stack.push(sub);
                        return sub.execute();
                    }
                }

                return result;
            } catch (NumberFormatException e) {
                return "❌ Введите число (1-3) или '/back' для выхода.";
            }
        }

        // === Главное меню ===
        switch (normalized) {
            case "/about":
                stack.push(aboutCommand);
                return aboutCommand.execute();
            case "/help":
                return "Доступные команды: /about, /help";
            default:
                return "Неизвестная команда. Попробуйте /about";
        }
    }
}