package plannerControl;

import java.io.FileReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

//https://mvnrepository.com/artifact/javax.json/javax.json-api/1.1.4

public class ScheduleParser {
	private JsonArray jsontasks;
	
	public ScheduleParser() {
		loadJson();
	}

	public ArrayList<Task> getTasks() {
		ArrayList<Task> result = new ArrayList<Task>();
		
        for (JsonValue taskjson : jsontasks) {
            JsonObject taskobject = (JsonObject) taskjson;
            
            Task task = new Task(taskobject.getString("time"), taskobject.getString("task"), taskobject.getString("sector"), taskobject.getBoolean("esControl"));
            result.add(task);
        }
        
        return result;
	}	
	
	private void loadJson() {
		String pathtofile = "C:\\Users\\Tony\\Desktop\\Archivos POO 2023\\Caso_1\\Caso_1_POO\\Caso_1\\src\\plannerControl\\schedule.json";
        
		try ( JsonReader reader = Json.createReader(new FileReader(pathtofile))) {
            JsonObject jsonparser = reader.readObject();
            
            jsontasks = jsonparser.getJsonArray("tareas");
            
            System.out.println("Loaded JSON: " + jsonparser);            
        } catch (Exception e) {
            e.printStackTrace();
        }		
	}
}
