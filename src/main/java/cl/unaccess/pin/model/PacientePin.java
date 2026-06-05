package cl.unaccess.pin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pacientes_pin")
public class PacientePin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String pacienteRut;
    private String pin; 

    public PacientePin() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPacienteRut() { return pacienteRut; }
    public void setPacienteRut(String pacienteRut) { this.pacienteRut = pacienteRut; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
}