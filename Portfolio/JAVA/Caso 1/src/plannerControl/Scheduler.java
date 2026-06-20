package plannerControl;

import java.util.ArrayList;

import Horarios.*;

public class Scheduler {
	
	private ArrayList<Task> tasks;
	private ArrayList<Sector> sectores;
	
	public Scheduler() {
		
		sectores = new ArrayList<Sector>();
		
		SectorControl sc = new SectorControl();
		sectores.add(sc);
		
		ScheduleParser parser = new ScheduleParser();
		tasks = parser.getTasks();
	}
	
	public void processTasks() {
		for(Task task: tasks) {
			for(Sector sector: sectores) {
				sector.process(task);
			}			
		}
	}
}
