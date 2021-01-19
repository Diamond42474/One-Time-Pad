import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Encry_Decry {
	private static HashMap<Integer, String> alphabet = new HashMap<Integer, String>();
	private static HashMap<String, Integer> ialphabet = new HashMap<String, Integer>();

	public static void Setup() {
		alphabet.put(1, "A");
		alphabet.put(2, "B");
		alphabet.put(3, "C");
		alphabet.put(4, "D");
		alphabet.put(5, "E");
		alphabet.put(6, "F");
		alphabet.put(7, "G");
		alphabet.put(8, "H");
		alphabet.put(9, "I");
		alphabet.put(10, "J");
		alphabet.put(11, "K");
		alphabet.put(12, "L");
		alphabet.put(13, "M");
		alphabet.put(14, "N");
		alphabet.put(15, "O");
		alphabet.put(16, "P");
		alphabet.put(17, "Q");
		alphabet.put(18, "R");
		alphabet.put(19, "S");
		alphabet.put(20, "T");
		alphabet.put(21, "U");
		alphabet.put(22, "V");
		alphabet.put(23, "W");
		alphabet.put(24, "X");
		alphabet.put(25, "Y");
		alphabet.put(26, "Z");
		alphabet.put(0, " ");
		alphabet.put(27, ".");
		alphabet.put(28, "?");
		alphabet.put(29, "!");
		alphabet.put(30, "0");
		alphabet.put(31, "1");
		alphabet.put(32, "2");
		alphabet.put(33, "3");
		alphabet.put(34, "4");
		alphabet.put(35, "5");
		alphabet.put(36, "6");
		alphabet.put(37, "7");
		alphabet.put(38, "8");
		alphabet.put(39, "9");
		alphabet.put(40, ",");
		List<Integer> keys = new ArrayList<>(alphabet.keySet());
		for (int i = 0; i < keys.size(); i++) {
			ialphabet.put(alphabet.get(keys.get(i)), keys.get(i));
		}
	}

	public static String encrypt(String key, String message) {
		String out = "";
		message = message.toUpperCase();
		while (message.length() / 2 < key.length()) {
			message = message + " ";
		}
		for (int i = 0; i < key.length() / 2; i++) {
			// System.out.println("Length: "+key.length());
			int charK = Integer.parseInt(key.substring(i * 2, (i * 2) + 2));
			int charM = ialphabet.get(message.charAt(i) + "");
			int add = charK + charM;
			out += toStringConv(add);
			// System.out.println("Charm: "+charM);
		}
		System.out.println("Message Length: " + out.length() + "\n" + Filer.formatting(out));
		return out;
	}

	public static String decrypt(String key, String message) {
		String out = "";
		for (int i = 0; i < key.length(); i += 2) {
			out += alphabet.get(
					subtract(Integer.parseInt(key.substring(i, i + 2)), Integer.parseInt(message.substring(i, i + 2))));
		}
		System.out.println(out);
		return null;
	}

	private static int subtract(int key, int message) {
		int out = message - key;
		if (out < 0) {
			out = (message + 100) - key;
		}
		return out;
	}

	private static String toStringConv(int num) {

		if (num < 10) {
			return "0" + Integer.toString(num);
		} else if (num > 100) {
			return toStringConv(num - 100);
		} else if (num == 100) {
			return "00";
		} else {
			return Integer.toString(num);
		}
	}
}
