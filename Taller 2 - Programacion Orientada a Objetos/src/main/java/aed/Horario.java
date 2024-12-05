package aed;

public class Horario {
    private int hora;
    private int minutos;

    public Horario(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public int hora() {
        return hora;
    }

    public int minutos() {
        return minutos;
    }

    @Override
    public String toString() {
        return  String.valueOf(hora) + ":" + String.valueOf(minutos);
    }

    @Override
    public boolean equals(Object otro) {
        if (otro instanceof Horario){
            Horario horario_posta = (Horario) otro;
            return (this.hora == horario_posta.hora() && this.minutos == horario_posta.minutos());
        }
        else {
            return false;
        }  
    }

}
