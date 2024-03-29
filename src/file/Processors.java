package file;

import execute.Settings;

/**
 * 
 * @author Logan Miller
 *
 *         Methods for formatting and processing the keys and messages
 */
public class Processors {
	public static String formatting(String txt) {
		String out = "";
		int origional_key_length = txt.length();
		for (int i = 0; i < origional_key_length / Settings.block_size; i++) {
			out += txt.substring(0, Settings.block_size) + "	";
			txt = txt.substring(Settings.block_size);
		}
		return out;
	}

	public static String unformat(String txt) {
		return txt.replaceAll("\\s", "");
	}

	public static class Message {

	}
}
