/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author USUARIO
 */
public class CasaRural {
    
    public int codigo, noDormitorios, noCocinas, noBaños, noComedores, noPlazas;
    
    public String poblacion, descripcion, estado;
    
    public Propietario propietario;

    public CasaRural() {
    }
    
    

    public CasaRural(int noDormitorios, int noCocinas, int noBaños, int noComedores, int noPlazas, String poblacion) {
        
        this.noDormitorios = noDormitorios;
        this.noCocinas = noCocinas;
        this.noBaños = noBaños;
        this.noComedores = noComedores;
        this.noPlazas = noPlazas;
        this.poblacion = poblacion;
        
    }
   
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
    
    
}
