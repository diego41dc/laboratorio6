/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import com.losalpes.entities.Promocion;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author da.lozano13
 */
@Stateless
public class ServicioCallCenterMock implements IServicioCallCenterLocal, IServicioCallCenterRemote {

     /**
     * Interface con referencia al servicio de persistencia en el sistema
     */
    @EJB
    private IServicioPersistenciaMockLocal persistencia;
    
    /**
     * Registro de la informacion de la promocion
     * @param promocion 
     */
    @Override
    public void registrarPromocion(Promocion promocion, Mueble mueble){
        //TODO usar la clase ServicioVenddedoresMock como ejemplo
    }
    
}
