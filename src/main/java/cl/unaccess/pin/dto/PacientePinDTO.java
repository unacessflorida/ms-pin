package cl.unaccess.pin.dto;

public class PacientePinDTO {
    private String pacienteRut;
    private String pin;

    public PacientePinDTO() {}

    public String getPacienteRut() { return pacienteRut; }
    public void setPacienteRut(String pacienteRut) { this.pacienteRut = pacienteRut; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
}