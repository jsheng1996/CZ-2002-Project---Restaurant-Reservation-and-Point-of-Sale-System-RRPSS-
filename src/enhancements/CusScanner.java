package enhancements;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Enhanced Scanner class which is used to facilitate TypeChecking, and exception handling on User inputs
 * @author JOSHHH
 *
 */
public class CusScanner {
	private static Scanner sc = new Scanner(System.in);
	/**
	 * Enhanced scanner class that incorporates TypeChecking and exception handling on int scanning.
	 * Mainly used for selecting UI options, preventing users from keying in invalid options
	 * @param lower lower bound of UI Selection
	 * @param upper upper bound of UI Selection
	 * @return Selected Integer option
	 */
	public static int nextInt(int lower,int upper) {
		int choice = upper;
		boolean checkInt;
		
		do{ 
			if (choice > upper || choice < lower)
				System.out.println("You keyed in " + choice + ", Please select between " + lower + " - " + upper);
			checkInt = false;
			do{ 
				try{
					choice = sc.nextInt();
					checkInt = true;
				}
				catch(InputMismatchException e){
					System.out.println("Please input an integer, and not strings / characters");
					sc.nextLine();
				}
			}while(!checkInt);
		}while(choice > upper || choice < lower);
		
		return choice;
	}

	/**
	 * Enhanced scanner class that incorporates TypeChecking and exception handling on double scanning.
	 * @return User input's double
	 */
	public static double nextDouble() {
		double choice = 0.00;
		boolean checkDouble = false;
		
		do{
			try{
				choice = sc.nextDouble();
				checkDouble = true;
			}
			catch(InputMismatchException e){
				System.out.println("Please input in a valid double");
				sc.nextLine();
			}
		}while(!checkDouble);
		
		return choice;
		}
}


