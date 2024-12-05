package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto

    private class Nodo {
        public Nodo father;
        public Nodo left;
        public Nodo right;
        public T value;
        
        public Nodo (T valor){
            this.value = valor;
            this.left = null;
            this.right = null;
            this.father = null;
        }
    }

    public Nodo original;
    private int cantidadNodos;

    public ABB() {
       this.original = null;
       this.cantidadNodos = 0;
    }

    public int cardinal() {
        return this.cantidadNodos;
    }

    public T minimo(){
        if (this.original == null){
            return null;
        }
        Nodo nodoIterador = this.original;
        while(nodoIterador.left != null){
            nodoIterador = nodoIterador.left;
        }
        return nodoIterador.value;
    }

    public T maximo(){
        if (this.original == null){
            return null;
        }
        Nodo nodoIterador = this.original;
        while(nodoIterador.right != null){
            nodoIterador = nodoIterador.right;
        }
        return nodoIterador.value;
    }

    public void insertar(T elem){
    Nodo insertNode = new Nodo(elem);

    if (original == null) {
        original = insertNode;
        cantidadNodos++; 
        return;
    }

    Nodo current = original;
    Nodo father = null;

    while (current != null) {
        father = current;
        if (elem.compareTo(current.value) == 0) {
            return;
        }
        else if (elem.compareTo(current.value) < 0) {
            current = current.left;
        }
        else {
            current = current.right;
        }
    }

    if (elem.compareTo(father.value) < 0) {
        father.left = insertNode;
    } else {
        father.right = insertNode;
    }

    insertNode.father = father;
    cantidadNodos++; 
    }

    public boolean pertenece(T elem){
        Nodo current = original;  
        while (current != null) {
            if (elem.compareTo(current.value) == 0) {
                return true;
            }
            else if (elem.compareTo(current.value) < 0) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }
        return false;
    }

    public void eliminar(T elem){
        Nodo nodoAEliminar = buscarNodo(original, elem);
    if (nodoAEliminar == null) {
        return;
    }
    if (nodoAEliminar.left == null && nodoAEliminar.right == null) {
        if (nodoAEliminar == original) {
            original = null; 
        } else {
            if (nodoAEliminar.father.left == nodoAEliminar) {
                nodoAEliminar.father.left = null;
            } else {
                nodoAEliminar.father.right = null;
            }
        }
    }
    else if (nodoAEliminar.left == null || nodoAEliminar.right == null) {
        Nodo hijo = (nodoAEliminar.left != null) ? nodoAEliminar.left : nodoAEliminar.right;
        if (nodoAEliminar == original) {
            original = hijo; 
        } else {
            if (nodoAEliminar.father.left == nodoAEliminar) {
                nodoAEliminar.father.left = hijo;
            } else {
                nodoAEliminar.father.right = hijo;
            }
        }

        hijo.father = nodoAEliminar.father;
    }
    else {
        Nodo sucesor = encontrarMinimo(nodoAEliminar.right);
        nodoAEliminar.value = sucesor.value;
        if (sucesor.father.left == sucesor) {
            sucesor.father.left = sucesor.right;
        } else {
            sucesor.father.right = sucesor.right;
        }
        if (sucesor.right != null) {
            sucesor.right.father = sucesor.father;
        }
    }
    cantidadNodos--;
}

private Nodo buscarNodo(Nodo nodo, T elem) {
    while (nodo != null) {
        if (elem.compareTo(nodo.value) == 0) {
            return nodo;
        } else if (elem.compareTo(nodo.value) < 0) {
            nodo = nodo.left;
        } else {
            nodo = nodo.right;
        }
    }
    return null;
}

private Nodo encontrarMinimo(Nodo nodo) {
    while (nodo.left != null) {
        nodo = nodo.left;
    }
    return nodo;
}

    public String toString(){
        Iterador iterador = iterador();
        if(cardinal() == 0){
            return "{}";
        }
        String res = "{";
        while (iterador.haySiguiente()) {
            res = res + iterador.siguiente() + ",";
        }
        res = res + iterador.siguiente() + "}";
        return res;
    }

    private class ABB_Iterador implements Iterador<T> {
        public T actual = minimo();
        public int i = 0;
        
        void inorder(Nodo father, List<T> lista){
            if (father != null){
                inorder(father.left, lista);
                lista.add(father.value);
                inorder(father.right, lista);}
            }

        public List<T> armarLista(Nodo father) {
            List<T> res = new ArrayList<T>();
            this.inorder(father, res);
            return res;
        }
        public boolean haySiguiente() {            
            List<T> lista = armarLista(original);
            if (i == lista.size() - 1) {
                return false;
            } else {
                return true;    
            }
        }
    
        public T siguiente() {
            List<T> lista = armarLista(original);
            T res = null;
            if (haySiguiente()) {
                res = actual;
                actual = lista.get(i + 1);
                i++;
            } else {
                res = actual;
            }
            return res;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
