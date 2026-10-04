package consolehandler.domain;

import java.util.List;

public class Restaurant {

    private String id;
    private String name;
    private String address;
    private String cuisine;
    private List<String> dishes;
    private int averageCheck;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCuisine() {
        return cuisine;
    }

    public List<String> getDishes() {
        return dishes;
    }

    public int getAverageCheck() {
        return averageCheck;
    }

    @Override
    public String toString() {
        return "🍴 " + name +
                "\n📍 Адрес: " + address +
                "\n🌍 Кухня: " + cuisine +
                "\n🍽 Блюда: " + String.join(", ", dishes) +
                "\n💰 Средний чек: " + averageCheck + " ₽";
    }
}