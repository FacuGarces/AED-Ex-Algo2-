package aed;

import java.util.ArrayList;

// Implementacion Generica de un Heap
public class Heap<T extends Comparable<T>> {
    public ArrayList<T> lista;

    // - Constructores -

    // Vacio
    public Heap() {
        lista = new ArrayList<T>(); // O(1)
    }

    // Desde Array
    public Heap(T[] array) { // O(T)
        lista = new ArrayList<T>(); // O(1)

        for (int i = 0; i < array.length; i++) { // O(T)
            // Como trabajamos con una ArrayList, el add lo podemos tomar con complejidad
            // O(1) Amortizado

            lista.add(array[i]); // O(1)
        }

        // Se deja Floyd Aparte del constructor para crear mas heaps antes de
        // conectarlos si hace falta
    }

    // - Encolar -
    public void encolar(T item) {
        int index = lista.size(); // O(1)

        // Como trabajamos con una ArrayList, el add lo podemos tomar con complejidad
        // O(1) Amortizado

        lista.add(item); // O(1)

        siftUp(index); // O(Log T)
    }

    // - Heapify -

    // Por como funciona un Heap, si realizamo el Heapify(SiftDown), tenemos una
    // cantida de operaciones maaxima que va desde el Root del Heap hasta la ultima
    // hoja del Heap, por lo tanto vamos a tener una cantida maxima de operaciones
    // de complejidad O(Log T)
    public void heapify(int index) { // O(Log T)
        int largest = index; // O(1)
        int left = leftChild(index); // O(1)
        int right = rightChild(index); // O(1)

        if (lista.size() > left && lista.get(left).compareTo(lista.get(largest)) < 0) { // O(1)
            largest = left; // O(1)
        }

        if (lista.size() > right && lista.get(right).compareTo(lista.get(largest)) < 0) { // O(1)
            largest = right; // O(1)
        }

        if (largest != index) { // O(1)
            swap(index, largest); // O(1)

            heapify(largest);
        }
    }

    // Algoritmo de Floyd:
    // El algoritmo de Floyd arma un heap empezando desde abajo, trabajando desde
    // los nodos que están justo antes de las hojas hasta llegar a la raíz.
    // La idea es ir ajustando cada nodo para que cumpla con la regla del heap (que
    // el padre sea mayor o menor que sus hijos, dependiendo del tipo de heap).
    // Básicamente, revisa cada nodo y lo acomoda junto con sus hijos para que todo
    // quede en orden.
    // El algoritmo de Floyd es O(n) porque ajusta los nodos de abajo hacia arriba,
    // y los niveles inferiores (más numerosos) requieren menos trabajo,
    // equilibrando el costo total.
    public void floyd() { // O(T)
        for (int i = (lista.size() / 2) - 1; i >= 0; i--)
            heapify(i);
    }

    // Con la misma explicacion que el heapify, la cantidad de operaciones maxima va
    // a ser hacer un siftUp desde una de las hojas del Heap hasta el Root. Por lo
    // tanto la complejidad de esta funcion va a ser O(Log T)
    public void siftUp(int index) { // O(Log T)
        int parent = parent(index); // O(1)

        if (index > 0 && lista.get(parent).compareTo(lista.get(index)) > 0) { // O(1)
            swap(index, parent); // O(1)

            siftUp(parent);
        }
    }

    // - Swap -

    // Como trabajamos con el set y get del ArrayList, toda esta funcion puede
    // operar en solo O(1) de complejidad
    public void swap(int child, int parent) { // O(1)
        T temp = lista.get(child); // O(1)

        lista.set(child, lista.get(parent)); // O(1)

        lista.set(parent, temp); // O(1)
    }

    // - Cambiar valor -

    // EL modificar tiene 2 casos diferentes, si el valor en el que esta
    // estructurado el Heap es cambiado,, entonces se realiza Heapify o SiftUp que
    // tiene complejidad O(Log T). Mientras que si no cambia el valor donde el
    // heap esta estructurado, como trabajamos con ArrayList, el Set solo es O(1) Ya
    // que solo modificacmos el valor, importante para en el futuro modificar
    // Indexes
    public void modificar(int index, T cambio) { // O(1) o //O(Log T)
        // Si modifica el valor en que esta ordenado
        if (lista.get(index).compareTo(cambio) > 0) { // O(1)
            lista.set(index, cambio); // O(1)
            siftUp(index); // O(Log(T))
        }
        if (lista.get(index).compareTo(cambio) < 0) { // O(1)
            lista.set(index, cambio); // O(1)
            heapify(index); // O(Log T)
        }

        // Si modifica cualquier otro
        if (lista.get(index).compareTo(cambio) == 0) { // O(1)
            lista.set(index, cambio); // O(1)
        }
    }

    // - Extraccion Maximo -
    public T returnMax() { // O(1)
        return lista.get(0); // O(1)
    }

    public T extractMax() { // O(1)
        swap(0, lista.size() - 1); // O(1)

        T max = lista.remove(lista.size() - 1); // O(1)

        if (!isEmpty()) { // O(1)
            heapify(0); // O(Log T)
        }

        return max; // O(1)
    }

    // - Eliminar Especifico -

    // Tenemos 2 casos posibles. Borrar uno en el final o borrar cualquier otro
    // elemento. Si borramos el ultimo Elemento, no hace falta ejecutar Heapify, por
    // lo tanto es de complejidad O(1). Si borramos cualquier otro, Primero tenemos
    // que moverlo al final, borrarlo y luego aplicar Heapify para mantener la
    // Propiedad del Heap
    public T eliminar(int index) { // O(1) o O(Log(T))
        if (lista.size() - 1 != index) // O(1)
            swap(index, lista.size() - 1); // O(1)

        // Como en el Añadir, este eliminar esta amortizado en O(1) ya que no
        // necesitamos copiar todo el array y juntarlo ya que tomamos la sublista desde
        // el primero hasta el anteultimo. Sin tener que copiar sus valores a una nueva
        // lista en memoria y quedando como un O(1)
        T eliminado = lista.remove(lista.size() - 1); // O(1)

        if (!isEmpty() && index < lista.size()) { // O(1)
            heapify(index); // O(Log(T))
        }

        return eliminado;
    }

    // - Auxiliares -
    public int size() { // O(1)
        return lista.size(); // O(1)
    }

    public boolean isEmpty() { // O(1)
        return lista.isEmpty(); // O(1)
    }

    // - Funciones de Arbol de Heap
    protected int parent(int i) { // O(1)
        return (i - 1) / 2; // O(1)
    }

    // Returns the index of the left child node
    protected int leftChild(int i) { // O(1)
        return 2 * i + 1; // O(1)
    }

    // Returns the index of the right child node
    protected int rightChild(int i) { // O(1)
        return 2 * i + 2; // O(1)
    }
}
