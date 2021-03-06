/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ $Id$
 * ServicioCatalogoMock.java Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación Licenciado bajo el
 * esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.Promocion;
import com.losalpes.excepciones.OperacionInvalidaException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * Implementacion de los servicios del catálogo de muebles que se le prestan al
 * sistema.
 */
@Stateless
public class ServicioCatalogoMock implements IServicioCatalogoMockRemote, IServicioCatalogoMockLocal {

    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------
    /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    @EJB
    private IServicioPersistenciaMockLocal persistencia;

    @Resource(mappedName = "jms/promocionTopicFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/promocionTopic")
    private Topic topic;

    //-----------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------
    /**
     * Constructor sin argumentos de la clase
     */
    public ServicioCatalogoMock() {
    }

    //-----------------------------------------------------------
    // Métodos
    //-----------------------------------------------------------
    /**
     * Agrega un mueble al sistema
     *
     * @param mueble Nuevo mueble
     */
    @Override
    public void agregarMueble(Mueble mueble) {
        try {
            persistencia.create(mueble);
        } catch (OperacionInvalidaException ex) {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Se elimina un mueble del sistema dado su identificador único
     *
     * @param id Identificador único del mueble
     */
    @Override
    public void eliminarMueble(long id) {
        Mueble m = (Mueble) persistencia.findById(Mueble.class, id);
        try {
            persistencia.delete(m);
        } catch (OperacionInvalidaException ex) {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Remueve un ejemplar del mueble (no el mueble)
     *
     * @param id Identificador único del mueble
     */
    @Override
    public void removerEjemplarMueble(long id) {
        ArrayList<Mueble> muebles = (ArrayList<Mueble>) persistencia.findAll(Mueble.class);
        Mueble mueble;
        for (int i = 0; i < muebles.size(); i++) {
            mueble = muebles.get(i);
            if (mueble.getReferencia() == id) {
                int cantidad = mueble.getCantidad();
                mueble.setCantidad(cantidad - 1);
                persistencia.update(mueble);
                break;
            }
        }
    }

    /**
     * Devuelve los muebles del sistema
     *
     * @return muebles Arreglo con todos los muebles del sistema
     */
    @Override
    public List<Mueble> darMuebles() {
        return persistencia.findAll(Mueble.class);
    }

    /**
     * Devuelve las promociones del sistema
     *
     * @return muebles Arreglo con todos las promociones del sistema
     */
    @Override
    public List<Promocion> darPromociones() {
        return persistencia.findAll(Promocion.class);
    }

    /**
     * Crea una promocion en el Sistema sin asosicar a un mueble
     *
     * @param promocion
     */
    @Override
    public void agregarPromocion(Promocion promocion) {
        try {
            persistencia.create(promocion);
        } catch (OperacionInvalidaException ex) {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Se elimina una promocion del sistema dado su identificador único
     *
     * @param codigo Identificador único de la promocion
     */
    @Override
    public void eliminarPromocion(String codigo) {
        Promocion m = (Promocion) persistencia.findById(Promocion.class, codigo);
        try {
            persistencia.delete(m);
        } catch (OperacionInvalidaException ex) {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Asocia una promocion a un mueble del sistema y hace la notificacion de la
     * nueva creacion de una promocion en un mueble
     *
     * @param promocion
     * @param mueble
     * @throws OperacionInvalidaException
     */
    @Override
    public void asociarPromocion(final Promocion promocion, final Mueble mueble) throws OperacionInvalidaException {

        try {

            promocion.setMueble(mueble);
            persistencia.update(promocion);

            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer((Destination) topic);
            try {
                messageProducer.send(createPromocionMessage(session, promocion));
            } catch (JMSException ex) {
                Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (session != null) {
                    try {
                        session.close();
                    } catch (JMSException e) {
                        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Error cerrando la"
                                + " sesión", e);
                    }
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (JMSException ex) {
            Logger.getLogger(ServicioCatalogoMock.class.getName()).log(Level.SEVERE, "Error "
                    + "enviando la notificación de creación de una nueva promoción", ex);
        }
    }

    /**
     * Crea el mensaje de la promocion
     *
     * @param session
     * @return
     * @throws JMSException
     */
    private Message createPromocionMessage(final Session session, final Promocion promocion) throws JMSException {
        String msg = "Creacion de promoción";
        TextMessage tm = session.createTextMessage();
        tm.setText(msg);
        tm.setObjectProperty("Promocion", promocion);
        return tm;
    }

}
