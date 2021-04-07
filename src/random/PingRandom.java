package random;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingRandom extends RandomData {
	private String[] addresses = {"google.com","facebook.com","twitter.com","github.com","stackoverflow.com","amazon.com"};
	private final int TIMEOUT = 500;

	public PingRandom() {
		super();
	}

	@Override
	protected long randomNumber() {
		long output = 1;
		
		for(int i = 0; i<addresses.length;i++) {
			long pTime = Ping(addresses[i]);
			if(pTime>0) {
				output *= pTime;
				//System.out.println(pTime);
			}else {
				//System.out.println("Timeout");
			}
		}
		
		return Math.abs(output);
	}
	private long Ping(String ip) {
		InetAddress address = null;
		try {
			address = InetAddress.getByName(ip);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		long start = System.nanoTime();
		try {
			if(address.isReachable(this.TIMEOUT)) {
				long stop = System.nanoTime();
				long ellapsedTime = stop-start;
				return ellapsedTime;
			}else {
				System.out.println(ip+" timed out");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
