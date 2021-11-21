package projetojogo;

public class Vida {
    private int vida;
    
    public Vida(){
        this.vida=3;
    }
    
    public void diminuirVida(){
        vida--;
    }
    public void aumentarVida(){
        vida++;
    }

    public int getVida() {
        return vida;
    }
}
