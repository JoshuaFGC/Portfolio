package Horarios;

import java.awt.Desktop.Action;
import java.util.ArrayList;

import plannerControl.Task;
import plannerControl.Clock;
import Control.Main;
import Soporte.Util;

public abstract class Sector {
	public ArrayList<String> tasks;
	
	public Sector() {
		tasks = new ArrayList<String>();
		loadMyTasks();
	}
	
	public void process(Task pTask) {
		
		for(String task : tasks) {
			if (task.compareTo(pTask.getTaskname())==0) {
				if(!pTask.isProcesada()) {
					if(pTask.getTaskhour()<=Clock.getTiempo()) 
					{
						pTask.setProcesada(true);
						Util.setTime(pTask.getTasktime());
						Main.doTask(pTask.getTaskname(), pTask.getTaskSector(), pTask.isControl());
						break;
					}
				}
			}
		}

	}
	
	protected abstract void loadMyTasks();
}
