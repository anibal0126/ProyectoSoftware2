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
public class Dormitorio {
    
    public int codigo, noCamasDobles, noCamasSencillas;
    
    
    public int casaRural;
    
    public String baño;

    public String getBaño() {
        return baño;
    }

    public int getCasaRural() {
        return casaRural;
    }

    public void setCasaRural(int casaRural) {
        this.casaRural = casaRural;
    }

    
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNoCamasDobles() {
        return noCamasDobles;
    }

    public void setNoCamasDobles(int noCamasDobles) {
        this.noCamasDobles = noCamasDobles;
    }

    public int getNoCamasSencillas() {
        return noCamasSencillas;
    }

    public void setNoCamasSencillas(int noCamasSencillas) {
        this.noCamasSencillas = noCamasSencillas;
    }


   
    
    
    
}
