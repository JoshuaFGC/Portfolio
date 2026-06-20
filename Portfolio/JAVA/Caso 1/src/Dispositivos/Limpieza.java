package Dispositivos;

public class Limpieza extends Clase_Padre{
    //Cuando la casa se ensucie, se llama la función de limpieza
    //En el parámetro se escribe qué habitación de la casa se ha ensuciado
    //La función retorna un representación en texto que se usa 
    //  en control para imprimir en el JFrame que invoca control
    public String limpiar(String habitación){
        String texto;
        texto = "Se ha llamado a la limpieza inteligente para limpiar el/la" + habitación;
        return texto;
    }

}


