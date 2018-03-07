package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * List of known foods that can transform into xml or json.
 * 
 * @author taylor
 *
 */
@XmlRootElement
@XmlSeeAlso(Food.class)
public class FoodList extends ArrayList<Food> {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public FoodList() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param c
	 *            collection of initial food.
	 */
	public FoodList(Collection<? extends Food> c) {
		super(c);
	}

	/**
	 * Get list of foods.
	 * 
	 * @return food list
	 */
	@XmlElement(name = "foods")
	public List<Food> getFoods() {
		return this;
	}

	/**
	 * Add a list of foods.
	 * 
	 * @param foods
	 *            list to add
	 */
	public void addFoods(List<Food> foods) {
		this.addAll(foods);
	}

}
