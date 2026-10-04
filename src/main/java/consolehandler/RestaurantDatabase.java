package consolehandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDatabase {

    private final List<Restaurant> restaurants;

    public RestaurantDatabase() {
        restaurants = loadRestaurants();
    }

    private List<Restaurant> loadRestaurants() {

        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream =
                     getClass()
                             .getClassLoader()
                             .getResourceAsStream("restaurant.json")) {

            if (inputStream == null) {
                throw new RuntimeException(
                        "Файл restaurant.json не найден!"
                );
            }

            return mapper.readValue(
                    inputStream,
                    new TypeReference<List<Restaurant>>() {}
            );

        } catch (Exception e) {
            throw new RuntimeException(
                    "Ошибка при загрузке базы ресторанов",
                    e
            );
        }
    }

    public List<Restaurant> getAllRestaurants() {
        return new ArrayList<>(restaurants);
    }

    public List<Restaurant> findByCuisine(String cuisine) {

        List<Restaurant> result = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {

            if (restaurant.getCuisine()
                    .equalsIgnoreCase(cuisine)) {

                result.add(restaurant);
            }
        }

        return result;
    }

    public List<Restaurant> findByDish(String dish) {

        List<Restaurant> result = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {

            for (String restaurantDish : restaurant.getDishes()) {

                if (restaurantDish.equalsIgnoreCase(dish)) {
                    result.add(restaurant);
                    break;
                }
            }
        }

        return result;
    }

    public List<Restaurant> findByMaxPrice(int maxPrice) {

        List<Restaurant> result = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {

            if (restaurant.getAverageCheck() <= maxPrice) {
                result.add(restaurant);
            }
        }

        return result;
    }
}