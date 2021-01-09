import java.io.FileWriter;

public class Filer {
	static class settings{
		final static String dir = System.getProperty("user.home") + "/Desktop/key.txt";
	}
	public static void process(String key, int block_size) {
		int zero = 0, one = 0, two = 0, three = 0, four = 0, five = 0, six = 0, seven = 0, eight = 0, nine = 0;
		int length = key.length();
		for(int i=0;i<key.length();i++) {
			switch(key.charAt(i)){
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
		System.out.printf("0: %d - 1: %d - 2: %d - 3: %d - 4: %d - 5: %d - 6: %d - 7: %d - 8: %d - 9: %d \n",zero,one,two,three,four,five,six,seven,eight,nine);
		String out = "";
		int origional_key_length = key.length();
		for(int i = 0; i < origional_key_length/block_size; i++) {
			out+=key.substring(0, block_size)+"	";
			key=key.substring(block_size);
		}
		System.out.println(out);
		write_file(out);
	}
	private static void write_file(String key) {
		try {
			FileWriter myWriter = new FileWriter(settings.dir);
		     myWriter.write(key);
		     myWriter.close();
		}catch(Exception e) {
			
		}
	}
}
