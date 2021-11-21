package projetojogo;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

abstract public class AbstractBloco extends GameObject{
    private int pontos;
    private Rectangle bloco;
    public AbstractBloco(int cx,int cy,int pontos,Pane pane){
        super(cx,cy,new Rectangle(cx,cy,85,40),pane);
        this.pontos=pontos;
    }
    
    public int getPontos() {
        return pontos;
    }

}
