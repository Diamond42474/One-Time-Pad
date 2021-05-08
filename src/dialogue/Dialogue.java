package dialogue;

import java.util.Scanner;
import execute.Encry_Decry;
import execute.Settings;
import execute.Statistics;
import file.Filer;
import file.Processors;
import random.CustomRandom;
import random.PingRandom;
import random.SystemRandom;

public class Dialogue {
	private static Scanner scan;

	/**
	 * Main starting point for the dialogue
	 * 
	 * Initializes the scanner then proceeds to setup the system preferences and
	 * runs the main program
	 */
	public static void Start() {
		scan = new Scanner(System.in);
		Setup.setStoragePreferences();
		main();
	}

	private static void main() {
		System.out.println(Settings.formatting.sep + "Which would you like to perform:\n" + "0: Generate Key\n"
				+ "1: Encrypt Message\n" + "2: Decrypt Message\n" + "3: Exit Program");
		int ans = Integer.parseInt(scan.nextLine());
		switch (ans) {
		case 0:
			Cryptography.setEncryptionMethod();
			Cryptography.generateKey();
			break;
		case 1:
			Cryptography.setEncryptionMethod();
			Cryptography.encryptMessage();
			break;
		case 2:
			Cryptography.decryptMessage();
			break;
		case 3:
			System.out.println("Goodbye!");
			System.exit(0);
		default:
			System.out.println("\nThe numbered you entered is not an option. Try again.\n");
			main();
			break;
		}
		main();
	}

	/**
	 * Handles all cryptographic functions
	 */
	private static class Cryptography {
		/**
		 * Sets the encryption algorithm to be used later in the program
		 */
		private static void setEncryptionMethod() {
			System.out.print(Settings.formatting.sep);
			System.out.println("Which encryption method would you like to use?\n0: System Random\n1: CustomRandom\n2: PingRandom");
			int ans = Integer.parseInt(scan.nextLine());
			switch (ans) {
			case 0:
				Settings.randomData = new SystemRandom();
				break;
			case 1:
				Settings.randomData = new CustomRandom();
				break;
			case 2:
				Settings.randomData = new PingRandom();
				break;
			default:
				System.out.println("\nThe numbered you entered is not an option. Try again.\n");
				main();
				break;
			}
		}

		/**
		 * Generates an encryption key and displays it to the console
		 */
		private static void generateKey() {
			System.out.print(Settings.formatting.sep);
			System.out.println("Message Size: ");
			Settings.message_size = Integer.parseInt(scan.nextLine()) * 2;
			System.out.println("Block Size: ");
			Settings.block_size = Integer.parseInt(scan.nextLine());
			System.out.println("Padding Ammount: ");
			Settings.padding_blocks = Integer.parseInt(scan.nextLine());
			System.out.printf("Estimated Time: %s\n", Settings.randomData.estimatedTime(Settings.message_size,
					Settings.block_size, Settings.padding_blocks, 5));
			Settings.randomData.generateKey(Settings.message_size, Settings.block_size, Settings.padding_blocks);

			Statistics.general();
			Statistics.keyAndMessageFormatted();
		}

		/**
		 * encrypts a given message and displays encrypted message to console
		 */
		private static void encryptMessage() {
			System.out.print(Settings.formatting.sep);
			System.out.println("Message: ");
			Settings.message = scan.nextLine();
			System.out.println(Settings.message);
			Settings.message_size = Settings.message.length() * 2;

			System.out.println("Block Size: ");
			Settings.block_size = Integer.parseInt(scan.nextLine());
			System.out.println("Padding Ammount: ");
			Settings.padding_blocks = Integer.parseInt(scan.nextLine());

			System.out.printf("Estimated Time: %s\n", Settings.randomData.estimatedTime(Settings.message_size,
					Settings.block_size, Settings.padding_blocks, 5));

			Settings.randomData.generateKey(Settings.message_size, Settings.block_size, Settings.padding_blocks);
			Encry_Decry.encrypt(Settings.key, Settings.message);
			if (Settings.preferences.store_data) {
				Filer.save.savePair();
				//Filer.save.message();
				//Filer.save.key();
			}
			Statistics.general();
			Statistics.keyAndMessageFormatted();
		}

		/**
		 * takes an encrypted message and key and returns the decrypted result
		 */
		private static void decryptMessage() {
			System.out.print(Settings.formatting.sep);
			System.out.println("Encrypted Message: ");
			Settings.enc_message = Processors.unformat(scan.nextLine());
			System.out.println("Key: ");
			Settings.key = Processors.unformat(scan.nextLine());
			Encry_Decry.decrypt(Settings.key, Settings.enc_message);
			if (Settings.preferences.store_data) {
				//Filer.save.txt_message();
			}
			//Statistics.keyAndMessageFormatted();
			Statistics.decryptedMessage();
		}
	}

	/**
	 * Responsible for taking care of all of the setup for user preferences
	 */
	private static class Setup {
		/**
		 * Sets all of the users preferences
		 */
		private static void setStoragePreferences() {
			System.out.println("Would you like to store data?\n0: yes\n1: no");
			int ans = Integer.parseInt(scan.nextLine());
			switch (ans) {
			case 0:
				Settings.preferences.store_data = true;
				setDirectories();
				break;
			case 1:
				Settings.preferences.store_data = false;
				break;
			default:
				System.out.println("That is not an option. Try again.");
				setStoragePreferences();
			}
		}

		/**
		 * Creates all of the necessary folders so that information can be saved and
		 * retrieved from a given directory
		 * 
		 * User also has the option to use a pre-defined directory
		 */
		private static void setDirectories() {
			System.out.print(Settings.formatting.sep);
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
					Settings.dir = di + "/";
					Filer.generate_folders();
				} else {
					System.out.println("Directory does not exist. Please try again");
					setDirectories();
				}
				break;
			default:
				System.out.println("That is not an option. Try again.");
				setDirectories();
			}
		}
	}

}
