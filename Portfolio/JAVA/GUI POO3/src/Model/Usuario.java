package Model;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import Control.Base;
public class Usuario implements java.io.Serializable{
    private int id;
    private String Name;
    private String email;
    private String nombre_Perfil;
    private int años_exp;
    private String tipo;
    private String Password;
    private Icon image;
    private Plan plan;
    private List<Publicacion> publicaciones;

    // Constructor para inicializar los atributos
    public Usuario (int id, String nombre, String Usuario, String Tipo, String email, int años_exp, String Contraseña, Icon Foto) {
        this.id = id;
        this.Name = nombre;
        this.nombre_Perfil = Usuario;
        this.email = email;
        this.tipo = Tipo;
        this.años_exp = años_exp;  
        this.Password = Contraseña;
        this.image = Foto;
        
    }
    
    public Icon getFoto() {
    	return image;
    }
    
    public void setFoto(Icon Foto) {
    	this.image = Foto;
    }
    // Getter para obtener el ID
    public int getId() {
        return id;
    }
    public String getNombreUsuario() {
        return nombre_Perfil ;
    }

    // Getter para obtener el nombre
    public String getNombre() {
        return Name;
    }

    // Getter para obtener el email
    public String getEmail() {
        return email;
    }

    // Getter para obtener los años de experiencia
    public int getAñosExp() {
        return años_exp;
    }

    // Getter para obtener la especialidad
    public String getTipo() {
        return tipo;
    }
    
    // Getter para obtener la especialidad
    public String getPassword() {
        return Password ;
    }

    //Getter para el plan
    public Plan getPlan() {
    	return plan;
    }
    
    public List<Publicacion> getPublicaciones() {
    	return publicaciones;
    }
    
    //----------------------------------------------------------------------
    
    // Setter para modificar el ID
    public void setId(int id) {
        this.id = id;
    }

    // Setter para modificar el nombre
    public void setNombre(String nombre) {
        this.Name = nombre;
    }

    // Setter para modificar el email
    public void setEmail(String email) {
        this.email = email;
    }

    // Setter para modificar los años de experiencia
    public void setAñosExp(int años_exp) {
        this.años_exp = años_exp;
    }

    // Setter para modificar la especialidad
    public void setEspecialidad(String pRol) {
        this.tipo = pRol;
    }
    
    // Setter para modificar la contraseña
    public void setPassword(String pPassword) {
        this.Password = pPassword;
    }
    
 // Setter para modificar el nombre de usuario
    public void setUserName(String name) {
        this.nombre_Perfil = name;
    }
    
    //Setter para el plan
    public void setPlan(Plan pPlan) {
    	this.plan = pPlan;
    }
    
    public void setPublicaciones(List<Publicacion> list) {
    	this.publicaciones = list;
    }
}


