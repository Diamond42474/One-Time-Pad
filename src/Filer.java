import java.io.FileWriter;

public class Filer {
	public static void process(String key, int block_size) {
		Statistics.run(key);
		String out = "";
		int origional_key_length = key.length();
		for (int i = 0; i < origional_key_length / block_size; i++) {
			out += key.substring(0, block_size) + "	";
			key = key.substring(block_size);
		}
		System.out.println(out);
		write_file(out);
	}

	private static void write_file(String key) {
		try {
			FileWriter myWriter = new FileWriter(Setup.settings.dir + "key.txt");
			myWriter.write(key);
			myWriter.close();
		} catch (Exception e) {

		}
	}
}
