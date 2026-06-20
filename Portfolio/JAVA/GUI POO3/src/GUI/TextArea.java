package GUI;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class TextArea {
	
	private LineBorder borde = new LineBorder(Color.BLACK);
	private Color colorTextoGris = Color.GRAY; // Color de texto gris
    
    public JTextArea textoDelTextArea(String pDato, int row, int column) {
    	// Crea el JTextArea con el texto y el tamaño deseados
        JTextArea textArea = new JTextArea(pDato, row, column);
        
        // Establece el color de texto gris cuando está vacío
        textArea.setForeground(colorTextoGris);
        
        // Para que haga algo cuando se selecciona
        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            	// Para quitar texto y cambiar el color del texto al negro
                if (textArea.getText().equals(pDato)) {
                    textArea.setText("");
                    textArea.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
            	// Para poner el texto anterior y cambiar el color del texto a gris si está vacío
                if (textArea.getText().isEmpty()) {
                    textArea.setText(pDato);
                    textArea.setForeground(colorTextoGris);
                }
            }
        });
        
        textArea.setBorder(borde);
        return textArea;
    }
}

