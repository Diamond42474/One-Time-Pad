import java.awt.MouseInfo;

public class Runner {

	public static void main(String[] args) throws InterruptedException {
		System.out.println(Randomizer.key(10));
		/*
		long time = 0;
		long x = 0;
		long y = 0;
		String test;
		long out = 0;
		Thread.sleep(1000);
	    for(int i = 0; i < 1000; i++) {
	    	time = System.currentTimeMillis();
	    	x = MouseInfo.getPointerInfo().getLocation().x;
	    	y = MouseInfo.getPointerInfo().getLocation().y;
	    	test = ((1+time)*(1+x)*(1+y))+"";
	    	test = test.substring(10);
	    	out =  (long) ((Integer.parseInt(test))*(Math.PI*1000000));
	    	System.out.println(out);
	    	Thread.sleep(100+((x/1919)*190));
	    }
	    */
	}

}
