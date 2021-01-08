public class Runner {
	static final int message_size = 100*2;
	static final int block_size = 4;
	static final int padding_blocks = 20;
	public static void main(String[] args) {
		Randomizer.generate(message_size, block_size, padding_blocks);
	}

}
