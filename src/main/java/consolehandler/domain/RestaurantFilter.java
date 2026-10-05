package consolehandler.domain;

public class RestaurantFilter {
    private String dish;
    private String cuisine;
    private int minCheck;
    private int maxCheck;

    private static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = isBlank(dish) ? null : dish.trim(); 
    }

    public String getCuisine() {
        return cuisine;
    }
    public void setCuisine(String cuisine) {
        this.cuisine = isBlank(cuisine) ? null : cuisine.trim();
    }

    public int getMinCheck() {
        return minCheck;
    }

    public void setMinCheck(int minCheck) {
        this.minCheck = Math.max(0, minCheck);
    }

    public int getMaxCheck() {
        return maxCheck;
    }
    public void setMaxCheck(int maxCheck) {
        this.maxCheck = Math.max(0, maxCheck);
    }

    public boolean hasAnyCriterion() {
        return dish != null || cuisine != null || minCheck > 0 || maxCheck > 0;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (dish != null) sb.append("🍽 Блюдо: ").append(dish).append('\n');
        if (cuisine != null) sb.append("🌍 Кухня: ").append(cuisine).append('\n');
        if (minCheck > 0 && maxCheck > 0) sb.append("💰 Чек: от ").append(minCheck).append(" до ").append(maxCheck).append(" ₽\n");
        else if (maxCheck > 0) sb.append("💰 Чек: до ").append(maxCheck).append(" ₽\n");
        else if (minCheck > 0) sb.append("💰 Чек: от ").append(minCheck).append(" ₽\n");
        return sb.isEmpty() ? "Фильтры не заданы" : sb.toString().trim();
    }

}
