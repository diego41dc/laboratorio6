/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ IServicioVendedoresMockRemote.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.servicios;

import com.losalpes.entities.Vendedor;
import com.losalpes.excepciones.OperacionInvalidaException;
import java.util.List;
import javax.ejb.Remote;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Contrato funcional de los servicios de administración de los vendedores del sistema
 * 
 */
@Remote
public interface IServicioVendedoresMockRemote
{
    /**
     * Agrega un vendedor al sistema
     * @param vendedor Nuevo vendedor
     * @throws OperacionInvalidaException Excepción lanzada en caso de error operacional
     */
    public void agregarVendedor(Vendedor vendedor)throws OperacionInvalidaException;

    /**
     * Elimina un vendedor del sistema
     * @param id Número de identificación único del cliente
     * @throws OperacionInvalidaException Excepción lanzada en caso de error operacional
     */
    public void eliminarVendedor(long id)throws OperacionInvalidaException;

    /**
     * Devuelve todos los vendedores del sistema
     * @return vendedores Vendedores del sistema
     */
    public List<Vendedor> getVendedores();
    
    
    /**
     * 
     * @param session
     * @return
     * @throws JMSException 
     */
    public Message createModificacionVendendorMessage(Session session) throws JMSException;
    
    /**
     * 
     * @throws JMSException 
     */
    public void notificarModificacionVendedor() throws JMSException;
}
