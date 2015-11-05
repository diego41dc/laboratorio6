/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Promocion;
import com.losalpes.entities.Vendedor;
import com.losalpes.excepciones.OperacionInvalidaException;
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
 *
 * @author da.lozano13
 */
@Stateless
public class ServicioVentas implements IServicioVentasLocal {

   /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    @EJB
    private IServicioPersistenciaMockLocal persistencia;
    
    @Resource(mappedName="jms/promocionVentasTopicFactory")
    private ConnectionFactory connectionFactory;
 
    @Resource(mappedName="jms/promocionVentasTopic")
    private Topic topic;
 
    private Promocion cPromocion;
    
    public void agregarPromocion(Promocion promocion) throws OperacionInvalidaException
    {
        try
        {
            cPromocion=promocion;
            persistencia.create(promocion);
        }
        catch (OperacionInvalidaException ex)
        {
            throw new OperacionInvalidaException(ex.getMessage());
        }
        try
        {
            notificarModificacionPromocion();
        } catch (JMSException ex)
        {
            Logger.getLogger(ServicioVendedoresMock.class.getName()).log(Level.SEVERE, "Error "
                    + "enviando la notificación de creación de un vendedor", ex);
        }
    }
    
    public Message createModificacionPromocionMessage(Session session) throws JMSException
    {
        String msg = /*"Vendedor: " + cPromocion.getNombres()+" "+cPromocion.getApellidos() + "\n";
        msg += "Cargo: " + cPromocion.getPerfil() + "\n";
        msg += "Salario: " + cPromocion.getSalario() + "\n"*/ "Aqui va el mensaje";//TODO modificar por el mensaje que va
        TextMessage tm = session.createTextMessage();
        tm.setText(msg);
        return tm;
    }
    
    public void notificarModificacionPromocion() throws JMSException 
     {
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageProducer messageProducer = session.createProducer((Destination) topic);
        try 
        {
            messageProducer.send(createModificacionPromocionMessage(session));
        } 
        catch (JMSException ex) 
        {
            Logger.getLogger(ServicioVentas.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally 
        {
            if (session != null) 
            {
                try 
                {
                    session.close();
                } 
                catch (JMSException e) 
                {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Error cerrando la"
                            + " sesión", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
