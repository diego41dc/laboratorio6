/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Mueble;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author da.lozano13
 */
@Stateless
public class ServicioMercadeoMock implements IServicioMercadeoLocal, IServicioMercadeoRemote {

    /**
     * Registro de la informacion del producto
     *
     * @param mueble
     */
    @Override
    public void registrarProductoPromocion(Mueble mueble) {
        Logger.getLogger(ServicioMercadeoMock.class.getName()).log(Level.INFO,
                "Área Mercadeo: Se ha creado una promocion para el producto: "
                + mueble.getNombre());
    }

}
