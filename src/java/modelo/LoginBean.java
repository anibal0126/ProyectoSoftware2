/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author anibalMuñoz
 */
@ManagedBean
@SessionScoped
public class LoginBean {
    
    Connection connect = null;
     
     Conexion conexion= new Conexion();
    
     public int cedula, contraseña;
     
     private boolean autenticado=true;
     
    public void ingresar() throws ClassNotFoundException, SQLException, IOException{
        
     System.out.println(cedula);
     System.out.println(contraseña);
     
     connect=conexion.conectar();
     
     String consulta = "select cedula, contraseña from usuario where cedula='"+cedula+"' AND contraseña='"+contraseña+"'" ;

        PreparedStatement pstmt = connect.prepareStatement(consulta);
        ResultSet rs = pstmt.executeQuery();
        
     if(rs.next()){
     
     FacesContext.getCurrentInstance().getExternalContext().redirect("faces/listaCasasRurales.xhtml");
     autenticado = true;
     }else{
         
         System.out.println("El usuario no existe o las credenciales son incorrectas.");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario no existe o las credenciales son incorrectas.", "PrimeFaces Rocks."));
        
     }
     
 }
    public void redirecciona() throws IOException{
        System.out.println("HOlas");
        FacesContext.getCurrentInstance().getExternalContext().redirect("faces/regitrarUsuario.xhtml");
    }

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getContraseña() {
        return contraseña;
    }

    public void setContraseña(int contraseña) {
        this.contraseña = contraseña;
    }
    
    
    
}
