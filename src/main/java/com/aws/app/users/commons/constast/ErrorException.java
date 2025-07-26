package com.aws.app.users.commons.constast;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorException {


    ENTITY_MAPPING_ERROR_MESSAGE ("Error al mapear los datos del usuario."),
    GENERAL_ERROR_MESSAGE ("Ha ocurrido un error inesperado. Por favor, intenta nuevamente más tarde."),
    USER_SAVE_ERROR_MESSAGE("Error al guardar el usuario."),
    USER_FETCH_ERROR_MESSAGE("Error al consultar usuario"),
    USER_NOT_FOUND_ERROR_MESSAGE("No se encontró el usuario solicitado.") ,
    USER_PERSISTENCE_ERROR_MESSAGE("Ocurrió un error al guardar o actualizar el usuario en la base de datos."),
    USER_QUERY_ERROR_MESSAGE("Usuario no encontrado con identificador: "),
    USER_QUERY_SUCCESS_MESSAGE ("Consulta exitosa."),
    USER_REGISTERED_SUCCESS_MESSAGE("Se ha registrado el usuario exitosamente."),
    USER_WITH_IDENTIFICATION_EXISTS("El usuario con identificación %s ya existe.");

    private final String message;

}
