package entities;
import java.util.ArrayList;

import enhancements.CusScanner;

/**
 * SetPackage object class that simulates a meal item in Restaurants. Contains FoodItem objects.
 * @author JOSHHH
 *
 */
public class SetPackage  extends MenuItem{
	private static final long serialVersionUID = -1762180885408039220L;
	private ArrayList<FoodItem> foodList;
	
	/**
	 * Constructor for SetPackage 
	 * @param name Name of SetPackage
	 * @param description Description of SetPackage
	 * @param price Price of SetPackage
	 */
	public SetPackage(String name, String description, double price){
		super(name, description, price);
		this.foodList = new ArrayList<FoodItem>();
	}
	
	public ArrayList<FoodItem> getFoodList(){
		return this.foodList;
	}
	/**
	 * Adds food item to the instance list of items
	 * @param foodToAdd Food Object to be added
	 */
	public void addFood(FoodItem foodToAdd) {
		this.foodList.add(foodToAdd);
	}
	
	/**
	 * Facilitates the addition of Food Item
	 * @param menuList Restaurant's MenuList obtained to retrieve list of possible FoodItems to add to instance's list of Food Items
	 */
	public void addFood(ArrayList<MenuItem> menuList) {
		int idx = 0;
		int i = 0;
		ArrayList<Integer> mapping = new ArrayList<Integer>();
		
		if (menuList.size() < 1) {
			System.out.println("There are no items in the menu, You must have at least 1 item in the menu to add");
			return;
		}
		
		System.out.println();
		System.out.println("Which food would you like to add to the " + this.getName() + " Set Package?");
		
		for(; i < menuList.size(); i++) {
			if (menuList.get(i) instanceof FoodItem) {
				System.out.println("[" + (idx++ + 1) + "] - " + menuList.get(i).getName());
				mapping.add(i);
			}
		}
		
		System.out.println();
		int choice = CusScanner.nextInt(1, idx);
		
		FoodItem foodToAdd = (FoodItem) menuList.get(mapping.get(choice - 1));
		this.foodList.add(foodToAdd);
		System.out.println(foodToAdd.getName() + " was successfully add to " + this.getName() + " Set Package");
		
	}
	/**
	 * Removes FoodItem from instance's List of FoodItems
	 */
	public void removeFood() {
		int idx = 0;
		
		if (this.foodList.size() < 1) {
			System.out.println("There are no food items in this set package, You must have at least 1 item in the package to remove");
			return;
		}
		
		System.out.println();
		System.out.println("Which food would you like to remove from the " + this.getName() + " Set Package?");
		for(FoodItem foodItem : this.foodList)
			System.out.println("[" + (idx++ + 1) + "] - " + foodItem.getName());
		
		System.out.println();
		int choice = CusScanner.nextInt(1, idx);
		
		String removedItemName = this.foodList.get(choice - 1).getName();
		this.foodList.remove(choice - 1);
		System.out.println(removedItemName + " was successfully removed from the " + this.getName() + " Set Package");
	}
}