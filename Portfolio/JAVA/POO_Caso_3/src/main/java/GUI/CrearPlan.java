package GUI;



import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class CrearPlan {
	private TextArea texto = new TextArea();

    public CrearPlan() {
        JFrame frame = new JFrame("CREAR CONTRATO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints posicion = new GridBagConstraints();
        panel.setBackground(Color.lightGray);

        JTextArea crearArea = texto.textoDelTextArea("CREAR PLAN", 1, 20);
        JTextArea planArea = texto.textoDelTextArea("NOMBRE DEL PLAN", 1, 20);
        JTextArea ideaArea = texto.textoDelTextArea("NOMBRE DEL DUEÑO DE LA IDEA", 1, 20);
        JTextArea plazoArea = texto.textoDelTextArea("PLAZO DE TIEMPO PARA EL PLAN", 1, 20);
        JTextArea planTerminArea = texto.textoDelTextArea("TODA LA INFORMACION DEL PLAN\nCOMO TIPO DE MAQUIANRA,"
        		+ "DE SER NECESARIA\nNOMBRES DE CUALQUIER PROVEEDOR Y DEMAS", 1, 20);
        JTextArea firmaAgriArea = texto.textoDelTextArea("F Agricultor", 1, 20);
        JTextArea firmaMediArea = texto.textoDelTextArea("F MEDIADOR", 1, 20);
        JTextArea firmaIdeaArea = texto.textoDelTextArea("F DEL DE LA IDEA", 1, 20);

        JButton crear = new JButton("CREAR");
        JButton cancelar = new JButton("CANCELAR");

        Insets espacio = new Insets(10, 10, 10, 10); 

        posicion.gridx = 0;
        posicion.gridy = 0;
        posicion.insets = espacio;
        posicion.anchor = GridBagConstraints.CENTER;
        panel.add(crearArea, posicion);

        posicion.gridy++;
        panel.add(planArea, posicion);

        posicion.gridy++;
        panel.add(ideaArea, posicion);

        posicion.gridy++;
        panel.add(ideaArea, posicion);

        posicion.gridy++;
        panel.add(plazoArea, posicion);

        posicion.gridy++;
        posicion.anchor = GridBagConstraints.EAST;
        panel.add(planTerminArea, posicion);

        posicion.gridy++;
        posicion.anchor = GridBagConstraints.CENTER;
        panel.add(firmaAgriArea, posicion);

        posicion.gridy++;
        posicion.anchor = GridBagConstraints.CENTER;
        panel.add(firmaMediArea, posicion);

        posicion.gridy++;
        posicion.anchor = GridBagConstraints.CENTER;
        panel.add(firmaIdeaArea, posicion);

        posicion.gridy++;
        posicion.anchor = GridBagConstraints.WEST;
        panel.add(crear, posicion);

        posicion.anchor = GridBagConstraints.EAST;
        panel.add(cancelar, posicion);
        
        crear.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        
        cancelar.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        
        
        frame.add(panel);
        frame.setVisible(true);
    }


}