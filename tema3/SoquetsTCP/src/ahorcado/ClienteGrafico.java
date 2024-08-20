package ahorcado;

package ahorcado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClienteGrafico extends javax.swing.JFrame {
    
    private JLabel lblPalabra;
    private JLabel lblErrores;
    private JTextField txtLetra;
    private JButton btnEnviar;
    private JTextArea txtMensajes;
    
    private Socket clientSocket;
    private BufferedReader fromServer;
    private PrintStream toServer;

    public ClienteGrafico() {
        setTitle("Juego del Ahorcado");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lblPalabra = new JLabel("Palabra: ");
        lblPalabra.setBounds(20, 20, 360, 25);
        add(lblPalabra);

        lblErrores = new JLabel("Intentos restantes: ");
        lblErrores.setBounds(20, 50, 360, 25);
        add(lblErrores);

        txtLetra = new JTextField();
        txtLetra.setBounds(20, 80, 50, 25);
        add(txtLetra);

        btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(80, 80, 100, 25);
        add(btnEnviar);

        txtMensajes = new JTextArea();
        txtMensajes.setBounds(20, 120, 360, 120);
        txtMensajes.setEditable(false);
        add(txtMensajes);

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarLetra();
            }
        });

        conectarAlServidor();
    }
    
    private void conectarAlServidor() {
        try {
            clientSocket = new Socket("localhost", 5002);
            fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            toServer = new PrintStream(clientSocket.getOutputStream());
            
            new Thread(() -> {
                try {
                    while (true) {
                        String mensaje = fromServer.readLine();
                        txtMensajes.append(mensaje + "\n");
                        if (mensaje.startsWith("Palabra: ")) {
                            lblPalabra.setText(mensaje);
                        } else if (mensaje.startsWith("Intentos restantes: ")) {
                            lblErrores.setText(mensaje);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviarLetra() {
        String letra = txtLetra.getText();
        if (!letra.isEmpty()) {
            toServer.println(letra.charAt(0));
            txtLetra.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Cliente().setVisible(true);
        });
    }
}

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteGrafico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
