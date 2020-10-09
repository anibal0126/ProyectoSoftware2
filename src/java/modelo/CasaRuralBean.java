/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@SessionScoped
public class CasaRuralBean implements Serializable  {
    
    public int  noDormitorios, noCocinas, noBaños, noComedores, noPlazas;
    
    public String poblacion, descripcion, estado;
    
    Conexion conexion=new Conexion();
    
    Connection connect = null;
    
    DormitorioBean dormitorio=new DormitorioBean();
    
    public void agregarPropiedad() {
        
      

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('myPropiedad').show();");
    }
    
    
    
    public void agregar() throws ClassNotFoundException, SQLException{
        
        connect=conexion.conectar();
        
        PreparedStatement pstmt = connect.prepareStatement("insert into casarural(descripcion, estado, noBaños, noCocinas, noComedores, noDormitorios, noPlazas, poblacion, Propietario_cedula) value ( '"+descripcion+"','"+estado+"',"+noBaños+","+noCocinas+","+noComedores+","+noDormitorios+","+noPlazas+",'"+poblacion+"','1' )");
        int rs = pstmt.executeUpdate();
        
         RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('myPropiedad').hide();");
        
    }
    public void cancelar() throws ClassNotFoundException, SQLException{
        
       
         RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('myPropiedad').hide();");
        
    }
    public void volver(){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('listaDormitorios').hide();");
    }
    
    public List<CasaRural> getCasasRurales() throws ClassNotFoundException, SQLException {
        
        connect=conexion.conectar();
        
        List<CasaRural> casasRurales = new ArrayList<>();
        String consulta = "select codigo, poblacion, noDormitorios, noCocinas, noBaños, noComedores, noPlazas, descripcion, estado from casarural";

        PreparedStatement pstmt = connect.prepareStatement(consulta);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            CasaRural casarural= new CasaRural();
            casarural.setCodigo(rs.getInt("codigo"));
            casarural.setPoblacion(rs.getString("poblacion"));
            casarural.setNoDormitorios(rs.getInt("noDormitorios"));
            casarural.setNoCocinas(rs.getInt("noCocinas"));
            casarural.setNoBaños(rs.getInt("noBaños"));
            casarural.setNoPlazas(rs.getInt("noPlazas"));
            casarural.setDescripcion(rs.getString("descripcion"));
            casarural.setEstado(rs.getString("estado"));
            casasRurales.add(casarural);

        }

        // close resources
        rs.close();
        pstmt.close();

        return casasRurales;
    }

    public int getNoDormitorios() {
        return noDormitorios;
    }

    public void setNoDormitorios(int noDormitorios) {
        this.noDormitorios = noDormitorios;
    }

    public int getNoCocinas() {
        return noCocinas;
    }

    public void setNoCocinas(int noCocinas) {
        this.noCocinas = noCocinas;
    }

    public int getNoBaños() {
        return noBaños;
    }

    public void setNoBaños(int noBaños) {
        this.noBaños = noBaños;
    }

    public int getNoComedores() {
        return noComedores;
    }

    public void setNoComedores(int noComedores) {
        this.noComedores = noComedores;
    }

    public int getNoPlazas() {
        return noPlazas;
    }

    public void setNoPlazas(int noPlazas) {
        this.noPlazas = noPlazas;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
