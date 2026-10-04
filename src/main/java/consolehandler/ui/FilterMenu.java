package consolehandler.ui;

import consolehandler.ConsoleReader;
import consolehandler.data.RestaurantDatabase;
import consolehandler.domain.Restaurant;
import consolehandler.domain.RestaurantFilter;

import java.util.ArrayList;
import java.util.List;

public class FilterMenu {
    private final RestaurantDatabase database;
    private String dish = null;
    private String cuisine = null;
    private int minCheck = 0;
    private int maxCheck = 0;

    public FilterMenu(RestaurantDatabase database) {
        this.database = database;
    }

    public boolean show() {
        System.out.println();
        System.out.println("🔍 Фильтры:");
        System.out.println("1. Тип еды (блюда)");
        System.out.println("2. Кухня");
        System.out.println("3. Средний чек");
        System.out.println("4. Сбросить все фильтры");
        System.out.println("0. Назад в главное меню");
        printCurrentFilters();
        return true;
    }

    public void handleInput(String input) {
        switch (input) {
            case "1":
                chooseDish();
                break;
            case "2":
                chooseCuisine();
                break;
            case "3":
                choosePrice();
                break;
            case "4":
                reset();
                show();
                break;
            default:
                System.out.println("❌ Введите 1, 2, 3, 4 или 0.");
        }
    }

    private void chooseDish() {
        List<String> dishes = database.getAllDishes();
        int choice = chooseFromList(dishes, "🍽 Выберите тип еды");
        if (choice > 0) {
            dish = dishes.get(choice - 1);
            showResults();
        }
    }

    private void chooseCuisine() {
        List<String> cuisines = database.getAllCuisines();
        int choice = chooseFromList(cuisines, "🌍 Выберите кухню");
        if (choice > 0) {
            cuisine = cuisines.get(choice - 1);
            showResults();
        }
    }

    private void choosePrice() {
        System.out.println("💰 Средний чек:");
        System.out.println("1. до 500 ₽");
        System.out.println("2. 500–1000 ₽");
        System.out.println("3. 1000–1500 ₽");
        System.out.println("4. свыше 1500 ₽");
        System.out.println("5. свой диапазон (введите числа, например 600-1200)");
        System.out.println("0. Назад");

        String input = readLine().trim();
        if (input.equals("5")) {
            chooseCustomPrice();
            return;
        }
        switch (input) {
            case "1":
                minCheck = 0;
                maxCheck = 500;
                showResults();
                break;
            case "2":
                minCheck = 500;
                maxCheck = 1000;
                showResults();
                break;
            case "3":
                minCheck = 1000;
                maxCheck = 1500;
                showResults();
                break;
            case "4":
                minCheck = 1500;
                maxCheck = 0;
                showResults();
                break;
            case "0":
                show();
                break;
            default:
                System.out.println("❌ Введите 1-5 или 0.");
        }
    }

    private void chooseCustomPrice() {
        System.out.print("Введите диапазон (например 600-1200, 'до 900' или 'от 1000'): ");
        String input = readLine().trim().toLowerCase().replace("₽", "");

        try {
            if (input.startsWith("до")) {
                minCheck = 0;
                maxCheck = Integer.parseInt(input.substring(2).trim());
            } else if (input.startsWith("от")) {
                minCheck = Integer.parseInt(input.substring(2).trim());
                maxCheck = 0;
            } else {
                String[] parts = input.split("-");
                if (parts.length == 2) {
                    minCheck = Integer.parseInt(parts[0].trim());
                    maxCheck = Integer.parseInt(parts[1].trim());
                } else {
                    System.out.println("❌ Не понял формат. Примеры: 600-1200, до 900, от 1000.");
                    return;
                }
            }
            if (minCheck > 0 && maxCheck > 0 && minCheck > maxCheck) {
                System.out.println("❌ Минимальная цена больше максимальной.");
                return;
            }
            showResults();
        } catch (NumberFormatException e) {
            System.out.println("❌ Нужно ввести числа. Примеры: 600-1200, до 900, от 1000.");
        }
    }

    private int chooseFromList(List<String> values, String title) {
        System.out.println(title + ":");
        for (int i = 0; i < values.size(); i++) {
            System.out.println((i + 1) + ". " + values.get(i));
        }
        System.out.println("0. Назад");

        String input = readLine();
        try {
            int choice = Integer.parseInt(input.trim());
            if (choice == 0) {
                show();
                return 0;
            }
            if (choice < 1 || choice > values.size()) {
                System.out.println("❌ Число вне диапазона. Попробуйте ещё раз.");
                return 0;
            }
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("❌ Нужно ввести число.");
            return 0;
        }
    }

    private void showResults() {
        RestaurantFilter filter = new RestaurantFilter();
        filter.setDish(dish);
        filter.setCuisine(cuisine);
        filter.setMinCheck(minCheck);
        filter.setMaxCheck(maxCheck);

        List<Restaurant> found = database.filter(filter);

        System.out.println();
        System.out.println("══════ Результаты ══════");
        printCurrentFilters();
        if (found.isEmpty()) {
            System.out.println("😕 Ничего не найдено. Ослабьте условия (пункт 4 — сброс).");
        } else {
            System.out.println("Найдено заведений: " + found.size());
            for (Restaurant r : found) {
                System.out.println(r);
                System.out.println("---------------------------");
            }
        }
        show();
    }

    private void printCurrentFilters() {
        List<String> parts = new ArrayList<>();
        if (dish != null) parts.add("🍽 блюдо: " + dish);
        if (cuisine != null) parts.add("🌍 кухня: " + cuisine);
        if (minCheck > 0 && maxCheck > 0) parts.add("💰 чек: " + minCheck + "-" + maxCheck + " ₽");
        else if (maxCheck > 0) parts.add("💰 чек: до " + maxCheck + " ₽");
        else if (minCheck > 0) parts.add("💰 чек: от " + minCheck + " ₽");

        if (parts.isEmpty()) {
            System.out.println("Фильтры пока не заданы.");
        } else {
            System.out.println("Текущие фильтры: " + String.join(", ", parts));
        }
    }

    private void reset() {
        dish = null;
        cuisine = null;
        minCheck = 0;
        maxCheck = 0;
        System.out.println("🧹 Все фильтры сброшены.");
    }

    private String readLine() {
        return ConsoleReader.scanner().nextLine();
    }
}
