package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class CiudadesTests {
    int cantCiudades;
    Traslado[] listaTraslados;
    ArrayList<Integer> actual;

    @BeforeEach
    void init() {
        // Reiniciamos los valores de las ciudades y traslados antes de cada test
        cantCiudades = 7;
        listaTraslados = new Traslado[] { new Traslado(1, 0, 6, 100, 1), new Traslado(2, 1, 5, 100, 2),
                new Traslado(3, 2, 4, 100, 3), new Traslado(4, 3, 3, 100, 4), new Traslado(5, 4, 2, 100, 5),
                new Traslado(6, 5, 1, 100, 6), new Traslado(7, 6, 0, 100, 7) };
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
    void todosEnListas() {
        Ciudades prueba = new Ciudades(7);

        Traslado[] lista = new Traslado[1];

        for (int i = 0; i < 7; i++) {
            lista[0] = listaTraslados[i];

            prueba.despachar(lista);

            switch (i) {
            case 0:
                assertSetEquals(new ArrayList<>(Arrays.asList(0)), prueba.mayorGanancia());
                assertSetEquals(new ArrayList<>(Arrays.asList(6)), prueba.mayorPerdida());
                break;
            case 1:
                assertSetEquals(new ArrayList<>(Arrays.asList(0, 1)), prueba.mayorGanancia());
                assertSetEquals(new ArrayList<>(Arrays.asList(6, 5)), prueba.mayorPerdida());
                break;
            case 2:
                assertSetEquals(new ArrayList<>(Arrays.asList(0, 1, 2)), prueba.mayorGanancia());
                assertSetEquals(new ArrayList<>(Arrays.asList(6, 5, 4)), prueba.mayorPerdida());
                break;
            case 3:
                assertSetEquals(new ArrayList<>(Arrays.asList(0, 1, 2, 3)), prueba.mayorGanancia());
                assertSetEquals(new ArrayList<>(Arrays.asList(6, 5, 4, 3)), prueba.mayorPerdida());
                break;
            case 4:
                assertSetEquals(new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4)), prueba.mayorGanancia());
                assertSetEquals(new ArrayList<>(Arrays.asList(6, 5, 4, 3, 2)), prueba.mayorPerdida());
                break;
            case 5:
                assertSetEquals(new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5)), prueba.mayorGanancia());
                assertSetEquals(new ArrayList<>(Arrays.asList(6, 5, 4, 3, 2, 1)), prueba.mayorPerdida());
                break;
            case 6:
                assertSetEquals(new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6)), prueba.mayorGanancia());
                assertSetEquals(new ArrayList<>(Arrays.asList(6, 5, 4, 3, 2, 1, 0)), prueba.mayorPerdida());
                break;
            }

        }
    }

    @Test
    void superavit() {
        Ciudades prueba = new Ciudades(7);

        Traslado[] lista = new Traslado[1];

        assertEquals(0, prueba.mayorSuperavit());

        for (int i = 0; i < 7; i++) {
            lista[0] = listaTraslados[i];

            prueba.despachar(lista);

            assertEquals(0, prueba.mayorSuperavit());
        }
    }

    @Test
    void gananciaPromedio() {
        Ciudades prueba = new Ciudades(7);

        Traslado[] lista = new Traslado[1];

        assertEquals(0, prueba.gananciaPromedioPorTraslado());

        for (int i = 0; i < 7; i++) {
            lista[0] = listaTraslados[i];

            prueba.despachar(lista);

            assertEquals(100, prueba.gananciaPromedioPorTraslado());
        }
    }
}
