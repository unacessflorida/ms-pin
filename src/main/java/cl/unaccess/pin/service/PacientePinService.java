package cl.unaccess.pin.service;

import cl.unaccess.pin.model.PacientePin;
import cl.unaccess.pin.dto.PacientePinDTO;
import cl.unaccess.pin.repository.PacientePinRepository;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt; 
import java.util.Optional;

@Service
public class PacientePinService {

    private final PacientePinRepository repo;

    public PacientePinService(PacientePinRepository repo) {
        this.repo = repo;
    }

    public PacientePin registrarPin(PacientePinDTO dto) {
        if (repo.findByPacienteRut(dto.getPacienteRut()).isPresent()) {
            throw new RuntimeException("Este paciente ya tiene un PIN asignado");
        }
        
        PacientePin pp = new PacientePin();
        pp.setPacienteRut(dto.getPacienteRut());
        
        
        String pinHasheado = BCrypt.hashpw(dto.getPin(), BCrypt.gensalt());
        pp.setPin(pinHasheado);
        
        return repo.save(pp);
    }

    public boolean verificarPin(String rut, String pinIngresado) {
        Optional<PacientePin> pinOpt = repo.findByPacienteRut(rut);
        if (pinOpt.isPresent()) {
            String pinBaseDatos = pinOpt.get().getPin();
            
            
            try {
                return BCrypt.checkpw(pinIngresado, pinBaseDatos);
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}