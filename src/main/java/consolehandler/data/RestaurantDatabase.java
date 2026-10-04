package consolehandler.data;

import consolehandler.domain.Restaurant;
import consolehandler.domain.RestaurantFilter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class RestaurantDatabase {

    private final List<Restaurant> restaurants;

    public RestaurantDatabase() {
        restaurants = loadRestaurants();
    }

    private List<Restaurant> loadRestaurants() {

        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("restaurant.json")) {

            if (inputStream == null) {
                throw new RuntimeException("Файл restaurant.json не найден!");
            }

            return mapper.readValue(inputStream, new TypeReference<List<Restaurant>>() {
                    }
            );

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке базы ресторанов", e);
        }
    }

//    public List<Restaurant> getAllRestaurants() {
//        return new ArrayList<>(restaurants);
//    }
//
//    public List<Restaurant> findByCuisine(String cuisine) {
//
//        List<Restaurant> result = new ArrayList<>();
//
//        for (Restaurant restaurant : restaurants) {
//
//            if (restaurant.getCuisine().equalsIgnoreCase(cuisine)) {
//                result.add(restaurant);
//            }
//        }
//
//        return result;
//    }
//
//    public List<Restaurant> findByDish(String dish) {
//
//        List<Restaurant> result = new ArrayList<>();
//
//        for (Restaurant restaurant : restaurants) {
//
//            for (String restaurantDish : restaurant.getDishes()) {
//
//                if (restaurantDish.equalsIgnoreCase(dish)) {
//                    result.add(restaurant);
//                    break;
//                }
//            }
//        }
//
//        return result;
//    }
//
//    public List<Restaurant> findByMaxPrice(int maxPrice) {
//
//        List<Restaurant> result = new ArrayList<>();
//
//        for (Restaurant restaurant : restaurants) {
//
//            if (restaurant.getAverageCheck() <= maxPrice) {
//                result.add(restaurant);
//            }
//        }
//
//        return result;
//    }

    public List<Restaurant> filter(RestaurantFilter f) {

        List<Restaurant> result = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {

            if (matches(restaurant, f)) {
                result.add(restaurant);
            }
        }

        return result;
    }

    private boolean matches(Restaurant restaurant, RestaurantFilter f) {

        if (f.getCuisine() != null && !containsIgnoreCase(restaurant.getCuisine(), f.getCuisine())) {
            return false;
        }

        if (f.getDish() != null) {
            boolean dishFound = restaurant.getDishes().stream().anyMatch(d -> containsIgnoreCase(d, f.getDish()));
            if (!dishFound) {
                return false;
            }
        }
        if (f.getMinCheck() > 0 && restaurant.getAverageCheck() < f.getMinCheck()) {
            return false;
        }

        if (f.getMaxCheck() > 0 && restaurant.getAverageCheck() > f.getMaxCheck()) {
            return false;
        }

        return true;
    }

    private static boolean containsIgnoreCase(String value, String query) {
        return value != null && value.toLowerCase().contains(query.toLowerCase());
    }

    public List<String> getAllCuisines() {
        TreeSet<String> cuisines = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getCuisine() != null) {
                cuisines.add(restaurant.getCuisine());
            }
        }
        return new ArrayList<>(cuisines);
    }

    public List<String> getAllDishes() {
        TreeSet<String> dishes = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getDishes() != null) {
                dishes.addAll(restaurant.getDishes());
            }
        }
        return new ArrayList<>(dishes);
    }
}