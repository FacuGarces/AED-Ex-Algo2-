package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TrasladosTests {
    int cantCiudades;
    Traslado[] listaTraslados;
    ArrayList<Integer> actual;

    @BeforeEach
    void init() {
        // Valores iniciales para los test de Traslados
        listaTraslados = new Traslado[] { new Traslado(1, 0, 1, 100, 10), new Traslado(2, 0, 1, 400, 20),
                new Traslado(3, 3, 4, 500, 50), new Traslado(4, 4, 3, 500, 11), new Traslado(5, 1, 0, 1000, 40),
                new Traslado(6, 1, 0, 1000, 41), new Traslado(7, 6, 3, 2000, 42) };
    }

    void assertSetEquals(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        assertEquals(s1.size(), s2.size());
        for (int e1 : s1) {
            boolean encontrado = false;
            for (int e2 : s2) {
                if (e1 == e2)
                    encontrado = true;
            }
            assertTrue(encontrado, "No se encontró el elemento " + e1 + " en el arreglo " + s2.toString());
        }
    }

    @Test
    void inicializacionTrasladosConLista() {
        Traslados system = new Traslados(listaTraslados);

        assertEquals(false, system.isEmpty());
    }

    @Test
    void inicializacionTrasladosSinLista() {
        Traslados system = new Traslados(new Traslado[0]);

        assertEquals(true, system.isEmpty());
    }

    @Test
    void extraerMasDelMaximoAntiguos() {
        Traslados system = new Traslados(listaTraslados);

        Traslado[] extraidos = system.masAntiguos(9);

        assertEquals(7, extraidos.length);

        assertEquals(true, system.isEmpty());
    }

    @Test
    void extraerMasDelMaximoRedituables() {
        Traslados system = new Traslados(listaTraslados);

        Traslado[] extraidos = system.masRedituables(9);

        assertEquals(7, extraidos.length);

        assertEquals(true, system.isEmpty());
    }

    @Test
    void extraerVacioRedituables() {
        Traslados system = new Traslados(new Traslado[0]);

        assertEquals(true, system.isEmpty());

        Traslado[] extraidos = system.masRedituables(9);

        assertEquals(0, extraidos.length);

        assertEquals(true, system.isEmpty());
    }

    @Test
    void extraerVacioAntiguos() {
        Traslados system = new Traslados(new Traslado[0]);

        assertEquals(true, system.isEmpty());

        Traslado[] extraidos = system.masAntiguos(9);

        assertEquals(0, extraidos.length);

        assertEquals(true, system.isEmpty());
    }
}
