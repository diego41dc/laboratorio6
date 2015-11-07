/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.jms;

import com.losalpes.entities.Promocion;
import com.losalpes.servicios.IServicioMercadeoLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Clase que atiende los MDB de Mercadeo
 *
 * @author da.lozano13
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/promocionTopic"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "durable"),
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/promocionTopic"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/promocionTopic")
})
public class PromocionMercadeoMessage implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;

    @EJB
    private IServicioMercadeoLocal sm;

    public PromocionMercadeoMessage() {
    }

    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;
        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;

                Promocion promocion = (Promocion) msg.getObjectProperty("Promocion");

                Logger.getLogger(PromocionCallCenterMessage.class.getName()).log(Level.INFO,
                        "Área Mercadeo: Se ha recibido la notificación de una nueva promoción");

                sm.registrarProductoPromocion(promocion.getMueble());

            } else {
                Logger.getLogger(PromocionMercadeoMessage.class.getName()).log(Level.SEVERE,
                        "Mensaje de tipo equivocado: " + message.getClass().getName());
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }
    }

}
