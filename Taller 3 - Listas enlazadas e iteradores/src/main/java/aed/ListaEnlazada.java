package aed;

import java.util.*;

@SuppressWarnings("unused")
public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo first_node;
    private Nodo last_node;
    private int size; 

    private class Nodo {
        T parametric_type;
        Nodo next;
        Nodo previous;
        Nodo(T valor){ this.parametric_type = valor; }
    }

    public ListaEnlazada() {
        this.first_node =  new Nodo(null);
        this.last_node = new Nodo(null);
        this.size = 0;
        

    }

    public int longitud() {
        return this.size;
    }

    public void agregarAdelante(T elem) {
        Nodo new_node = new Nodo(elem);
        if (this.size == 0){
            this.first_node = new_node;
            this.last_node = new_node;
        }
        else {
            this.first_node.previous = new_node;
            new_node.next = this.first_node;
            this.first_node = new_node;
        }
        this.size += 1;
    }

    public void agregarAtras(T elem) {
        Nodo new_node = new Nodo(elem);
        if (this.size == 0){
            this.first_node = new_node;
            this.last_node = new_node;
        }
        else {
            this.last_node.next = new_node;
            new_node.previous = this.last_node;
            this.last_node = new_node;
        }
        this.size += 1;
    }

    public T obtener(int i) {
        Nodo recorrer = first_node;
        for(int j=0; j < i; j++){
            recorrer =  recorrer.next;
        }
        return recorrer.parametric_type;
    }

    public void eliminar(int i) {
        Nodo recorrer = first_node;
        if(size == 1){
            first_node = null;
        }
        else{
            for(int j=0;j<i-1;j++){
                recorrer = recorrer.next;
            }
            if(i==0){
                first_node = recorrer.next;
            }
            else{
                recorrer.next = recorrer.next.next;
                recorrer = recorrer.next;
                if(recorrer != null){
                    recorrer.previous = recorrer.previous.previous;
                }
            }
        }
        size -= 1;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo new_node = new Nodo(elem);
        Nodo recorrer = first_node;
        for(int j=0; j < indice - 1; j++){
            recorrer = recorrer.next;
        }
        new_node.previous = recorrer;
        new_node.next = recorrer.next.next;
        recorrer.next = new_node;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        this.size = 0;
        first_node = null;
        for(int j = 0; j < lista.longitud(); j++){
            this.agregarAtras(lista.obtener(j));
        }
    }
    
    @Override
    public String toString() {
       String res = "[";
       for(int i=0; i < this.size - 1;i++){
        res = res.concat(this.obtener(i).toString() + ", ");
       }
       res = res.concat(this.obtener(size-1).toString());
       res += "]";

       return res;
    }

    private class ListaIterador implements Iterador<T> {
        int selector;
        ListaEnlazada<T> new_list;
        
        public ListaIterador(ListaEnlazada<T> lista){   // Agregado xq sino no hay chance de hacer andar esto :D
            selector = 0;
            new_list = lista;
        }

        public boolean haySiguiente() {
            return selector != new_list.size;
        }
        
        public boolean hayAnterior() {
            return selector != 0; 
        }

        public T siguiente() {
            selector ++;
            return new_list.obtener(selector - 1);
        }
        
        public T anterior() {
            selector --;
            return new_list.obtener(selector);
        }
    }
    public Iterador<T> iterador() {
        return new ListaIterador(this);
    }
}

