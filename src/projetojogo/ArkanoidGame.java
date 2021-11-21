package projetojogo;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ArkanoidGame extends Pane{
    private List<GameObject> componentes;
    private AnimationTimer timer;
    private Thread efeitos;
    private int level;
    private boolean efeitoLigado;
    private boolean pausado;
    private Score score=new Score();
    
    private String estiloBotao="-fx-background-color: #ff6600;"+
                               "-fx-border-color: #ff0000;"+
                               "-fx-border-radius: 20px;"+
                               "-fx-background-radius: 20px;";
    
    private String estiloEntraBotao="-fx-background-color: #ff0000;"+
                                    "-fx-border-color: #ffffff;"+
                                    "-fx-border-radius: 20px;"+
                                    "-fx-background-radius: 20px;";
    
    private String estiloSaiBotao="-fx-background-color: #ff6600;"+
                                  "-fx-border-radius: 20px;"+
                                  "-fx-border-color: #ff0000;"+
                                  "-fx-background-radius: 20px;";
    
    private String fonteBotao="-fx-font: 20px Verdana;"+
                              "-fx-text-fill:radial-gradient(radius 100%, #ff99ff, #d9b3ff, #ff99ff);"+
                              "-fx-stroke-width: 10;";
    public ArkanoidGame() {
        super();
        this.level=1;
        this.efeitoLigado=false;
        this.pausado=false;
        telaInicial();
    }
    private void telaInicial(){
        Button iniciar;
        this.setStyle("-fx-background-image: url(\"projetojogo/fundoJogo1.png\");"+
                      "-fx-background-repeat: stretch;"+
                      "-fx-background-size: 745 620;");
        iniciar=new Button("Jogar");
        iniciar.setPrefSize(170, 40);
        iniciar.setPadding(new Insets(10));
        iniciar.setStyle(estiloBotao);
        iniciar.setOnMouseEntered(e -> iniciar.setStyle(estiloEntraBotao));
        iniciar.setOnMouseExited(e -> iniciar.setStyle(estiloSaiBotao));
        
        
        Button sair=new Button("Sair");
        sair.setPrefSize(170, 40);
        sair.setPadding(new Insets(10));
        sair.setStyle(estiloBotao);
        sair.setOnMouseEntered(e -> sair.setStyle(estiloEntraBotao));
        sair.setOnMouseExited(e -> sair.setStyle(estiloSaiBotao));
        
        Label titulo=new Label("ARKANOID");
        titulo.setStyle("-fx-font: 80px Verdana;"+
                        "-fx-text-fill:radial-gradient(radius 100%, #ff6600, #ff0000, #ff6600);");
        
        Font font=new Font("Verdana",17);
        iniciar.setFont(font);
        sair.setFont(font);
        titulo.relocate(150, 90);
        iniciar.relocate(290,240);
        sair.relocate(290, 300);
        
        getChildren().addAll(titulo,iniciar,sair);
        iniciar.setOnAction(e->{
                getChildren().removeAll(titulo,iniciar,sair);
                score.zerar();
                Platform.runLater(()->gameStart());
        });
        
        sair.setOnAction(e->{
                System.exit(0);
        });
        
    }
    private void gameStart(){
        if(level==1){
            this.setStyle("-fx-background-image: url(\"projetojogo/ImgLvl1.jpg\");"+
                          "-fx-background-repeat: stretch;"+
                          "-fx-background-size: 745 620;");
        }
        else if(level==2){
            this.setStyle("-fx-background-image: url(\"projetojogo/ImgLvl2.jpg\");"+
                      "-fx-background-repeat: stretch;"+
                      "-fx-background-size: 745 620;");
        }else{
            this.setStyle("-fx-background-image: url(\"projetojogo/ImgLvl3.jpg\");"+
                      "-fx-background-repeat: stretch;"+
                      "-fx-background-size: 745 620;");
        }
         
        Vida vida=new Vida();
        Label pontuacao=new Label(""+score.getPontos());
        Image imagep=new Image("projetojogo/pontos.jpg");
        ImageView estrela=new ImageView(imagep);
        estrela.setFitHeight(25);
        estrela.setFitWidth(32);
        estrela.relocate(1,5);
        
        Label vidas=new Label(""+vida.getVida());
        Image image=new Image("projetojogo/coracao.png");
        ImageView coracao=new ImageView(image);
        coracao.setFitHeight(25);
        coracao.setFitWidth(32);
        coracao.relocate(115,5);
        if(score.getPontos()>=100)
            coracao.relocate(140,5);
        else if(score.getPontos()>=10)
            coracao.relocate(127,5);
        
        Label nivel=new Label("Level "+level);
        String estiloInfo="-fx-font: 20px Verdana;"+
                          "-fx-font-weight: bold;"+
                          "-fx-text-fill:radial-gradient(radius 100%, #ff0000, #ff6600, #ff0000);"+
                          "-fx-stroke-width: 2;"+
                          "-fx-stroke-color: #ffffff;";
        pontuacao.setStyle(estiloInfo);
        vidas.setStyle(estiloInfo);
        nivel.setStyle(estiloInfo); 
        
        HBox info=new HBox(pontuacao,vidas,nivel);
        
        //info.setAlignment(Pos.CENTER_LEFT);
        info.setSpacing(100);
        info.setPadding(new Insets(5,5,5,35));
        info.setMinWidth(this.getWidth());
        info.setMinHeight(36);
        info.setStyle("-fx-background-color:#000000");
        
        getChildren().addAll(info,coracao,estrela);
     
        Button btmenu=new Button("Menu");
        btmenu.setPrefSize(80, 30);
        btmenu.setStyle(estiloBotao);   
        btmenu.setOnMouseEntered(e -> btmenu.setStyle(estiloEntraBotao));
        btmenu.setOnMouseExited(e -> btmenu.setStyle(estiloSaiBotao));      
        
        getChildren().add(btmenu);
        
        btmenu.relocate(this.getWidth()-80, 3);
        
        
        int px=2,py=40; 
        
        componentes=new ArrayList();
        Pane pane=this;
        
        componentes.add(new Raquete((int)(getWidth()/2),(int)getHeight()-10,this));
        componentes.add(new Bolinha(350,550,this));
        for(int i=0;i<level*8;i++){
            int num=(int)(Math.random()*(level+2));
            if(num==0 || num==1)
                componentes.add(new BlocoAmarelo(px,py,5,this));
            else{
                int tipo=(int)(Math.random()*5);
                if(tipo==0)
                    componentes.add(new BlocoVerde(px,py,15,this));
                else if(tipo==1)
                        componentes.add(new BlocoBranco(px,py,10,this));
                    else if(tipo==2)
                            componentes.add(new BlocoVermelho(px,py,5,this));
                        else
                            componentes.add(new BlocoAzul(px,py,10,this));
            }
            px+=90;
            if(px>700){
                px=2;
                py+=42;
            }       
        }
       
        for(GameObject elem:componentes){
            getChildren().add(elem.getFigura());
        }

        //ciclo automático

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for(GameObject elem:componentes)
                {
                    if(elem instanceof BlocoVerde){
                        if(((BlocoVerde)elem).getQtColisao()==1)
                            ((BlocoVerde)elem).mover();
                    }
                    else
                        elem.mover();
                }
                
                for(int i=0; i<componentes.size();i++){
                    if(componentes.get(i) instanceof Bolinha){
                        for(int j=0;j<componentes.size();j++){
                            if(!(componentes.get(j) instanceof Bolinha)){
                                if(((Bolinha)componentes.get(i)).colidiu(componentes.get(j))){
                                    if(componentes.get(j) instanceof Raquete)
                                        ((Bolinha)componentes.get(i)).mudarDirecao(componentes.get(j));
                                    if(componentes.get(j) instanceof BlocoAmarelo){

                                        getChildren().remove(componentes.get(j).getFigura());
                                        ((Bolinha)componentes.get(i)).mudarDirecao(componentes.get(j));
                                        score.somar(((AbstractBloco)componentes.get(j)).getPontos());
                                        componentes.remove(componentes.get(j));
                                        
                                    }
                                    else{
                                        if(componentes.get(j) instanceof BlocoVerde){
                                            if(((BlocoVerde)componentes.get(j)).getQtColisao()==1){
                                                ((Bolinha)componentes.get(i)).mudarDirecao(componentes.get(j));
                                                getChildren().remove(componentes.get(j).getFigura());
                                                score.somar(((AbstractBloco)componentes.get(j)).getPontos());
                                                componentes.remove(componentes.get(j));
                                            }
                                            else{
                                                ((Bolinha)componentes.get(i)).mudarDirecao(componentes.get(j));
                                                ((BlocoVerde)componentes.get(j)).aumentarColisoes();
                                            }
                                            
                                        }
                                        else if(componentes.get(j) instanceof BlocoBranco){
                                                getChildren().remove(componentes.get(j).getFigura());
                                                ((Bolinha)componentes.get(i)).mudarDirecao(componentes.get(j));
                                                score.somar(((AbstractBloco)componentes.get(j)).getPontos());
                                                componentes.remove(componentes.get(j));
                                                if(((Bolinha)componentes.get(i)).getTempo()==0)
                                                    ((Bolinha)componentes.get(i)).aumentarVelocidade();
                                                ((Bolinha)componentes.get(i)).tempoMovimentoRapido();
                                            }
                                            else if(componentes.get(j) instanceof BlocoAzul){
                                                    getChildren().remove(componentes.get(j).getFigura());
                                                    ((Bolinha)componentes.get(i)).mudarDirecao(componentes.get(j));
                                                    score.somar(((AbstractBloco)componentes.get(j)).getPontos());
                                                    componentes.remove(componentes.get(j));
                                                    componentes.add(1,new Bolinha(
                                                            componentes.get(i).cx,componentes.get(i).cy,pane));
                                                    if((((Bolinha)componentes.get(i+1)).getTempo()!=0)){
                                                       ((Bolinha)componentes.get(1)).setPassox(((Bolinha)componentes.get(i+1)).getPassox()/2*-1);
                                                       ((Bolinha)componentes.get(1)).setPassoy(((Bolinha)componentes.get(i+1)).getPassoy()/2);
                                                    }
                                                    else{
                                                       ((Bolinha)componentes.get(1)).setPassox(((Bolinha)componentes.get(i+1)).getPassox()*-1);
                                                       ((Bolinha)componentes.get(1)).setPassoy(((Bolinha)componentes.get(i+1)).getPassoy());
                                                    }
                                                    
                                                    getChildren().add(componentes.get(1).getFigura());
                                                }
                                            else if(componentes.get(j) instanceof BlocoVermelho){
                                                    getChildren().remove(componentes.get(j).getFigura());
                                                    ((Bolinha)componentes.get(i)).mudarDirecao(componentes.get(j));
                                                    score.somar(((AbstractBloco)componentes.get(j)).getPontos());
                                                    componentes.remove(componentes.get(j));
                                                    vida.aumentarVida();
                                                    vidas.setText(""+vida.getVida());
                                                 }
                                    }
                                    pontuacao.setText(""+score.getPontos());
                                    if(score.getPontos()>=100)
                                        coracao.relocate(140,5);
                                    else if(score.getPontos()>=10)
                                        coracao.relocate(127,5);
                                    
                                }
                            }
                        }
                        
                        if(((Bolinha)componentes.get(i)).bateu()){
                            int a=0;
                            while(a<componentes.size() && (!(componentes.get(a) instanceof Bolinha) || a==i))
                                a++;
                            if(a==componentes.size()){
                                vida.diminuirVida();
                                if(vida.getVida()<0){
                                    timer.stop();
                                    gameOverPainel();
                                }
                                else{
                                    vidas.setText(""+vida.getVida());
                                    getChildren().remove(componentes.get(i).getFigura());
                                    componentes.remove(componentes.get(i));
                                    componentes.add(1,new Bolinha(componentes.get(0).cx+45,550,pane));
                                    getChildren().add(componentes.get(1).getFigura());
                                }
                            }
                            else{
                                getChildren().remove(componentes.get(i).getFigura());
                                componentes.remove(componentes.get(i));
                            }
                            
                        }
                        
                    }
                    else if (componentes.get(i) instanceof BlocoVerde){
                            for(int j=0;j<componentes.size();j++){
                                if(i!=j && componentes.get(j) instanceof BlocoVerde){
                                    if(((BlocoVerde)componentes.get(i)).colidiu(componentes.get(j))){
                                        ((BlocoVerde)componentes.get(i)).mudarDirecao();
                                        ((BlocoVerde)componentes.get(j)).mudarDirecao();
                                    }
                                        
                                }
                            }
                            
                         }
                        
                    
                }
                int pos=0;
                while(pos<componentes.size() && !(componentes.get(pos) instanceof AbstractBloco))
                    pos++;
                if(pos==componentes.size()){
                    timer.stop();
                    if(level<3)
                    proximoLevel();
                    else{
                        jogoFim();
                    }
                        
                }
                
            }
        };
        timer.start();
        
        if(!efeitoLigado){
            efeitos=new Thread(()->{
                while(true){
                    try{Thread.sleep(1000);}catch(InterruptedException ex){}
                    if(!pausado){
                        for(int i=0;i<componentes.size();i++){
                            if(componentes.get(i) instanceof Bolinha){
                                if((((Bolinha)componentes.get(i)).getTempo()!=0)){
                                    ((Bolinha)componentes.get(i)).diminuirTempo();
                                    if(((Bolinha)componentes.get(i)).getTempo()==0)
                                        ((Bolinha)componentes.get(i)).diminuirVelocidade();               
                                }
                            }
                        }
                    }
                }

            });
            efeitos.start();
            efeitoLigado=true;
        }
        
        
        btmenu.setOnAction(e->{
            timer.stop();
            pausado=true;
            abrirMenu();     
        });
        pane.setOnKeyPressed(e->{
            if(e.getCode()==KeyCode.P){
                timer.stop();
                pausado=true;
                abrirMenu();
            }
        });
        this.getScene().getWindow().setOnCloseRequest(e->{System.exit(0);});
    }
    
    private void abrirMenu() {
        
        Button btvoltar = new Button("Retornar");btvoltar.setPrefSize(200, 36);
        Button btinicio = new Button("Inicio");btinicio.setPrefSize(200, 36);
        Button btsair = new Button("Sair");btsair.setPrefSize(200, 36);
        Label titulo=new Label("MENU");
        VBox menu=new VBox(titulo,btvoltar,btinicio,btsair);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-border-color:#ffffff;"+            
                      "-fx-background-color:#000000;");
        menu.setPrefWidth(270); menu.setPrefHeight(280); 
        menu.setSpacing(20);
        
        btvoltar.setStyle(estiloBotao);
        btvoltar.setOnMouseEntered(e -> btvoltar.setStyle(estiloEntraBotao));
        btvoltar.setOnMouseExited(e -> btvoltar.setStyle(estiloSaiBotao));
        
        
        btinicio.setStyle(estiloBotao);
        btinicio.setOnMouseEntered(e -> btinicio.setStyle(estiloEntraBotao));
        btinicio.setOnMouseExited(e -> btinicio.setStyle(estiloSaiBotao));
        
        btsair.setStyle(estiloBotao);
        btsair.setOnMouseEntered(e -> btsair.setStyle(estiloEntraBotao));
        btsair.setOnMouseExited(e -> btsair.setStyle(estiloSaiBotao));
        
        Font font=new Font("Verdana",17);
        btvoltar.setFont(font);
        btinicio.setFont(font);
        btsair.setFont(font);
        
        titulo.setStyle("-fx-font: 30px Verdana;"+
                        "-fx-text-fill:radial-gradient(radius 100%, #ff6600, #ff0000, #ff6600);"+
                        "-fx-stroke-width: 10;");
        
        btvoltar.setOnAction(e->{
            menu.getScene().getWindow().hide();
            timer.start();
            pausado=false;
        });
        
        btinicio.setOnAction(e->{
            level=1;
            getChildren().clear();
            componentes.removeAll(componentes);
            menu.getScene().getWindow().hide();
            telaInicial();
            pausado=false;
        });
        btsair.setOnAction(e->{
            System.exit(0);
        });
        Stage stage=new Stage();
        Scene scene=new Scene(menu);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);// retira toda estrutura da janela, inclusive as bordas, tive que cofigurar uma nova borda
        stage.showAndWait();
    }
    
    private void gameOverPainel(){
        Button btjogar = new Button("Jogar Novamente");btjogar.setPrefSize(200, 36);
        Button btinicio = new Button("Inicio");btinicio.setPrefSize(200, 36);
        Button btsair = new Button("Sair");btsair.setPrefSize(200, 36);
        Label titulo=new Label("Game Over");
        Label pontuacao=new Label("Pontuação: "+score.getPontos());
        VBox gameOver=new VBox(titulo,pontuacao,btjogar,btinicio,btsair);
        gameOver.setPrefWidth(350); gameOver.setPrefHeight(280);
        gameOver.setAlignment(Pos.CENTER);
        gameOver.setStyle("-fx-border-color:#ffffff;"+
                          "-fx-background-color:#000000;");
        gameOver.setSpacing(20); 
        
        btjogar.setStyle(estiloBotao);
        btjogar.setOnMouseEntered(e -> btjogar.setStyle(estiloEntraBotao));
        btjogar.setOnMouseExited(e -> btjogar.setStyle(estiloSaiBotao));
        btinicio.setStyle(estiloBotao);
        
        btinicio.setOnMouseEntered(e -> btinicio.setStyle(estiloEntraBotao));
        btinicio.setOnMouseExited(e -> btinicio.setStyle(estiloSaiBotao));
        
        btsair.setStyle(estiloBotao);
        btsair.setOnMouseEntered(e -> btsair.setStyle(estiloEntraBotao));
        btsair.setOnMouseExited(e -> btsair.setStyle(estiloSaiBotao));
        
        Font font=new Font("Verdana",17);
        btjogar.setFont(font);
        btinicio.setFont(font);
        btsair.setFont(font);
        
        titulo.setStyle("-fx-font: 30px Verdana;"+
                        "-fx-text-fill:radial-gradient(radius 100%, #ff6600, #ff0000, #ff6600);"+
                        "-fx-stroke-width: 10;");
        pontuacao.setStyle("-fx-font: 15px Verdana;"+
                           "-fx-text-fill:radial-gradient(radius 100%, #ffff1a, #ffffff, #ffff1a);"+
                           "-fx-stroke-width: 1;");

        btjogar.setOnAction(e->{
            getChildren().clear();
            score.zerar();
            componentes.removeAll(componentes);
            gameOver.getScene().getWindow().hide();
            Platform.runLater(()->gameStart());
        });
        
        btinicio.setOnAction(e->{
            getChildren().clear();
            componentes.removeAll(componentes);
            gameOver.getScene().getWindow().hide();
            telaInicial();

        });
        
        btsair.setOnAction(e->{
            System.exit(0);
        });
        level=1;
        Stage stage=new Stage();
        Scene scene=new Scene(gameOver);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);// retira toda estrutura da janela, inclusive as bordas, tive que cofigurar uma nova borda
        stage.show();
    }
    public void proximoLevel(){
        Button btprox = new Button("Próximo level");btprox.setPrefSize(200, 36);
        Button btinicio = new Button("Inicio");btinicio.setPrefSize(200, 36);
        Button btsair = new Button("Sair");btsair.setPrefSize(200, 36);
        
        Label titulo=new Label("Level Completado");
        Label pontuacao=new Label("Pontuação: "+score.getPontos());
        VBox proxLevel=new VBox(titulo,pontuacao,btprox,btinicio,btsair);
        proxLevel.setPrefWidth(350); proxLevel.setPrefHeight(280);
        proxLevel.setAlignment(Pos.CENTER);
        proxLevel.setStyle("-fx-border-color:#ffffff;"+
                          "-fx-background-color:#000000;");
        proxLevel.setSpacing(20); 
        
        titulo.setStyle("-fx-font: 30px Verdana;"+
                        "-fx-text-fill:radial-gradient(radius 100%, #ff6600, #ff0000, #ff6600);"+
                        "-fx-stroke-width: 1;");
        
        pontuacao.setStyle("-fx-font: 15px Verdana;"+
                           "-fx-text-fill:radial-gradient(radius 100%, #ffff1a, #ffffff, #ffff1a);"+
                           "-fx-stroke-width: 1;");
        
        btprox.setStyle(estiloBotao);
        btprox.setOnMouseEntered(e -> btprox.setStyle(estiloEntraBotao));
        btprox.setOnMouseExited(e -> btprox.setStyle(estiloSaiBotao));
        
        btinicio.setStyle(estiloBotao);
        btinicio.setOnMouseEntered(e -> btinicio.setStyle(estiloEntraBotao));
        btinicio.setOnMouseExited(e -> btinicio.setStyle(estiloSaiBotao));
        
        btsair.setStyle(estiloBotao);
        btsair.setOnMouseEntered(e -> btsair.setStyle(estiloEntraBotao));
        btsair.setOnMouseExited(e -> btsair.setStyle(estiloSaiBotao));
        
        Font font=new Font("Verdana",17);
        btprox.setFont(font);
        btinicio.setFont(font);
        btsair.setFont(font);
        
        btprox.setOnAction(e->{
            getChildren().clear();
            componentes.removeAll(componentes);
            proxLevel.getScene().getWindow().hide();
            score.zerar();
            level++;
            Platform.runLater(()->gameStart());
        });
        btinicio.setOnAction(e->{
            getChildren().clear();
            componentes.removeAll(componentes);
            proxLevel.getScene().getWindow().hide();
            level=1;
            telaInicial();

        });
        
        btsair.setOnAction(e->{
            System.exit(0);
        });
        
        Stage stage=new Stage();
        Scene scene=new Scene(proxLevel);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);// retira toda estrutura da janela
        stage.show();
    }
    public void jogoFim(){
        Button btinicio = new Button("Inicio");btinicio.setPrefSize(200, 36);
        Button btsair = new Button("Sair");btsair.setPrefSize(200, 36);
        
        Label titulo=new Label("Jogo Completado");
        Label pontuacao=new Label("Pontuação: "+score.getPontos());
        VBox proxLevel=new VBox(titulo,pontuacao,btinicio,btsair);
        
        
        proxLevel.setPrefWidth(350); proxLevel.setPrefHeight(250);
        proxLevel.setAlignment(Pos.CENTER);
        proxLevel.setStyle("-fx-border-color:#ffffff;"+
                          "-fx-background-color:#000000;");
        proxLevel.setSpacing(20); 
        
        titulo.setStyle("-fx-font: 30px Verdana;"+
                        "-fx-text-fill:radial-gradient(radius 100%, #ff6600, #ff0000, #ff6600);"+
                        "-fx-stroke-width: 1;");
        pontuacao.setStyle("-fx-font: 15px Verdana;"+
                           "-fx-text-fill:radial-gradient(radius 100%, #ffff1a, #ffffff, #ffff1a);"+
                           "-fx-stroke-width: 1;");
        
        btinicio.setStyle(estiloBotao);
        btinicio.setOnMouseEntered(e -> btinicio.setStyle(estiloEntraBotao));
        btinicio.setOnMouseExited(e -> btinicio.setStyle(estiloSaiBotao));
        
        btsair.setStyle(estiloBotao);
        btsair.setOnMouseEntered(e -> btsair.setStyle(estiloEntraBotao));
        btsair.setOnMouseExited(e -> btsair.setStyle(estiloSaiBotao));
        
        Font font=new Font("Verdana",17);
        btinicio.setFont(font);
        btsair.setFont(font);
        
        btinicio.setOnAction(e->{
            getChildren().clear();
            componentes.removeAll(componentes);
            proxLevel.getScene().getWindow().hide();
            level=1;
            telaInicial();    

        });
        
        btsair.setOnAction(e->{
            System.exit(0);
        });
        
        Stage stage=new Stage();
        Scene scene=new Scene(proxLevel);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);// retira toda estrutura da janela, inclusive as bordas, tive que cofigurar uma nova borda
        stage.show();
    }
}
