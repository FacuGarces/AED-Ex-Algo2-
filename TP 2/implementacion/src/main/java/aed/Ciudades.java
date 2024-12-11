package aed;

import java.util.ArrayList;

//Esta clase vamos a usarla para crear el conjunto de ciudades y sacar estadistica
public class Ciudades {
    InfoCiudades ciudades;

    int cantidadTraslados;
    int gananciaPromedio;

    // Constructor
    public Ciudades(int cantidad) { // O(C)
        ciudades = new InfoCiudades(cantidad); // O(C)

        cantidadTraslados = 0; // O(1)
        gananciaPromedio = 0; // O(1)
    }

    // - Funciones Best Effort -
    public int[] despachar(Traslado[] traslados) { // O(n Log C)
        int[] trasladosMasRedituables = new int[traslados.length]; // O(1)

        for (int i = 0; i < traslados.length; i++) { // O(n)
            ciudades.registrarTraslado(traslados[i]); // O(Log C)

            trasladosMasRedituables[i] = traslados[i].id; // O(1)

            cantidadTraslados++; // O(1)
            gananciaPromedio += traslados[i].gananciaNeta; // O(1)
        }

        return trasladosMasRedituables;
    }

    public int mayorSuperavit() { // O(1)
        return ciudades.mayorSuperavit(); // O(1)
    }

    public int gananciaPromedioPorTraslado() { // O(1)
        if (cantidadTraslados == 0) // O(1)
            return 0;
        return gananciaPromedio / cantidadTraslados; // O(1)
    }

    public ArrayList<Integer> mayorGanancia() { // O(1)
        return ciudades.ciudadesMayorGanancia(); // O(1)
    }

    public ArrayList<Integer> mayorPerdida() { // O(1)
        return ciudades.ciudadesMayorPerdida(); // O(1)
    }
}
