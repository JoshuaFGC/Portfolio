package UI;

import devices.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class UIDevices {
	private JFrame frame;
	private JPanel panel;

	
	
	private JLabel TV;
	private JLabel smartPhone;
	private JLabel laptop;
	private JLabel tablet;
	private JLabel estetica;

    private JLabel VolumenTV;
    private JLabel VolumenSmart;
    private JLabel VolumenLaptop;
    private JLabel VolumenTablet;
    
	private JComboBox<String> devices;
	
	
	private JButton cargar;

	
	private Tv TVdisp = new Tv();
	private Smartphone SmartDisp = new Smartphone();
	private Laptop LapDisp = new Laptop();
	private Tablet TabDisp = new Tablet();
	private JButton bajarVolumen;
	private JButton subirVolumen;
	
	public UIDevices() {
		frame = new JFrame("Alexa");
		panel = new JPanel();
		
		subirVolumen = new JButton(" + ");
		subirVolumen.setBorder(new LineBorder(Color.BLACK));
		
        bajarVolumen = new JButton(" - ");
        bajarVolumen.setBorder(new LineBorder(Color.BLACK));
		
        VolumenTV = new JLabel("Vol TV: " + TVdisp.getVolume());
        VolumenTV.setBorder(new LineBorder(Color.BLACK));
        
        VolumenSmart = new JLabel("Vol Smartphone: " + SmartDisp.getVolume());
        VolumenSmart.setBorder(new LineBorder(Color.BLACK));
        
        VolumenLaptop = new JLabel("Vol Laptop: " + LapDisp.getVolume());
        VolumenLaptop.setBorder(new LineBorder(Color.BLACK));
        
        VolumenTablet = new JLabel("Vol Tablet: " + TabDisp.getVolume());
        VolumenTablet.setBorder(new LineBorder(Color.BLACK));
		
        estetica = new JLabel("           ");
		
		TV = new JLabel();
		TV.setText("TV");
		TV.setBorder(new LineBorder(Color.BLACK));
		Dimension tam1 = new Dimension(300, 200);
		TV.setPreferredSize(tam1);
		
		smartPhone = new JLabel();
		smartPhone.setText("SmartPhone");
		smartPhone.setBorder(new LineBorder(Color.BLACK));
		Dimension tam2 = new Dimension(100, 200);
		smartPhone.setPreferredSize(tam2);
		
		
		laptop = new JLabel();
		laptop.setText("Laptop");
		laptop.setBorder(new LineBorder(Color.BLACK));
		Dimension tam3 = new Dimension(100, 100);
		laptop.setPreferredSize(tam3);
		
		tablet = new JLabel();
		tablet.setText("Tablet");
		tablet.setBorder(new LineBorder(Color.BLACK));
		Dimension tam4 = new Dimension(500, 500);
		tablet.setPreferredSize(tam4);
		
		cargar = new JButton("Cargar imagen");
		
		devices = new JComboBox<>(new String[]{"TV", "Smartphone", "Laptop", "Tablet"});
		
		
		cargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = devices.getSelectedIndex();
				
				switch(op) {
				case 0:
					TVdisp.setImage(TV);
					break;
					
				case 1:
					SmartDisp.setImage(smartPhone);
					break;
					
				case 2:
					LapDisp.setImage(laptop);			
					break;
								
				case 3:
					TabDisp.setImage(tablet);
					break;
				}
			}
		});
		
		
		subirVolumen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int op = devices.getSelectedIndex();
                switch (op) {
                    case 0:
                        TVdisp.setVolume(TVdisp.getVolume() + 1); // Ajustar el volumen de la TV
                        VolumenTV.setText("Vol TV: " + TVdisp.getVolume());
                        break;

                    case 1:
                        SmartDisp.setVolume(SmartDisp.getVolume() + 1); // Ajustar el volumen del Smartphone
                        VolumenSmart.setText("Vol SmartPhone: " + SmartDisp.getVolume());
                        break;

                    case 2:
                        LapDisp.setVolume(LapDisp.getVolume() + 1); // Ajustar el volumen de la Laptop
                        VolumenLaptop.setText("Vol Laptop: " + LapDisp.getVolume());
                        break;

                    case 3:
                        TabDisp.setVolume(TabDisp.getVolume() + 1); // Ajustar el volumen de la Tablet
                        VolumenTablet.setText("Vol Tablet: " + TabDisp.getVolume());
                        break;
                }
            }
        });

        bajarVolumen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int op = devices.getSelectedIndex();
                switch (op) {
                    case 0:
                        TVdisp.setVolume(TVdisp.getVolume() - 1); // Ajustar el volumen de la TV
                        VolumenTV.setText("Vol TV: " + TVdisp.getVolume());
                        break;

                    case 1:
                        SmartDisp.setVolume(SmartDisp.getVolume() - 1); // Ajustar el volumen del Smartphone
                        VolumenSmart.setText("Vol SmartPhone: " + SmartDisp.getVolume());
                        break;

                    case 2:
                        LapDisp.setVolume(LapDisp.getVolume() - 1); // Ajustar el volumen de la Laptop
                        VolumenLaptop.setText("Vol Laptop: " + LapDisp.getVolume());
                        break;

                    case 3:
                        TabDisp.setVolume(TabDisp.getVolume() - 1); // Ajustar el volumen de la Tablet
                        VolumenTablet.setText("Vol Tablet: " + TabDisp.getVolume());
                        break;
                }
            }
        });
        
        
        
		panel.add(devices);
		panel.add(cargar);
		
		panel.add(TV);
		panel.add(smartPhone);
		panel.add(laptop);
		panel.add(tablet);
		
		panel.add(estetica);
		panel.add(subirVolumen);
		panel.add(bajarVolumen);
		panel.add(VolumenTV);
        panel.add(VolumenSmart);
        panel.add(VolumenLaptop);
        panel.add(VolumenTablet);

		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setVisible(true);
	}
	
	
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new UIDevices();
	            }
	        });
	    }
	
}
