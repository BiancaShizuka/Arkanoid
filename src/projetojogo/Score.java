package projetojogo;

public class Score {
    private int pontos;
    
    public void Score(){
        this.pontos=0;
    }
    
    public void somar(int pontos){
        this.pontos+=pontos;
    }
    
    public int getPontos() {
        return pontos;
    }
    
    public void zerar(){
        this.pontos=0;
    }
}
