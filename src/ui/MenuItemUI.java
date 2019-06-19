package ui;

import java.util.Scanner;
import mgr.MenuItemMgr;
import java.util.ArrayList;
import entities.MenuItem;
import entities.FoodItem;
import entities.SetPackage;
import enhancements.CusScanner;
/**
 * Boundary Class that facilitates actions between users and the MenuItem Objects
 * @author JOSHHH
 *
 */
public class MenuItemUI {
	private static Scanner sc = new Scanner(System.in);
	/**
	 * MainMenu UI, provides actions that can be performed by user
	 */
	public static void menuItemMainOptions() {
		int choice;
		do {
			System.out.println("\nMENU UI");
			
			System.out.println("[1] - View menu");
			System.out.println("[2] - Add new food item to menu");
			System.out.println("[3] - Add new Set Package to menu");
			System.out.println("[4] - Remove item from menu");
			System.out.println("[5] - Update item from menu");
			System.out.println("[0] - Go Back");
			
			choice = CusScanner.nextInt(0, 5);
			
			switch(choice) {
				case 1:
					viewMenuItemUI();
					break;
				case 2:
					addNewFoodItemUI();
					break;
				case 3:
					addNewSetPackageUI();
					break;
				case 4:
					removeMenuItemUI();
					break;
				case 5:
					updateMenuItemUI();
					break;
				case 0:
					return;
			}
		}while (true);
	}
	
	/**
	 * Provides the list of menuItems in Restaurant Menu, allowing users to get more detail on specific MenuItems
	 */
	private static void viewMenuItemUI() {
		int choice, idx;
		ArrayList<MenuItem> menuItemList = MenuItemMgr.getRestaurantMenu();
		
		if (menuItemList.size() < 1) {
			System.out.println("There are no items in the menu, You must have at least 1 item in the menu to view the menu");
			return;
		}
		
		do {
			idx = 0;
			System.out.println("\nThese are the items on the menu");
			for(MenuItem menuItem : menuItemList)
				System.out.println("[" + (idx++ + 1) + "] - " + menuItem.getName());
			System.out.println("[" + (0) + "] - " + "Back");
			
			choice = CusScanner.nextInt(0, idx);
			
			if (choice == 0)
				break;
			
			MenuItem selectedItem = menuItemList.get(choice - 1);
			if (selectedItem instanceof entities.FoodItem)
				MenuItemMgr.printFoodItemDetails((entities.FoodItem) selectedItem);
			else if (selectedItem instanceof entities.SetPackage)
				MenuItemMgr.printSetPackageDetails((entities.SetPackage) selectedItem);
			else
				System.out.println("DEBUGGERINO");
		} while(true);
		
	}
	/**
	 * UI provided to user to facilitate the addition of new FoodItems to the Restaurant Menu
	 */
	private static void addNewFoodItemUI() {
		System.out.println();
		
		System.out.println("What is the name of the new Food Item?");
		String name = sc.nextLine();
		
		System.out.println("What is the description of the new Food Item?");
		String description = sc.nextLine();
		
		System.out.println("What is the price of the new Food Item?");
		double price;
		price = enhancements.CusScanner.nextDouble();

		
		FoodItem.FoodType foodType = returnFoodItemCourseType();
		MenuItemMgr.addNewFoodItem(name, description, price, foodType);
		
	}
	/**
	 * UI that facilitates that addition of new SetPackage Items to Restaurant Menu
	 */
	private static void addNewSetPackageUI() {		
		System.out.println("What is the name of the new Set Package?");
		String name = sc.nextLine();
		
		System.out.println("What is the description of the new Set Package?");
		String description = sc.nextLine();
		
		System.out.println("What is the price of the new Set Package?");
		double price = enhancements.CusScanner.nextDouble();

		
		MenuItemMgr.addNewSetPackage(name, description, price);
	}
	
	/**
	 * UI provided to user to faciliate removal of MenuItems from Restaurant Menu
	 */
	private static void removeMenuItemUI() {
		int idx = 0, choice;
		ArrayList<MenuItem> menuItemList = MenuItemMgr.getRestaurantMenu();
		
		
		if (menuItemList.size() < 1) {
			System.out.println("There are no items in the menu, You must have at least 1 item in the menu to remove");
			return;
		}
		
		System.out.println("Which Menu Item do you wanna remove?");
		for(MenuItem menuItem : menuItemList) 
			System.out.println("[" + (idx++ + 1) + "] - " + menuItem.getName());
		System.out.println("[" + (0) + "] - Back");
		
		choice = CusScanner.nextInt(0, idx);
		if (choice == 0)
			return;
		
		MenuItemMgr.removeMenuItem(choice - 1);
		
	}
	
	/**
	 * UI provided to user to facilitate the updating of details of MenuItems in Restaurant Menu
	 */
	private static void updateMenuItemUI() {
		int idx = 0, choice;
		ArrayList<MenuItem> menuItemList = MenuItemMgr.getRestaurantMenu();
		
		if (menuItemList.size() < 1) {
			System.out.println("There are no items in the menu, You must have at least 1 item in the menu to remove");
			return;
		}
		
		System.out.println("Which Menu Item do you wanna update?");

		for(MenuItem menuItem : menuItemList)
			System.out.println("[" + (idx++ + 1) + "] - " + menuItem.getName());
		System.out.println("[" + (0) + "] - " + "Back");
		
		choice = CusScanner.nextInt(0, idx);
		
		if (choice == 0)
			return;
		
		if (menuItemList.get(choice - 1) instanceof FoodItem)
			updateFoodItemUI(choice - 1);
		else if (menuItemList.get(choice - 1) instanceof SetPackage) 
			updateSetPackageUI(choice - 1);
		else 
			System.out.println("DEBUGGERINO");
		
		return;
	}
	
	/**
	 * UI provided to user to faciliate the updating of a specific foodItem's details
	 * @param idx index of FoodItem in Restaurant menu to be updated
	 */
	private static void updateFoodItemUI(int idx) {
		System.out.println("What do you want to update about this item ?");
		
		System.out.println("[1] - Name");
		System.out.println("[2] - Description");
		System.out.println("[3] - Price");
		System.out.println("[4] - Food Type");
		System.out.println("[0] - Back");
		
		int choice = CusScanner.nextInt(0, 4);
		
		switch(choice) {
			case 1:
				System.out.println("What do you want to update the name to?");
				MenuItemMgr.updateMenuItemName(idx, sc.nextLine());
				break;
			case 2:
				System.out.println("What do you want to update the description to?");
				MenuItemMgr.updateMenuItemDescription(idx, sc.nextLine());
				break;
			case 3:
				System.out.println("What do you want to update the price to?");
				double updatePrice = 0.00;
				updatePrice = CusScanner.nextDouble();
				MenuItemMgr.updateMenuItemPrice(idx, updatePrice);
				break;
			case 4:
				MenuItemMgr.updateFoodItemType(idx, returnFoodItemCourseType());
				break;
			case 0:
				break;
		}
		
		return;
		
	}
	/**
	 * UI provided to user to faciliate the updating of a specific SetPackage's details
	 * @param idx index of SetPackage in Restaurant menu to be updated
	 */
	private static void updateSetPackageUI(int idx) {
		System.out.println("What do you want to update about this item ?");
		ArrayList<MenuItem> menuItemList = MenuItemMgr.getRestaurantMenu();
		
		System.out.println("[1] - Name");
		System.out.println("[2] - Description");
		System.out.println("[3] - Price");
		System.out.println("[4] - Add Food Items");
		System.out.println("[5] - Remove Food Items");
		System.out.println("[0] - Back");
		
		int choice = CusScanner.nextInt(0, 5);
		
		switch(choice) {
			case 1:
				System.out.println("What do you want to update the name to?");
				MenuItemMgr.updateMenuItemName(idx, sc.nextLine());
				break;
			case 2:
				System.out.println("What do you want to update the description to?");
				MenuItemMgr.updateMenuItemDescription(idx, sc.nextLine());
				break;
			case 3:
				System.out.println("What do you want to update the price to?");
				double updatePrice = 0.00;
				updatePrice = CusScanner.nextDouble();
				MenuItemMgr.updateMenuItemPrice(idx, updatePrice);
				break;
			case 4:
				MenuItemMgr.addFoodItemToSetPackage((SetPackage) menuItemList.get(idx));
				break;
			case 5:
				MenuItemMgr.removeFoodItemFromSetPackage((SetPackage) menuItemList.get(idx));
				break;
			case 0:
				break;
		}
		
		return;
		
	}
	
	/**
	 * Internal method used for selection of FoodType for addition / modification of FoodType details
	 * @return FoodType that is required by caller method
	 */
	private static FoodItem.FoodType returnFoodItemCourseType(){
		System.out.println("Which of the following Food Type do you want the Food Item to be?\n");
		
		int idx = 0;
		for(FoodItem.FoodType foodTypeLoop : FoodItem.FoodType.values())
			System.out.println("[" + (idx++ + 1) + "] - " + foodTypeLoop);
		
		int choice = CusScanner.nextInt(1, idx);
			
		return FoodItem.FoodType.values()[choice - 1];
	}
}
