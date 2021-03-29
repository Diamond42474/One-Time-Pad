package random;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SystemRandom extends RandomData {
	private SecureRandom rand;

	public SystemRandom() {
		new SecureRandom();
		try {
			rand = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected long randomNumber() {
		return Math.abs(rand.nextLong());
	}
}
