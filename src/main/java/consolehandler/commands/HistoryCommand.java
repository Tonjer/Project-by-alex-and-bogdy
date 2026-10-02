package consolehandler.commands;

public class HistoryCommand implements Command {

    @Override
    public String execute() {
        return "📜 История посещений (выберите 1-4):\n" +
                "1. Последние 10 посещений\n" +
                "2. Избранные места\n" +
                "3. Статистика\n" +
                "4. Очистить историю\n" +
                "0. ↩️ Назад";
    }

    @Override
    public boolean isInteractive() {
        return true;
    }

    @Override
    public String handleChoice(int choice) {
        switch (choice) {
            case 0: return "__EXIT__";
            case 1: return "🕒 Последние посещения:\n  1. ...\n  2. ...";
            case 2: return "❤️ Избранные места: ...";
            case 3: return "📈 Статистика: посещений — 0, любимая кухня — ...";
            case 4: return "🗑️ История очищена.";
            default: return "❌ Введите 1-4 или 0.";
        }
    }
}