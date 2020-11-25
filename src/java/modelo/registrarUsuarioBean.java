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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author anibalMuñoz
 */
@ManagedBean
@SessionScoped
public class registrarUsuarioBean {
    
    Connection connect = null;
     
     Conexion conexion= new Conexion();
    
    
    public int cedula;
    
    public int contraseña;
    
    public double telefono;
    
    public String nombreCompleto;
    
    public String direccion;
    
    public void registrarUsuario() throws ClassNotFoundException, SQLException, IOException{
    
    connect=conexion.conectar();
     
     String consulta = "select cedula from usuario where cedula='"+cedula+"'" ;

        PreparedStatement pstmt = connect.prepareStatement(consulta);
        ResultSet rs = pstmt.executeQuery();
        
     if(rs.next()){
     
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario ya se encuentra registrado", "PrimeFaces Rocks."));
        
     }else{
         
        PreparedStatement pstmt1;
        pstmt1 = connect.prepareStatement("INSERT INTO Usuario (cedula, contraseña, telefono, nombreCompleto, direccion) value ( '" + cedula + "','" + contraseña + "','" + telefono + "','" + nombreCompleto + "','" + direccion + "')");
        int rs1;
        rs1 = pstmt1.executeUpdate();
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("faces/login.xhtml");
         
     }
     
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

    public double getTelefono() {
        return telefono;
    }

    public void setTelefono(double telefono) {
        this.telefono = telefono;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
}
