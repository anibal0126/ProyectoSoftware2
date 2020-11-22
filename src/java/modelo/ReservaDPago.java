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
public class ReservaDPago {

    public int codigo;
    public String fechaPago;
    public int importe; // Valor del pago
    public int noReserva;

    public ReservaDPago() {
    }

    public ReservaDPago(int codigo, String fechaPago, int importe, int noReserva) {

        this.codigo = codigo;
        this.fechaPago = fechaPago;
        this.importe = importe;
        this.noReserva = noReserva;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public int getNoReserva() {
        return noReserva;
    }

    public void setNoReserva(int noReserva) {
        this.noReserva = noReserva;
    }

    
}
