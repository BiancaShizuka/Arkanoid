package projetojogo;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Raquete extends GameObject{
    private Pane pane;
    Image raquete = new Image("projetojogo/raquete.jpg");
    public Raquete(int cx,int cy,Pane pane){
        super(cx,cy,new Rectangle(cx,cy,90,20),pane);
        this.pane=pane;
        getFigura().setFill(new ImagePattern(raquete));
    }
    @Override
    public void mover(){
        pane.getScene().setOnMouseMoved(e->{
            if(e.getX()>=cx+45 && cx<=pane.getWidth()-90)
                cx=cx+8;
            else{
                if(cx>=0)
                    cx=cx-8;
            }
                
            
            ((Rectangle)getFigura()).setX(cx);
        });
        pane.getScene().setOnKeyPressed(e->{

            if(e.getCode()==KeyCode.RIGHT){
                if(cx<=pane.getWidth()-90){
                    cx=cx+10;
                }
            }
            if(e.getCode()==KeyCode.LEFT){
                if(cx>=0){
                    cx=cx-10;
                }
            }
            ((Rectangle)getFigura()).setX(cx);
        });
    }

}
