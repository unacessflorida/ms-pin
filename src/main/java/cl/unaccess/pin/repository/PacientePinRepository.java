package cl.unaccess.pin.repository;

import cl.unaccess.pin.model.PacientePin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PacientePinRepository extends JpaRepository<PacientePin, Long> {
    Optional<PacientePin> findByPacienteRut(String pacienteRut);
}