package cl.duoc.taller.ms_clientes.exception;

/**
 * Excepcion personalizada que se lanza cuando un recurso (ej: un cliente)
 * no es encontrado en la base de datos.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
