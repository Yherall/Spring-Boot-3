package med.voll.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    private String calle;
    private String numero;
    private String comuna;
    private String complemento;
    private String ciudad;

    public Direccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.comuna = direccion.comuna();
        this.complemento = direccion.complemento();
        this.ciudad = direccion.ciudad();
    }

    public Direccion actualizarDatos(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.comuna = direccion.comuna();
        this.complemento = direccion.complemento();
        this.ciudad = direccion.ciudad();
        return this;
    }

    public String getCalle() {return calle;}
    public void setCalle(String calle) {this.calle = calle;}

    public String getNumero() {return numero;}
    public void setNumero(String numero) {
        this.numero = numero;}

    public String getComuna() {return comuna;}
    public void setComuna(String comuna) {this.comuna = comuna;}

    public String getComplemento() {return complemento;}
    public void setComplemento(String complemento) {this.complemento = complemento;}

    public String getCiudad() {return ciudad;}
    public void setCiudad(String ciudad) {this.ciudad = ciudad;}

    public Direccion actualizarDireccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.numero = direccion.numero();
        this.comuna = direccion.comuna();
        this.complemento = direccion.complemento();
        this.ciudad = direccion.ciudad();
        return this;
    }
}
