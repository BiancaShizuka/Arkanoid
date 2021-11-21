package projetojogo;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BlocoVerde extends AbstractBloco{
    private int qtColisao;
    private Pane pane;
    private int passo;
    Image verde = new Image("projetojogo/verde.png");
    public BlocoVerde(int cx,int cy,int pontos,Pane pane){
        super(cx,cy,pontos,pane);
        //getFigura().setStyle("-fx-fill: radial-gradient(radius 100%, #00802b, #00cc44, #00802b);");
        getFigura().setFill(new ImagePattern(verde));
        this.qtColisao=0;
        this.pane=pane;
        this.passo=1;
    }
    @Override
    public void mover(){
        cx+=passo;
        ((Rectangle)getFigura()).setX(cx);
        if(cx>=pane.getWidth()-85 || cx<=0){    
            passo=passo*=-1;
        } 
        
    }
    
    public void aumentarColisoes(){
        int num=(int)(Math.random()*3)+1;
        cy=160+num*90;
        ((Rectangle)getFigura()).setY(cy);
        this.qtColisao++;
    }
    
    public int getQtColisao() {
        return qtColisao;
    }
    public void mudarDirecao(){
        passo=passo*=-1;
        mover();
    }
            
    public boolean colidiu(GameObject outra)
    {    
        return figura.intersects(outra.getFigura().getLayoutBounds());
    }
}
