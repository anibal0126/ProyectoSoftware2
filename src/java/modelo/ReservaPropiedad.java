/*
 * 
 */
package modelo;

/**
 *
 * @author German Gonzales
 * @author Alexis Castrillo
 * @author Darwin Bonilla
 * @author Camilo Ortiz
 * @autor Anibal Muñoz 
 */
public class ReservaPropiedad {

    public int noReserva;
    public String fechaInicioReserva, fechaFinReserva, estadoPago, casaEntera, precio, estadoReserva;
    CasaRural propiedad;

    public Usuario usuario;

    public ReservaPropiedad() {
    }

    public ReservaPropiedad(String fechaInicioReserva, String fechaFinReserva, String estadoPago, String casaEntera, String precio, String estadoReserva, Usuario usuario, CasaRural propiedad) {

        this.fechaInicioReserva = fechaInicioReserva;
        this.fechaFinReserva = fechaFinReserva;
        this.estadoPago = estadoPago;
        this.casaEntera = casaEntera;
        this.precio = precio;
        this.estadoReserva = estadoReserva;
        this.usuario = usuario;
        this.propiedad = propiedad;

    }

    public int getNoReserva() {
        return noReserva;
    }

    public void setNoReserva(int noReserva) {
        this.noReserva = noReserva;
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
    
    public CasaRural getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(CasaRural propiedad) {
        this.propiedad = propiedad;
    }

}
