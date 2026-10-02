package consolehandler.commands;

public class FiltersCommand implements Command {

    @Override
    public String execute() {
        return "🍕 Фильтры (выберите 1-4):\n" +
                "1. Тип еды (пицца, суши, бургеры...)\n" +
                "2. Кухня (итальянская, японская...)\n" +
                "3. Цена (₽, ₽₽, ₽₽₽)\n" +
                "4. Расстояние (до 500м, 1км, 5км)\n" +
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
            case 1: return "🍔 Тип еды: пицца / суши / бургеры / шаурма / паста";
            case 2: return "🌍 Кухня: итальянская / японская / русская / грузинская";
            case 3: return "💰 Цена: ₽ (до 500) / ₽₽ (500-1500) / ₽₽₽ (1500+)";
            case 4: return "📍 Расстояние: до 500м / до 1км / до 5км";
            default: return "❌ Введите 1-4 или 0.";
        }
    }
}