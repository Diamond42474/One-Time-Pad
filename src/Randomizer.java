import java.awt.HeadlessException;
import java.awt.MouseInfo;

public class Randomizer {
	class settings{
		static final int x_bounds = 1919;
		static final int min_time = 100;
		static final int max_time = 199;
	}
	public static void generate(int message_size, int block_size, int padding_blocks) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(message_size%block_size!=0) {
			message_size++;
		}
		int key_size = message_size + (padding_blocks*block_size);
		String raw_key = key(key_size);
		Filer.process(raw_key,block_size);
	}
	private static long number() {
		//long time = System.currentTimeMillis();
    	int x = MouseInfo.getPointerInfo().getLocation().x;
    	int y = MouseInfo.getPointerInfo().getLocation().y;
    	try {
			Thread.sleep(settings.min_time+((x/settings.x_bounds)*settings.max_time));
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	long time = System.nanoTime();
    	time = Long.parseLong(Long.toString(time).substring(6));
    	//System.out.println("Time: "+time);
    	String out = ((1+time)*(1+x)*(1+y))+"";
    	//System.out.println("Number: "+out);
    	return  (long) (Long.parseLong(out));
	}
	private static String key(int size) {
		String key = "";
		while(key.length()<size) {
			key+=number();
			System.out.println("Processing: "+(int)((double)(key.length())/size*100)+"%");
		}
		if(key.length()>size) {
			key=key.substring(key.length()-size);
		}
		return key;
	}
}
