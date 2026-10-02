package consolehandler.commands;

public class RatingsCommand implements Command {

    @Override
    public String execute() {
        return "⭐ Рейтинги и отзывы (выберите 1-4):\n" +
                "1. Топ-10 заведений\n" +
                "2. Последние отзывы\n" +
                "3. Оставить отзыв\n" +
                "4. Мои оценки\n" +
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
            case 1: return "🏆 Топ-10 заведений по рейтингу:\n  1. ...\n  2. ...";
            case 2: return "💬 Последние отзывы:\n  • ...\n  • ...";
            case 3: return "✍️ Напишите ваш отзыв:";
            case 4: return "📊 Ваши оценки: ...";
            default: return "❌ Введите 1-4 или 0.";
        }
    }
}