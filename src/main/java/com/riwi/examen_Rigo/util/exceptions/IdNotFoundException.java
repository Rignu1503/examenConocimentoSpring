package com.riwi.examen_Rigo.util.exceptions;

public class IdNotFoundException  extends RuntimeException {
    
    private static final String ERROR_MESSAGE = "No hay registro en la entidad %s con el id suministrado";

    // utilizamos el constructor de runtime y eviamos el mensaje inyectando el
    // nombre de la entidad
    public IdNotFoundException(String nameEntity){

        super(String.format(ERROR_MESSAGE, nameEntity));
    }
}
