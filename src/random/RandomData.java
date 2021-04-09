package random;

import execute.Settings;

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

		Settings.key = rawKey;
	}

	protected long randomNumber() {
		return 0;
	}

	public String estimatedTime(int message_size, int block_size, int padding_blocks, int estimationRounds) {
		while (message_size % block_size != 0) {
			message_size++;
		}
		String rawKey = "";
		int keySize = message_size + (padding_blocks * block_size);

		long start = System.currentTimeMillis();
		for (int i = 0; i < estimationRounds; i++) {
			rawKey += randomNumber();
		}
		long end = System.currentTimeMillis();
		long ellapsedTime = end - start;
		long ellapsedTimePerUnit = ellapsedTime / estimationRounds;

		double roundsNeeded = (double) keySize / ((double) rawKey.length() / estimationRounds);
		long time = (long) (ellapsedTimePerUnit * roundsNeeded);
		int seconds = (int) (time / 1000) % 60 ;
		int minutes = (int) ((time / (1000*60)) % 60);
		int hours   = (int) ((time / (1000*60*60)) % 24);
		return String.format("%dh:%dm:%ds",hours,minutes,seconds);
	}
}
