/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ $Id$
 * IServicioCatalogoMockRemote.java Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación Licenciado bajo el
 * esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.Promocion;
import com.losalpes.excepciones.OperacionInvalidaException;
import java.util.List;
import javax.ejb.Remote;

/**
 * Contrato funcional de los servicios que se le prestan al catálogo
 *
 */
@Remote
public interface IServicioCatalogoMockRemote {

    /**
     * Agrega un mueble al sistema
     *
     * @param mueble Nuevo mueble
     */
    public void agregarMueble(Mueble mueble);

    /**
     * Elimina un mueble del sistema
     *
     * @param id Identificador único del mueble a eliminar
     */
    public void eliminarMueble(long id);

    /**
     * Devuelve todos los muebles del sistema
     *
     * @return muebles Lista de muebles
     */
    public List<Mueble> darMuebles();

    /**
     * Devuelve las promociones del sistema
     *
     * @return muebles Arreglo con todos las promociones del sistema
     */
    public List<Promocion> darPromociones();

    /**
     * Remueve un ejemplar del mueble (no el mueble)
     *
     * @param id Identificador único del mueble
     */
    public void removerEjemplarMueble(long id);

    /**
     * Se elimina una promocion del sistema dado su identificador único
     *
     * @param codigo Identificador único de la promocion
     */
    public void eliminarPromocion(String codigo);

    /**
     * Agrega una nueva promocion
     *
     * @param promocion
     * @param mueble
     * @throws OperacionInvalidaException
     */
    public void asociarPromocion(Promocion promocion, Mueble mueble) throws OperacionInvalidaException;

    /**
     * Crea una nueva promocion
     *
     * @param promocion
     */
    public void agregarPromocion(Promocion promocion);

}
