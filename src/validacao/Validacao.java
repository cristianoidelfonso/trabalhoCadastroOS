package validacao;

/**
 *
 * @author Idelfonso
 */
public class Validacao {
    
    public Validacao(){
        
    }
    
    private static boolean validaTexto(String txt){
        return !txt.equals("") && txt.matches("([A-Z]{1}[A-Za-z\\s]+)");
    }
    
    private static boolean validaData(String data){
        return !data.equals("") && data.matches("(\\d{4}\\-\\d{2}\\-\\d{2})");
    }
}
