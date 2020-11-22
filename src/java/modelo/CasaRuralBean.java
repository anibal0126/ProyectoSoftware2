/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author German Gonzales
 * @author Alexis Castrillo
 * @author Darwin Bonilla
 * @author Camilo Ortiz
 * @autor Anibal Muñoz 
 */
@ManagedBean
@SessionScoped
public class CasaRuralBean implements Serializable  {
    
    public int  noDormitorios, noCocinas, noBaños, noComedores, noPlazas, codigoCasaRural;
    
    public String poblacion, descripcion, estado, filtroPoblacion, filtroId, filtroDescripcion;
    
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
    
    
    public void modificarPropiedad()throws ClassNotFoundException, SQLException, IOException{
        
        connect=conexion.conectar();
        
        PreparedStatement pstmt = connect.prepareStatement("update casarural set descripcion = '"+descripcion+"'"+", estado = '"+estado+"'"+", noComedores = '"+noComedores+"'"+", noPlazas = '"+noPlazas+"'"+", poblacion = '"+poblacion+"'"+" where codigo = '"+codigoCasaRural+"'");
        int rs = pstmt.executeUpdate();
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("faces/listaCasasRurales.xhtml");
        
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
        
        String consulta = "SELECT codigo, poblacion, noDormitorios, noCocinas, noBaños, noComedores, noPlazas, descripcion, estado FROM casarural WHERE 1=1";
        
        if (filtroPoblacion != null) {

            if (!filtroPoblacion.equalsIgnoreCase("")) {
                System.out.println("Entro filtroNombre: " + filtroPoblacion);
                consulta += " AND poblacion = '" + filtroPoblacion + "'";
            }
        }

        if (filtroId != null) {

            if (!filtroId.equalsIgnoreCase("")) {
                System.out.println("Entro filtroAutor: " + filtroId);
                consulta += " AND codigo = '" + filtroId + "'";
            }
        }
        
        if (filtroDescripcion != null) {

            if (!filtroDescripcion.equalsIgnoreCase("")) {
                System.out.println("Entro filtroAutor: " + filtroDescripcion);
                consulta += " AND descripcion LIKE '%" + filtroDescripcion + "'%";
            }
        }
        
        List<CasaRural> casasRurales = new ArrayList<>();

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
    
    public void redirecciona(int codigo) throws IOException{
        System.out.println("HOlas");
        
        codigoCasaRural = codigo;
        
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("faces/modificarPropiedad.xhtml");
    }
    
    public void quitar(int codigo) throws IOException{
        System.out.println("Va a eliminar la propiedad");
        
        codigoCasaRural = codigo;
        
        
        RequestContext.getCurrentInstance().execute("PF('confirmDlg').show();");
    }
    
    public void eliminarPropiedad() throws ClassNotFoundException, SQLException{
        
        connect = conexion.conectar();
        
        System.out.println("codigo a eliminar: "+codigoCasaRural);
        
        String consulta = "SELECT codigo FROM `casarural` WHERE (codigo IN (SELECT d.CasaRural_codigo FROM dormitorio d WHERE d.CasaRural_codigo = "+codigoCasaRural+") OR codigo IN (SELECT c.CasaRural_codigo FROM cocina c WHERE c.CasaRural_codigo = "+codigoCasaRural+")) AND codigo = "+codigoCasaRural;

        PreparedStatement pstmtSelect = connect.prepareStatement(consulta);
        ResultSet rsSelect = pstmtSelect.executeQuery();
        
        int codigoPropiedad = 0;

        while (rsSelect.next()) {
            
            codigoPropiedad = rsSelect.getInt("codigo");
        }
        
        if(codigoPropiedad != 0)
        {
            System.out.println("No se puede eliminar esta propiedad");
            RequestContext.getCurrentInstance().execute("PF('error_eliminar_propiedad').show();");
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se puede eliminar esta propiedad, ya que tiene relación con algunos registros.", "PrimeFaces Rocks."));
        } else {
            PreparedStatement pstmt = connect.prepareStatement("DELETE FROM casarural WHERE codigo = "+codigoCasaRural);
            int rs = pstmt.executeUpdate();
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Propiedad eliminada con éxito!.", "PrimeFaces Rocks."));
            System.out.println("Propiedad eliminada");
            RequestContext.getCurrentInstance().execute("PF('eliminar_propiedad_correcta').show();");
        }
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('confirmDlg').hide();");
  
        
    }
    
    public List<CasaRural> getCasasRuralesFiltro() throws ClassNotFoundException, SQLException {

        connect=conexion.conectar();
        System.out.println("Entro a listar los libros");

        List<CasaRural> casasRurales = new ArrayList<>();
        String consulta = "select codigo, poblacion from casarural";
        System.out.println("Filtro: " + filtroPoblacion);

        if (filtroPoblacion != null) {

            if (!filtroPoblacion.equalsIgnoreCase("")) {
                System.out.println("Entro filtroNombre: " + filtroPoblacion);
                consulta = "select codigo, poblacion from casarural WHERE poblacion = '" + filtroPoblacion + "'";
            }
        }

        if (filtroId != null) {

            if (!filtroId.equalsIgnoreCase("")) {
                System.out.println("Entro filtroAutor: " + filtroId);
                consulta = "select codigo, poblacion from casarural WHERE codigo = '" + filtroId + "'";
            }
        }
        
        if (filtroDescripcion != null) {

            if (!filtroDescripcion.equalsIgnoreCase("")) {
                System.out.println("Entro filtroAutor: " + filtroDescripcion);
                consulta = "select codigo, poblacion from casarural WHERE descripcion LIKE '%" + filtroDescripcion + "'%";
            }
        }

        PreparedStatement pstmt = connect.prepareStatement(consulta);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            CasaRural casa = new CasaRural();
            casa.setCodigo(rs.getInt("codigo"));
            casa.setPoblacion(rs.getString("poblacion"));
            

            casasRurales.add(casa);

        }

        // close resources
        rs.close();
        pstmt.close();

        return casasRurales;

    }
    
    /**
     * Me permite listar las calificaciones
     *
     * @param id id de libro calificacion
     */
    public void listarCasaRural(int id) {

        codigoCasaRural = id;

        System.out.println("OKEY: " + codigoCasaRural);

        RequestContext.getCurrentInstance().update("listadoEspecificaciones");

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('listadoEspecificaciones').show();");
    }
    public void limpiarFiltros() {
        System.out.println("Hola");
        filtroPoblacion = "";
        filtroId = "";
        filtroDescripcion = "";
    }

    public String getFiltroPoblacion() {
        return filtroPoblacion;
    }

    public void setFiltroPoblacion(String filtroPoblacion) {
        this.filtroPoblacion = filtroPoblacion;
    }

    public String getFiltroId() {
        return filtroId;
    }

    public void setFiltroId(String filtroId) {
        this.filtroId = filtroId;
    }
    
    public String getFiltroDescripcion() {
        return filtroDescripcion;
    }

    public void setFiltroDescripcion(String filtroDescripcion) {
        this.filtroDescripcion = filtroDescripcion;
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
