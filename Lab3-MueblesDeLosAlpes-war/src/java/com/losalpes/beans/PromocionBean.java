/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ $Id$
 * CatalogoBean.java Universidad de los Andes (Bogotá - Colombia) Departamento
 * de Ingeniería de Sistemas y Computación Licenciado bajo el esquema Academic
 * Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.losalpes.beans;

import com.losalpes.entities.Promocion;
import com.losalpes.servicios.IServicioCatalogoMockLocal;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * Managed bean encargado del catálogo de promociones en el sistema
 *
 */
public class PromocionBean implements Serializable {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Representa un nueva promocion a ingresar
     */
    private Promocion promocion;

    /**
     * Relación con la interfaz que provee los servicios necesarios del
     * catálogo.
     */
    @EJB
    private IServicioCatalogoMockLocal catalogo;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    /**
     * Constructor de la clase principal
     */
    public PromocionBean() {
        promocion = new Promocion();
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------
    /**
     * Devuelve el objeto promocion
     *
     * @return mueble Objeto promocion
     */
    public Promocion getPromocion() {
        return promocion;
    }

    /**
     * Modifica el objeto promocion
     *
     * @param promocion Nueva promocion
     */
    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    /**
     * Devuelve una lista con todos los muebles del sistema
     *
     * @return muebles Muebles del sistema
     */
    public List<Promocion> getPromociones() {

        return catalogo.darPromociones();
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------
    /**
     * Agrega un nueva promocion al sistema
     */
    public void agregarPromocion() {
        catalogo.agregarPromocion(promocion);
        promocion = new Promocion();
    }

    /**
     * Elimina un promociones del sistema
     *
     * @param evento Evento que tiene como parámetro el ID del mueble
     */
    public void eliminarPromocion(ActionEvent evento) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String promocionId = (String) map.get("promocionId");

        catalogo.eliminarPromocion(promocionId);
    }

    /**
     * Elimina la información del mueble
     */
    public void limpiar() {
        promocion = new Promocion();
    }
}
