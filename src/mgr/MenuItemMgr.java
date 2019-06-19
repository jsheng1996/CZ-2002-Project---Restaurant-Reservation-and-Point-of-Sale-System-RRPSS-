package mgr;

import java.util.ArrayList;
import java.util.Scanner;
import entities.Restaurant;
import entities.FoodItem;
import entities.SetPackage;
import entities.MenuItem;

/**
 * Menu item Manager class that manages creation, removal and modifications to Menu Items
 * @author user JOSHUA
 * @version 1.0
 * @since 2019-04-10
 */

public class MenuItemMgr {
	private static ArrayList<MenuItem> menuItemList = Restaurant.menuItems;
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * Retrieves Restaurant Menu that is required for mgr class
	 * @return The Restaurant's Menu
	 */
	public static ArrayList<MenuItem> getRestaurantMenu(){
		return menuItemList; 
	}
	
	/**
	 * Prints the details of an input food item
	 * @param food Food whose details is to be printed
	 */
	public static void printFoodItemDetails(FoodItem food) {
		System.out.println();
		System.out.println("Food Name: " + food.getName());
		System.out.println("Food Description: " + food.getDescription());
		System.out.println("Food Price: " + food.getPrice());
		System.out.println("Food course type: " + food.getType());
	}
	
	/**
	 * Prints the details of an input SetPackage
	 * @param setPackage setPackage whose details is to be printed
	 */
	public static void printSetPackageDetails(SetPackage setPackage) {
		System.out.println();
		System.out.println("Set Package Name: " + setPackage.getName());
		System.out.println("Set Package Description: " + setPackage.getDescription());
		System.out.println("Set Package Price: " + setPackage.getPrice());
		
		int idx = 0;
		System.out.println("The " + setPackage.getName() + " contains these food items");
		for(FoodItem foodItem : setPackage.getFoodList())
			System.out.println("[" + (idx++ + 1) + "] - " + foodItem.getName());
		
	}
	/**
	 * Adds a new Food Item to the Restaurant Menu
	 * @param name Name of food
	 * @param description Description of food
	 * @param price Price of food
	 * @param foodType Food type of food
	 */
	public static void addNewFoodItem(String name, String description, double price, FoodItem.FoodType foodType) {
		FoodItem newFood;
		newFood = new FoodItem(name, description, price, foodType);
		menuItemList.add((MenuItem) newFood);
		System.out.println();
		System.out.println("Food " + newFood.getName() + " has been added to the Food Menu");
	}
	
	/**
	 * Adds a new SetPackage to the Restaurant Menu
	 * @param name Name of SetPackage
	 * @param description Description of SetPackage
	 * @param price Price of SetPackage
	 */
	public static void addNewSetPackage(String name, String description, double price) {
		SetPackage newSetPackage;
		newSetPackage = new SetPackage(name, description, price);
		
		newSetPackage = addFoodItemToSetPackage(newSetPackage);
		menuItemList.add((MenuItem) newSetPackage);
	}
	
	/**
	 * Facilitates the addition of food items to input setPackage
	 * @param setPackage setPackage to be modified
	 * @return setPackage Modified setPackage
	 */
	public static SetPackage addFoodItemToSetPackage(SetPackage setPackage) {
		String choice = "", compare = "Y";
		do { 
			setPackage.addFood(menuItemList);
			System.out.println("Input \"Y\" to add more food to the Set Package, or other characters to stop adding");
			choice = sc.nextLine();
		}while (choice.toUpperCase().equals(compare));
		
		return setPackage;
	}
	
	/**
	 * Removes Menu Items from input SetPackage
	 * @param setPackage SetPackage to be modified
	 */
	public static void removeFoodItemFromSetPackage(SetPackage setPackage) {
		String choice = "", compare = "Y";
		
		do{
			setPackage.removeFood();
			System.out.println("Input \"Y\" to remove food to the Set Package, or other characters to stop removing");
			choice = sc.nextLine();
		}while (choice.toUpperCase().equals(compare));
		
		return;
	}
	
	/**
	 * Removes item from Restaurant menu
	 * @param idx index of menuItem in Restaurant menu to be removed
	 */
	public static void removeMenuItem(int idx) {
		try{
			String removedMenuItemName = menuItemList.get(idx).getName();
			menuItemList.remove(idx);
			System.out.println("Successfully removed " + removedMenuItemName + " from the menu");
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Removal of Food Item failed, invalid index");
		}
		
	}
	/**
	 * Updates menu item name
	 * @param idx index of menuItem in Restaurant menu to be updated
	 * @param name String to modify menuItem name into
	 */
	public static void updateMenuItemName(int idx, String name) {
		String old_name = menuItemList.get(idx).getName();
		menuItemList.get(idx).setName(name);
		System.out.println("Successfully Updated " + old_name + " name to " + name);
	}
	
	/**
	 * Updates menu item description
	 * @param idx idx index of menuItem in Restaurant menu to be updated
	 * @param description String to modify menuItem description into
	 */
	public static void updateMenuItemDescription(int idx, String description) {
		menuItemList.get(idx).setDescription(description);
		System.out.println("Successfully Updated " + menuItemList.get(idx).getName() + " description to " + description);
	}
	/**
	 * Updates Menu Item Price
	 * @param idx idx index of menuItem in Restaurant menu to be updated
	 * @param price Value to modify menuItem price into
	 */
	public static void updateMenuItemPrice(int idx, double price) {
		menuItemList.get(idx).setPrice(price);
		System.out.println("Successfully Updated " + menuItemList.get(idx).getName() + " price to " + price);
	}
	
	/**
	 * Updates Food Item Type
	 * @param idx idx index of menuItem in Restaurant menu to be updated
	 * @param type Type to modify menuItem Type into
	 */
	public static void updateFoodItemType(int idx, FoodItem.FoodType type) {
		FoodItem foodToBeUpdated = (FoodItem) menuItemList.get(idx);
		foodToBeUpdated.setType(type);
		System.out.println("Successfully Updated " + foodToBeUpdated.getName() + " Course Type to " + type);
	}

}
