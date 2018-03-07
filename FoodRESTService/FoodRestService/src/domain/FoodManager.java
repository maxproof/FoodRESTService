package domain;

import java.util.Optional;

/**
 * Manages CRUD operations for foods.
 * 
 * @author taylor
 *
 */
public class FoodManager {
	private static FoodList list = new FoodList();

	static { // initialize with some data
		list.add(new Food("onion", 20, "2 slices"));
		list.add(new Food("Beans", 60, "1 cup"));
		list.add(new Food("Orange", 80, "1 whole"));
		list.add(new Food("Cereal", 120, "1 cup"));
	}

	/**
	 * Get all known foods.
	 * 
	 * @return food list
	 */
	public static FoodList getFoods() {
		return list;
	}

	/**
	 * Find a food in the list of foods.
	 * 
	 * @param name
	 *            food to find
	 * @return food or null
	 */
	public static Food find(String name) {
		final Optional<Food> foundFood = list.stream()
				.filter(food -> food.getName()
						.equalsIgnoreCase(name))
				.findFirst();
		return foundFood.orElse(null);
	}

	/**
	 * Add a food to the list of foods.
	 * 
	 * @param food
	 *            food to add
	 */
	public static void add(Food food) {
		list.add(food);
	}

	/**
	 * Delete a food from the list of foods.
	 * 
	 * @param name
	 *            food to delete
	 */
	public static void delete(String name) {
		Food food = find(name);
		list.remove(food);
	}

}
