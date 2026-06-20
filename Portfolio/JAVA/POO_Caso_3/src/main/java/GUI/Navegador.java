package GUI;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Navegador {
    private LineBorder borde = new LineBorder(Color.BLACK);
    private TextArea texto = new TextArea();
    
    public Navegador() {
        JFrame frame = new JFrame("NAVEGADOR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints posicion = new GridBagConstraints();

        JButton registrarse = new JButton("REGISTRARSE");

        JTextArea busquedaLabel = texto.textoDelTextArea("Barra de busqueda..", 1, 30);

        JLabel publicacionesLabel = new JLabel("Publicaciones recientes");
        
        Insets espacio = new Insets(5, 5, 5, 5);
        
        //Para publicaciones
        JPanel panelPublicaciones = new JPanel(new GridBagLayout());
        //Dimension en y grande porque si no para el ejemplo no me salia la scrollbar
        panelPublicaciones.setPreferredSize(new java.awt.Dimension(600, 800));
        
        //Contadores para mostrar todo en orden
        int x = 0;
        int y = 0;
        int cont = 0;
        // Simulación de publicaciones 
        for (int i = 1; i <= 9; i++) {//Puede poner más publicaciones si quiere
            //Verificacion para saber si ya puso 3 publicaciones en la misma fila
            if (cont==3) {
            	x = 0;
            	y ++;
            	cont = 0;
            	
            }
            
        	JTextPane publi = new JTextPane();
            publi.setEditable(false);
            publi.setFocusable(false);
            publi.setBorder(borde);
            
            posicion.gridx = x;
            posicion.gridy = y;
            posicion.insets = espacio;
            panelPublicaciones.add(publi, posicion);
            x++;
        	cont++;
        	
        	//Todo eso es para ponerle formato al texto
        	StyledDocument doc = publi.getStyledDocument();
        	
        	SimpleAttributeSet center = new SimpleAttributeSet();
        	StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        	doc.setParagraphAttributes(0, doc.getLength(), center, false);
        	 
        	//Puse el mismo texto para efectos del ejemplo
        	String text = "Post\nTitulo: Ahorro agua\nCon este plan podra\nahorrar agua\n"
        			+ "Precio: 100000\nContacto: 1234-5678";
        	
        	try {
        		doc.insertString(0,text, null);
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }

        

        posicion.gridx = 0;
        posicion.gridy = 0;
        posicion.insets = espacio;
        posicion.anchor = GridBagConstraints.EAST;
        panel.add(registrarse, posicion);

        posicion.gridx = 0;
        posicion.gridy = 1;
        posicion.insets = espacio;
        posicion.anchor = GridBagConstraints.CENTER;
        panel.add(busquedaLabel, posicion);

        posicion.gridx = 0;
        posicion.gridy = 2;
        posicion.insets = espacio;
        posicion.anchor = GridBagConstraints.WEST;
        panel.add(publicacionesLabel, posicion);

        //Agregar las publicaciones
        posicion.gridx = 0;
        posicion.gridy = 3;
        posicion.gridwidth = 1;
        posicion.fill = GridBagConstraints.BOTH;
        panel.add(panelPublicaciones, posicion);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//Para que la scroll se active cuando se necesite
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//Creo que no se ocupa scroll horizontal
        //scrollPane.getViewport().setPreferredSize(new java.awt.Dimension(700, 700)); //  Esto creo que no hace falta
        
        
        registrarse.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        
        
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }


}

