package plannerControl;

import java.util.Calendar;
import java.util.Date;

public class Clock extends Thread {
	private static boolean running = true;
	private static Date currentTime;
	private static int tiempo = 5;
	private Scheduler scheduler;
	
	public Clock(Scheduler pScheduler) {
		scheduler = pScheduler;
	}
	
	public void stopTimer() {
		running = false;
	}
	
	public void run() {
		while (running) {
			try {
				currentTime = Calendar.getInstance().getTime();
				System.out.println(tiempo);
				scheduler.processTasks();
				Thread.sleep(5000);	// controlar la escala de tiempo
				tiempo++;
				if(tiempo == 24) {
					tiempo = 0;
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static int getSecondsToNow(Date pBaseTime) {
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pBaseTime);
        
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        
        // total seconds
        return (hours * 3600) + (minutes * 60) + seconds;		
	}
	
	public static int getTiempo() {
		return tiempo;
	}
	
	public static Date getTime() {
		return currentTime;
	}
}
