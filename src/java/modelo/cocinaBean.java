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
public class cocinaBean implements Serializable {
    
    public String vajilla, lavadora;
    
    public int casaRural,cocinas;
    
    Connection connect = null;
     
    Conexion conexion= new Conexion();
    
     public void listarCocina(int casa, int cocinascasa) throws SQLException, ClassNotFoundException {
         
        casaRural=casa; 
        
        cocinas=cocinascasa;

        RequestContext.getCurrentInstance().update("listaCocinas");
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('listaCocinas').show();");
    }
    
    public void agregarCocina() throws ClassNotFoundException, SQLException{
        
        connect=conexion.conectar();
        
        PreparedStatement pstmt = connect.prepareStatement("insert into cocina(CasaRural_codigo, tieneLavadora, tieneLavajillas) value ( '"+casaRural+"','"+lavadora+"',"+vajilla+")");
        int rs = pstmt.executeUpdate();
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('myCocina').hide();");
    }
    public void formularioCocina() throws SQLException{
        
        PreparedStatement pstmt = connect.prepareStatement("select count(*) cocinas from cocina where CasaRural_codigo="+casaRural);
        ResultSet rs = pstmt.executeQuery();
        
        int noCocinas=0;

        while (rs.next()) {

            noCocinas=rs.getInt("cocinas");

        }
         ;
        
        rs.close();
        
        if(noCocinas>=cocinas){
            
            System.out.println("No puede retornar mas cocinas");
            
            return;
            
        }
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('myCocina').show();");
    }
    
    public List<Cocina> getCocinas() throws ClassNotFoundException, SQLException {
        
        System.out.println("Sisas");
        connect=conexion.conectar();
        
        List<Cocina> cocinas = new ArrayList<>();
        String consulta = "select CasaRural_codigo, tieneLavadora, tieneLavajillas from cocina where CasaRural_codigo="+casaRural;

        PreparedStatement pstmt = connect.prepareStatement(consulta);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            Cocina cocina= new Cocina();
            cocina.setCasaRural(rs.getInt("CasaRural_codigo"));
            cocina.setLavadora(rs.getString("tieneLavadora"));
            cocina.setVajilla(rs.getString("tieneLavajillas"));
            
            cocinas.add(cocina);

        }

        // close resources
        rs.close();
        pstmt.close();

        return cocinas;
    }
    

    public int getCasaRural() {
        return casaRural;
    }

    public void setCasaRural(int casaRural) {
        this.casaRural = casaRural;
    }

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) {
        this.connect = connect;
    }
    
    

    public String getVajilla() {
        return vajilla;
    }

    public void setVajilla(String vajilla) {
        this.vajilla = vajilla;
    }

    public String getLavadora() {
        return lavadora;
    }

    public void setLavadora(String lavadora) {
        this.lavadora = lavadora;
    }

    
    
}
