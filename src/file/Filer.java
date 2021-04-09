package file;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import execute.Settings;

public class Filer {
	public static void generate_folders() {
		File keys = new File(Settings.dir + "keys");
		File messages = new File(Settings.dir + "messages");
		if (!keys.exists()) {
			keys.mkdir();
		}
		if (!messages.exists()) {
			messages.mkdir();
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
				System.out.println(out);
				Settings.message = out;
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}

		public static void message(String dir) {
			String out = "";
			try {
				File file = new File(dir);
				Scanner myReader = new Scanner(file);
				while (myReader.hasNextLine()) {
					out += myReader.nextLine();
				}
				myReader.close();
				System.out.println(out);
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
				System.out.println(out);
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
		public static void key() {
			boolean created = false;
			int num = 0;
			while (!created) {
				String name = "key";
				name += Integer.toString(num);
				File file = new File(Settings.dir + "keys/" + name + ".txt");
				if (!file.exists()) {
					try {
						String txt = Processors.formatting(Settings.key);
						FileWriter myWriter = new FileWriter(Settings.dir + "keys/" + name + ".txt");
						myWriter.write(txt);
						myWriter.close();
						created = true;
						break;
					} catch (Exception e) {

					}
				}
				num++;
			}
		}

		public static void message() {
			boolean created = false;
			int num = 0;
			while (!created) {
				String name = "message";
				name += Integer.toString(num);
				File file = new File(Settings.dir + "messages/" + name + ".txt");
				if (!file.exists()) {
					try {
						String txt = Processors.formatting(Settings.enc_message);
						FileWriter myWriter = new FileWriter(Settings.dir + "messages/" + name + ".txt");
						myWriter.write(txt);
						myWriter.close();
						created = true;
						break;
					} catch (Exception e) {

					}
				}
				num++;
			}
		}

		public static void txt_message() {
			boolean created = false;
			int num = 0;
			while (!created) {
				String name = "message";
				name += Integer.toString(num);
				File file = new File(Settings.dir + "messages/" + name + ".txt");
				if (!file.exists()) {
					try {
						FileWriter myWriter = new FileWriter(Settings.dir + "messages/" + name + ".txt");
						myWriter.write(Settings.message);
						myWriter.close();
						created = true;
						break;
					} catch (Exception e) {

					}
				}
				num++;
			}
		}
	}
}
