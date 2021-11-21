package projetojogo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ProjetoJogo extends Application {
    
    @Override
    public void start(Stage primaryStage) {
            ArkanoidGame gp= new ArkanoidGame();
            Scene scene = new Scene(gp, 720, 600);
            primaryStage.setTitle("Game Arkanoid");
            primaryStage.setScene(scene);
            primaryStage.show();
    };
        
    

    public static void main(String[] args) {
        launch(args);
    }
    
}
