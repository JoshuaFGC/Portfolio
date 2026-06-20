package Dispositivos;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class Tester {
    public static void main(String[] args) throws Exception {
        // Tu JSON como una cadena
        String amanecer = "{\"nombre\":\"Amanecer\", \"hora\":6, \"dias\":[\"lunes\",\"martes\",\"miercoles\",\"jueves\",\"sabado\",\"domingo\"]}";
        String anochecer = "{\"nombre\":\"Anochecer\", \"hora\":18, \"dias\":[\"lunes\",\"martes\",\"miercoles\",\"jueves\",\"sabado\",\"domingo\"]}";
        
        // Crear un objeto Gson
        Gson gson = new Gson();

        // Parsear el JSON en un objeto JsonObject
        JsonObject ObjetoAmanecer = gson.fromJson(amanecer, JsonObject.class);
        JsonObject ObjetoAnochecer = gson.fromJson(anochecer, JsonObject.class);

        // Acceder a los atributos de amanecer
        String nombre = ObjetoAmanecer.get("nombre").getAsString();
        int hora = ObjetoAmanecer.get("hora").getAsInt();
        JsonArray diasArray = ObjetoAmanecer.get("dias").getAsJsonArray();

        // Ahora tienes los valores en las variables nombre, hora, diasArray
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + hora);
        System.out.println("Dias: " + diasArray);
    }
}

