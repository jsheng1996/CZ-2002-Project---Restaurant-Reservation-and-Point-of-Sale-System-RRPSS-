package entities;
import java.util.ArrayList; 
import java.util.Arrays;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.*;

import db.fileIO;
import entities.FoodItem.FoodType;

public class Restaurant {
	//private FoodItemManager foodManager;
	//private SetPackageManager setPackageManager;
	public static ArrayList<Table> tables;
	public static ArrayList<MenuItem> menuItems;
	public static ArrayList<Order> orders;
	public static ArrayList<Order> invoices;
	public static ArrayList<Staff> staff;
	public static ArrayList<Table> sessionReservations;

	public static void initRestaurant() {
		initMenu();
		initTable();
		initStaff();
		initOrders();
		initInvoices();
		initReservations();
	}
	
	
	public static void initMenu() {
		//foodManager = new FoodItemManager();
		//setPackageManager = new SetPackageManager();
		menuItems = new ArrayList<MenuItem>();
		File tempFile = new File("menu.dat");
		if (tempFile.exists()) {//read from tables.dat
			MenuItem[] temp = new MenuItem[30];//used fixed int because size==0
			temp = fileIO.readItem();
			for(int i=0;i<temp.length;i++)
				menuItems.add(temp[i]);
//			tempFile = new File("package.dat");
//			if (tempFile.exists()) {//read from tables.dat
//				SetPackage[] temp1 = new SetPackage[30];//used fixed int because size==0
//				temp = fileIO.readItem();
//				for(int i=0;i<temp1.length;i++)
//					SetPackage.add(temp1[i]);
//				}
			}else {
			FoodItem food_1 = new FoodItem("CheeseBurger", "Burger with 2 patties of beef and 2 slices of cheese mixed with sweet tomato sauce", 7.50, FoodType.MAIN_COURSE);
			FoodItem food_2 = new FoodItem("FishBurger", "Burger with 2 patties of fish and smothered in mayonaise sauce", 5.50, FoodType.MAIN_COURSE);
			FoodItem food_3 = new FoodItem("IceLemonTea", "Self-Explanatory", 2.50, FoodType.DRINK);
			FoodItem food_4 = new FoodItem("Coke", "Why is there even a need for discription for Coke", 2.00, FoodType.DRINK);
			FoodItem food_5 = new FoodItem("Fish&Chips", "Reward yourself with ESKETIT'S Fried Fish&Chips decorated with Awsum Mayo", 7.50, FoodType.MAIN_COURSE);
			FoodItem food_6 = new FoodItem("Grilled Steak", "Get a taste of ESKETIT'S BOOMZ Grilled Steak smothered in Mind Blowing Mushroom Sauce grilled to YEET Perfection bois", 7.50, FoodType.MAIN_COURSE);
			FoodItem food_7 = new FoodItem("French Fries", "Fried to divine soggyness, trinkled with salt and sauz on the side", 7.50, FoodType.MAIN_COURSE);
			FoodItem food_8 = new FoodItem("Sundae", "Heavenly sweet ice cream mixed with Chocolate Fudege that will be sure to bring you euphoria(Hypothetically speaking)", 7.50, FoodType.MAIN_COURSE);
	
	
			
			menuItems.add((MenuItem) food_1);
			menuItems.add((MenuItem) food_2);
			menuItems.add((MenuItem) food_3);
			menuItems.add((MenuItem) food_4);
			menuItems.add((MenuItem) food_5);
			menuItems.add((MenuItem) food_6);
			menuItems.add((MenuItem) food_7);
			menuItems.add((MenuItem) food_8);
			
			SetPackage sp_1 = new SetPackage("SetA", "CheeseBurger + Coke + Fries", 9.99);
			SetPackage sp_2 = new SetPackage("Tasty Ass Steak Meal", "Steak + Coke + Fries + Sundae", 9.99);
			//SetPackage sp_3 = new SetPackage("SetC", "CheeseBurger + Coke + Fries", 9.99);
			//SetPackage sp_4 = new SetPackage("SetD", "CheeseBurger + Coke + Fries", 9.99);
			
			sp_1.addFood(food_1);
			sp_1.addFood(food_4);
			sp_1.addFood(food_7);
			
			sp_2.addFood(food_6);
			sp_2.addFood(food_4);
			sp_2.addFood(food_7);
			sp_2.addFood(food_8);
			
			menuItems.add((MenuItem) sp_1);
			menuItems.add((MenuItem) sp_2);
			fileIO.write(menuItems.toArray(),"menu.dat");
		}
		
	}
	
	public static void initTable(){
		int j = 1;
		tables = new ArrayList<Table>();
		File tempFile = new File("tables.dat");
		if (tempFile.exists()) {//read from tables.dat
			Table[] temp = new Table[30];//used fixed int because size==0
			temp = fileIO.readTable();
			for(int i=0;i<temp.length;i++)
				tables.add(temp[i]);
			//fileIO.write(tables.toArray(),"tables.dat");
		}else {//generate new tables
			System.out.println("tables manually created");
			for(int i = 0; i < 10; i++){
				tables.add(new Table(2, j));
				j++;
			}
			for(int i = 0; i < 10; i++){
				tables.add(new Table(4, j));
				j++;
			}
			for(int i = 0; i < 5; i++){
				tables.add(new Table(8, j));
				j++;
			}
			for(int i = 0; i < 5; i++){
				tables.add(new Table(10, j));
				j++;
			}
			fileIO.write(tables.toArray(),"tables.dat");
		}
	}
	
	public static void initStaff() {
		staff = new ArrayList<Staff>();
		File tempFile = new File("staff.dat");
		if (tempFile.exists()) {//read from tables.dat
			Staff[] temp = new Staff[30];//used fixed int because size==0
			temp = fileIO.readStaff();
			for(int i=0;i<temp.length;i++)
				staff.add(temp[i]);
		}else {
			Staff staff_1 = new Staff(0, "Terry", "Cashier", Staff.GenderType.GENDER_MALE);
			Staff staff_2 = new Staff(1, "Megan", "Chef", Staff.GenderType.GENDER_FEMALE);
			Staff staff_3 = new Staff(2, "Cruz", "Cashier", Staff.GenderType.GENDER_MALE);
			staff.add(staff_1);
			staff.add(staff_2);
			staff.add(staff_3);
			fileIO.write(staff.toArray(),"staff.dat");
		}
		
	}
	
	public static void initOrders() {
		orders = new ArrayList<Order>();
		File tempFile = new File("order.dat");
		if (tempFile.exists()) {//read from tables.dat
			Order[] temp = new Order[30];//used fixed int because size==0
			temp = fileIO.readOrder();
			for(int i=0;i<temp.length;i++)
				orders.add(temp[i]);
		}else {
			Order order_1 = new Order(staff.get(1), tables.get(1));
			//order_1.setTimestamp(timestamp);
			order_1.addFood(menuItems.get(1));
			order_1.addFood(menuItems.get(4));
			order_1.addFood(menuItems.get(7));
			order_1.getTable().isOccupied = true;
			orders.add(order_1);
			
			Order order_2 = new Order(staff.get(2), tables.get(2));
			order_2.addFood(menuItems.get(3));
			order_2.addFood(menuItems.get(5));
			order_2.addFood(menuItems.get(8));
			order_2.getTable().isOccupied = true;
			orders.add(order_2);
			
			Order order_3 = new Order(staff.get(0), tables.get(0));
			order_3.addFood(menuItems.get(9));
			order_3.addFood(menuItems.get(5));
			order_3.addFood(menuItems.get(6));
			order_3.getTable().isOccupied = true;
			orders.add(order_3);

			fileIO.write(orders.toArray(),"order.dat");
		}
	}
	
	public static void initInvoices(){
		invoices = new ArrayList<Order>();
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		File tempFile = new File("invoice.dat");
		if (tempFile.exists()) {//read from tables.dat
			Order[] temp = new Order[30];//used fixed int because size==0
			temp = fileIO.readInvoice();
			for(int i=0;i<temp.length;i++)
				invoices.add(temp[i]);
			}else {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Order invoice_1 = new Order(staff.get(1), tables.get(1));
				//invoice_1.setTimestamp(timestamp);
				invoice_1.addFood(menuItems.get(2));
				invoice_1.addFood(menuItems.get(3));
				invoice_1.addFood(menuItems.get(8));
				invoice_1.setInvoiced(false);
				//invoice_1.setTimestamp(formatter.parse("01/02/2019 11:05:00"));
				
				try {
					invoice_1.setTimestamp(formatter.parse("01/02/2019 11:05:00"));
					invoice_1.setName(invoice_1.getStaffAssigned().toString() + ", " + formatter.format(invoice_1.getTimestamp()));
				} catch (ParseException e) {
					System.out.println(e);
					e.printStackTrace();
				}
				
				invoices.add(invoice_1);
				
				Order invoice_2 = new Order(staff.get(2), tables.get(2));
				invoice_2.addFood(menuItems.get(2));
				invoice_2.addFood(menuItems.get(1));
				invoice_2.addFood(menuItems.get(0));
				invoice_2.setInvoiced(false);
				//invoice_2.setTimestamp(formatter.parse("01/01/2019 14:05:00"));
				
				try {
					invoice_2.setTimestamp(formatter.parse("11/03/2019 11:05:00"));
					invoice_2.setName(invoice_2.getStaffAssigned().toString() + ", " + formatter.format(invoice_2.getTimestamp()));
				} catch (ParseException e) {
					System.out.println(e);
					e.printStackTrace();
				}
				
				invoices.add(invoice_2);
				
				Order invoice_3 = new Order(staff.get(0), tables.get(0));
				invoice_3.addFood(menuItems.get(5));
				invoice_3.addFood(menuItems.get(7));
				invoice_3.addFood(menuItems.get(1));
				invoice_3.setInvoiced(false);
				//invoice_3.setTimestamp(formatter.parse("22/02/2019 18:05:00"));
				
				try {
					invoice_3.setTimestamp(formatter.parse("28/02/2019 11:05:00"));
					invoice_3.setName(invoice_3.getStaffAssigned().toString() + ", " + formatter.format(invoice_3.getTimestamp()));
				} catch (ParseException e) {
					System.out.println(e);
					e.printStackTrace();
				}
				
				invoices.add(invoice_3);
				
				Order invoice_4 = new Order(staff.get(0), tables.get(0));
				invoice_4.addFood(menuItems.get(1));
				invoice_4.addFood(menuItems.get(0));
				invoice_4.addFood(menuItems.get(3));
				invoice_4.setInvoiced(false);
				//invoice_4.setTimestamp(formatter.parse("14/02/2019 19:05:00"));
				
				try {
					invoice_4.setTimestamp(formatter.parse("18/07/2019 11:05:00"));
					invoice_4.setName(invoice_4.getStaffAssigned().toString() + ", " + formatter.format(invoice_4.getTimestamp()));
				} catch (ParseException e) {
					System.out.println(e);
					e.printStackTrace();
				}
				
				invoices.add(invoice_4);
				
				Order invoice_5 = new Order(staff.get(0), tables.get(0));
				invoice_5.addFood(menuItems.get(2));
				invoice_5.addFood(menuItems.get(7));
				invoice_5.addFood(menuItems.get(8));
				invoice_5.setInvoiced(false);
				//invoice_5.setTimestamp(formatter.parse("07/03/2019 21:05:00"));
				
				try {
					invoice_5.setTimestamp(formatter.parse("18/06/2019 11:05:00"));
					invoice_5.setName(invoice_5.getStaffAssigned().toString() + ", " + formatter.format(invoice_5.getTimestamp()));
				} catch (ParseException e) {
					System.out.println(e);
					e.printStackTrace();
				}
				
				invoices.add(invoice_5);
				
				Order invoice_6 = new Order(staff.get(0), tables.get(0));
				invoice_6.addFood(menuItems.get(9));
				invoice_6.addFood(menuItems.get(6));
				invoice_6.addFood(menuItems.get(0));
				invoice_6.setInvoiced(false);
				//invoice_6.setTimestamp(formatter.parse("05/03/2019 11:05:00"));
				
				try {
					invoice_6.setTimestamp(formatter.parse("18/04/2019 11:05:00"));
					invoice_6.setName(invoice_6.getStaffAssigned().toString() + ", " + formatter.format(invoice_6.getTimestamp()));
				} catch (ParseException e) {
					System.out.println(e);
					e.printStackTrace();
				}
				
				invoices.add(invoice_6);
			fileIO.write(invoices.toArray(),"invoice.dat");
		}	
	}
	
	public static void initReservations() {
		sessionReservations = new ArrayList<Table>();
		LocalDate date = LocalDate.now();
		int session = (LocalTime.now().isAfter(LocalTime.parse("15:00"))? 2:1);
		String sessionStr = (session == 1?"AM":"PM");
		String fileName = "reservation"+date+sessionStr+".dat";
		sessionReservations.addAll(Arrays.asList(fileIO.readReservation(fileName)));
	}
}
