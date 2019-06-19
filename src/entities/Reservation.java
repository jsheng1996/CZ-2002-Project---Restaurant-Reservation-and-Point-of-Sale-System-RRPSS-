package entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation implements java.io.Serializable{
	public Reservation(LocalDate rDate, LocalTime rTime, int pax, String name, String num, Table tab){
		this.rDate = rDate;
		this.rTime = rTime;
		this.pax = pax;
		this.tab = tab;
		this.name = name;
		this.num = num;
	}
	
	public LocalDate rDate;
	public LocalTime rTime;	
	public int pax;
	public Table tab;
	private String name;
	private String num;
	
	public String getName() {
		return this.name;
	}
	
	public String getNum() {
		return this.num;
	}

	
}