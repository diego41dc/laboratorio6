/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ Promocion.java
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 * Licenciado bajo el esquema Academic Free License version 3.0
 *
 * Ejercicio: Muebles de los Alpes
 * 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package com.losalpes.entities;

/**
 * Clase que representa una promocion en el sistema
 * 
 */
public class Promocion
{
      
    //-----------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------

    /**
     * Nombre de la promocion
     */
    private String nombre;

    /**
     * Devuelve el nombre de la ciudad
     * @return nombre Nombre de la ciudad
     */
    public String getNombre()
    {
        return nombre;
    }

    //-----------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------

    /**
     * Constructor de la clase (sin argumentos)
     */
    public Promocion()
    {

    }

    /**
     * Constructor de la clase (con argumentos)
     * @param nombre
     */
    public Promocion(String nombre)
    {
        this.nombre = nombre;
    }

    //-----------------------------------------------------------
    // Getters y setters
    //-----------------------------------------------------------

    /**
     * Modifica el nombre de la ciudad
     * @param nombre Nuevo nombre de la ciudad
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

}
