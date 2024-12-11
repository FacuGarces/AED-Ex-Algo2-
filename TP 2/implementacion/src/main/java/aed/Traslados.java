package aed;

public class Traslados {
    HeapsTraslados listaPrioridad;

    // Constructor
    public Traslados(Traslado[] traslados) { // O(T)
        listaPrioridad = new HeapsTraslados(traslados); // O(T)
    }

    // Funciones de Best Effort
    public void agregar(Traslado[] traslados) { // O(n Log T)
        listaPrioridad.agregarLista(traslados); // O(n Log T)
    }

    public Traslado[] masAntiguos(int n) { // O(n Log T)
        return listaPrioridad.masAntiguos(n); // O(n Log T)
    }

    public Traslado[] masRedituables(int n) { // O(n Log T)
        return listaPrioridad.masRedituables(n); // O(n Log T)
    }

    // Funcion Auxiliar
    public boolean isEmpty() { // O(1)
        return listaPrioridad.isEmpty(); // O(1)
    }
}
