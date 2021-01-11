
public class Statistics {
	public static void run(String key) {
		int zero = 0, one = 0, two = 0, three = 0, four = 0, five = 0, six = 0, seven = 0, eight = 0, nine = 0;
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
		System.out.printf("Key Stats:\n"
				+ "0: %d - 1: %d - 2: %d - 3: %d - 4: %d - 5: %d - 6: %d - 7: %d - 8: %d - 9: %d \n",zero,one,two,three,four,five,six,seven,eight,nine);
		System.out.println("Key Length: "+key.length());
	}
}
