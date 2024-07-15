package med.voll.api.infra.errores;

public class ValdacionDeIntegridad extends RuntimeException {
    public ValdacionDeIntegridad(String s) {
        super(s);
    }
}
