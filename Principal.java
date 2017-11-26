package br.com.API4CC;
// importaçoes necessárias para a execução do jogo
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import javafx.stage.WindowEvent;



public class Principal extends Application
{
    // instanceia a classe Sons
    Sons s = new Sons();
              

    public static void main(String[] args)
    {       
        Splash sp = new Splash();
        sp.splash();
        // inicia a função start do projeto
        launch(args);
    }

    @Override
    
    public void start(Stage theStage)
    {
            
            // trata o botão close do formulario com tomada de decisão
            
            theStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                t.consume();
                Alert alert = new Alert(AlertType.CONFIRMATION);
                
                alert.setResizable(true);
                alert.setTitle("As Aventuras de Darion");
                alert.setHeaderText("Você realmente deseja sair ?");
                alert.setContentText("Se você deseja sair clique em OK.");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setResizable(true);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    theStage.close();
                    Platform.exit();
                    System.exit(0);
                }
            }
        });
        // trata quando pressionar o botao ESC do teclado com tomada de decisão
        theStage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            
            @Override
            public void handle(KeyEvent event) {
                
                if (event.getCode() == KeyCode.ESCAPE) {
                event.consume();
                Alert alert = new Alert(AlertType.INFORMATION);
                
                alert.setResizable(true);
                alert.setTitle("As Aventuras de Darion");
                alert.setHeaderText("Você realmente deseja sair ?");
                alert.setContentText("Se você deseja sair clique em OK. Se não clique em 'ESC'");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setResizable(true);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.CANCEL){
                    theStage.show();
                    theStage.removeEventFilter(KeyEvent.KEY_PRESSED,(null));
                }else if(result.get() == ButtonType.OK){
                    theStage.close();
                    Platform.exit();
                    System.exit(0);
                }
                }
            }
        });
       
        theStage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            
            @Override
            public void handle(KeyEvent event) {
                 if (event.getCode() == KeyCode.F2) {
                 
                  Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("As Aventuras de Darion");
                alert.setHeaderText("Ajuda do Jogo");
                alert.setContentText("-> Use as setas direcionais do teclado para navegar pelo cenário.\n"
                        + "-> Enquanto você não recolher todas as lixeiras o jogo não acaba !!!\n"
                        + "-> As lixeiras creditam 100 pontos.\n"
                        + "-> Os cubos com espinhos debitam 100 pontos.\n"
                        + "-> Para Sair do jogo pressione 'ESC'.\n"
                        + "-> Para sair da Ajuda clique em 'OK'.\n");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setResizable(true);
                alert.showAndWait();
                event.consume();
               
                }
            }
        });
        
        theStage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            
            @Override
            public void handle(KeyEvent event) {
                 if (event.getCode() == KeyCode.N) {
                 
                  Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("As Aventuras de Darion");
                alert.setHeaderText("Você realmente deseja Começar um jogo novo ?");
                alert.setContentText("Se você desejar sair clique em OK ,se não pressione 'ESC'.");
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setResizable(true);
                event.consume();
                
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    //theStage.show();
                    
                    s.clip.stop();
                    s.clip3.stop();
                    s.clip_fim.stop();
                    s.clip_Finale.stop();
                    
                    event.getCode();
                    theStage.close();
                    Principal.this.start(theStage);
                    theStage.removeEventFilter(KeyEvent.KEY_PRESSED,(null));
                }else if (result.get() == ButtonType.CANCEL){
                    theStage.show();
                    theStage.removeEventFilter(KeyEvent.KEY_PRESSED,(null));
                }
                
                }
            }
        });
        //theStage.removeEventFilter(KeyEvent.KEY_PRESSED,(null));
        
        
        // cria o titulo da janela
        theStage.setTitle( "As Aventuras de Darion - Limpe o Vilarejo" );
        theStage.getIcons().add(new Image("/earth.png"));
        // cria uma scene
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        
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
        s.clip.play();
        s.clip3.play();
        s.clip_fim.play();
        // instanceia da classe Image uma imagem para o background
        Image space = new Image( "fundo.jpg" );
        // cria a fonte e a cor do jogo
        Font theFont = Font.font( "Arial Black", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.RED );
        gc.setStroke( Color.BLACK );
        
        gc.setLineWidth(1);

        // instanceia da classe Sprite um novo personagem
        Sprite darion = new Sprite();
        darion.setImage("darionfrente.png");
        darion.setPosition(650, 230);
        // cria uma lista de quantas lixeiras vao ser utilizados
        ArrayList<Sprite> lixoList = new ArrayList<Sprite>();

        for (int i = 0; i < 20; i++)
        {
            //instanceia da classe Sprite o alvo (Lixeira)
            Sprite lixo = new Sprite();
            lixo.setImage("lixo.png");

            // define as posiçoes em que vão ficar randomicamente
            double px = 500 * Math.random()+ 55;
            double py = 500 * Math.random()+ 55;
            lixo.setPosition(px,py);
            lixoList.add( lixo );

        }
        
        ArrayList<Sprite> mudaList = new ArrayList<Sprite>();

        for (int i = 0; i < 10; i++)
        {
            //instanceia da classe Sprite o alvo (Lixeira)
            Sprite muda = new Sprite();
            muda.setImage("espinho.png");

            // define as posiçoes em que vão ficar randomicamente
            double px = 550 * Math.random()+ 40;
            double py = 450 * Math.random()+ 65;
            muda.setPosition(px,py);
            mudaList.add( muda );

        }
        
        
        // instanceia da classe Long Value
        LongValue lastNanoTime = new LongValue( System.nanoTime());
        // instanceia da classe Int Value
        IntValue score = new IntValue(0);

        
        // cria a animação do Srite Personagem (Darion)
        new AnimationTimer()
        {

            public void handle(long currentNanoTime)
            {


                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;


                // Lógica do Jogo

                darion.setVelocity(0,0);
                if (input.contains("LEFT")){
                   
                    darion.setImage("darionesq.png");
                 
                    darion.addVelocity(-50,0);
                    
                    
                }                       
                    
                if (input.contains("RIGHT")){
                    darion.setImage("dariondir.png");
                    darion.addVelocity(50,0);
                }    
                if (input.contains("UP")){
                    darion.setImage("dariontras.png");
                    darion.addVelocity(0,-50);
                }    
                if (input.contains("DOWN")){
                    darion.setImage("darionfrente.png");
                    darion.addVelocity(0,50);
                }    


                darion.update(elapsedTime);
               

                 

                // Detecta as colisões com a lixeira

                Iterator<Sprite> lixoIter = lixoList.iterator();
                Iterator<Sprite> mudaIter = mudaList.iterator();
                
                while ( lixoIter.hasNext())
                {
                    Sprite lixo = lixoIter.next();
                    //Sprite muda = mudaIter.next();
                    if ( darion.intersects(lixo))
                    {
                        lixoIter.remove();
                        score.value++;
                        s.clip_lixo.play(99);
                        
                        if(lixoList.isEmpty() == true){
                            // define a tela de fundo e seus respectivos sons
                            //darion.setImage("fundo2.jpg");
                            darion.setPosition(0, 0);
                            s.clip.stop();
                            s.clip3.stop();
                            //s.clip_Parabens.play();
                            s.clip_Corr.play();
                            s.clip_Finale.play();
                            
                            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    
                            dialogoInfo.setTitle("As Aventuras de Darion");
                            dialogoInfo.setHeaderText("Parabéns... Sua Pontuação foi     >>>>>>>>> "+ (100 * score.value)+" <<<<<<<<<");
                            String version = System.getProperty("java.version");
                            String content = String.format("Parabéns !!! Você conseguiu a preservar a natureza !!!! Lembre-se devemos sempre preservar o meio ambiente, para que futuras gerações também se beneficie dos benefícios do meio ambiente !!! Para Finalizar o Jogo pressione 'ESC' !!! Para começar um novo Jogo pressione 'N' !!!",version);
                            dialogoInfo.setContentText(content);
                            dialogoInfo.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                            dialogoInfo.setResizable(true);
                            dialogoInfo.show();
                            
                            
                            
                        //////////////////////////////////////    
                        }


                    }

                }
                while ( mudaIter.hasNext())
                {
                    //Sprite lixo = lixoIter.next();
                    Sprite muda = mudaIter.next();
                    if ( darion.intersects(muda))
                    {
                        mudaIter.remove();
                        score.value--;
                        s.clip_Erro.play(99);
                        

                    }

                }
                
                          


                // renderiza as atualizações
                gc.clearRect(0, 0, 700,500);
                gc.drawImage( space, 0, 0 );
                darion.render( gc );


                for (Sprite lixo : lixoList ){
                 lixo.render( gc );}
                for (Sprite muda : mudaList ){
                 muda.render( gc );}
                
                
                
                // cria e mostra na tela o nome e a pontuação
                String pointsText = "Score: " + (100 * score.value);
                gc.fillText( pointsText, 10, 81 );
                gc.strokeText( pointsText, 10, 81 );
                
                String nText = "Novo Jogo";
                gc.fillText( nText, 473, 39 );
                gc.strokeText( nText, 473, 39 );

                String Text = "Sair";
                gc.fillText( Text, 745, 39 );
                gc.strokeText( Text, 745, 39 );
                
                String help = "Ajuda";
                gc.fillText( help, 745, 91 );
                gc.strokeText( help, 745, 91 );

                String TituloText = "As Aventuras de Darion";
                gc.fillText( TituloText, 10, 39 );
                gc.strokeText( TituloText, 10, 39 );

                if(lixoList.isEmpty() == true){
                            // define a tela de fundo e seus respectivos sons
                            
                            darion.setPosition(650, 230);
                            
                                                        
                            String pText = " !!!!!!! Jogo Finalizado !!!!!!";
                            gc.fillText( pText, 250, 250 );
                            gc.strokeText( pText, 250, 250 );
                            
                            score.value=0;
                            
                        }

            }



        }.start();
        // cria a mensagem de boas vindas
        
        theStage.show();
        
        
        
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
         dialogoInfo.setTitle("Limpe o Vilarejo");

         dialogoInfo.setHeaderText("Sua Missão é a seguinte...");
         String version = System.getProperty("java.version");
         String content = String.format("Darion e seus colegas participaram de uma festa no vilarejo. A festa acabou e agora sobrou muito lixo espalhado !!!! Ajude Darion a limpar o sujeira deixada no vilarejo!!! Lembre-se devemos sempre preservar o meio ambiente, para que futuras gerações também se beneficie dos benefícios do meio ambiente !!!    Bom Jogo !!!! Clique em OK para começar !!!",version);
         dialogoInfo.setContentText(content);
         dialogoInfo.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
         dialogoInfo.setResizable(true);
         dialogoInfo.showAndWait();


    }

}
