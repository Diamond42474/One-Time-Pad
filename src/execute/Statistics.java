package execute;

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
		int[] stats = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < Setup.settings.key.length(); i++) {
			char c = Setup.settings.key.charAt(i);
			switch (c) {
			case '0':
				stats[0]++;
				break;
			case '1':
				stats[1]++;
				break;
			case '2':
				stats[2]++;
				break;
			case '3':
				stats[3]++;
				break;
			case '4':
				stats[4]++;
				break;
			case '5':
				stats[5]++;
				break;
			case '6':
				stats[6]++;
				break;
			case '7':
				stats[7]++;
				break;
			case '8':
				stats[8]++;
				break;
			case '9':
				stats[9]++;
				break;
			}
		}
		int kz = Setup.settings.key.length();
		System.out.printf("0: %d\n1: %d\n2: %d\n3: %d\n4: %d\n5: %d\n6: %d\n7: %d\n8: %d\n9: %d\n", stats[0], stats[1],
				stats[2], stats[3], stats[4], stats[5], stats[6], stats[7], stats[8], stats[9]);
		System.out.print("\n");
		System.out.printf("0: %f\n1: %f\n2: %f\n3: %f\n4: %f\n5: %f\n6: %f\n7: %f\n8: %f\n9: %f\n",
				(float) stats[0] / kz * 100, (float) stats[1] / kz * 100, (float) stats[2] / kz * 100,
				(float) stats[3] / kz * 100, (float) stats[4] / kz * 100, (float) stats[5] / kz * 100,
				(float) stats[6] / kz * 100, (float) stats[7] / kz * 100, (float) stats[8] / kz * 100,
				(float) stats[9] / kz * 100);

		for (int i = 0; i < stats.length; i++) {
			System.out.print(i + ": ");
			for (int ie = 0; ie < (float) stats[i] / kz * 100; ie++) {
				System.out.print("#");
			}
			System.out.print("\n");
		}
		
		for(int i = 0;i<kz;) {
			for(int ie = 0;ie<100 && i<kz;ie++) {
				System.out.print(Setup.settings.key.charAt(i));
				i++;
			}
			System.out.print("\n");
		}
	}

	public static void simpleDisplay() {
		System.out.printf("\nKey:\nSize: %d\nPadding: %d\nBlock Size: %d\n\n%s\n", Setup.settings.key.length(),
				Setup.settings.padding_blocks, Setup.settings.block_size, Processors.formatting(Setup.settings.key));
	}
}
