package mgr;

import java.util.ArrayList;
import java.util.Scanner;
import entities.Staff;
import entities.MenuItem;
import entities.Table;
import entities.Order;
import entities.Restaurant;

/**
 * Order Manager class that manages creation, removal and modifications to Orders
 * @author user JOSHUA
 * @version 1.0
 * @since 2019-04-10
 */
public class OrderMgr {
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Order> orderList = Restaurant.orders;
	private static ArrayList<MenuItem> menuItemList = Restaurant.menuItems;
	private static ArrayList<Table> tableList = Restaurant.tables;
	private static ArrayList<Order> invoiceList = Restaurant.invoices;
	private static ArrayList<Staff> staffList = Restaurant.staff;
	
	/**
	 * Retrieves List of Restaurant's Orders
	 * @return Array of Restaurant Orders
	 */
	public static ArrayList<Order> getRestaurantOrders(){
		return orderList; 
	}
	
	/**
	 * Retrieves List of Restaurant's Tables
	 * @return Array of Restaurant Tables
	 */
	public static ArrayList<Table> getRestaurantTables(){
		return tableList; 
	}
	
	/**
	 * Retrieves List of Restaurant's Staff
	 * @return Array of Restaurant Staff
	 */
	public static ArrayList<Staff> getRestaurantStaff(){
		return staffList; 
	}
	
	/**
	 * Prints the input Order's details
	 * @param order Prints detail of input Order object
	 */
	public static void printOrderDetails(Order order) {
		System.out.println();
		System.out.println("*********************** RECEIPT ***************************");
		System.out.println("Order ID \t: " + order.getName());
		System.out.println("Staff \t\t: " + order.getStaffAssigned());
		System.out.println("Table ID\t: " + order.getTable().tableId);
		System.out.println("DateTime \t: " + order.getTimestamp().toString());
		
		System.out.println("\n\t================= ORDERS MADE =================\t\n");
		double price = 0;
		int idx = 0;
		for(MenuItem menuItem : order.getListOfItems()) {
			System.out.println((idx++ + 1) + ". " + menuItem.getName() + " - " + menuItem.getPrice());
			price += menuItem.getPrice();
		}
		System.out.printf("\nSUBTOTAL\t\t:%.2f \n", price);
		System.out.printf("GST ( 7%% )\t\t:%.2f \n", price * 0.07);
		System.out.printf("SERVICE TAX ( 10 %% )\t:%.2f \n",  price * 0.10);
		System.out.printf("GRAND TOTAL\t\t:%.2f \n", price * 1.17);
		System.out.println("*********************** RECEIPT END ***********************");
		System.out.println();

	}
	
	/**
	 * Adds a new order to the restaurant's Order List
	 * @param staff Staff object that keys in the order
	 * @param table Table which is assigned to this order
	 */
	public static void addNewOrder(Staff staff, Table table) {
		Order newOrder = new Order(staff, table);
		
		newOrder = addFoodItemToOrder(newOrder);
		table.isOccupied = true;
		orderList.add(newOrder);
		
	}
	/**
	 * Adds MenuItems to the order
	 * @param order Order to be modified
	 * @return Modified Order
	 */
	public static Order addFoodItemToOrder(Order order) {
		String choice = "", compare = "Y";
		do { 
			order.addFood(menuItemList);
			System.out.println("Input \"Y\" to add more food to the order, or other characters to stop adding");
			choice = sc.nextLine();
		}while (choice.toUpperCase().equals(compare));
		
		return order;
	}
	
	/**
	 * Removes Menu Items from the order
	 * @param order input Order object to be modified
	 */
	public static void removeFoodItemFromOrder(Order order) {
		String choice = "", compare = "Y";
		do { 
			order.removeFood();
			System.out.println("Input \"Y\" to remove more food from the order, or other characters to stop adding");
			choice = sc.nextLine();
		}while (choice.toUpperCase().equals(compare));
	}
	
	/**
	 * Removes an order from Restaurant's orders
	 * @param idx index of order in Restaurant Order to remove order from
	 */
	public static void removeOrder(int idx) {
		String removedOrderName = orderList.get(idx).getName();
		orderList.remove(idx);
		System.out.println("Successfully removed " + removedOrderName + " from the list of current orders");
	}
	
	/**
	 * Simulates the customer paying for his/her orders
	 * @param idx index of order in Restaurant Order which is to be completed
	 */
	public static void completeOrder(int idx) {
		Order completedOrder = orderList.get(idx);
		String removedOrderName = completedOrder.getName();
		completedOrder.setInvoiced(true);
		completedOrder.getTable().isOccupied = false;
		invoiceList.add(completedOrder);
		
		orderList.remove(idx);
		
		System.out.println("\n~~~~~~~This is the printed invoice~~~~~~~\n");
		printOrderDetails(completedOrder);
		System.out.println("\nSuccessfully completed [" + removedOrderName + "] Order");
	}
}
