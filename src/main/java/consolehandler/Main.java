package consolehandler;

import consolehandler.data.RestaurantDatabase;

public class Main {

    public static void main(String[] args) {
        RestaurantDatabase database = new RestaurantDatabase();
        new ConsoleApp(database).run();
    }
}