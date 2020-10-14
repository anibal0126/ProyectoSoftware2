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
    public int cedulaUsuario;

    public Usuario usuario;
    public List<Usuario> usuarios;

    Conexion conexion = new Conexion();

    Connection connect = null;

    public List<Usuario> getListarUsuarios() throws SQLException, ClassNotFoundException {

        usuarios = new ArrayList<>();

        connect = conexion.conectar();

        String consulta = "SELECT cedula, nombreCompleto FROM Usuario";

        try (
               PreparedStatement pstmt = connect.prepareStatement(consulta)) {
                ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                
                Usuario usuarioListado = new Usuario();
                usuarioListado.setId(rs.getInt("cedula"));
                usuarioListado.setNombreCompleto(rs.getString("nombreCompleto"));
                
                usuarios.add(usuarioListado);
            }
            
            // close resources
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
        
        context.execute("PF('formularioReserva').show();");
    }

    public void guardarReserva() throws ClassNotFoundException, SQLException {

        System.out.println("Entro a reservar");

        connect = conexion.conectar();

        PreparedStatement pstmt = connect.prepareStatement("INSERT INTO Reserva (fechaInicioReserva, fechaFinReserva, estadoPago, casaEntera, precio, estadoReserva, Usuario_cedula) value ( '" + fechaInicioReserva + "','" + fechaFinReserva + "','" + estadoPago + "','" + casaEntera + "','" + precio + "','" + estadoReserva + "'," + cedulaUsuario + ")");
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
