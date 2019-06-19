package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import enhancements.CusScanner;
import entities.MenuItem;
import entities.Order;
import entities.Restaurant;
import mgr.OrderMgr;
/**
 * Bouundary Class to allow users to interact with Restaurant's Invoices
 * @author JOSHHH
 *
 */
public class SalesInvoiceUI {

	private static Scanner sc = new Scanner(System.in);
	/**
	 * UI provided to display list of actions that can be performed on Restaurant's Invoices
	 */
	public static void salesInvoiceMainOption() {
		int choice;
		do {
			System.out.println("\nWhat do you wanna do ?\n");

			System.out.println("[1] - View Sales Revenue (Month)");
			System.out.println("[2] - View Sales Revenue (Day)");
			System.out.println("[3] - View List of invoices by month");
			System.out.println("[0] - Go Back");

			choice = CusScanner.nextInt(0, 3);

			switch (choice) {
			case 1:
				viewSaleMthUi();
				break;
			case 2:
				viewSaleDayUi();
				break;
			case 3:
				viewSale();
				break;
			case 0:
				return;
			}
		} while (true);
	}
	/**
	 * UI which allows user to retrieve the list of invoices of a specific day
	 */
	public static void viewSaleMthUi() {
		// using Order for testing purpose
		ArrayList<Order> unsorted_Invoices = Restaurant.invoices;
		ArrayList<Order> invoices = new ArrayList<Order>();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern("MM/yyyy");
			sdf.setLenient(false);

			System.out.println();
			System.out.print(new String(new char[12]).replace("\0", "*"));
			System.out.print("SALE REVENUE BY MONTH ");
			System.out.println(new String(new char[12]).replace("\0", "*"));
			System.out.println();
			System.out.print("Enter month (mm/yyyy): ");
			String saleRevDateStr = sc.next();

			Date reqDate = sdf.parse(saleRevDateStr);
			Calendar reqCalendar = toCalendar(reqDate);
			
			
			for (Order sort_order : unsorted_Invoices) {
				Calendar orderCalendar = toCalendar(sort_order.getTimestamp());

				if (reqCalendar.get(Calendar.YEAR) == orderCalendar.get(Calendar.YEAR)
						&& reqCalendar.get(Calendar.MONTH) == orderCalendar.get(Calendar.MONTH)) {
					invoices.add(sort_order);
				}
			}

			if (invoices.isEmpty()) {
				System.out.println("\nInvoice list is empty. There is no sales " + "to display. ");
				return;
			} else {
				/*
				ArrayList<MenuItem> totalFoodDetail = new ArrayList<MenuItem>();

				double totalSum = 0.0;

				for (int i = 0; i < invoices.size(); i++) {
					for (MenuItem m : invoices.get(i).getListOfItems()) {
						totalFoodDetail.add(m);
						totalSum += m.getPrice();
					}

				}

				if (totalSum <= 0) {
					System.out.printf("%nThere are no sales made on the selected" + " day " + reqCalendar + ".%n");
					return;
				} else {
					System.out.println(String.format("%30s", "================================================="));
					System.out.println(String.format("%30s", "Food sold that month: \n"));

					for (MenuItem detail : totalFoodDetail) {
						System.out.println(String.format("%30s %10.2f", detail.getName(), detail.getPrice()));
					}

					System.out.println(String.format("%30s", "================================================="));
					System.out.println(String.format("%30s %10.2f", "Revenue earned: ", totalSum));
				}
				
				*/
				
				Map<String, Integer> saleDictionary = new HashMap<String, Integer>();
				//HashSet<Double> individualPrice = new HashSet<Double>(); 
				double totalSum = 0.0;
				
				for (int i = 0; i < invoices.size(); i++) {
					for (MenuItem m : invoices.get(i).getListOfItems()) {
						
						if(saleDictionary.containsKey(m.getName()))
						{
							saleDictionary.merge(m.getName(), 1, Integer::sum);
						}
						else
						{
							saleDictionary.put(m.getName(), 1);
						}
						
						totalSum += m.getPrice();
					}
				}
				
				if (totalSum <= 0) {
					System.out.printf("%nThere are no sales made on the selected" + " day " + reqCalendar + ".%n");
					return;
				} else {
					System.out.println(String.format("%30s", "Food sold that month:"));
					System.out.println(String.format("%30s", "================================================="));
					System.out.println(String.format("%30s %10s", "Name:", "Qty:"));

					/*
					for (MenuItem detail : totalFoodDetail) {
						System.out.println(String.format("%30s %10.2f", detail.getName(), detail.getPrice()));
					}
					*/

					for(Map.Entry<String, Integer> entry : saleDictionary.entrySet())
					{
						System.out.println(String.format("%30s %10d", entry.getKey(), entry.getValue()));
					}
					
					System.out.println(String.format("%30s", "================================================="));
					System.out.println(String.format("%30s $%10.2f", "Revenue earned: ", totalSum));
				}
				
			}
		} catch (ParseException ex) {
			System.out.println("\nInvalid input! ");
			System.out.println("Failed to display sale revenue report." + " Please try again..");
			return;
		} catch (InputMismatchException ex) {
			System.out.println("\nInvalid input! ");
			System.out.println("Failed to display sale revenue report." + " Please try again..");

			sc.nextLine(); // Clear the garbage input
			return;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			ex.getStackTrace();
			System.out.println("\nFailed to display sale revenue report." + " Please try again..");

			sc.nextLine(); // Clear the garbage input
			return;
		}

	}
	/**
	 * UI which allows user to retrieve the list of invoices of a specific day
	 */
	public static void viewSaleDayUi() {
		// using Order for testing purpose

		ArrayList<Order> unsorted_Invoices = Restaurant.invoices;
		ArrayList<Order> invoices = new ArrayList<Order>();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern("dd/MM/yyyy");
			sdf.setLenient(false);

			System.out.println();
			System.out.print(new String(new char[12]).replace("\0", "*"));
			System.out.print("SALE REVENUE BY DAY ");
			System.out.println(new String(new char[12]).replace("\0", "*"));
			System.out.println();
			System.out.print("Enter month (dd/mm/yyyy): ");
			String saleRevDateStr = sc.next();

			Date reqDate = sdf.parse(saleRevDateStr);
			Calendar reqCalendar = toCalendar(reqDate);

			for (Order sort_order : unsorted_Invoices) {
				Calendar orderCalendar = toCalendar(sort_order.getTimestamp());

				if (reqCalendar.get(Calendar.YEAR) == orderCalendar.get(Calendar.YEAR)
						&& reqCalendar.get(Calendar.MONTH) == orderCalendar.get(Calendar.MONTH)) {
					if (reqCalendar.get(Calendar.DAY_OF_MONTH) == orderCalendar.get(Calendar.DAY_OF_MONTH)) {
						invoices.add(sort_order);
					}
				}
			}

			if (invoices.isEmpty()) {
				System.out.println("\nInvoice list is empty. There is no sales " + "to display. ");
				return;
			} else {
				/*
				ArrayList<MenuItem> totalFoodDetail = new ArrayList<MenuItem>();

				double totalSum = 0.0;

				for (int i = 0; i < invoices.size(); i++) {
					for (MenuItem m : invoices.get(i).getListOfItems()) {
						totalFoodDetail.add(m);
						totalSum += m.getPrice();
					}

				}
				*/
				Map<String, Integer> saleDictionary = new HashMap<String, Integer>();
				//HashSet<Double> individualPrice = new HashSet<Double>(); 
				double totalSum = 0.0;
				
				for (int i = 0; i < invoices.size(); i++) {
					for (MenuItem m : invoices.get(i).getListOfItems()) {
						
						if(saleDictionary.containsKey(m.getName()))
						{
							saleDictionary.merge(m.getName(), 1, Integer::sum);
						}
						else
						{
							saleDictionary.put(m.getName(), 1);
						}
						
						totalSum += m.getPrice();
					}
				}

				if (totalSum <= 0) {
					System.out.printf("%nThere are no sales made on the selected" + " day " + reqCalendar + ".%n");
					return;
				} else {
					System.out.println(String.format("%30s", "Food sold that day:"));
					System.out.println(String.format("%30s", "================================================="));
					System.out.println(String.format("%30s %10s", "Name:", "Qty:"));
					
					/*
					for (MenuItem detail : totalFoodDetail) {
						System.out.println(detail.getName() + "\t\t" + detail.getPrice());
					}
					*/
					
					for(Map.Entry<String, Integer> entry : saleDictionary.entrySet())
					{
						System.out.println(String.format("%30s %10d", entry.getKey(), entry.getValue()));
					}
					
					System.out.println(String.format("%30s", "================================================="));
					System.out.println(String.format("%30s $%10.2f", "Revenue earned: ", totalSum));

					/*
					System.out.printf("Revenue earned: %s%n", new DecimalFormat("$###,##0.00").format(totalSum));
					*/
				}

			}
		} catch (ParseException ex) {
			System.out.println("\nInvalid input! ");
			System.out.println("Failed to display sale revenue report." + " Please try again..");
			return;
		} catch (InputMismatchException ex) {
			System.out.println("\nInvalid input! ");
			System.out.println("Failed to display sale revenue report." + " Please try again..");

			sc.nextLine(); // Clear the garbage input
			return;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			System.out.println("\nFailed to display sale revenue report." + " Please try again..");

			sc.nextLine(); // Clear the garbage input
			return;
		}

	}
	/**
	 * Prints out the list of invoices of a specific month
	 */
	public static void viewSale() {
		// using Order for testing purpose

		ArrayList<Order> unsorted_Invoices = Restaurant.invoices;
		ArrayList<Order> invoices = new ArrayList<Order>();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat();
			sdf.applyPattern("MM/yyyy");
			sdf.setLenient(false);

			System.out.println();
			System.out.print(new String(new char[12]).replace("\0", "*"));
			System.out.print("SALE REVENUE BY MONTH ");
			System.out.println(new String(new char[12]).replace("\0", "*"));
			System.out.println();
			System.out.print("Enter month (mm/yyyy): ");
			String saleRevDateStr = sc.next();

			Date reqDate = sdf.parse(saleRevDateStr);
			Calendar reqCalendar = toCalendar(reqDate);
			
			
			for (Order sort_order : unsorted_Invoices) {
				Calendar orderCalendar = toCalendar(sort_order.getTimestamp());

				if (reqCalendar.get(Calendar.YEAR) == orderCalendar.get(Calendar.YEAR)
						&& reqCalendar.get(Calendar.MONTH) == orderCalendar.get(Calendar.MONTH)) {
					invoices.add(sort_order);
				}
			}

			if (invoices.isEmpty()) {
				System.out.println("\nInvoice list is empty. There is no sales " + "to display. ");
				return;
			} else {
				for(Order order : invoices)
					OrderMgr.printOrderDetails(order);
			}
		} catch (ParseException ex) {
			System.out.println("\nInvalid input! ");
			System.out.println("Failed to display sale revenue report." + " Please try again..");
			return;
		} catch (InputMismatchException ex) {
			System.out.println("\nInvalid input! ");
			System.out.println("Failed to display sale revenue report." + " Please try again..");

			sc.nextLine(); // Clear the garbage input
			return;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			ex.getStackTrace();
			System.out.println("\nFailed to display sale revenue report." + " Please try again..");

			sc.nextLine(); // Clear the garbage input
			return;
		}

	}
	
	public static Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal;
	}

	
}
