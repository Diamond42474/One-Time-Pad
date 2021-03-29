package random;

import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Toolkit;

public class CustomRandom extends RandomData {
	static int x_bounds = 1919;
	static final int min_time = 100;
	static final int max_time = 199;

	public CustomRandom() {
		x_bounds = Toolkit.getDefaultToolkit().getScreenSize().width;
	}

	@Override
	protected long randomNumber() {
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y = MouseInfo.getPointerInfo().getLocation().y;
		try {
			Thread.sleep(min_time + ((x / x_bounds) * max_time));
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time = System.nanoTime();
		String out = (x + y + time) + "";
		return (long) (Long.parseLong(out));
	}
}
