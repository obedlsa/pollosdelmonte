package com.example.obed.pollosdelmonte;


/* Clase Promocion.
        3  *
        4  * @author Obed Alvarado
       5  * @version 18/05/2018
*/
public class Promocion {
    private String idPromocion,promocion;

    /**
     * Constructor.
     * @param idPromocion: identificador de cada promocion.
     * @param promocion: texto de cada promocion.
     */
    public Promocion(String idPromocion, String promocion) {
        this.idPromocion = idPromocion;
        this.promocion = promocion;
    }
    /**
     * Getter.
     * @return idpromocion: idpromocion.
     */
    public String getIdPromocion() {
        return idPromocion;
    }
    /**
     * Setter.
     * @param idPromocion: idpromocion.
     */

    public void setIdPromocion(String idPromocion) {
        this.idPromocion = idPromocion;
    }
    /**
     * Getter.
     * @return promocion: promocion.
     */

    public String getPromocion() {
        return promocion;
    }
    /**
     * Setter.
     * @param promocion: promocion.
     */

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }
}

