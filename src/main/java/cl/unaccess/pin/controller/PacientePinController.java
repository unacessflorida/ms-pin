package cl.unaccess.pin.controller;

import cl.unaccess.pin.model.PacientePin;
import cl.unaccess.pin.dto.PacientePinDTO;
import cl.unaccess.pin.service.PacientePinService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/pin")
public class PacientePinController {

    private final PacientePinService service;

    public PacientePinController(PacientePinService service) {
        this.service = service;
    }

    
    @PostMapping
    public ResponseEntity<PacientePin> crear(@RequestBody PacientePinDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarPin(dto));
    }

    
    @GetMapping("/verificar")
    public ResponseEntity<Boolean> verificar(@RequestParam String rut, @RequestParam String pin) {
        boolean esValido = service.verificarPin(rut, pin);
        return ResponseEntity.ok(esValido);
    }
}