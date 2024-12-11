package aed;

// Heap doble con sus referencias por index del otro heap para realizar 
// operaciones con menor complejidad sin tener que buscarlos con complejidad O(Log T)
public class HeapsTraslados {
    private HeapRedituables redituables;
    private HeapTimestamp timestamp;

    // - Constructor del Heap de Traslados -

    // Calculo de Complejidad
    // O(T+T+T+T+T) = O(5T) = O(T)
    public HeapsTraslados(Traslado[] traslados) { // O(T)
        // Crear arrays para usar floyd
        ItemTimestamp[] listaTime = new ItemTimestamp[traslados.length]; // O(1)
        ItemRedituables[] listaRed = new ItemRedituables[traslados.length]; // O(1)

        for (int i = 0; i < traslados.length; i++) { // O(T)
            listaTime[i] = new ItemTimestamp(i, traslados[i]); // O(1)
            listaRed[i] = new ItemRedituables(i, traslados[i]); // O(1)
        }

        timestamp = new HeapTimestamp(listaTime); // O(T)
        redituables = new HeapRedituables(listaRed); // O(T)

        timestamp.floyd(); // O(T)
        redituables.floyd(); // O(T)
    }

    // - Funciones de Best Effort -
    public void agregarLista(Traslado[] traslados) { // O(n Log T)
        for (int i = 0; i < traslados.length; i++) // O(n)
            this.insert(traslados[i]); // O(Log T)
    }

    public Traslado[] masAntiguos(int cantidad) { // O(n Log(T))
        if (cantidad > timestamp.size()) // O(1)
            cantidad = timestamp.size(); // O(1)

        Traslado[] traslados = new Traslado[cantidad]; // O(1)

        for (int i = 0; i < cantidad; i++) { // O(n)
            ItemTimestamp maximo = timestamp.extractMax(); // O(Log T)

            traslados[i] = maximo.getTraslado(); // O(1)
        }

        return traslados;
    }

    public Traslado[] masRedituables(int cantidad) { // O(n Log T)
        if (cantidad > redituables.size()) // O(1)
            cantidad = redituables.size(); // O(1)

        Traslado[] traslados = new Traslado[cantidad]; // O(1)

        for (int i = 0; i < cantidad; i++) { // O(n)
            ItemRedituables maximo = redituables.extractMax(); // O(Log T)

            traslados[i] = maximo.getTraslado(); // O(1)
        }

        return traslados;
    }

    // - Funciones Auxiliares -
    private void insert(Traslado traslado) { // O(Log T)
        int IndexTime = timestamp.size(); // O(1)

        // Maneja ambos encolados para crear ambos y despues ordenarlos
        timestamp.encolar(new ItemTimestamp(IndexTime, traslado)); // O(Log T)
    }

    public boolean isEmpty() { // O(1)
        return timestamp.isEmpty(); // O(1)
    }

    // Overrides de la clase Heap para El Heap de Timestamp y Redituables
    // Funciones swap y extraccion de maximo para guardar la relacion de los index
    // Junto con la creacion de su constructor
    class HeapTimestamp extends Heap<ItemTimestamp> {
        // Constructor
        public HeapTimestamp(ItemTimestamp[] array) { // O(T)
            super(array); // O(T)
        }

        // Funciones modificadas
        @Override
        public void swap(int parent, int child) { // O(1)
            ItemTimestamp temp = lista.get(child); // O(1)

            lista.set(child, lista.get(parent)); // O(1)

            lista.set(parent, temp); // O(1)

            // Como estos dos no estamos modificando el Traslado y solo modificamos el
            // index, el modificar opera con O(1) recordando la explicacion dada en la
            // funcion modificar de la clase Heap
            redituables.modificar(lista.get(child).getIndex(),
                    new ItemRedituables(child, lista.get(child).getTraslado())); // O(1)
            redituables.modificar(lista.get(parent).getIndex(),
                    new ItemRedituables(parent, lista.get(parent).getTraslado())); // O(1)
        }

        // Calculo de Complejidad
        // O(Log T + Log T) = O(2Log T) = O(Log T)
        @Override
        public ItemTimestamp extractMax() { // O(Log T)
            // Devolver Maximo
            ItemTimestamp maximo = returnMax(); // O(1)

            int indexTime = maximo.getIndex(); // O(1)

            // LLevar items al final
            swap(0, lista.size() - 1); // O(1)

            redituables.swap(indexTime, redituables.size() - 1); // O(1)

            // Eliminar Finales

            // Como estamos eliminando los ultimos elementos de ambos, tenemos una
            // complijidad final de O(1)
            eliminar(lista.size() - 1); // O(1)

            redituables.eliminar(redituables.size() - 1); // O(1)

            // Realizar Heapify si es necesario
            if (!isEmpty()) { // O(1)
                heapify(0); // O(LogT)

                redituables.heapify(indexTime); // O(LogT)
            }

            return maximo; // O(1)
        }

        // Calculo de Complejidad
        // O(Log T + Log T) = O(2Log T) = O(Log T)
        @Override
        public void encolar(ItemTimestamp item) { // O(Log T)
            int index = lista.size(); // O(1)

            // Como trabajamos con una ArrayList, el add lo podemos tomar con complejidad
            // O(1) Amortizado
            lista.add(item); // O(1)

            // Esto esta ejecutado en este encolar para que se cree el item en Redituables y
            // no tener errores cuando se este operando los swap que guardan sus posiciones
            // entre si
            redituables.encolar(new ItemRedituables(index, item.getTraslado())); // O(Log T)

            siftUp(index); // O(Log T)
        }
    }

    class HeapRedituables extends Heap<ItemRedituables> {
        public HeapRedituables(ItemRedituables[] array) { // O(T)
            super(array); // O(T)
        }

        // Son las misma funciones que en el HeapTimestamp y mismas explicaciones
        @Override
        public void swap(int parent, int child) { // O(1)
            ItemRedituables temp = lista.get(child);

            lista.set(child, lista.get(parent));

            lista.set(parent, temp);

            timestamp.modificar(lista.get(child).getIndex(), new ItemTimestamp(child, lista.get(child).getTraslado()));
            timestamp.modificar(lista.get(parent).getIndex(),
                    new ItemTimestamp(parent, lista.get(parent).getTraslado()));
        }

        @Override
        public ItemRedituables extractMax() { // O(Log T)
            // Devolver Maximo
            ItemRedituables maximo = returnMax();

            int indexRed = maximo.getIndex();

            // LLevar items al final
            swap(0, lista.size() - 1);

            timestamp.swap(indexRed, timestamp.size() - 1);

            // Eliminar Finales
            eliminar(lista.size() - 1);

            timestamp.eliminar(timestamp.size() - 1);

            // Realizar Heapify si es necesario
            if (!isEmpty()) {
                heapify(0);

                timestamp.heapify(indexRed);
            }

            return maximo;
        }
    }

    // Items
    private class ItemTimestamp implements Comparable<ItemTimestamp> {
        int index;
        Traslado traslado;

        public ItemTimestamp(int index, Traslado traslado) {
            this.index = index;
            this.traslado = traslado;
        }

        // Getter y Setters
        public int getIndex() {
            return index;
        }

        public Traslado getTraslado() {
            return traslado;
        }

        // Funciones de la clase
        public int compareTo(ItemTimestamp comparador) {
            if (this.traslado.timestamp > comparador.traslado.timestamp)
                return 1;
            if (this.traslado.timestamp < comparador.traslado.timestamp)
                return -1;
            return 0;
        }

    }

    private class ItemRedituables implements Comparable<ItemRedituables> {
        int index;
        Traslado traslado;

        public ItemRedituables(int index, Traslado traslado) {
            this.index = index;
            this.traslado = traslado;
        }

        // Getter y Setters
        public int getIndex() {
            return index;
        }

        public Traslado getTraslado() {
            return traslado;
        }

        // Funciones de la clase
        public int compareTo(ItemRedituables comparador) {
            if (this.traslado.gananciaNeta < comparador.traslado.gananciaNeta)
                return 1;
            if (this.traslado.gananciaNeta > comparador.traslado.gananciaNeta)
                return -1;
            return 0;
        }
    }
}
