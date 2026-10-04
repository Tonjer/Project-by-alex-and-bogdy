package consolehandler.domain;

import java.util.List;

public class Restaurant {

    private String id;
    private String name;
    private String address;
    private String cuisine;
    private List<String> dishes;
    private int averageCheck;

    public Restaurant() {
    }

    public Restaurant(
            String id,
            String name,
            String address,
            String cuisine,
            List<String> dishes,
            int averageCheck) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.cuisine = cuisine;
        this.dishes = dishes;
        this.averageCheck = averageCheck;
    }

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