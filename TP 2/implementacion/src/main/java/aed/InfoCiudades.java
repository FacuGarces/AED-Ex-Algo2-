package aed;

import java.util.ArrayList;

// Se dejo el Heap y las listas juntas ya que ambas depende 
// del ArrayList de ciudades y los necesito para modificar sus valores
public class InfoCiudades {
    ArrayList<CiudadIndex> ciudades;

    HeapSuperavit superavits;

    ArrayList<Integer> listaGanancias;
    ArrayList<Integer> listaPerdidas;

    // Constructor

    // Calculo de complejidad
    // O(C + C) = O(2C) = O(C)
    public InfoCiudades(int cantidad) { // O(C)
        ciudades = new ArrayList<CiudadIndex>(); // O(1)
        listaGanancias = new ArrayList<Integer>(); // O(1)
        listaPerdidas = new ArrayList<Integer>(); // O(1)

        SuperavitIndex[] listaTemporal = new SuperavitIndex[cantidad]; // O(1)

        for (int i = 0; i < cantidad; i++) { // O(C)
            // Como trabajamos con ArrayList, este add los podemos tomar como un O(1)
            // amortizado, que seria nuestra complejidad final
            ciudades.add(new CiudadIndex(i)); // O(1)

            listaGanancias.add(i); // O(1)
            listaPerdidas.add(i); // O(1)

            listaTemporal[i] = new SuperavitIndex(i); // O(1)
        }

        superavits = new HeapSuperavit(listaTemporal); // O(C)
    }

    // Funciones

    // Calculo de Complejidaad
    // O(Log C + Log C) = (2 Log C) = O(Log C)
    public void registrarTraslado(Traslado traslado) { // O(Log C)
        // Heap y Ciudades
        ciudades.get(traslado.origen).modificarGanancia(traslado.gananciaNeta); // O(1)

        superavits.modificar(ciudades.get(traslado.origen).posHeap,
                new SuperavitIndex(traslado.origen, ciudades.get(traslado.origen).superavit())); // O(Log C)

        ciudades.get(traslado.destino).modificarPerdida(traslado.gananciaNeta); // O(1)

        superavits.modificar(ciudades.get(traslado.destino).posHeap,
                new SuperavitIndex(traslado.destino, ciudades.get(traslado.destino).superavit())); // O(Log C)

        // Listas

        // Como todas estas operaciones son get, clear y add las cuales son O(1)
        // (Tambien add ya que esta amortizado), todos estos chequeos son de complejidad
        if (listaGanancias.get(0) == traslado.origen
                || ciudades.get(listaGanancias.get(0)).ganancia() < ciudades.get(traslado.origen).ganancia()) {// O(1)
            listaGanancias.clear();// O(1)
            listaGanancias.add(traslado.origen);// O(1)
        } else if (ciudades.get(listaGanancias.get(0)).ganancia() == ciudades.get(traslado.origen).ganancia()) {// O(1)
            listaGanancias.add(traslado.origen);// O(1)
        }

        if (listaPerdidas.get(0) == traslado.destino
                || ciudades.get(listaPerdidas.get(0)).perdida() < ciudades.get(traslado.destino).perdida()) {// O(1)
            listaPerdidas.clear();
            listaPerdidas.add(traslado.destino);// O(1)
        } else if (ciudades.get(listaPerdidas.get(0)).perdida() == ciudades.get(traslado.destino).perdida()) {// O(1)
            listaPerdidas.add(traslado.destino);// O(1)
        }
    }

    public int mayorSuperavit() { // O(1)
        return superavits.returnMax().ciudad; // O(1)
    }

    public ArrayList<Integer> ciudadesMayorGanancia() { // O(1)
        return listaGanancias; // O(1)
    }

    public ArrayList<Integer> ciudadesMayorPerdida() { // O(1)
        return listaPerdidas; // O(1)
    }

    // - Clase del Heap
    class HeapSuperavit extends Heap<SuperavitIndex> {
        // Constructor
        public HeapSuperavit(SuperavitIndex[] array) { // O(C)
            super(array); // O(C)
        }

        // Funciones Modificadas
        @Override
        public void swap(int child, int parent) { // O(1)
            // Primerpo modifico los indexes ciudades
            ciudades.get(lista.get(child).getCiudad()).cambiarPos(parent); // O(1)
            ciudades.get(lista.get(parent).getCiudad()).cambiarPos(child); // O(1)

            // Luego realizo el Swap del Heap
            SuperavitIndex temp = lista.get(child); // O(1)

            lista.set(child, lista.get(parent)); // O(1)

            lista.set(parent, temp); // O(1)
        }
    }

    // - Clases para Ciudades -
    public class Ciudad {
        int ganancia;
        int perdida;

        public Ciudad() {
            ganancia = 0;
            perdida = 0;
        }

        public int superavit() {
            return ganancia - perdida;
        }
    }

    public class CiudadIndex {
        private Ciudad ciudad;
        private int posHeap;

        public CiudadIndex(int index) {
            ciudad = new Ciudad();
            posHeap = index;
        }

        public void cambiarPos(int index) {
            posHeap = index;
        }

        public int ganancia() {
            return ciudad.ganancia;
        }

        public int modificarGanancia(int ganancia) {
            return ciudad.ganancia += ganancia;
        }

        public int perdida() {
            return ciudad.perdida;
        }

        public int modificarPerdida(int perdida) {
            return ciudad.perdida += perdida;
        }

        public int superavit() {
            return ciudad.superavit();
        }
    }

    public class SuperavitIndex implements Comparable<SuperavitIndex> {
        private int superavit;
        private int ciudad;

        // Constructor
        public SuperavitIndex(int index) {
            superavit = 0;
            ciudad = index;
        }

        public SuperavitIndex(int index, int superavit) {
            this.superavit = superavit;
            this.ciudad = index;
        }

        // Get y Set
        public int getSuperavit() {
            return superavit;
        }

        public int getCiudad() {
            return ciudad;
        }

        // Funciones de la clase
        public int compareTo(SuperavitIndex comparador) {
            if (this.superavit < comparador.superavit)
                return 1;
            if (this.superavit > comparador.superavit)
                return -1;
            return 0;
        }
    }
}
