/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losalpes.servicios;

import com.losalpes.entities.Promocion;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author da.lozano13
 */
@Stateless
public class ServicioCallCenterMock implements IServicioCallCenterLocal, IServicioCallCenterRemote {

    /**
     * Registro de la informacion de la promocion
     *
     * @param promocion
     */
    @Override
    public void registrarPromocion(Promocion promocion) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Logger.getLogger(ServicioCallCenterMock.class.getName()).log(Level.INFO,
                "Área CallCenter: Se ha creado la Promoción '" + promocion.getNombre() + "' "
                + "para el Producto '" + promocion.getMueble().getNombre() + "'. "
                + "La promoción aplica desde el dia " + sdf.format(promocion.getFechaInicio()) + " hasta el dia " + sdf.format(promocion.getFechaFinalizacion()));
    }

}
