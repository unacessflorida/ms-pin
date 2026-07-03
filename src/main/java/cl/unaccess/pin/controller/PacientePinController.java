package cl.unaccess.pin.controller;

import cl.unaccess.pin.model.PacientePin;
import cl.unaccess.pin.dto.PacientePinDTO;
import cl.unaccess.pin.service.PacientePinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/pin")
@Tag(name = "PIN Pacientes", description = "Gestion de PIN de acceso para pacientes")
public class PacientePinController {

    private final PacientePinService service;

    public PacientePinController(PacientePinService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar un PIN para un paciente (valida paciente)")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "PIN registrado",
            content = @Content(examples = @ExampleObject(value = "{\"pacienteRut\":\"12345678-9\",\"pin\":\"4821\"}"))),
        @ApiResponse(responseCode = "500", description = "Paciente no existe o ya tiene PIN")
    })
    @PostMapping
    public ResponseEntity<PacientePin> crear(@RequestBody PacientePinDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarPin(dto));
    }

    @Operation(summary = "Verificar si un PIN ingresado es correcto para un paciente")
    @ApiResponse(responseCode = "200", description = "true si el PIN es valido, false si no")
    @GetMapping("/verificar")
    public ResponseEntity<Boolean> verificar(@RequestParam String rut, @RequestParam String pin) {
        boolean esValido = service.verificarPin(rut, pin);
        return ResponseEntity.ok(esValido);
    }
}