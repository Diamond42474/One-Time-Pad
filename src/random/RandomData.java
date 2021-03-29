package random;

import execute.Setup;

public abstract class RandomData {
	public void generateKey(int message_size, int block_size, int padding_blocks) {
		while (message_size % block_size != 0) {
			message_size++;
		}
		String rawKey = "";
		int keySize = message_size + (padding_blocks * block_size);

		while (rawKey.length() < keySize) {
			rawKey += randomNumber();
			System.out.println("Processing: " + (int) ((double) (rawKey.length()) / keySize * 100) + "%");
		}
		if (rawKey.length() > keySize) {
			rawKey = rawKey.substring(rawKey.length() - keySize);
		}

		Setup.settings.key = rawKey;
	}

	protected long randomNumber() {
		return 0;
	}
}
