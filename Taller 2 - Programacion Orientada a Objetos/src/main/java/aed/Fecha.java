package aed;

public class Fecha {
    private int dia;
    private int mes;
    
    public Fecha(int dia_variable, int mes_variable) {
        this.dia = dia_variable;
        this.mes = mes_variable;
    }

    public Fecha(Fecha fecha_variable) {
        this.dia = fecha_variable.dia;
        this.mes = fecha_variable.mes;
    }

    public Integer dia() {
        return dia;
    }

    public Integer mes() {
        return mes;
    }

    public String toString() {
        return  String.valueOf(dia) + "/" + String.valueOf(mes); 
    }

    @Override
    public boolean equals(Object otra) {
        if (otra instanceof Fecha){
            Fecha fechaposta = (Fecha) otra;
            return (this.dia == fechaposta.dia() && this.mes == fechaposta.mes());
        }
        else {
            return false;
        }  
    }

    public void incrementarDia() {
        if (diasEnMes(mes) < dia + 1){
            if (mes == 12){
                mes = 0;
            }
            mes = mes + 1;
            dia = 0;
        }
        dia = dia + 1;
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
