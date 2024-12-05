package aed;

public class Recordatorio {
    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = mensaje;
        this.fecha = new Fecha(fecha);
        this.horario = horario;
    }

    public Horario horario() {
        return horario;
    }

    public Fecha fecha() {
        Fecha fecha2 = new Fecha(this.fecha);
        return fecha2;
    }

    public String mensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return this.mensaje + " @ " + this.fecha + " " + this.horario;
    }

    @Override
    public boolean equals(Object otro) {
        if (this.getClass() != otro.getClass()){
            return false;
        }
        else {
            Recordatorio recordatorio_posta = (Recordatorio) otro;
            return (recordatorio_posta.mensaje == this.mensaje && recordatorio_posta.horario.equals(this.horario) && recordatorio_posta.fecha.equals(this.fecha));
        }
    }

}
