package cl.duoc.taller.ms_ordenes_trabajo.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje) { super(mensaje); }
}
