package consolehandler.commands;

public class AboutCommand implements Command {
    private final String botName = "Где поесть?";
    private final String description = "Ваш гид по вкусным местам.";
    private final String features =
            "🔍 Поиск заведений\n" +
            "🍕 Фильтры: тип еды, кухня, цена, расстояние\n" +
            "⭐ Рейтинги и отзывы\n" +
            "📜 История посещений";
    public String execute() {
        StringBuilder response = new StringBuilder();
        response.append("ℹ️ ").append(botName).append("\n");
        response.append("-------------------------\n");
        response.append(description).append("\n\n");
        response.append("Что я умею:\n");
        response.append(features);

        return response.toString();
    }
}
