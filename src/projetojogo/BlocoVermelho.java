package projetojogo;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;

public class BlocoVermelho extends AbstractBloco{
    Image vermelho = new Image("projetojogo/vermelho.jpg");
    public BlocoVermelho(int cx,int cy,int pontos,Pane pane){
        super(cx,cy,pontos,pane);
        //getFigura().setStyle("-fx-fill: radial-gradient(radius 100%, #990000, #ff0000, #990000);");
        getFigura().setFill(new ImagePattern(vermelho));
    }
    @Override
    public void mover(){
        
    }
}
