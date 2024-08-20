
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de Agenda de Contactos:");
            System.out.println("1. Agregar Contacto");
            System.out.println("2. Buscar Contacto por Nombre");
            System.out.println("3. Eliminar Contacto");
            System.out.println("4. Mostrar Todos los Contactos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarContacto(agenda, scanner);
                    break;
                case 2:
                    buscarContacto(agenda, scanner);
                    break;
                case 3:
                    eliminarContacto(agenda, scanner);
                    break;
                case 4:
                    agenda.mostrarContactos();
                    break;
                case 5:
                    System.out.println("Saliendo de la agenda...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 5);

        scanner.close();
    }

    private static void agregarContacto(Agenda agenda, Scanner scanner) {
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el nombre del contacto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el teléfono del contacto: ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese el email del contacto: ");
        String email = scanner.nextLine();

        Contacto contacto = new Contacto(nombre, telefono, email);
        agenda.agregarContacto(contacto);
        System.out.println("Contacto agregado exitosamente.");
    }

    private static void buscarContacto(Agenda agenda, Scanner scanner) {
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el nombre del contacto a buscar: ");
        String nombre = scanner.nextLine();

        Contacto contacto = agenda.buscarContactoPorNombre(nombre);
        if (contacto != null) {
            System.out.println("Contacto encontrado: " + contacto);
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    private static void eliminarContacto(Agenda agenda, Scanner scanner) {
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el nombre del contacto a eliminar: ");
        String nombre = scanner.nextLine();

        boolean eliminado = agenda.eliminarContacto(nombre);
        if (eliminado) {
            System.out.println("Contacto eliminado exitosamente.");
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }
}
