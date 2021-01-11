import java.util.Scanner;

public class Runner {
	static int message_size = 0;
	static int block_size = 0;
	static int padding_blocks = 0;
	private static Scanner scan;
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		//System.out.println("Message Size: ");
		//message_size = Integer.parseInt(scan.nextLine());
		System.out.println("Message: ");
		message_size = scan.nextLine().length()*2;
		System.out.println("Block Size: ");
		block_size = Integer.parseInt(scan.nextLine());
		System.out.println("Padding Ammount: ");
		padding_blocks = Integer.parseInt(scan.nextLine());
		System.out.println("Press Enter & Start Moving The Mouse: ");
		scan.nextLine();
		Randomizer.generate(message_size*2, block_size, padding_blocks);
	}

}
