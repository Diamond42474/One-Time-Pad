package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import execute.Settings;

public class Filer {
	public static void generate_folders() {
		File main = new File(Settings.dir + "OTP/");
		if (!main.exists()) {
			main.mkdir();
		}
		Settings.dir = Settings.dir + "OTP/";
		File keys = new File(Settings.dir + "keys");
		File messages = new File(Settings.dir + "messages");
		File output = new File(Settings.dir + "output");
		if (!keys.exists()) {
			keys.mkdir();
		}
		if (!messages.exists()) {
			messages.mkdir();
		}
		if (!output.exists()) {
			output.mkdir();
		}
	}

	public static boolean directory_exists(String dir) {
		File file = new File(dir);
		return file.exists();
	}

	public static class read {
		public static void txt_message(String dir) {
			String out = "";
			try {
				File file = new File(dir);
				Scanner myReader = new Scanner(file);
				while (myReader.hasNextLine()) {
					out += myReader.nextLine();
				}
				myReader.close();
				Settings.message = out;
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}

		public static void enc_message(String dir) {
			String out = "";
			try {
				File file = new File(dir);
				Scanner myReader = new Scanner(file);
				while (myReader.hasNextLine()) {
					out += myReader.nextLine();
				}
				myReader.close();
				//System.out.println(out);
				//System.out.println("Message Size: "+out.length());
				Settings.enc_message = Processors.unformat(out);
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}

		public static void key(String dir) {
			String out = "";
			try {
				File file = new File(dir);
				Scanner myReader = new Scanner(file);
				while (myReader.hasNextLine()) {
					out += myReader.nextLine();
				}
				myReader.close();
				//System.out.println(out);
				//System.out.println("Message Size: "+out.length());
				Settings.key = Processors.unformat(out);
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * Contains all of the methods that save files
	 *
	 */
	public static class save {
		public static void savePair() {
			String fileName = getTitleID();
			key(fileName);
			message(fileName);

		}

		private static String getTitleID() {
			File keyFolder = new File(Settings.dir + "keys/");
			String[] keyNames = keyFolder.list();
			File txtFolder = new File(Settings.dir + "messages/");
			String[] txtNames = txtFolder.list();

			int x = 0;
			String name = formatTitle(x);
			while (listContainsString(keyNames, name) || listContainsString(txtNames, name)) {
				x++;
				name = formatTitle(x);
			}
			return name;
		}
		private static String formatTitle(int input) {
			DecimalFormat formatter = new DecimalFormat("000000");
			String output = formatter.format(input);
			StringBuilder stringBuilder = new StringBuilder(output);
	        stringBuilder.insert(3, '-');
			return stringBuilder.toString();
		}
		private static boolean listContainsString(String[] list, String str) {
			for (int i = 0; i < list.length; i++) {
				if (list[i].equals(str + ".txt")) {
					return true;
				}
			}
			return false;
		}

		private static void key(String name) {
			try {
				String txt = Processors.formatting(Settings.key);
				FileWriter myWriter = new FileWriter(Settings.dir + "keys/" + name + ".txt");
				myWriter.write(txt);
				myWriter.close();
			} catch (Exception e) {

			}
		}

		private static void message(String name) {
			try {
				String txt = Processors.formatting(Settings.enc_message);
				FileWriter myWriter = new FileWriter(Settings.dir + "messages/" + name + ".txt");
				myWriter.write(txt);
				myWriter.close();
			} catch (Exception e) {

			}

		}

		public static void txt_message(String name) {
			try {
				FileWriter myWriter = new FileWriter(Settings.dir + "output/" + name + ".txt");
				myWriter.write(Settings.message);
				myWriter.close();
			} catch (Exception e) {

			}
		}
	}
}
