package execute;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Filer {
	
	/*
	public static void process(String key, int block_size) {
		//Statistics.run(key);
		String out = formatting(key);
		System.out.println(out);
		// write_file(out,"key");
	}
	*/
	/*
	public static String formatting(String key) {
		String out = "";
		int origional_key_length = key.length();
		for (int i = 0; i < origional_key_length / Setup.settings.block_size; i++) {
			out += key.substring(0, Setup.settings.block_size) + "	";
			key = key.substring(Setup.settings.block_size);
		}
		return out;
	}

	public static String unformat(String txt) {
		return txt.replaceAll("\\s", "");
	}
	*/
	public static void generate_folders() {
		File keys = new File(Setup.settings.dir + "keys");
		File messages = new File(Setup.settings.dir + "messages");
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

	static class read {
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
				Setup.settings.message = out;
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
				Setup.settings.enc_message = Processors.unformat(out);
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
				Setup.settings.key = Processors.unformat(out);
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
	static class save {
		public static void key() {
			boolean created = false;
			int num = 0;
			while (!created) {
				String name = "key";
				name += Integer.toString(num);
				File file = new File(Setup.settings.dir + "keys/" + name + ".txt");
				if (!file.exists()) {
					try {
						String txt = Processors.formatting(Setup.settings.key);
						FileWriter myWriter = new FileWriter(Setup.settings.dir + "keys/" + name + ".txt");
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
				File file = new File(Setup.settings.dir + "messages/" + name + ".txt");
				if (!file.exists()) {
					try {
						String txt = Processors.formatting(Setup.settings.enc_message);
						FileWriter myWriter = new FileWriter(Setup.settings.dir + "messages/" + name + ".txt");
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
				File file = new File(Setup.settings.dir + "messages/" + name + ".txt");
				if (!file.exists()) {
					try {
						FileWriter myWriter = new FileWriter(Setup.settings.dir + "messages/" + name + ".txt");
						myWriter.write(Setup.settings.message);
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
