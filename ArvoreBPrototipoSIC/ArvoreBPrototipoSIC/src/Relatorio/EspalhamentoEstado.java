package Relatorio;

/**
 *
 * @author ray, gustavo
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
    
    public static String retornaIndiceEstado(int i) {
        switch(i){
            case 0:
                return "AC";
            case 1:
                return "AL";
            case 2:
                return "AP";
            case 3:
                return "AM";
            case 4:
                return "BA";
            case 5:
                return "CE";
            case 6:
                return "DF";
            case 7:
                return "ES";
            case 8:
                return "GO";
            case 9:
                return "MA";
            case 10:
                return "MT";
            case 11:
                return "MS";
            case 12:
                return "MG";
            case 13:
                return "PA";
            case 14:
                return "PB";
            case 15:
                return "PR";
            case 16:
                return "PE";
            case 17:
                return "PI";
            case 18:
                return "RJ";
            case 19:
                return "RN";
            case 20:
                return "RS";
            case 21:
                return "RO";
            case 22:
                return "RR";
            case 23:
                return "SC";
            case 24:
                return "SP";
            case 25:
               return "SE";
            case 26:
                return "TO";         
            default:
            break;
        }  
        return "Erro";
    }
}
