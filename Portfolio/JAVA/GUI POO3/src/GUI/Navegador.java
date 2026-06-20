package GUI;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import Model.Publicacion;
import Model.Usuario;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class Navegador extends JFrame {
    
    private TextArea texto = new TextArea();
    //Para publicaciones
    private JPanel panelPublicaciones = new JPanel(new GridBagLayout());
    private GridBagConstraints posicion = new GridBagConstraints();
    private Insets espacio = new Insets(5, 5, 5, 5);
    
    public Navegador(Usuario user) {
        JFrame frame = new JFrame("NAVEGADOR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());

        

        JButton registrarse = new JButton("REGISTRARSE");



        JLabel publicacionesLabel = new JLabel("Publicaciones recientes");
        
        
        
        
        //Dimension en y grande porque si no para el ejemplo no me salia la scrollbar
        panelPublicaciones.setPreferredSize(new java.awt.Dimension(600, 800));
        
        
        

        posicion.gridx = 0;
        posicion.gridy = 0;
        posicion.insets = espacio;
        posicion.anchor = GridBagConstraints.EAST;
        panel.add(registrarse, posicion);



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
        		//Se llama a la ventana de registro
        	}
        });
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void actualizar(List<Publicacion> publicaciones, Usuario user) {
    	panelPublicaciones.removeAll();
    	
        int x = 0; //Contador de posicion en x
        int y = 0; //Contador de posicion en y
        int cont = 0; //Contador de publicaciones por fila
        for (Publicacion pub : publicaciones) {
        	
        	if (cont==4) {
        		x = 0;
        		y++;
        		cont = 0;		
        	}
        	
        	JButton publi = new JButton("<html>"+pub.getTitulo()+"<br>"+pub.getAhorro()+"<br>"+pub.getLink()+"</html>");
        	publi.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     PostConcreto post = new PostConcreto(pub, user);
                     post.setVisible(true);
                 }
             });
        	
        	posicion.gridx = x;
        	posicion.gridy = y;
        	posicion.insets = espacio;
        	panelPublicaciones.add(publi, posicion);
        	x++;
        	cont++;
        }
        panelPublicaciones.revalidate();
        panelPublicaciones.repaint();
    	
    }

}
