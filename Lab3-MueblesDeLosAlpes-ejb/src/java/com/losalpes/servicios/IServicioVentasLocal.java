/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Promocion;
import javax.ejb.Local;

/**
 * Interfaz que tiene el servicio del Ventas Local
 * @author da.lozano13
 */
@Local
public interface IServicioVentasLocal {
    
    /**
     * Crea una promocion
     * @param promocion 
     */
    public void crearPromocion(Promocion promocion);
}
