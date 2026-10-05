package consolehandler;

import consolehandler.data.RestaurantDatabase;
import consolehandler.ui.FilterMenu;

public class ConsoleApp {
    private final RestaurantDatabase database;
    private FilterMenu filterMenu;

    public ConsoleApp(RestaurantDatabase database) {
        this.database = database;
    }

    public void run() {
        System.out.println("Бот 'Где поесть?' запущен!");
        System.out.println("Для помощи введите /help");
        System.out.println("Для закрытия программы введите /exit");
        printMainMenu();
        while (true) {
            System.out.print("> ");
            String input = ConsoleReader.scanner().nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }
            switch (input.toLowerCase()) {
                case "/exit":
                case "exit":
                    System.out.println("До свидания! Приятного аппетита!");
                    return;
                case "1":
                    openFilters();
                    break;
                case "2":
                case "3":
                    System.out.println("🚧 Раздел находится в разработке.");
                    break;
                case "/help":
                case "help":
                    printHelp();
                    break;
                default:
                    System.out.println("Неизвестная команда. Введите 1-3 или /exit для выхода.");
                    printMainMenu();
            }
        }
    }

    private void openFilters() {
        if (filterMenu == null) {
            filterMenu = new FilterMenu(database);
        }
        filterMenu.show();

        while (true) {
            System.out.print("(фильтры) > ");
            String input = ConsoleReader.scanner().nextLine().trim();

            if (input.equals("0")) {
                printMainMenu();
                return;
            }
            filterMenu.handleInput(input);
        }
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("Выберите 1-3");
        System.out.println("1. 🍕 Фильтры: тип еды, кухня, цена");
        System.out.println("2. ⭐ Рейтинги и отзывы");
        System.out.println("3. 📜 История посещений");
        System.out.println("/exit — выход из программы");
    }

    private void printHelp() {
        System.out.println("Доступные команды:");
        System.out.println("  1      — 🍕 Фильтры: тип еды, кухня, цена");
        System.out.println("  2      — ⭐ Рейтинги и отзывы (в разработке)");
        System.out.println("  3      — 📜 История посещений (в разработке)");
        System.out.println("  /help  — эта справка");
        System.out.println("  /exit  — выход из программы");
        System.out.println("Внутри фильтров: выбирайте пункты цифрами, 0 — назад.");
    }
}
