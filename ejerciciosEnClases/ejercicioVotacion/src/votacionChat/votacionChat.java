package votacionChat;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author sergio
 */
public class votacionChat extends ReceiverAdapter {
    JChannel channel;
    private String nombre;

    private final Map<String, Integer> totalVotos = new HashMap<>();

    public votacionChat(String nombre) {
        this.nombre = nombre;
        
        // Initialize the votes
        totalVotos.put("Juan", 0);
        totalVotos.put("Pedro", 0);
        totalVotos.put("Maria", 0);
    }

    public void viewAccepted(View new_view) {
        System.out.println("** Vista: " + new_view);
    }

    public void receive(Message msg) {
        String result = msg.getObject().toString().trim();
        System.out.println("Resividos: " + result);
        
        String[] parts = result.split(", ");
        String table = parts[0];
        int votesJuan = Integer.parseInt(parts[1].split(" ")[1]);
        int votesPedro = Integer.parseInt(parts[2].split(" ")[1]);
        int votesMaria = Integer.parseInt(parts[3].split(" ")[1]);

        synchronized (totalVotos) {
            totalVotos.put("Juan", totalVotos.get("Juan") + votesJuan);
            totalVotos.put("Pedro", totalVotos.get("Pedro") + votesPedro);
            totalVotos.put("Maria", totalVotos.get("Maria") + votesMaria);
        }

        totalVotos();
    }

    public void getState(OutputStream output) throws Exception {
        synchronized (totalVotos) {
            Util.objectToStream(totalVotos, new DataOutputStream(output));
        }
    }

    @SuppressWarnings("unchecked")
    public void setState(InputStream input) throws Exception {
        Map<String, Integer> state = (Map<String, Integer>) Util.objectFromStream(new DataInputStream(input));
        synchronized (totalVotos) {
            totalVotos.clear();
            totalVotos.putAll(state);
        }
        System.out.println("Votos recibidos:");
        totalVotos();
    }

    private void totalVotos() {
        System.out.println("Total votos:");
        for (Map.Entry<String, Integer> entry : totalVotos.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private void start() throws Exception {
        channel = new JChannel();
        channel.setReceiver(this);
        channel.connect("VotingCluster");
        channel.getState(null, 10000);
        eventLoop();
        channel.close();
    }

    private void eventLoop() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Ingrese los resutados de su mesa: (ej, mesa #, Juan #, Pedro #, Maria #): ");
                String result = sc.nextLine().trim();
                if (result.equalsIgnoreCase("salir") || result.equalsIgnoreCase("exit")) {
                    break;
                }
                Message msg = new Message(null, "[" + nombre + "] " + result);
                channel.send(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String name = sc.nextLine();
        new votacionChat(name).start();
    }
}
