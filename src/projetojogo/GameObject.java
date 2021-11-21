package projetojogo;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;


abstract public class GameObject implements IGame{
    protected int cx;
    protected int cy;
    protected Shape figura;
    public GameObject(int cx,int cy,Shape figura,Pane pane){
        this.cx=cx;
        this.cy=cy;
        this.figura=figura;
    }
    
    public Shape getFigura() {
        return figura;
    }
    public void mover(){
        
    }
}
