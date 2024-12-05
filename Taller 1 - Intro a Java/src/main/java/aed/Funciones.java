package aed;

class Funciones {
    int cuadrado(int x) {
        int cuadrado = x*x;
        return cuadrado;
    }

    double distancia(double x, double y) {
        double distancia = Math.sqrt(x*x + y*y);
        return distancia;
    }

    boolean esPar(int n) {
        return n%2 == 0;
    }

    boolean esBisiesto(int n) {
        if(n % 4 == 0 && n % 100 != 0){
            return true;
        }
        if(n % 400 == 0){
            return true;
        }
        return false;
        }

    int factorialIterativo(int n) {
        int factorial = 1;
        for(int i = 1; i <= n; i++){
            factorial = factorial*i;
        }
       return factorial;
    }

    int factorialRecursivo(int n) {
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return 1;
        }
        else{
            return n*factorialRecursivo(n-1);
        }
    }

    boolean esPrimo(int n) {
        if(n == 0 || n == 1){
            return false;
        }
        else{
            for(int i = 2; i < n; i++){
                if(n % i == 0){
                    return false;
                }
            }
        }
        return true;
    }

    int sumatoria(int[] numeros) {
        int res = 0;
        for(int i = 0; i < numeros.length; i++){
            res = res + numeros[i];
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        int res = 0;
        for(int i = 0; i < numeros.length; i++){
            if(numeros[i] != buscado){
                res+=1;
            }
            else{
                return res;
            }
        }
        return res;
    }
    

    boolean tienePrimo(int[] numeros) {
        for(int i = 0; i < numeros.length; i++){
            if(esPrimo(numeros[i])){
                return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        for(int i = 0; i < numeros.length; i++){
            if((esPar(numeros[i]) == false)){
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        if(s1.length() > s2.length()){
            return false;
        }
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    boolean esSufijo(String s1, String s2) {
        if(s1.length() > s2.length()){
            return false;
        }
        return esPrefijo(invertir(s1), invertir(s2));
    }

    
// Funciones Auxiliares
    String invertir(String original){
        String invertida = "";
        for (int i = original.length() - 1; i >= 0; i--) {
            invertida += original.charAt(i);
        }

        return invertida;
    }
}

