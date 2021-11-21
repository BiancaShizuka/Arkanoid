package projetojogo;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;

public class BlocoBranco extends AbstractBloco{
    private int tempo;
    Image branco = new Image("projetojogo/branco.jpg");
    public BlocoBranco(int cx,int cy,int pontos,Pane pane){
        super(cx,cy,pontos,pane);
        //getFigura().setStyle("-fx-fill: radial-gradient(radius 100%, #ffffff, #d9d9d9, #ffffff);");
        getFigura().setFill(new ImagePattern(branco));
        this.tempo=0;
    }
    @Override
    public void mover(){
        
    }

}
