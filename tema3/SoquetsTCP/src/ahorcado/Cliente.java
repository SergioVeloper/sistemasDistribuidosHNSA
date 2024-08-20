package ahorcado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        int port = 5003;
        String direccionServer = "localhost";
        
        try {
            Socket client = new Socket(direccionServer, port);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            String mensaje;
            while ((mensaje = fromServer.readLine()) != null) {
                System.out.println(mensaje);

                if (mensaje.contains("Adivina una letra")) {
                    String letra = scanner.nextLine();
                    toServer.println(letra);
                }
            }

            client.close();
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
