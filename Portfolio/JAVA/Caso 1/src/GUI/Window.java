package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Patio.TaskPatio;
import Soporte.Action;
import Control.Main;

public class Window extends JFrame {
	
	// Lista de JPaneles:
	/*
	 0 = pnlCuartoPilas
	 1 = pnlBaños
	 2 = pnlCuartos
	 3 = pnlSala
	 4 = pnlComedor
	 5 = pnlCochera
	 6 = pnlCocina
	 7 = pnlSueloPatio
	 8 = pnlCesped
	 9 = pnlArbol
	 10 = pnlPlanta
	 */
	private JPanel[] paneles = new JPanel[11];
	
	//Lista de JLabels de Dispositivos
	/*
	 0 = lblLavadora
	 1 = lblTrapeador
	 2 = lblAspiradora
	 3 = lblMicroondas
	 4 = lblRefrigeradora
	 5 = lblGenerador
	 6 = lblModem
	 7 = lblTelefono
	 */
	private JLabel[] lbldispositivos = new JLabel[8];
	
	private JTextArea textArea;
	private JButton button2;
	
	private MatteBorder borde = new MatteBorder(2, 2, 2, 2, Color.BLACK);
	
	private void Colocar_label(JLabel pLabel, JPanel pPanel, int x, int y, int h, int w, boolean pOpacity, Color pFondo, int pTamaño, boolean pBorde) {
		pLabel.setFont(new Font("Arial", Font.BOLD, pTamaño)); // Set font and sizO
		pLabel.setForeground(Color.black); // Set text color
		pLabel.setBackground(pFondo); // Set text color
		pLabel.setHorizontalAlignment(SwingConstants.CENTER);
		if(pBorde == true) {pLabel.setBorder(borde);}
		pLabel.setOpaque(pOpacity);
		pLabel.setBounds(x, y, h ,w);
        if (pPanel != null) {pPanel.add(pLabel);}
	}
	
	private void Colocar_panel(JPanel pPanel, int x, int y, int h, int w, Color pFondo) {
		pPanel.setLayout(null);
		pPanel.setBackground(pFondo);
		pPanel.setBounds(x, y, h, w);
		pPanel.setBorder(borde);
        getContentPane().add(pPanel);
	}
	
	public void AsignarEstado(int pIndex, Color pColor, boolean pEsDispositivo, String pProblema) {
		if(pEsDispositivo == true) {
			lbldispositivos[pIndex].setBackground(pColor);
		}else {
			paneles[pIndex].setBackground(pColor);
		}
		textArea.setText(textArea.getText() + "\n" + pProblema);
	}
	
	public Window() {
		setTitle("Casa #1");
        setSize(780, 750); // Set the desired size
        setResizable(false); // Disable frame resizing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocation(50, 60);
        
        getContentPane().setBackground(Color.LIGHT_GRAY);
        getContentPane().setLayout(null);
        
        
        JLabel label = new JLabel("Casa 1");
        Colocar_label(label, null, 340, 10, 120, 30, true, Color.LIGHT_GRAY, 18, false);
        getContentPane().add(label);
        
        // Habitaciones ====================================================================
        
        // Genera panel y label de cuarto de pilas.
        
        paneles[0] = new JPanel();
        Colocar_panel(paneles[0], 260, 150, 250, 100, Main.color_casa);
        
        JLabel lblCuartoPilas = new JLabel("Cuarto de pilas");
        Colocar_label(lblCuartoPilas, paneles[0], 55, 35, 150, 30, false, Color.white, 18, false);
        
        // Genera panel y label de baños.
        
        paneles[1] = new JPanel();
        Colocar_panel(paneles[1], 520, 150, 80, 100, Main.color_casa);
        
        JLabel lblBaños = new JLabel("Baños");
        Colocar_label(lblBaños, paneles[1], 10, 35, 60, 30, false, Color.white, 18, false);
        
        // Genera panel y label de cuartos.
        
        paneles[2] = new JPanel();
        Colocar_panel(paneles[2], 610, 150, 150, 300, Main.color_casa);
        
        JLabel lblCuartos = new JLabel("Cuartos");
        Colocar_label(lblCuartos, paneles[2], 40, 130, 80, 30, false, Color.white, 18, false);
        
        // Genera panel y label de sala.
        
        paneles[3] = new JPanel();
        Colocar_panel(paneles[3], 400, 460, 360, 200, Main.color_casa);
        
        JLabel lblSala = new JLabel("Sala");
        Colocar_label(lblSala, paneles[3], 145, 85, 80, 30, false, Color.white, 18, false);
        
        // Genera panel y label de comedor.
        
        paneles[4] = new JPanel();
        Colocar_panel(paneles[4], 400, 260, 200, 190, Main.color_casa);
        
        JLabel lblComedor = new JLabel("Comedor");
        Colocar_label(lblComedor, paneles[4], 55, 75, 90, 30, false, Color.white, 18, false);
        
        // Genera panel y label de cochera.
        
        paneles[5] = new JPanel();
        Colocar_panel(paneles[5], 260, 410, 130, 220, Main.color_casa);
        
        JLabel lblCochera = new JLabel("Cochera");
        Colocar_label(lblCochera, paneles[5], 20, 105, 90, 30, false, Color.white, 18, false);
        
        // Genera panel y label de cocina.
        
        paneles[6] = new JPanel();
        Colocar_panel(paneles[6], 260, 260, 130, 140, Main.color_casa);
        
        JLabel lblCocina = new JLabel("Cocina");
        Colocar_label(lblCocina, paneles[6], 20, 55, 90, 30, false, Color.white, 18, false);
        
        // Patio ===========================================================================
        
        // Genera panel y label del suelo del patio.
        
        paneles[7] = new JPanel();
        Colocar_panel(paneles[7], 350, 40, 410, 100, Main.color_suelo);
        
        JLabel lblSueloPatio = new JLabel("Suelo");
        Colocar_label(lblSueloPatio, paneles[7], 160, 35, 90, 30, false, Color.white, 18, false);
        
        // Genera panel y label del cesped.
        
        paneles[8] = new JPanel();
        Colocar_panel(paneles[8], 240, 40, 100, 100, Main.color_verde);
        
        JLabel lblCesped = new JLabel("Cesped");
        Colocar_label(lblCesped, paneles[8], 15, 35, 70, 30, false, Color.white, 18, false);
        
        // Genera panel y label del arbol.
        
        paneles[9] = new JPanel();
        Colocar_panel(paneles[9], 130, 40, 100, 100, Main.color_verde);
        
        JLabel lblArbol = new JLabel("Árbol");
        Colocar_label(lblArbol, paneles[9], 15, 35, 70, 30, false, Color.white, 18, false);
        
        // Genera panel y label del planta.
        
        paneles[10] = new JPanel();
        Colocar_panel(paneles[10], 20, 40, 100, 100, Main.color_verde);
        
        JLabel lblPlanta = new JLabel("Planta");
        Colocar_label(lblPlanta, paneles[10], 15, 35, 70, 30, false, Color.white, 18, false);
        
        // Dispositivos ===================================================================
        
        lbldispositivos[0] = new JLabel("Lavadora");
        Colocar_label(lbldispositivos[0], paneles[0], 10, 15, 65, 25, true, Color.white, 12, true);

        lbldispositivos[1] = new JLabel("Trapeador");
        Colocar_label(lbldispositivos[1], paneles[0], 10, 60, 65, 25, true, Color.white, 12, true);
        
        lbldispositivos[2] = new JLabel("Aspiradora");
        Colocar_label(lbldispositivos[2], paneles[0], 170, 15, 70, 25, true, Color.white, 12, true);

        lbldispositivos[3] = new JLabel("Microondas");
        Colocar_label(lbldispositivos[3], paneles[6], 10, 15, 75, 25, true, Color.white, 12, true);
        
        lbldispositivos[4] = new JLabel("Refrigeradora");
        Colocar_label(lbldispositivos[4], paneles[6], 10, 105, 90, 25, true, Color.white, 12, true);
        
        lbldispositivos[5] = new JLabel("Generador");
        Colocar_label(lbldispositivos[5], paneles[5], 10, 15, 75, 25, true, Color.white, 12, true);
        
        lbldispositivos[6] = new JLabel("Modem");
        Colocar_label(lbldispositivos[6], paneles[3], 10, 15, 75, 25, true, Color.white, 12, true);
        
        lbldispositivos[7] = new JLabel("Teléfono");
        Colocar_label(lbldispositivos[7], paneles[3], 10, 160, 75, 25, true, Color.white, 12, true);
        
        // Display del estado de la casa
        
        JLabel label2 = new JLabel("Estado");
        Colocar_label(label2, null, 10, 160, 75, 25, true, Color.white, 12, true);

        label2.setBounds(5, 150, 250, 25);
        getContentPane().add(label2);
        
        
        textArea = new JTextArea();
        textArea.setLineWrap(true); // Enable line wrapping
        textArea.setWrapStyleWord(true); // Wrap at word boundaries
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBorder(borde);

        Rectangle textAreaBounds = new Rectangle(5, 180, 250, 480);
        textArea.setBounds(textAreaBounds);
        
        // Add text area to the frame's content pane
        getContentPane().add(textArea);
        
        setLocationRelativeTo(null);
    }
	
}
