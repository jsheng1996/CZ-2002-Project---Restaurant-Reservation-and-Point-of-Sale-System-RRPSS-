package db;
import entities.*;
import java.time.*;
import java.util.ArrayList;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class fileIO {
	public static void write(Object[] obj, String fileName) {//general write method
		try {	
			FileOutputStream f = new FileOutputStream(new File(fileName));
			ObjectOutputStream o = new ObjectOutputStream(f);
			for(int i=0;i<obj.length;i++){//ends at end of array
					o.writeObject(obj[i]);
			}
			o.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Object[] read(String fileName) {//general read method
		ArrayList<Object> temp=new ArrayList<Object>();
		try {
			FileInputStream fi = new FileInputStream(new File(fileName));
			ObjectInputStream oi = new ObjectInputStream(fi);
			while(oi.available()==0) {//read until end of file
				temp.add(oi.readObject());
			}
			oi.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (EOFException e) {
			
		}catch (IOException e) {
			e.printStackTrace(); //TODO hits exception
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return temp.toArray();
	}
	
	public static void writeTable(Table[] table) {
		String fileName = new String("tables.dat");
		write(table, fileName);
	}
		
	public static Table[] readTable() {
		String fileName = new String("tables.dat");
		Object[] temp1 = read(fileName);
		Table[] temp = new Table[temp1.length];
		for(int i=0;i<temp1.length;i++){
				temp[i] = (Table)temp1[i];
				//System.out.println(temp[i].tableId);
		}
		return temp;
	}
		
	public static Staff[] readStaff() {
		String fileName = new String("staff.dat");
		Object[] temp1 = read(fileName);
		Staff[] temp = new Staff[temp1.length];
		for(int i=0;i<temp1.length;i++){
				temp[i]=(Staff)temp1[i];
		}
		return temp;
	}
		
	public static Order[] readOrder() {
		String fileName = new String("order.dat");
		Object[] temp1 = read(fileName);
		Order[] temp = new Order[temp1.length];
		for(int i=0;i<temp1.length;i++){
				temp[i]=(Order)temp1[i];
		}
		return temp;
	}
	
	public static Order[] readInvoice() {
		String fileName = new String("invoice.dat");
		Object[] temp1 = read(fileName);
		Order[] temp = new Order[temp1.length];
		for(int i=0;i<temp1.length;i++){
				temp[i]=(Order)temp1[i];
		}
		return temp;
	}
	
	public static MenuItem[] readItem() {
		String fileName = new String("menu.dat");
		Object[] temp1 = read(fileName);
		MenuItem[] temp = new MenuItem[temp1.length];
		for(int i=0;i<temp1.length;i++){
				temp[i]=(MenuItem)temp1[i];
		}
		return temp;
	}
	
	public static SetPackage[] readPackage() {
		String fileName = new String("package.dat");
		Object[] temp1 = read(fileName);
		SetPackage[] temp = new SetPackage[temp1.length];
		for(int i=0;i<temp1.length;i++){
				temp[i]=(SetPackage)temp1[i];
		}
		return temp;
	}
	
	public static Table[] readReservation(String fileName) {
		
		File tempFile = new File(fileName);
		if(!tempFile.exists()) {
			int j = 1;
			ArrayList<Table> tempT = new ArrayList<Table>();
			for(int i = 0; i < 5; i++){
				tempT.add(new Table(10, j));
				j++;
			}
			for(int i = 0; i < 5; i++){
				tempT.add(new Table(8, j));
				j++;
			}
			for(int i = 0; i < 10; i++){
				tempT.add(new Table(4, j));
				j++;
			}
			for(int i = 0; i < 10; i++){
				tempT.add(new Table(2, j));
				j++;
			}
			db.fileIO.write(tempT.toArray(new Table[30]), fileName);
			return tempT.toArray(new Table[30]);
		}
		Object[] temp = read(fileName); 
		Table[] temp1 = new Table[temp.length];
		for(int i=0;i<temp.length;i++){
			temp1[i]=(Table)temp[i];
		}
		return temp1;
	}
}
