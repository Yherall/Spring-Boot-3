package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroMedico(
        @NotBlank //evita que nombre llegue nulo o blanco
        String nombre,
        @NotBlank //evita que nombre llegue nulo o blanco
        @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank //evita que nombre llegue nulo o blanco
        @Pattern(regexp = "\\d{4,6}")
        String documento,
        @NotNull //Como es objeto nunca llega en blanco si no con NULL, por eso se utiliza NOT NULL
        Especialidad especialidad,
        @NotNull //Como es objeto nunca llega en blanco si no con NULL, por eso se utiliza NOT NULL
        @Valid
        DatosDireccion direccion) {

}
