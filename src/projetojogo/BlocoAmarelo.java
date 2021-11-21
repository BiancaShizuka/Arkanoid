package projetojogo;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Shape;


public class BlocoAmarelo extends AbstractBloco{
    Image amarelo = new Image("projetojogo/amarelo.jpg");
    public BlocoAmarelo(int cx,int cy,int pontos,Pane pane){
        super(cx,cy,pontos,pane);
        //getFigura().setStyle("-fx-fill: radial-gradient(radius 100%, #ffcc00, #ffe066, #ffcc00);");
        getFigura().setFill(new ImagePattern(amarelo));
    }

 
   
}
