package mgr;
import entities.Reservation;
import entities.Restaurant;
import java.util.ArrayList; 
import java.util.Arrays;
import java.io.File;
import java.time.*;
import entities.Table;

import db.fileIO;

public class ReservationMgr {
	
	public static void checkReservationExpiry(Reservation r) {
		if(LocalTime.now().isAfter(r.rTime.plusMinutes(30))) {
			cancelReservation(r);	//Free up table for walk-in customers
	    }
	}
	
	public static void clearExpiredReservations(ArrayList<Table> t) {
		for(int i=0;i<t.size();i++) {
			if(t.get(i).tableReservation != null) {
				checkReservationExpiry(t.get(i).tableReservation);
			}
		}
		System.out.println("Expired reservations purged!");
	}
	
	public static void reservationArrival(String number) {
		for(int i=0;i<entities.Restaurant.sessionReservations.size();i++) {
			if(entities.Restaurant.sessionReservations.get(i).tableReservation!=null) {
				if(entities.Restaurant.sessionReservations.get(i).tableReservation.getNum().equals(number)) {
					ui.OrderUI.addNewOrderUI(entities.Restaurant.sessionReservations.get(i).tableId);
					System.out.println("Customer successfuilly seated!");
					return;
				}
			}
		}
		System.out.println("Reservation does not exsist");
	}
	
	
	public static void refreshSessionReservations() {
		entities.Restaurant.initReservations();
	}
	
	public static void update(LocalDate date, int session, int tableID, Reservation r) {
		String sessionStr = (session == 1?"AM":"PM");
		String fileName = "reservation"+date+sessionStr+".dat";
		Table[] temp = db.fileIO.readReservation(fileName);
		for(int i=0;i<temp.length;i++) {
			if(temp[i].tableId == tableID) {
				temp[i].tableReservation = r;
			}
		}
		db.fileIO.write(temp, fileName);
	}
	
	public static void cancelReservation(Reservation r) {
		for(int i=0;i<entities.Restaurant.sessionReservations.size();i++) {
			if(entities.Restaurant.sessionReservations.get(i).tableId == r.tab.tableId) {
				entities.Restaurant.sessionReservations.get(i).tableReservation = null;
			}
		}
	}
}
