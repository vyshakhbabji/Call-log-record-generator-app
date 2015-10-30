package CallLogGenerator;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import platform.Platform;
import platform.Platform.Server;
import core.SDK;

public class Main extends TimerTask {

	public static HashMap<String, String> configMap = new HashMap<String, String>();

	static int j = -1;

	public static void main(String[] args) throws Exception {

		final long startTime = System.currentTimeMillis();
		System.out.println("Call-log generation started. Start Time :"
				+ startTime);
		Main main = new Main();

		Timer time = new Timer();
		time.schedule(main, 1, 3000);

		for (; j <= Integer.parseInt(configMap.get("app.counter"));) {
			TimeUnit.SECONDS.sleep(3);
			if (j == Integer.parseInt(configMap.get("app.counter"))) {

				System.out.println("\nApplication Terminates...");
				System.out.println("\nCall-log generated. End Time: "
						+ System.currentTimeMillis() + "");
				long elapsedTimeNs = System.currentTimeMillis() - startTime;
				System.out.println("\nTotal elapsed time of the app: "
						+ (elapsedTimeNs / 1000.0) + "");
				System.out.println("\nTotal records generated are: " + j);
				System.exit(0);
			}
		}
	}

	String callerID = "";
	String fromNumber = configMap.get("app.fromNumber");
	Platform platform;

	RingOut ringout;

	SDK sdk;
	String toNumber = configMap.get("app.toNumber");

	public Main() {
		Main.configMap = LoadData.loadData();
		this.sdk = new SDK(configMap.get("app.key"),
				configMap.get("app.secret"), Server.SANDBOX);
		this.platform = sdk.platform();
		platform.login(configMap.get("app.username"),
				configMap.get("app.extension"), configMap.get("app.password"));
		this.ringout = new RingOut(platform, configMap.get("app.fromNumber"),
				configMap.get("app.toNumber"), configMap.get("app.callerID"),
				Integer.parseInt(configMap.get("app.counter")));
	}

	@Override
	public void run() {
		j++;
		String ringOutId = null;
		try {
			TimeUnit.SECONDS.sleep(3);
			ringOutId = ringout.createRingOut();
			TimeUnit.SECONDS.sleep(3);
			ringout.deleteRingOut(ringOutId);
			TimeUnit.SECONDS.sleep(6);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
