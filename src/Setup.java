import java.util.Scanner;

public class Setup {
	static class settings{
		static int message_size = 0;
		static int block_size = 0;
		static int padding_blocks = 0;
		static String key = "";
		
		static String dir = System.getProperty("user.home");
	}
	private static Scanner scan;
	public static void dialog() {
		scan = new Scanner(System.in);
		start();
	}
	private static void start() {
		System.out.println("Which would you like to perform:\n0: Generate Key\n1: Encrypt Message\n2: Decrypt Message");
		int ans =  Integer.parseInt(scan.nextLine());
		switch(ans){
		case 0:
			generate();
			break;
		case 1:
			encrypt();
			break;
		case 2:
			decrypt();
			break;
		default:
			System.out.println("\nThe numbered you entered is not an option. Try again.\n");
			start();
			break;
		}
	}
	private static void generate() {
		
	}
	private static void encrypt() {
		System.out.println("Message: ");
		Setup.settings.message_size = scan.nextLine().length()*2;
		System.out.println("Block Size: ");
		Setup.settings.block_size = Integer.parseInt(scan.nextLine());
		System.out.println("Padding Ammount: ");
		Setup.settings.padding_blocks =  Integer.parseInt(scan.nextLine());
		System.out.println("Press Enter & Start Moving The Mouse: ");
		 scan.nextLine();
	}
	private static void decrypt() {
		
	}
}
