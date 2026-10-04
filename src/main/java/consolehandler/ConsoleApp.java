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
        printMainMenu();
        while (true) {
            System.out.println(">");
            String input = ConsoleReader.scanner().nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }
            switch (input.toLowerCase()) {
                case "/exit":
                case "exit":
                    System.out.println("До свидания! Приятного аппетита!");
                    return;
                case "/help":
                case "help":
                    printHelp();
                    break;
                case "/filters":
                case "фильтры":
                    openFilters();
                    break;
                default:
                    System.out.println("Неизвестная команда. Введите /help");
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
        System.out.println("Главное меню команд:");
        System.out.println("  /filters — подобрать заведения по типу еды, кухне и среднему чеку");
        System.out.println("  /help    — список команд");
        System.out.println("  /exit    — выход");
    }

    private void printHelp() {
        System.out.println("Доступные команды:");
        System.out.println("  /filters — фильтр по типу еды, кухне и среднему чеку");
        System.out.println("  /help    — эта справка");
        System.out.println("  /exit    — выход из программы");
        System.out.println("Внутри фильтров: выбирайте пункты цифрами, 0 — назад.");
    }
}
