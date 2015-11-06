/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Promocion;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author da.lozano13
 */
@Stateless
public class ServicioVentasMock implements IServicioVentasLocal, IServicioVentasRemote {

   /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    @EJB
    private IServicioPersistenciaMockLocal persistencia;

    /**
     * Crea una promocion
     * @param promocion 
     */
    @Override
    public void crearPromocion(Promocion promocion) {
         //TODO usar la clase ServicioVenddedoresMock como ejemplo
    }
    
    
   
    
   
}
