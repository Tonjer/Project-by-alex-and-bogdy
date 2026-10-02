////package consolehandler.commands;
//
//public class AboutCommand implements Command {
//    private final String botName = "Где поесть?";
//    private final String description = "Ваш гид по вкусным местам.";
//    private final String features =
//            "🔍 Поиск заведений\n" +
//            "🍕 Фильтры: тип еды, кухня, цена, расстояние\n" +
//            "⭐ Рейтинги и отзывы\n" +
//            "📜 История посещений";
//    public String execute() {
//        StringBuilder response = new StringBuilder();
//        response.append("ℹ️ ").append(botName).append("\n");
//        response.append("-------------------------\n");
//        response.append(description).append("\n\n");
//        response.append("Что я умею:\n");
//        response.append(features);
//
//        return response.toString();
//    }
//}
package consolehandler.commands;

public class AboutCommand implements Command {

    private final Command[] subCommands = {
            new FiltersCommand(),
            new RatingsCommand(),
            new HistoryCommand()
    };

    @Override
    public String execute() {
        return "ℹ️ Где поесть?\n" +
                "-------------------------\n" +
                "Ваш гид по вкусным местам.\n\n" +
                "Что я умею (выберите 1-3):\n" +
                "1. 🍕 Фильтры\n" +
                "2. ⭐ Рейтинги и отзывы\n" +
                "3. 📜 История посещений\n" +
                "0. ↩️ Выход";
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public String handleChoice(int choice) {
        if (choice == 0) return "__EXIT__";
        if (choice < 1 || choice > 3) return "❌ Введите 1-3 или 0.";
        return subCommands[choice - 1].execute();
    }

    public Command getSubCommand(int choice) {
        if (choice < 1 || choice > 3) return null;
        return subCommands[choice - 1];
    }
}