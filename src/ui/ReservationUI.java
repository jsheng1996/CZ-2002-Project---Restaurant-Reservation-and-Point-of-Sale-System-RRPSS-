package ui;
	
import entities.Table;
import entities.Reservation;
import java.util.Scanner;
import java.time.*;
import mgr.ReservationMgr;

public class ReservationUI {
	public static void CreateReservation() {
		Scanner sc = new Scanner(System.in);
		String buff;
		
		System.out.println("Enter name: ");
		String name = sc.nextLine();
		
		System.out.println("Enter date of reservation in the format of YYYY-MM-DD: ");
		LocalDate date	= LocalDate.parse(sc.nextLine());
		LocalDate today = LocalDate.now();
		//Check that reservation date is not more than 1 month from now
		if(date.isAfter(today.plusMonths(1))) {
			System.out.println("Sorry, we only accept bookings 1 monnth in advance");
			return;
		}
		//Check reservation date is not in the past
		if(date.isBefore(today)) {
			System.out.println("Sorry, cannot time travel");
			return;
		}
		
		System.out.println("Enter session choice: 1) AM 2)PM");
		int session = sc.nextInt();
		buff = sc.nextLine();
		
		System.out.println("Enter time of arrival in format of hh:mm");
		LocalTime time = LocalTime.parse(sc.nextLine());
		//check validity of session times
		while(session==1&& (time.isBefore(LocalTime.parse("11:00")) || time.isAfter(LocalTime.parse("15:00")))) {
			System.out.println("Sorry, AM session is only from 11:00-15:00");
			System.out.println("Enter time of arrival in format of hh:mm");
			time = LocalTime.parse(sc.nextLine());
		}
		while(session==2&& (time.isBefore(LocalTime.parse("16:00")) || time.isAfter(LocalTime.parse("20:00")))) {
			System.out.println("Sorry, PM session is only from 16:00-20:00");
			System.out.println("Enter time of arrival in format of hh:mm");
			time = LocalTime.parse(sc.nextLine());
		}
		
		System.out.println("Enter number of pax:");
		int pax = sc.nextInt();
		buff = sc.nextLine();
		
		int choice=0;
		
		do{
			if(choice==1) {
				System.out.println("Enter date of reservation in the format of YYYY-MM-DD: ");
				date = LocalDate.parse(sc.nextLine());
				//Check that reservation date is not more than 1 month from now
				if(date.isAfter(today.plusMonths(1))) {
					System.out.println("Sorry, we only accept bookings 1 monnth in advance");
					return;
				}
				//Check reservation date is not in the past
				if(date.isBefore(today)) {
					System.out.println("Sorry, cannot time travel");
					return;
				}
				
				System.out.println("Enter session choice: 1) AM 2)PM");
				session = sc.nextInt();
				buff = sc.nextLine();
				
				System.out.println("Enter time of arrival in format of hh:mm");
				time = LocalTime.parse(sc.nextLine());
				//check validity of session times
				if(session==1&& (time.isBefore(LocalTime.parse("11:00")) || time.isAfter(LocalTime.parse("15:00")))) {
					System.out.println("Sorry, AM session is only from 11:00-15:00");
				}
				if(session==2&& (time.isBefore(LocalTime.parse("16:00")) || time.isAfter(LocalTime.parse("20:00")))) {
					System.out.println("Sorry, PM session is only from 16:00-20:00");
				}
			}
			
			String sessionStr = (session == 1?"AM":"PM");
			String fileName = "reservation"+date+sessionStr+".dat";
			Table[] tableArr = db.fileIO.readReservation(fileName);
			//here im assuming the array of tables is sorted in asc order of pax size
			
			for(int i=0; i<tableArr.length;i++) {
				if(tableArr[i].numOfSeats>=pax && tableArr[i].tableReservation==null) {
					System.out.println("Enter number to confirm reservation: ");
					String number = sc.nextLine();
					
					Reservation r = new Reservation(date, time, pax, name, number, tableArr[i]);
					mgr.ReservationMgr.update(date, session, tableArr[i].tableId, r);
					mgr.ReservationMgr.refreshSessionReservations();
					System.out.println("Reservation successfully created!");
					return;
				}
			}
			
			System.out.println("Sorry, we are fully booked!");
			System.out.println("Enter your choice: 1) Choose another date and time 2) Exit");
			choice = sc.nextInt();
			buff = sc.nextLine();
		} while(choice<2);
	}
	
	public static void checkIn() {
		Scanner sc = new Scanner(System.in);
		String number;
		
		System.out.println("Enter number used to confirm reservation:");
		number = sc.nextLine();
		mgr.ReservationMgr.reservationArrival(number);
	}
	
	public static void cancelReservation() {
		Scanner sc = new Scanner(System.in);
		LocalDate date;
		int session;
		String number, buff;
		
		System.out.println("Enter date of reservation to cancel in format YYYY-MM-DD:");
		date = LocalDate.parse(sc.nextLine());
		
		System.out.println("Enter session of reservation: 1)AM 2)PM");
		session = sc.nextInt();
		buff = sc.nextLine();
		
		System.out.println("Enter number used to confirm reservation:");
		number = sc.nextLine();
		
		String sessionStr = (session == 1?"AM":"PM");
		String fileName = "reservation"+date+sessionStr+".dat";
		Table[] tableArr = db.fileIO.readReservation(fileName);
		for(int i=0;i<tableArr.length;i++) {
			if(tableArr[i].tableReservation != null) {
				if(tableArr[i].tableReservation.getNum().equals(number)) {
					mgr.ReservationMgr.update(date, session, tableArr[i].tableReservation.tab.tableId, null);
					mgr.ReservationMgr.refreshSessionReservations();
					System.out.println("Reservation successfully removed!");
					return;
				}
			}
		}
		System.out.println("Reservation not found!");
	}
	
	public static void displayReservations() {
		Scanner sc = new Scanner(System.in);
		LocalDate date;
		int session;
		String buff;
		
		System.out.println("Enter date of reservation to display in format YYYY-MM-DD:");
		date = LocalDate.parse(sc.nextLine());
		
		System.out.println("Enter session of reservation: 1)AM 2)PM");
		session = sc.nextInt();
		buff = sc.nextLine();
		
		String sessionStr = (session == 1?"AM":"PM");
		String fileName = "reservation"+date+sessionStr+".dat";
		Table[] tableArr = db.fileIO.readReservation(fileName);
		System.out.println("Displaying reservations:");
		System.out.println("========================");
		for(int i=0;i<tableArr.length;i++) {
			if(tableArr[i].tableReservation != null) {
				Reservation r = tableArr[i].tableReservation;
				System.out.printf("Reservation at %s, %s for %d pax.\nTable number: %d\nCreator: %s\nNumber: %s\n\n", r.rTime.toString(), r.rDate.toString(), r.pax, r.tab.tableId, r.getName(), r.getNum());

			}
		}
		System.out.println("========================");
		System.out.println("Reservations displayed");
	}
}
