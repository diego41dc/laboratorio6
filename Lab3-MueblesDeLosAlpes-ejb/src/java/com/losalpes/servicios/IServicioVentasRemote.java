/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Promocion;
import javax.ejb.Remote;

/**
 * Interfaz que tiene el servicio del Ventas Remota
 * @author da.lozano13
 */
@Remote
public interface IServicioVentasRemote {
    /**
     * Crea una promocion
     * @param promocion 
     */
    public void crearPromocion(Promocion promocion);
}
