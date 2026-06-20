package plannerControl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
	private String[] tasktime;
	private String taskname;
	private String taskSector;
	private boolean esControl;
	private boolean procesada;
	
	public Task(String pTasktime, String pTaskName, String pTaskSector, boolean pEsControl) {
		this.procesada = false;
		this.taskSector = pTaskSector;
		this.esControl = pEsControl;
		this.tasktime = pTasktime.split(":");
		this.taskname = pTaskName;
	}
	
	public int getTaskhour() {
		return Integer.parseInt(tasktime[0]);
	}
	
	public String getTasktime() {
		return tasktime[0] + ":" + tasktime[1];
	}
	public String getTaskSector() {
		return taskSector;
	}
	
	public String getTaskname() {
		return taskname;
	}
	
	public boolean isControl() {
		return esControl;
	}

	public boolean isProcesada() {
		return procesada;
	}

	public void setProcesada(boolean procesada) {
		this.procesada = procesada;
	}
}
