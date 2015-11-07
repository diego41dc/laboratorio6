/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Promocion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author da.lozano13
 */
@Stateless
public class ServicioVentasMock implements IServicioVentasLocal, IServicioVentasRemote {

    /**
     * Creda una promocion
     *
     * @param promocion
     */
    @Override
    public void crearPromocion(Promocion promocion) {
        Logger.getLogger(ServicioVentasMock.class.getName()).
                log(Level.INFO, "Área ventas: Se creó la Promoción '"
                        + promocion.getNombre() + "'  para el Mueble '" + promocion.getMueble().getNombre() + "'");
    }

}
