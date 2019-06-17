import javax.sound.midi.SysexMessage;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the main class - the program is run by java Shop
 *
 * @author Mrs Miggins
 * @version 1.0 (22nd February 2015)
 */

public class Shop {

	private ArrayList<Item> stocks = new ArrayList<Item>();
	private String shopName;
	private Scanner scan;
	private UKTill till;
	private int totalCostDue; // Store as pence
	private static final String SHOP_STOCK_DATA_FILE = "./stock.txt";
	private static final String SHOP_TILL_DATA_FILE = "./till.txt";

	public Shop(String name) {
		shopName = name;
		till = new UKTill();
		scan = new Scanner(System.in);
	}

	/**
	 * Using stockShop an owner can add various goods to the system. We can then
	 * can add an item, set the price and set the stock level.
	 */
	public void stockShop() {

		String barCodeID;
		int penny;
		int quantity;
		String name;
		Scanner input = new Scanner(System.in);

		System.out.println("Please enter the name of the item: ");
		name = input.nextLine();

		System.out.println("Please enter the Barcode ID in:");
		barCodeID = input.nextLine();


		System.out.println("Please enter the cost in pennies ");
		penny = input.nextInt();
		double cost = penny/100;



		System.out.println("Please the quantity of the item");
		quantity = input.nextInt();

		Item item = new Item(barCodeID, name, penny, quantity );
		stocks.add(item);



	}

	/**
	 * Using startTill, an owner can add the various denomination floats to the till
	 * specifying the name, value and quantity of each item (If she has 33
	 * "10p pieces", each of which is worth 10, she enters "10p piece", 10, 33
	 * (one separate lines)).
	 */
	public void startTill() {
		do {
			UKDenomination ct = getDenominationType();
			int nc = getInt("Number of these coins: ");
			DenominationFloat m = new DenominationFloat(ct, nc);
			till.addFloat(m);

			System.out.println("Denomination floats enetered into till: " + m);
		} while (doContinue());
	}

	

	/**
	 * Using runTill, an owner can sell items. Customers put in their order, the
	 * system then tells her how much to charge.
	 */
	public void runTill() {

		double total= 0;
		// ENTER CODE HERE
		// Repeatedly ask for barcode id (simulates scanning barcode)
		Scanner input = new Scanner(System.in);
		String userInput = input.nextLine();

		while(!userInput.equals("Q")){
			System.out.println("Please enter the Items BarCode Id");
			for (Item item: stocks){

				if(item.getIdentifier().equals(userInput)){
					total += item.getCost();
					System.out.println("Your total is" + total);
					System.out.println("Your items price is:" + item.getCost());
					if (item.getQuanity() > 0){
						item.decreaseQuantity();
					}
					else {
						stocks.remove(item);
					}

				}
			}
			System.out.println("Your total is:" + total);

		}

	}	

	/**
	 * Using getChange, an owner can tell the system how much of each
	 * denomination she has been given by the customer and the till tells her
	 * what to giveback.
	 */
	public void getChange() {
		System.out.println("Hand over the money!");
		do {
			// ENTER CODE HERE
			// Get the denomination
			System.out.println(getDenominationType());
			// Get the number of coins

			// Add float of coins to the till
			// Calculate remaining amount to pay
			// Display remaining amount to pay
		} while (totalCostDue > 0);
		
		// Calculate change
		if (totalCostDue == 0){
			System.out.println("You provided the exact amount, thank you!");
		} else {
			DenominationFloat[] change = till.getChange(Math.abs(totalCostDue));
			System.out.println("Here is your change:");
			for (DenominationFloat m: change){
				if (m != null){
					System.out.println(m);
				}
			}
		}
	}

	/**
	 * Using getBalance it tells the owner what is left in the till
	 */
	public void getBalance() {
		System.out.println(till);
	}

	/**
	 * runMenu provides the main menu to the shop allowing a user to select
	 * their required operation
	 */
	public void runMenu() {
		// This is the main menu which runs the whole shop

		String choice;
		do {
			printMenu();
			choice = scan.nextLine();

			switch (choice) {
			case "1":
				stockShop();
				break;
			case "2":
				startTill();
				break;
			case "3":
				runTill();
				break;
			case "4":
				getChange();
				break;
			case "5":
				getBalance();
				break;
			case "6":
				System.out.println("Thankyou for running " + shopName
						+ " program");
				break;
			default:
				System.err.println("Incorrect choice entered");
			}
		} while (!choice.equals("6"));
	}

	private boolean doContinue() {
		System.out.println("Continue? (Y/N)");
		String answer = scan.next().toUpperCase();
		scan.nextLine();
		return answer.equals("Y");
	}

	private int getInt(String message) {
		boolean correct = false;
		int result = 0;
		do {
			System.out.println(message);
			try {
				result = scan.nextInt();
				scan.nextLine();
				correct = true;
			} catch (InputMismatchException ime) {
				System.err.println("Please enter an number");
				scan.nextLine();
			}
		} while (!correct);
		return result;
	}
	
	private void displayCost(String message, int amountInPence) {
		System.out.format("%s %d.%02d\n", message, amountInPence / 100, amountInPence % 100);
	}
	
	private UKDenomination getDenominationType() {
		UKDenomination result;
		do {
			System.out.println("Enter the denomination type. One of: ");
			for (UKDenomination denom: UKDenomination.values()){
				System.out.print(denom + " ");
			}
			String choice = scan.nextLine();
			result = UKDenomination.fromString(choice);
			if (result == null){
				System.err.println("Incorrect denomination entered. Try again!");
			}
		}while(result == null);
		return result;
	}

	private void printMenu() {
		System.out.println("Welcome to " + shopName + ". Please enter choice:");
		System.out.println("1 - Stock the shop");
		System.out.println("2 - Add coins to the till");
		System.out.println("3 - Process customer order");
		System.out.println("4 - Process customer payment");
		System.out.println("5 - Display till balance");
		System.out.println("6 - Exit shop program");
	}

	/**
	 * Saves data to the shop database (stock and till)
	 * @exception IOException
	 *                thrown when file problems occur
	 */
	public void save() throws IOException {
		// ENTER CODE HERE
	}

	/**
	 * Loads data from the shop database (stock and till)
	 * @exception IOException
	 *                thrown when file problems occur
	 */
	public void load() throws IOException {
		// ENTER CODE HERE
	}

	public static void main(String[] args) {
		// Don't touch any of this code
		Shop migginsPieShop = new Shop("Mrs Miggins Pie Shop");
		try {
			migginsPieShop.load();
		} catch (IOException e) {
			// Something went wrong so start a new shop
			System.err.println("Sorry but we were unable to load shop data: "
					+ e.getMessage());
		}

		migginsPieShop.runMenu();
		
		try {
			migginsPieShop.save();
		} catch (IOException e) {
			System.err
					.println("Sorry but we just lost everything. Unable to save shop data: "
							+ e.getMessage());
		}
	}

}
