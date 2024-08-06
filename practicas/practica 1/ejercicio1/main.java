package ejercicio1;
import java.util.Scanner;

public class main{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese un numero: ");
        int n = scanner.nextInt();
        int op = 0;
        System.out.println("Ingrese la operacion a realizar: ");
        System.out.println("1. Fibonacci");
        System.out.println("2. Factorial");
        System.out.println("3. Sumatoria");
        op = scanner.nextInt();

        switch(op){
            case 1:
                System.out.println("El fibonacci de " + n + " es: " + Operaciones.fibonacci(n));
                break;
            case 2:
                System.out.println("El factorial de " + n + " es: " + Operaciones.factorial(n));
                break;
            case 3:
                System.out.println("La sumatoria de " + n + " es: " + Operaciones.sumatoria(n));
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }
}

