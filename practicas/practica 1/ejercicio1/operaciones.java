package ejercicio1;

class Operaciones{

    public static int fibonacci(int n){
        if(n<=1) return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static long factorial(int n){
        long fact = 1;
        for(int i = 1; i <= n; i++){
            fact *= i;
        }
        return fact;
    }

    public static int sumatoria(int n){
        return n*(n+1)/2;
    }
}
