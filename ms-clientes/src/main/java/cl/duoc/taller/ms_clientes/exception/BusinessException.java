package cl.duoc.taller.ms_clientes.exception;

/**
 * Excepcion para violaciones de reglas de negocio del dominio.
 * Ej: un cliente con RUT duplicado, formato invalido, etc.
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String mensaje) {
        super(mensaje);
    }
}
