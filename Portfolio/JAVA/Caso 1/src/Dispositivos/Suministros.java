package Dispositivos;

public class Suministros extends Clase_Padre{
    //La función de suministros recibe como parámetro el nombre del suministro faltante
    // y retorna un string de representación sobre invocar más suministros
    public String Llamar_Suministros(String faltante){
        String respuesta;
        respuesta = "¡Oh no! Se ha agotado el suministro de " + faltante
        + "\nSe ha llamado a la compañía encargada para reabastecer " + faltante;
        return respuesta;
    }
}
