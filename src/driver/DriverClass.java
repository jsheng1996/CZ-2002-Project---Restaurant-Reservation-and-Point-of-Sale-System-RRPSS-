package driver;
import entities.Restaurant;
import entities.Table;
import entities.Reservation;
import ui.*;

import java.time.LocalTime;


import db.fileIO;
import enhancements.CusScanner;


/**
 * Driver Class for Restaurant POS Java App
 * @author JOSHHH
 *
 */

public class DriverClass {

	public static void main(String[] args) {
		int choice = 1;
		Restaurant.initRestaurant();
		
		do {
			System.out.println("What do you wanna do ?\n");
			
			System.out.println("[1] - View Menu");
			System.out.println("[2] - View Orders");
			System.out.println("[3] - New Reservation");
			System.out.println("[4] - Seat Customer");
			System.out.println("[5] - Sales invoice");
			System.out.println("[6] - Clear Expired Reservations");
			System.out.println("[7] - View All Reservations");
			System.out.println("[8] - View Session Reservations");
			System.out.println("[9] - Remove Reservation");
			System.out.println("[10] - View Available Tables");
			System.out.println("[0] - Go Back");
			
			choice = CusScanner.nextInt(0, 10);
			
			switch(choice) {
				case 1:
					ui.MenuItemUI.menuItemMainOptions();
					break;
				case 2:
					ui.OrderUI.orderMainOptions();
					break;
				case 3:
					ui.ReservationUI.CreateReservation();
					break;
				case 4:
					ui.SeatCustomerUI.seat();
					break;
				case 5:
					ui.SalesInvoiceUI.salesInvoiceMainOption();
					break;
				case 6:
					mgr.ReservationMgr.clearExpiredReservations(entities.Restaurant.sessionReservations);
					break;
				case 7:
					ui.ReservationUI.displayReservations();
					break;
				case 8:
					mgr.ReservationMgr.clearExpiredReservations(entities.Restaurant.sessionReservations);
					mgr.ReservationMgr.refreshSessionReservations();
					System.out.println("Displaying reservations:");
					System.out.println("========================");
					for(int i=0;i<entities.Restaurant.sessionReservations.size();i++) {
						if(entities.Restaurant.sessionReservations.get(i).tableReservation != null) {
							Reservation r = entities.Restaurant.sessionReservations.get(i).tableReservation;
							System.out.printf("Reservation at %s, %s for %d pax.\nTable number: %d\nCreator: %s\nNumber: %s\n\n", r.rTime.toString(), r.rDate.toString(), r.pax, r.tab.tableId, r.getName(), r.getNum());
						}
					}
					System.out.println("========================");
					System.out.println("Reservations displayed");
					break;
				case 9:
					ui.ReservationUI.cancelReservation();
					break;
				case 10:
					mgr.ReservationMgr.refreshSessionReservations();
					//mgr.ReservationMgr.clearExpiredReservations(entities.Restaurant.sessionReservations);
					System.out.println("Displaying available tables:");
					System.out.println("============================");
					for(int i=0;i<30;i++) {
						if(entities.Restaurant.tables.get(i).isOccupied == false && entities.Restaurant.sessionReservations.get(i).tableReservation == null) {
							Table temp = entities.Restaurant.tables.get(i);
							System.out.printf("Available Table:\nTable ID:%d\nPax:%d\n\n", temp.tableId, temp.numOfSeats);
						}
					}
					System.out.println("============================");
					System.out.println("Available tables displayed");
					break;
				case 0:
					System.out.println("EXITING THE APPLICATION GUD BYE");
					fileIO.write(Restaurant.tables.toArray(),"tables.dat");
					fileIO.write(Restaurant.staff.toArray(),"staff.dat");
					fileIO.write(Restaurant.orders.toArray(),"order.dat");
					fileIO.write(Restaurant.invoices.toArray(),"invoice.dat");
					fileIO.write(Restaurant.menuItems.toArray(),"menu.dat");
					return;
			}
		}while (true);
	}

}
