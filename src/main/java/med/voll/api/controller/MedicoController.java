package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                                                UriComponentsBuilder uriComponentsBuilder){
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(),medico.getEspecialidad().toString(), new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getComuna(),
                medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), medico.getDireccion().getComplemento()));

        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri(); //Se crea la URL dinamicamente con ID dinamico
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(sort = "nombre") Pageable paginacion){
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarRegistroMedico(@RequestBody @Valid DatosActualizarRegistroMedico datosActualizarRegistroMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarRegistroMedico.id());
        medico.actualizarDatos(datosActualizarRegistroMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(),medico.getEspecialidad().toString(), new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getComuna(),
                medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), medico.getDireccion().getComplemento())));
    }

    // DELETE LOGICO CAMBIAR ACTIVO A FALSE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedicos = new DatosRespuestaMedico(medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(),medico.getEspecialidad().toString(), new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getComuna(),
                medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(), medico.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosMedicos);
    }
}

