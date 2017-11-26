/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.API4CC;

import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author leonardo
 */
public class Menu {
    
    public void start(Stage theMenu)
    {
            
            // trata o botão close do formulario com tomada de decisão
            theMenu.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                t.consume();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                
                alert.setResizable(true);
                alert.setTitle("As Aventuras de Darion");
                alert.setHeaderText("Você realmente deseja sair ?");
                alert.setContentText("Se você deseja sair clique em OK.");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    theMenu.close();
                    Platform.exit();
                    System.exit(0);
                }
            }
        });
        // trata quando pressionar o botao ESC do teclado com tomada de decisão
        theMenu.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.N) {
                Principal p = new Principal();    
                    Stage theStage = null;
                p.start(theStage);
                        
                
                }
            }
        });
        // cria o titulo da janela
        theMenu.setTitle( "As Aventuras de Darion - Limpe o Vilarejo" );
        theMenu.getIcons().add(new Image("/earth.png"));
        // cria uma scene
        Group root = new Group();
        Scene theScene = new Scene( root );
        theMenu.setScene( theScene );
        // cria uma imagem
        Canvas canvas = new Canvas( 830, 607 );
        root.getChildren().add( canvas );
        // cria uma array list onde serão salvo os alvos
        ArrayList<String> input = new ArrayList<String>();
        // salva os comandos UP, DONW, LEFT, RIGTH
        theScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                }
            });

        theScene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    input.remove( code );
                }
            });
        // cria os graficos da tela
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // inicia os sons do começo do jogo
       
        // instanceia da classe Image uma imagem para o background
        Image space = new Image( "fundo.jpg" );
        // cria a fonte e a cor do jogo
        Font theFont = Font.font( "Comic", FontWeight.BOLD, 22 );
        gc.setFont( theFont );
        gc.setFill( Color.RED );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);
        
        gc.clearRect(0, 0, 700,500);
        gc.drawImage( space, 0, 0 );
        
        String Text = "P/ Sair";
                gc.fillText( Text, 745, 39 );
                gc.strokeText( Text, 745, 39 );

                String TituloText = "As Aventuras de Darion";
                gc.fillText( TituloText, 10, 39 );
                gc.strokeText( TituloText, 10, 39 );
        String pText = " !!!!!!! Jogo Finalizado !!!!!!";
                            gc.fillText( pText, 250, 250 );
                            gc.strokeText( pText, 250, 250 );
                            String p2Text = " !!!!!!! Clique em 'ESC' para Sair !!!!!!";
                            gc.fillText( p2Text, 190, 340 );
                            gc.strokeText( p2Text, 190, 340 );        
    }
    
}
