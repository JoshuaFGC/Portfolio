package Dispositivos;

//import java.lang.reflect.Array;

public class Triggers extends Clase_Padre{
    //Las funciones en los trigers reciben dos parametros
    // el estado de la luz o ventana (False = apagado/cerrado, True = encendido/abierto)
    // y si la casa tiene electricidad (En caso contrario se llama al generador para proveer electricidad)
    // cada función retorna una representación en texto de las acciones correspondientes

    // T es por "Trigger" y luego lo que va a cambiar, en este caso Luces
    public String TLuces(boolean estado){
        String texto;
        //Este if valida si electricidad es true
        if (isElectricidad()){
            //Este if valida el estado de la luz es encendido
            if (estado){
                texto = "Las luces se han apagado";
            }
            //En otro caso enciende las luces
            else{
                texto = "Las luces se han encendido";
            }
        } 
        //De otra forma """llama""" al generador 
        else {
            recuperarLuz();
            return TLuces(estado);
        }
        return texto;
    }

    // T es por "Trigger" y luego lo que va a cambiar, en este caso Ventanas
    public String TVentanas(boolean estado, boolean electricidad){
        String texto;
        //Este if valida si electricidad es true
        if (electricidad){
            //Este if valida el estado de la ventana es abierto
            if (estado){
                texto = "Las ventanas se han cerrado";
            }
            //En otro caso abre las ventanas
            else{
                texto = "Las ventanas se han abierto";
            }
        } 
        //De otra forma """llama""" al generador 
        else {
            System.out.println("No hay electricidad!!" + "\nSe ha activado el generador de la casa");
            return TVentanas(estado, electricidad = true);
        }
        return texto;
    }

    
}