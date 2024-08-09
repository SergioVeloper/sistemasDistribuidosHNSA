import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de Calculadora Científica:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicación");
            System.out.println("4. División");
            System.out.println("5. Potencia");
            System.out.println("6. Raíz Cuadrada");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    realizarSuma(scanner);
                    break;
                case 2:
                    realizarResta(scanner);
                    break;
                case 3:
                    realizarMultiplicacion(scanner);
                    break;
                case 4:
                    realizarDivision(scanner);
                    break;
                case 5:
                    realizarPotencia(scanner);
                    break;
                case 6:
                    realizarRaizCuadrada(scanner);
                    break;
                case 7:
                    System.out.println("Saliendo de la calculadora...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 7);

        scanner.close();
    }

    private static void realizarSuma(Scanner scanner) {
        System.out.print("Ingrese el primer número: ");
        double a = scanner.nextDouble();
        System.out.print("Ingrese el segundo número: ");
        double b = scanner.nextDouble();
        System.out.println("Resultado: " + CalculadoraCientifica.suma(a, b));
    }

    private static void realizarResta(Scanner scanner) {
        System.out.print("Ingrese el primer número: ");
        double a = scanner.nextDouble();
        System.out.print("Ingrese el segundo número: ");
        double b = scanner.nextDouble();
        System.out.println("Resultado: " + CalculadoraCientifica.resta(a, b));
    }

    private static void realizarMultiplicacion(Scanner scanner) {
        System.out.print("Ingrese el primer número: ");
        double a = scanner.nextDouble();
        System.out.print("Ingrese el segundo número: ");
        double b = scanner.nextDouble();
        System.out.println("Resultado: " + CalculadoraCientifica.multiplicacion(a, b));
    }

    private static void realizarDivision(Scanner scanner) {
        System.out.print("Ingrese el primer número: ");
        double a = scanner.nextDouble();
        System.out.print("Ingrese el segundo número: ");
        double b = scanner.nextDouble();
        try {
            System.out.println("Resultado: " + CalculadoraCientifica.division(a, b));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void realizarPotencia(Scanner scanner) {
        System.out.print("Ingrese la base: ");
        double base = scanner.nextDouble();
        System.out.print("Ingrese el exponente: ");
        double exponente = scanner.nextDouble();
        System.out.println("Resultado: " + CalculadoraCientifica.potencia(base, exponente));
    }

    private static void realizarRaizCuadrada(Scanner scanner) {
        System.out.print("Ingrese el número: ");
        double a = scanner.nextDouble();
        try {
            System.out.println("Resultado: " + CalculadoraCientifica.raizCuadrada(a));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
