package Relatorio;

/**
 *
 * @author nivis
 */
public class EspalhamentoEstado {
    public static int retornaIndiceEstado(String sigla) {
        int i = -1;
        switch(sigla){
            case "AC":
                i = 0;
            break;
            case "AL":
                i = 1;
            break;
            case "AP":
                i = 2;
            break;
            case "AM":
                i = 3;
            break;
            case "BA":
                i = 4;
            break;
            case "CE":
                i = 5;
            break;
            case "DF":
                i = 6;
            break;
            case "ES":
                i = 7;
            break;
            case "GO":
                i = 8;
            break;
            case "MA":
                i = 9;
            break;
            case "MT":
                i = 10;
            break;
            case "MS":
                i = 11;
            break;
            case "MG":
                i = 12;
            break;
            case "PA":
                i = 13;
            break;
            case "PB":
                i = 14;
            break;
            case "PR":
                i = 15;
            break;
            case "PE":
                i = 16;
            break;
            case "PI":
                i = 17;
            break;
            case "RJ":
                i = 18;
            break;
            case "RN":
                i = 19;
            break;
            case "RS":
                i = 20;
            break;
            case "RO":
                i = 21;
            break;
            case "RR":
                i = 22;
            break;
            case "SC":
                i = 23;
            break;
            case "SP":
                i = 24;
            break;
            case "SE":
               i = 25;
            break;
            case "TO":
                i = 26;
            break;               
            default:
            break;
        }       
        return i;             
    }
}
