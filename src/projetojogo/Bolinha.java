package projetojogo;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Bolinha extends GameObject{
    private int passox;
    private int passoy;
    private Pane pane;
    private int tempo;
    Image bola = new Image("projetojogo/bola.jpg");
    public Bolinha(int cx,int cy,Pane pane){
        super(cx,cy,new Circle(cx,cy,10),pane);
        getFigura().setFill(new ImagePattern(bola));
        this.passox=4;
        this.passoy=-4;
        this.pane=pane;
        this.tempo=0;
    }
    @Override
    public void mover()
    {
       
       if( cx>=pane.getWidth()-10 || cx<=2){
           if(cx>=pane.getWidth()-10){
               cx=(int) (pane.getWidth()-12);
           }
           else
               cx=3;
           passox=passox*-1;
       }
       if(cy<=50)
       {
           cy=51;
           passoy=passoy*-1;
       }
       
       cx+=passox;
       cy+=passoy;
       
       ((Circle)getFigura()).setCenterX(cx);
       ((Circle)getFigura()).setCenterY(cy);
      
    }
    public int getTempo(){
        return tempo;
    }
    public void diminuirTempo(){
        tempo--;
    }
    public void tempoMovimentoRapido(){
        this.tempo+=15;
    }
    public void aumentarVelocidade(){
        this.passox*=2;
        this.passoy*=2;
    }
    public void diminuirVelocidade(){
        this.passox/=2;
        this.passoy/=2;
    }
    public void mudarDirecao(GameObject outro){
        if(outro instanceof AbstractBloco){
            if(this.cy>((AbstractBloco) outro).cy && this.cy<((AbstractBloco) outro).cy+40){
                passox=passox*-1;
            }
            else
                passoy=passoy*-1;
        }
        else
            passoy=passoy*-1;
            
        cx+=passox;
        cy+=passoy;
        ((Circle)getFigura()).setCenterY(cy);
        ((Circle)getFigura()).setCenterX(cx);
    }
            
    public boolean colidiu(GameObject outra)
    {    
        return figura.intersects(outra.getFigura().getLayoutBounds());
    }
    
    public boolean bateu(){
        return cy>=pane.getHeight()-10;
    }

    public int getPassox() {
        return passox;
    }

    public int getPassoy() {
        return passoy;
    }

    public void setPassox(int passox) {
        this.passox = passox;
    }

    public void setPassoy(int passoy) {
        this.passoy = passoy;
    }
    
}
