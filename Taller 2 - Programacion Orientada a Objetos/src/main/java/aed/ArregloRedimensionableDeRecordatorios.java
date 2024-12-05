package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] recordatorio_array;
    private int length_array;
 
    public ArregloRedimensionableDeRecordatorios() {
        this.recordatorio_array = new Recordatorio[0];
        this.length_array = 0;
    }

    public int longitud() {
        return length_array;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio[] new_array = new Recordatorio[this.length_array + 1];
        for(int j = 0; j < this.length_array; j++){
            new_array[j] = this.recordatorio_array[j]; 
        }
        new_array[this.length_array] = i;
        this.recordatorio_array = new_array;
        this.length_array++;
    }

    public Recordatorio obtener(int i) {
        return this.recordatorio_array[i];
    }

    public void quitarAtras() {
        Recordatorio[] new_array = new Recordatorio[this.length_array - 1];
        for(int j = 0; j < this.length_array - 1; j++){
            new_array[j] = this.recordatorio_array[j];
        }
        this.recordatorio_array = new_array;
        this.length_array--;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        this.recordatorio_array[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        this.length_array = vector.length_array;
        this.recordatorio_array = new Recordatorio[vector.length_array];
        for(int i=0; i < vector.length_array; i++){
            this.recordatorio_array[i] = vector.obtener(i);
        }
        this.length_array = vector.longitud();
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
    ArregloRedimensionableDeRecordatorios otro_recordatorio = new ArregloRedimensionableDeRecordatorios();
    for(int i=0; i < this.length_array; i++){
        otro_recordatorio.agregarAtras(this.recordatorio_array[i]);
    }
    return otro_recordatorio;
    }
}
