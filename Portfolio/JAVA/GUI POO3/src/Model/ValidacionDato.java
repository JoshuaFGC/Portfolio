package Model;

import javax.swing.JTextArea; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionDato {
    private String textoRecuperado;

    // Verifica si el texto del JTextArea no está vacío
    public boolean noEstaVacio(JTextArea texto) {
        textoRecuperado = texto.getText();
        return !textoRecuperado.isEmpty();
    }

    // Verifica si el texto del JTextArea puede convertirse a un número entero
    public boolean esNumeroEntero(JTextArea texto) {
    	textoRecuperado = texto.getText();
        try {
            int numero = Integer.parseInt(textoRecuperado);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // Verifica si el texto del JTextArea tiene el formato de un correo electrónico
    public boolean esCorreoElectronico(JTextArea texto) {
        textoRecuperado = texto.getText();
        // Patrón para validar un correo electrónico básico
        String patronCorreoElectronico = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(patronCorreoElectronico);
        Matcher matcher = pattern.matcher(textoRecuperado);
        return matcher.matches();
    }
}