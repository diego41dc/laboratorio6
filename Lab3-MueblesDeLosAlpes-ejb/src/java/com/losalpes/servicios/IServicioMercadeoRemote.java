/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import javax.ejb.Remote;

/**
 *
 * @author da.lozano13
 */
@Remote
public interface IServicioMercadeoRemote {
    /**
     * Registro de informacion del producto
     * @param mueble 
     */ 
    public void registrarProductoPromocion(Mueble mueble);
}
