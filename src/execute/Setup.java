package execute;

import java.util.Scanner;

import file.Filer;
import file.Processors;
import random.CustomRandom;
import random.PingRandom;
import random.RandomData;
import random.SystemRandom;

/**
 * 
 * Class that controls all of the set up, settings, and dialogue
 */
public class Setup {
	/**
	 * 
	 * Settings and main data for encryption and decryption
	 */
	public static class settings {
		public static int message_size = 0;
		public static int block_size = 0;
		public static int padding_blocks = 0;
		public static String key = "";
		public static String message = "";
		public static String enc_message = "";

		public static String dir = System.getProperty("user.home") + "/Desktop/";

		public static RandomData randomData;

		public static class preferences {
			public static boolean store_data = false;
		}

		public static class formatting {
			public static final String sep = "\n##########\n\n";
		}
	}

	private static Scanner scan;

	/**
	 * The main controller for all of the dialogue
	 */
	public static void dialog() {
		scan = new Scanner(System.in);
		set_up();
		main();
	}

	/**
	 * central loop to work with encryption
	 */
	private static void main() {
		System.out.println(settings.formatting.sep
				+ "Which would you like to perform:\n0: Generate Key\n1: Encrypt Message\n2: Decrypt Message");
		int ans = Integer.parseInt(scan.nextLine());
		switch (ans) {
		case 0:
			setEncryptionMethod();
			generate();
			break;
		case 1:
			setEncryptionMethod();
			encrypt();
			break;
		case 2:
			decrypt();
			break;
		default:
			System.out.println("\nThe numbered you entered is not an option. Try again.\n");
			main();
			break;
		}
		looper();
	}

	private static void setEncryptionMethod() {
		System.out.println(settings.formatting.sep
				+ "Which encryption method would you like to use?\n0: System Random\n1: CustomRandom\n2: PingRandom");
		int ans = Integer.parseInt(scan.nextLine());
		switch (ans) {
		case 0:
			settings.randomData = new SystemRandom();
			break;
		case 1:
			settings.randomData = new CustomRandom();
			break;
		case 2:
			settings.randomData = new PingRandom();
			break;
		default:
			System.out.println("\nThe numbered you entered is not an option. Try again.\n");
			main();
			break;
		}
	}

	/**
	 * Makes decision on if the program should continue running or not
	 */
	private static void looper() {
		System.out.println("\n0: Exit\n1: Continue");
		int ans = Integer.parseInt(scan.nextLine());
		switch (ans) {
		case 0:
			System.exit(0);
			break;
		case 1:
			main();
			break;
		default:
			System.out.println("That is not an option. Please try again.");
			looper();
		}
	}

	/**
	 * Takes input data from the user and sets up the directory if the user decides
	 * to store data
	 */
	private static void set_up() {
		System.out.println("Would you like to store data?\n0: yes\n1: no");
		int ans = Integer.parseInt(scan.nextLine());
		switch (ans) {
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

	/**
	 * Creates files for storing data
	 */
	private static void directory_setup() {
		System.out.println("Would you like to use default directory or custom?\n0: Default\n1: Custom");
		int ans = Integer.parseInt(scan.nextLine());
		switch (ans) {
		case 0:
			Filer.generate_folders();
			break;
		case 1:
			System.out.println("Enter your working directory");
			String di = scan.nextLine();
			if (Filer.directory_exists(di)) {
				Setup.settings.dir = di + "/";
				Filer.generate_folders();
			} else {
				System.out.println("Directory is not correct. Please try again");
				directory_setup();
			}
			break;
		default:
			System.out.println("That is not an option. Try again.");
			directory_setup();
		}
	}

	/**
	 * Goes through the process of generating a key based off of user
	 * specifications.
	 */
	private static void generate() {
		System.out.println("Message Size: ");
		Setup.settings.message_size = Integer.parseInt(scan.nextLine()) * 2;
		System.out.println("Block Size: ");
		Setup.settings.block_size = Integer.parseInt(scan.nextLine());
		System.out.println("Padding Ammount: ");
		Setup.settings.padding_blocks = Integer.parseInt(scan.nextLine());

		// settings.randomData.generateKey(Setup.settings.message_size,
		// Setup.settings.block_size, Setup.settings.padding_blocks);
		System.out.printf("Estimated Time: %s\n", settings.randomData.estimatedTime(Setup.settings.message_size,
				Setup.settings.block_size, Setup.settings.padding_blocks, 5));
		settings.randomData.generateKey(Setup.settings.message_size, Setup.settings.block_size,
				Setup.settings.padding_blocks);
		Statistics.general();
		Statistics.keyAndMessageFormatted();
	}

	/**
	 * Goes through the process of generating a key and encrypting a message based
	 * off of user input.
	 */
	private static void encrypt() {
		System.out.println("Message: ");
		Setup.settings.message = scan.nextLine();
		Setup.settings.message_size = Setup.settings.message.length() * 2;

		System.out.println("Block Size: ");
		Setup.settings.block_size = Integer.parseInt(scan.nextLine());
		System.out.println("Padding Ammount: ");
		Setup.settings.padding_blocks = Integer.parseInt(scan.nextLine());

		System.out.printf("Estimated Time: %s\n", settings.randomData.estimatedTime(Setup.settings.message_size,
				Setup.settings.block_size, Setup.settings.padding_blocks, 5));
		
		settings.randomData.generateKey(Setup.settings.message_size, Setup.settings.block_size,
				Setup.settings.padding_blocks);
		Encry_Decry.encrypt(Setup.settings.key, Setup.settings.message);
		if (settings.preferences.store_data) {
			Filer.save.message();
			Filer.save.key();
		}
		Statistics.general();
		Statistics.keyAndMessageFormatted();
	}

	/**
	 * Decrypts message using user input (needs to be upgraded to read from a file)
	 */
	private static void decrypt() {
		System.out.println("Encrypted Message: ");
		Setup.settings.enc_message = Processors.unformat(scan.nextLine());
		System.out.println("Key: ");
		Setup.settings.key = Processors.unformat(scan.nextLine());
		Encry_Decry.decrypt(Setup.settings.key, Setup.settings.enc_message);
		if (settings.preferences.store_data) {
			Filer.save.txt_message();
		}
		Statistics.keyAndMessageFormatted();
		Statistics.decryptedMessage();
	}
}
