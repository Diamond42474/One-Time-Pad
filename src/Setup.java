import java.util.Scanner;

public class Setup {
	static class settings {
		static int message_size = 0;
		static int block_size = 0;
		static int padding_blocks = 0;
		static String key = "";
		static String message = "";
		static String enc_message = "";

		static String dir = System.getProperty("user.home") + "/Desktop/";
		
		static class preferences{
			static boolean store_data = false;
		}
	}

	private static Scanner scan;

	public static void dialog() {
		scan = new Scanner(System.in);
		set_up();
		start();
	}

	/**
	 * Where the dialogue starts
	 */
	private static void start() {
		System.out.println("Which would you like to perform:\n0: Generate Key\n1: Encrypt Message\n2: Decrypt Message");
		int ans = Integer.parseInt(scan.nextLine());
		switch (ans) {
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
	private static void set_up() {
		System.out.println("Would you like to store data?\n0: yes\n1: no");
		int ans = Integer.parseInt(scan.nextLine());
		switch(ans) {
		case 0:
			settings.preferences.store_data = true;
			directory_setup();
			break;
		case 1:
			settings.preferences.store_data = false;
			break;
		default:
			System.out.println("That is not an option. Try again.");
			set_up();
		}
	}
	private static void directory_setup() {
		System.out.println("Would you like to use default directory or custom?\n0: Default\n1: Custom");
		int ans = Integer.parseInt(scan.nextLine());
		switch(ans) {
		case 0:
			Filer.generate_folders();
			break;
		case 1:
			System.out.println("Enter your working directory");
			String di = scan.nextLine();
			if(Filer.directory_exists(di)) {
				Setup.settings.dir = di+"/";
				Filer.generate_folders();
			}else {
				System.out.println("Directory is not correct. Please try again");
				directory_setup();
			}
			break;
		default:
			System.out.println("That is not an option. Try again.");
			directory_setup();
		}
	}
	private static void generate() {
		System.out.println("Message Size: ");
		Setup.settings.message_size = Integer.parseInt(scan.nextLine()) * 2;
		System.out.println("Block Size: ");
		Setup.settings.block_size = Integer.parseInt(scan.nextLine());
		System.out.println("Padding Ammount: ");
		Setup.settings.padding_blocks = Integer.parseInt(scan.nextLine()) * 2;
		System.out.println("Press Enter & Start Moving The Mouse: ");
		scan.nextLine();
		Randomizer.generate(Setup.settings.message_size, Setup.settings.block_size, Setup.settings.padding_blocks);
	}

	private static void encrypt() {
		System.out.println("Message: ");
		Setup.settings.message = scan.nextLine();
		Setup.settings.message_size = Setup.settings.message.length() * 2;

		System.out.println("Block Size: ");
		Setup.settings.block_size = Integer.parseInt(scan.nextLine());
		System.out.println("Padding Ammount: ");
		Setup.settings.padding_blocks = Integer.parseInt(scan.nextLine());
		System.out.println("Press Enter & Start Moving The Mouse: ");
		scan.nextLine();
		Randomizer.generate(Setup.settings.message_size, Setup.settings.block_size, Setup.settings.padding_blocks);
		Encry_Decry.encrypt(Setup.settings.key, Setup.settings.message);
		Filer.save.message();
		Filer.save.key();
	}

	private static void decrypt() {
		System.out.println("Encrypted Message: ");
		Setup.settings.message = scan.nextLine().replaceAll("\\s", "");
		System.out.println("Key: ");
		Setup.settings.key = scan.nextLine().replaceAll("\\s", "");
		Encry_Decry.decrypt(Setup.settings.key, Setup.settings.enc_message);
	}
}
