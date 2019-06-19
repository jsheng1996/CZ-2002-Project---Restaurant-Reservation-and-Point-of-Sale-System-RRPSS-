package mgr;
import entities.Table;
import java.util.*;
import mgr.ReservationMgr;

public class TableMgr {
	
	public static void seatWalkIn(int pax) {
		for(int i=0;i<entities.Restaurant.sessionReservations.size();i++) {
			if(entities.Restaurant.sessionReservations.get(i).numOfSeats >= pax && entities.Restaurant.sessionReservations.get(i).isOccupied==false && entities.Restaurant.sessionReservations.get(i).tableReservation==null) {
				setTableStatus(entities.Restaurant.sessionReservations, entities.Restaurant.sessionReservations.get(i).tableId, true);
				System.out.println("Customer successfully seated!");
				return;
			}
		}
	}
	
	public static void setTableStatus(ArrayList<Table> tables, int tableID, boolean status) {
		for(int i=0;i<tables.size();i++) {
			if(tables.get(i).tableId == tableID) {
				tables.get(i).isOccupied = status;
				return;
			}
		}
	}
	
}
