public class Runner {

	public static void main(String[] args) {
		Setup.dialog();
		Randomizer.generate(Setup.settings.message_size * 2, Setup.settings.block_size, Setup.settings.padding_blocks);
	}
}
