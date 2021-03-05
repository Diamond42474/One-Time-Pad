import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class Statistics {
	private static String removeCharAt(String s, int index) {
		return s.substring(0, index).concat(s.substring(index + 1));
	}

	@SuppressWarnings("unused")
	@Deprecated
	private static void image_maker(String key, String name) {
		int w = (int) Math.sqrt(key.length());
		int h = (int) Math.sqrt(key.length());
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		for (int x = 0; x < h; x++) {
			for (int y = 0; y < h; y++) {
				int n = (int) ((double) (Integer.parseInt(key.charAt(0) + "")) / 10 * 255);
				key = removeCharAt(key, 0);
				// System.out.println(n);
				Color newColor = new Color(n, n, n);
				image.setRGB(x, y, newColor.getRGB());
			}
		}

		try {
			ImageIO.write(image, "jpg", new File(Setup.settings.dir + name + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void general() {
		int zero = 0, one = 0, two = 0, three = 0, four = 0, five = 0, six = 0, seven = 0, eight = 0, nine = 0;
		for (int i = 0; i < Setup.settings.key.length(); i++) {
			switch (Setup.settings.key.charAt(i)) {
			case '0':
				zero++;
				break;
			case '1':
				one++;
				break;
			case '2':
				two++;
				break;
			case '3':
				three++;
				break;
			case '4':
				four++;
				break;
			case '5':
				five++;
				break;
			case '6':
				six++;
				break;
			case '7':
				seven++;
				break;
			case '8':
				eight++;
				break;
			case '9':
				nine++;
				break;
			}
		}
		System.out.printf(
				"Key Stats:\n" + "0: %d - 1: %d - 2: %d - 3: %d - 4: %d - 5: %d - 6: %d - 7: %d - 8: %d - 9: %d \n",
				zero, one, two, three, four, five, six, seven, eight, nine);
		System.out.println("Key Length: " + Setup.settings.key.length());
	}

	public static void simpleDisplay() {
		System.out.printf("\nKey:\nSize: %d\nPadding: %d\nBlock Size: %d\n\n%s\n", Setup.settings.key.length(),
				Setup.settings.padding_blocks, Setup.settings.block_size, Processors.formatting(Setup.settings.key));
	}
}
