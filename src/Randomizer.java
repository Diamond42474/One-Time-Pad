import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.security.SecureRandom;

public class Randomizer {
	private static SecureRandom secureRandom;
	static class settings {
		static int x_bounds = 1919;
		static final int min_time = 100;
		static final int max_time = 199;
	}
	public static void startup() {
		secureRandom = new SecureRandom();
	}
	public static void generate(int message_size, int block_size, int padding_blocks) {
		settings.x_bounds = Toolkit.getDefaultToolkit().getScreenSize().width;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (message_size % block_size != 0) {
			message_size++;
		}
		int key_size = message_size + (padding_blocks * block_size);
		String raw_key = key(key_size);
		Setup.settings.key = raw_key;
		//Filer.process(raw_key, block_size);
	}
	private static class Numbers{
		private static long Custom() {
			// long time = System.currentTimeMillis();
			int x = MouseInfo.getPointerInfo().getLocation().x;
			int y = MouseInfo.getPointerInfo().getLocation().y;
			try {
				Thread.sleep(settings.min_time + ((x / settings.x_bounds) * settings.max_time));
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long time = System.nanoTime();
			time = Long.parseLong(Long.toString(time).substring(6));
			// System.out.println("Time: "+time);
			String out = (x + y + time) + "";
			// System.out.println("Number: "+out);
			return (long) (Long.parseLong(out));
		}
		private static long BuiltInNumber() {
			return Math.abs(secureRandom.nextLong());
		}
	}

	private static String key(int size) {
		String key = "";
		while (key.length() < size) {
			key += Numbers.Custom();
			System.out.println("Processing: " + (int) ((double) (key.length()) / size * 100) + "%");
		}
		if (key.length() > size) {
			key = key.substring(key.length() - size);
		}
		return key;
	}
}
