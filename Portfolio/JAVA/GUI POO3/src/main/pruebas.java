package main;

import java.io.File;  
import javax.swing.Icon;
import javax.swing.ImageIcon;
import Control.*;
import GUI.*;
import Model.*;

public class pruebas {
	
	public static void main(String[] args) {
		
		
		Base baseInstance = Base.getInstance();;
		
        String rutaImagen = "D:\\System\\Documents\\GitHub\\TEC-RETO3-POO\\src\\main\\1006543.png"; 
        
        File archivoImagen = new File(rutaImagen);
        if (!archivoImagen.exists()) {
            System.out.println("El archivo de imagen no existe en la ruta especificada.");
            return;
        }

        
        ImageIcon icono = new ImageIcon(rutaImagen);

		
		Usuario usuario1 = new Usuario(0, "Juan Pérez", "juanperez", "Representante", "juan.perez@example.com", 5, "clave123", icono);
		baseInstance .addPerfil(usuario1);
		Usuario usuario2 = new Usuario(1, "Ana Gómez", "anagomez", "Ingeniero", "ana.gomez@example.com", 3, "password456", icono);
		baseInstance.addPerfil(usuario2);
		Usuario usuario3 = new Usuario(2, "Luis Martínez", "luismartinez", "Ingeniero", "luis.martinez@example.com", 8, "segura789", icono);
		baseInstance.addPerfil(usuario3);
		Usuario usuario4 = new Usuario(3, "María Rodríguez", "mariarodriguez", "Ingeniero", "maria.rodriguez@example.com", 2, "contraseña1", icono);
		baseInstance.addPerfil(usuario4);
		Usuario usuario5 = new Usuario(4, "Carlos López", "carloslopez", "Representante", "carlos.lopez@example.com", 6, "clave456", icono);
		baseInstance.addPerfil(usuario5);
		Usuario usuario6 = new Usuario(5, "Laura Fernández", "laurafernandez", "Ingeniero", "laura.fernandez@example.com", 10, "pass789", icono);
		baseInstance.addPerfil(usuario6);
		Usuario usuario7 = new Usuario(6, "Sofía García", "sofiagarcia", "Ingeniero", "sofia.garcia@example.com", 4, "123456", icono);
		baseInstance.addPerfil(usuario7);
		Usuario usuario8 = new Usuario(7, "Daniel Herrera", "danielherrera", "Representante", "daniel.herrera@example.com", 7, "password789", icono);
		baseInstance.addPerfil(usuario8);
		Usuario usuario9 = new Usuario(8, "Lucía Navarro", "lucianavarro", "Ingeniero", "lucia.navarro@example.com", 1, "clave123", icono);
		baseInstance.addPerfil(usuario9);
		Usuario usuario10 = new Usuario(9, "Alejandro Torres", "alejandrotorres", "Representante", "alejandro.torres@example.com", 9, "contraseña456", icono);
		baseInstance.addPerfil(usuario10);
		Usuario usuario11 = new Usuario(10, "Elena Ruiz", "elenaruiz", "Representante", "elena.ruiz@example.com", 5, "pass789", icono);
		baseInstance.addPerfil(usuario11);
		Usuario usuario12 = new Usuario(11, "Héctor Morales", "hectormorales", "Ingeniero", "hector.morales@example.com", 3, "123456", icono);
		baseInstance.addPerfil(usuario12);
		Usuario usuario13 = new Usuario(12, "Isabel Cruz", "isabelcruz", "Ingeniero", "isabel.cruz@example.com", 6, "password123", icono);
		baseInstance.addPerfil(usuario13);
		Usuario usuario14 = new Usuario(13, "Miguel Castro", "miguelcastro", "Representante", "miguel.castro@example.com", 8, "clave456", icono);
		baseInstance.addPerfil(usuario14);
		Usuario usuario15 = new Usuario(14, "Paula Sánchez", "paulasanchez", "Representante", "paula.sanchez@example.com", 2, "contraseña789", icono);
		baseInstance.addPerfil(usuario15);
		
		String ruta = "D:\\System\\Documents\\GitHub\\TEC-RETO3-POO\\src\\main\\agricultura-e1551193452226.jpg";
		
		Publicacion publicacion1 = new Publicacion("Ahorro de agua", new ImageIcon(ruta), "link1",
                "100Kg por hectarea", "Ahorro de agua", 500, "Maquinaria1", "3 Meses");
		baseInstance.addPubli(publicacion1);
        Publicacion publicacion2 = new Publicacion("Eliminacion de pestes", new ImageIcon(ruta), "link2",
                "150Kg por hectarea", "Eliminación de insectos", 700, "Maquinaria2", "6 Meses");
        baseInstance.addPubli(publicacion2);
        Publicacion publicacion3 = new Publicacion("Ahorro de fertilizante", new ImageIcon(ruta), "link3",
                "120Kg por hectarea", "Uso eficiente de fertilizantes", 600, "Maquinaria3", "4 Meses");
        baseInstance.addPubli(publicacion3);
        Publicacion publicacion4 = new Publicacion("Optimizacion de rotacion", new ImageIcon(ruta), "link4",
                "200Kg por hectarea", "Rotación de cultivos", 800, "Maquinaria4", "5 Meses");
        baseInstance.addPubli(publicacion4);
        Publicacion publicacion5 = new Publicacion("control de plagas", new ImageIcon(ruta), "link5",
                "180Kg por hectarea", "Control biológico de plagas", 900, "Maquinaria5", "7 Meses");
        baseInstance.addPubli(publicacion5);
        Publicacion publicacion6 = new Publicacion("Intercambio de cultivos", new ImageIcon(ruta), "link6",
                "90Kg por hectarea", "Intercambio de cultivos", 400, "Maquinaria6", "2 Meses");
        baseInstance.addPubli(publicacion6);
        Publicacion publicacion7 = new Publicacion("Uso optimo de abono", new ImageIcon(ruta), "link7",
                "250Kg por hectarea", "Uso de abonos orgánicos", 1000, "Maquinaria7", "8 Meses");
        baseInstance.addPubli(publicacion7);
        Publicacion publicacion8 = new Publicacion("Manejo integrado de plagas", new ImageIcon(ruta), "link8",
                "130Kg por hectarea", "Manejo integrado de plagas", 550, "Maquinaria8", "4 Meses");
        baseInstance.addPubli(publicacion8);
        Publicacion publicacion9 = new Publicacion("Conservacion del terreno", new ImageIcon(ruta), "link9",
                "170Kg por hectarea", "Conservación del suelo", 750, "Maquinaria9", "6 Meses");
        baseInstance.addPubli(publicacion9);
        Publicacion publicacion10 = new Publicacion("Agricultura de presicion", new ImageIcon(ruta), "link10",
                "160Kg por hectarea", "Agricultura de precisión", 850, "Maquinaria10", "5 Meses");
        baseInstance.addPubli(publicacion10);
		
		InicioSesion in = new InicioSesion(baseInstance);
		//Registro reg = new Registro();
		//CrearPost post = new CrearPost();
		//Perfil perfil = new Perfil();
		//Navegador nav = new Navegador();
		//Contrato cont = new Contrato();

		
	}
}











