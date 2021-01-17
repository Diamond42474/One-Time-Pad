import java.io.FileWriter;

public class Filer {
	public static void process(String key, int block_size) {
		Statistics.run(key);
		String out = formatting(key);
		System.out.println(out);
		write_file(out,"key");
	}
	public static String formatting(String key) {
		String out = "";
		int origional_key_length = key.length();
		for (int i = 0; i < origional_key_length / Setup.settings.block_size; i++) {
			out += key.substring(0, Setup.settings.block_size) + "	";
			key = key.substring(Setup.settings.block_size);
		}
		return out;
	}
	public static void write_file(String txt, String name) {
		try {
			FileWriter myWriter = new FileWriter(Setup.settings.dir + name+".txt");
			myWriter.write(txt);
			myWriter.close();
		} catch (Exception e) {

		}
	}
}
