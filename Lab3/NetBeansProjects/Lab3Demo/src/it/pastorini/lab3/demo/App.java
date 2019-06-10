/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.pastorini.lab3.demo;

import it.pastorini.lab3.entity.JMSClient;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author biar
 */
public class App extends JApplet {
    
    private static final int JFXPANEL_WIDTH_INT = 300;
    private static final int JFXPANEL_HEIGHT_INT = 250;
    private static JFXPanel fxContainer;

        
    public static final String SERVANT_IP = "192.168.49.81";
    
    public static final int SERVANT_PORT = 61616;
    
    public static final String QUOTAZIONI_TOPIC = "dynamicTopics/Quotazioni";
    
     public static final String ORDINI_TOPIC = "dynamicTopics/Ordini";
    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            
//            @Override
//            public void run() {
//                try {
//                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//                } catch (Exception e) {
//                }
//                
//                JFrame frame = new JFrame("JavaFX 2 in Swing");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                
//                JApplet applet = new App();
//                applet.init();
//                
//                frame.setContentPane(applet.getContentPane());
//                
//                frame.pack();
//                frame.setLocationRelativeTo(null);
//                frame.setVisible(true);
//                
//                applet.start();
//            }
//        });
//    }
    
    public static void main(String[] args) throws JMSException, NamingException {
        JMSClient client = new JMSClient(SERVANT_IP, SERVANT_PORT);
        client.start();
        client.receive(QUOTAZIONI_TOPIC, null, new MessageListener() {
            @Override
            public void onMessage(Message message) {
                Logger.getLogger(App.class.getName()).log(Level.INFO, message.toString());
                if (message instanceof TextMessage) {
                    try {
                        TextMessage textMessage = (TextMessage) message;
                    
                        System.out.println(String.format("Received: %s", textMessage.getText()));
                    } catch (JMSException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println(String.format("Message of wrong type: %s", message.getClass().getName()));
                }
            }
        });
        TextMessage textMessage = client.provideTextMessage();
        textMessage.setStringProperty("Utente", "Pippo");
        textMessage.setStringProperty("Nome", "Barilla");
        textMessage.setFloatProperty("Prezzo", 10f);
        textMessage.setIntProperty("Quantita", 1);
        

        client.send(ORDINI_TOPIC, textMessage);
       
//        client.start(null);
    }
    
    @Override
    public void init() {
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        add(fxContainer, BorderLayout.CENTER);
        // create JavaFX scene
        Platform.runLater(new Runnable() {
            
            @Override
            public void run() {
                createScene();
            }
        });
    }
    
    private void createScene() {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        fxContainer.setScene(new Scene(root));
    }
    
}
