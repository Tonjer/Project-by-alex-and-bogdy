package consolehandler.commands;

public interface Command {
    String execute();
    default boolean isInteractive() {
        return false;
    }

    default String handleChoice(int choice) {
        return "Неизвестный пункт меню.";
    }
}
