package projetojogo;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class BlocoAzul extends AbstractBloco{
    Image azul = new Image("projetojogo/azul.jpg");
    public BlocoAzul(int cx,int cy,int pontos,Pane pane){
       super(cx,cy,pontos,pane);
       //getFigura().setStyle("-fx-fill: radial-gradient(radius 100%, #003d99, #1a75ff, #003d99);");
       getFigura().setFill(new ImagePattern(azul));
    }

    @Override
    public void mover() {
    }


    
    
}
