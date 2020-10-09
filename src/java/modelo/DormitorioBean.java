/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.sql.Connection;
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
public class DormitorioBean implements Serializable{
    
    
   public int noCamasSencillas, noCamasDobles, casaRural, dormitorios;
    
   public String baño;
    
     Connection connect = null;
     
     Conexion conexion= new Conexion();
     
     public void listarDormitorio(int casa, int dormitoriosCasa) throws SQLException, ClassNotFoundException {
         
        casaRural=casa; 
        
        System.out.println(casa+"porque");
        
        dormitorios=dormitoriosCasa;

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('listaDormitorios').show();");
    }
     
     public List<Dormitorio> getDormitorios() throws ClassNotFoundException, SQLException {
        
        connect=conexion.conectar();
        
        System.out.println(casaRural+"Ws");
        
        List<Dormitorio> dormitorios1;
       dormitorios1 = new ArrayList<>();
        String consulta = "select CasaRural_codigo, noCamasDobles, noCamasSencillas from dormitorio where CasaRural_codigo="+casaRural;

        PreparedStatement pstmt = connect.prepareStatement(consulta);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            Dormitorio dormitorio= new Dormitorio();
            dormitorio.setCasaRural(rs.getInt("CasaRural_codigo"));
            dormitorio.setNoCamasDobles(rs.getInt("noCamasDobles"));
            dormitorio.setNoCamasSencillas(rs.getInt("noCamasSencillas"));
            
            dormitorios1.add(dormitorio);

        }

        // close resources
        rs.close();
        pstmt.close();

        return dormitorios1;
    }
     public void agregarCocina(int casaRural) {

        
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('listaDormitorios').show();");
    }
    public void formularioDormitorio() throws ClassNotFoundException, SQLException{
        
        connect=conexion.conectar();
        
        
        PreparedStatement pstmt = connect.prepareStatement("select count(*) dormitorios from dormitorio where CasaRural_codigo="+casaRural);
        ResultSet rs = pstmt.executeQuery();
        
        int noDormitorios=0;

        while (rs.next()) {

            noDormitorios=rs.getInt("dormitorios");

        }
         System.out.println("Numero de dormitorios"+noDormitorios);
        
        rs.close();
        
        if(noDormitorios>=dormitorios){
            
            System.out.println("No puede retornar mas dormitorios");
            
            return;
            
        }
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('myDialogVar').show();");
    }
     
    
    public void agregarDormitorio() throws ClassNotFoundException, SQLException{
        
        connect=conexion.conectar();
        
        PreparedStatement pstmt = connect.prepareStatement("insert into dormitorio(CasaRural_codigo, noCamasDobles, noCamasSencillas ) value ( "+casaRural+",'"+noCamasDobles+"','"+noCamasSencillas+"')");
        int rs = pstmt.executeUpdate();
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('myDialogVar').hide();");
        
    }
    
    
    

    public DormitorioBean() {
    }

    public int getNoCamasSencillas() {
        return noCamasSencillas;
    }

    public void setNoCamasSencillas(int noCamasSencillas) {
        this.noCamasSencillas = noCamasSencillas;
    }

    public int getNoCamasDobles() {
        return noCamasDobles;
    }

    public void setNoCamasDobles(int noCamasDobles) {
        this.noCamasDobles = noCamasDobles;
    }

    public String getBaño() {
        return baño;
    }

    public void setBaño(String baño) {
        this.baño = baño;
    }
    
    
    
}
