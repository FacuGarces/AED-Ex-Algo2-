package aed;


public class Agenda {
    private ArregloRedimensionableDeRecordatorios array_recordatorios;
    private Fecha fecha;

    public Agenda(Fecha fechaActual) {
        this.array_recordatorios = new ArregloRedimensionableDeRecordatorios();
        this.fecha = fechaActual;
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        array_recordatorios.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        String mensaje = new String(fechaActual() + "\n=====\n");
        for(int i=0; i < this.array_recordatorios.longitud(); i++){
            if(fechaActual().equals(this.array_recordatorios.obtener(i).fecha())){
                mensaje += this.array_recordatorios.obtener(i).toString() + "\n";
            }
        }
        return mensaje;
    }

    public void incrementarDia() {
        this.fecha.incrementarDia();
    }

    public Fecha fechaActual() {
        Fecha fecha_posta = new Fecha(this.fecha);
        return fecha_posta;
    }

}





    

