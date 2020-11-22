/*
 * 
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
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
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
@ViewScoped
public class ReservaPropiedadBean implements Serializable {

    public String fechaInicioReserva, fechaFinReserva, estadoPago, casaEntera, precio, estadoReserva;
    public int cedulaUsuario, codigoPropiedad;

    public Usuario usuario;
    public List<SelectItem> usuarios, propiedades;

    Conexion conexion = new Conexion();

    Connection connect = null;
    
    public List<SelectItem> getListarPropiedades() throws SQLException, ClassNotFoundException {

        propiedades = new ArrayList<>();

        connect = conexion.conectar();

        String consulta = "SELECT codigo, descripcion FROM casarural";

        try (
            
            PreparedStatement pstmt = connect.prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery()) {  
            
            while (rs.next()) {
                
                propiedades.add(new SelectItem(rs.getInt("codigo"), rs.getString("descripcion")));
            }
        }

        return propiedades;
    }

    public List<SelectItem> getListarUsuarios() throws SQLException, ClassNotFoundException {

        usuarios = new ArrayList<>();

        connect = conexion.conectar();

        String consulta = "SELECT cedula, nombreCompleto FROM Usuario";

        try (
            
            PreparedStatement pstmt = connect.prepareStatement(consulta)) {
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                usuarios.add(new SelectItem(rs.getInt("cedula"), rs.getString("nombreCompleto")));
            }
            
            rs.close();
        }

        return usuarios;
    }

    public void reservarPropiedad() {

        System.out.println("Va a reservar...");
        RequestContext context = RequestContext.getCurrentInstance();
        
        fechaInicioReserva = "";
        fechaFinReserva = "";
        estadoPago = "";
        casaEntera = "";
        precio = "";
        estadoReserva = "";
        codigoPropiedad = 0;
        
        context.execute("PF('formularioReserva').show();");
    }

    public void guardarReserva() throws ClassNotFoundException, SQLException {

        System.out.println("Entro a reservar");

        connect = conexion.conectar();

        PreparedStatement pstmt = connect.prepareStatement("INSERT INTO Reserva (fechaInicioReserva, fechaFinReserva, estadoPago, casaEntera, precio, estadoReserva, Usuario_cedula, casarural_codigo) value ( '" + fechaInicioReserva + "','" + fechaFinReserva + "','" + estadoPago + "','" + casaEntera + "','" + precio + "','" + estadoReserva + "'," + cedulaUsuario + "," + codigoPropiedad + ")");
        int rs = pstmt.executeUpdate();

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('formularioReserva').hide();");

    }

    public void cancelar() throws ClassNotFoundException, SQLException {

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('formularioReserva').hide();");

    }

    public List<ReservaPropiedad> getReservas() throws ClassNotFoundException, SQLException {

        connect = conexion.conectar();

        List<ReservaPropiedad> listadoReservas = new ArrayList<>();
        String consulta = "SELECT r.noReserva, r.fechaInicioReserva, r.fechaFinReserva, r.estadoPago, r.casaEntera, r.precio, r.estadoReserva, u.nombreCompleto FROM Reserva r JOIN Usuario u ON r.Usuario_cedula = u.cedula";

        try (
                PreparedStatement pstmt = connect.prepareStatement(consulta)) {
                ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                
                ReservaPropiedad reserva = new ReservaPropiedad();
                reserva.setNoReserva(rs.getInt("noReserva"));
                reserva.setFechaInicioReserva(rs.getString("fechaInicioReserva"));
                reserva.setFechaFinReserva(rs.getString("fechaFinReserva"));
                reserva.setEstadoPago(rs.getString("estadoPago"));
                reserva.setCasaEntera(rs.getString("casaEntera"));
                reserva.setPrecio(rs.getString("precio"));
                reserva.setEstadoReserva(rs.getString("estadoReserva"));
                reserva.setUsuario(new Usuario(rs.getString("nombreCompleto")));
                
                listadoReservas.add(reserva);
            }
            
            // close resources
            rs.close();
        }

        return listadoReservas;
    }

    public int getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(int cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public int getCodigoPropiedad() {
        return codigoPropiedad;
    }

    public void setCodigoPropiedad(int codigoPropiedad) {
        this.codigoPropiedad = codigoPropiedad;
    }
    
    

    public String getFechaInicioReserva() {
        return fechaInicioReserva;
    }

    public void setFechaInicioReserva(String fechaInicioReserva) {
        this.fechaInicioReserva = fechaInicioReserva;
    }

    public String getFechaFinReserva() {
        return fechaFinReserva;
    }

    public void setFechaFinReserva(String fechaFinReserva) {
        this.fechaFinReserva = fechaFinReserva;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getCasaEntera() {
        return casaEntera;
    }

    public void setCasaEntera(String casaEntera) {
        this.casaEntera = casaEntera;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
