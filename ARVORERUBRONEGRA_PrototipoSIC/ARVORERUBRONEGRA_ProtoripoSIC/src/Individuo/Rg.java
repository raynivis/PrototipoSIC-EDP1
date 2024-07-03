package Individuo;

/**
 *
 * @author ra
 */
public class Rg {
    private String rg;
    private String estadoRG;

    public Rg(String rg, String estadoRG) {
        this.rg = rg;
        this.estadoRG = estadoRG;
    }
    
    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEstadoRG() {
        return estadoRG;
    }

    public void setEstadoRG(String estadoRG) {
        this.estadoRG = estadoRG;
    }
    
}
