package ui;
import java.util.Scanner;
import mgr.TableMgr;
import ui.ReservationUI;

public class SeatCustomerUI {
	public static void seat() {
		int choice, pax;
		String number;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter choice: 1) Walk-in 2) Reservation");
		choice = sc.nextInt();
		switch(choice) {
			case 1:
				ui.OrderUI.addNewOrderUI();
				break;
			case 2:
				ui.ReservationUI.checkIn();				
		}
	}
}
