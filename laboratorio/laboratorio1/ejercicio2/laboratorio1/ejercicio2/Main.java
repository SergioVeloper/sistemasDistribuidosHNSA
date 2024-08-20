package laboratorio1.ejercicio2;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Empresa empresa = new Empresa();
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menú de Gestión de Empleados:");
            System.out.println("1. Agregar Empleado Fijo");
            System.out.println("2. Agregar Empleado por Horas");
            System.out.println("3. Mostrar todos los Empleados");
            System.out.println("4. Calcular Salario Total de la Empresa");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarEmpleadoFijo(empresa, scanner);
                    break;
                case 2:
                    agregarEmpleadoPorHoras(empresa, scanner);
                    break;
                case 3:
                    empresa.mostrarEmpleados();
                    break;
                case 4:
                    System.out.println("Salario total de la empresa: " + empresa.calcularSalarioTotal());
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 5);

        scanner.close();
    }

    private static void agregarEmpleadoFijo(Empresa empresa, Scanner scanner) {
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el salario del empleado: ");
        double salario = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el departamento del empleado: ");
        String departamento = scanner.nextLine();

        Empleado emp = new EmpleadoFijo(nombre, salario, departamento);
        empresa.agregarEmpleado(emp);
        System.out.println("Empleado fijo agregado exitosamente.");
    }

    private static void agregarEmpleadoPorHoras(Empresa empresa, Scanner scanner) {
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el departamento del empleado: ");
        String departamento = scanner.nextLine();
        System.out.print("Ingrese las horas trabajadas: ");
        int horasTrabajadas = scanner.nextInt();
        System.out.print("Ingrese la tarifa por hora: ");
        double tarifaPorHora = scanner.nextDouble();

        Empleado emp = new EmpleadoPorHoras(nombre, departamento, horasTrabajadas, tarifaPorHora);
        empresa.agregarEmpleado(emp);
        System.out.println("Empleado por horas agregado exitosamente.");
    }
}
