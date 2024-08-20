package socketstcpOperaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        int port = 5002;
        String direccionServer = "localhost";
        int n = 0;
        
        try {
            Socket client = new Socket(direccionServer, port);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            String opcion;

            do {
                System.out.println("Menú:");
                System.out.println("1. Introducir número");
                System.out.println("2. Calcular Factorial");
                System.out.println("3. Calcular Fibonacci");
                System.out.println("4. Calcular Sumatoria");
                System.out.println("5. Salir");
                System.out.print("Elija una opción: ");
                opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        System.out.print("Ingrese un número: ");
                        n = Integer.parseInt(scanner.nextLine());
                        break;
                    case "2": 
                    case "3": 
                    case "4": 
                        //envio al servidor
                        toServer.println(opcion);
                        toServer.println(n);
                        String resultado = fromServer.readLine();
                        System.out.println(resultado);
                        break;
                    case "5":
                        toServer.println("5");
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            } while (!opcion.equals("5"));

            client.close();
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
