package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValdacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    public void agendar(DatosAgendarConsultar datos){
        if (pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValdacionDeIntegridad("Este ID Para El Paciente No Fue Encontrado");
        }

        if (datos.idMedico() != null && medicoRepository.existsById(datos.idMedico())){
            throw new ValdacionDeIntegridad("Este ID Para El Medico No Fue Encontrado");
        }

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var medico = seleccionarMedico(datos);
        var consulta = new Consulta(null,medico,paciente,datos.fecha());
        consultaRepository.save(consulta);
    }

    private Medico seleccionarMedico(DatosAgendarConsultar datos) {
        if (datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null){
            throw new ValdacionDeIntegridad("Debe Seleccionarse Una Especialidad Para El Medico");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(),datos.fecha());
    }
}
