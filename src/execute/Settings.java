package execute;

import random.RandomData;

/**
 * 
 * Class that controls all of the set up, settings, and dialogue
 */
public class Settings {
	public static int message_size = 0;
	public static int block_size = 0;
	public static int padding_blocks = 0;
	public static String key = "";
	public static String message = "";
	public static String enc_message = "";

	public static String dir = System.getProperty("user.home") + "/";

	public static RandomData randomData;

	public static class preferences {
		public static boolean store_data = false;
	}

	public static class formatting {
		public static final String sep = "\n########################################\n########################################\n\n";
	}
}
