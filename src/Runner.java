import java.util.Scanner;

public class Runner {
	static int message_size = 100;
	static int block_size = 4;
	static int padding_blocks = 0;
	private static Scanner scan;
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		System.out.println("Message Size: ");
		message_size = Integer.parseInt(scan.nextLine());
		System.out.println("Block Size: ");
		block_size = Integer.parseInt(scan.nextLine());
		System.out.println("Padding Ammount: ");
		padding_blocks = Integer.parseInt(scan.nextLine());
		System.out.println("Press Enter to Generate Key: ");
		scan.nextLine();
		Randomizer.generate(message_size, block_size, padding_blocks);
	}

}
