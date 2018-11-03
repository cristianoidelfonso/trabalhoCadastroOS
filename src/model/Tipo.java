package model;

/**
 *
 * @author Idelfonso
 */
class Tipo {
    
    private static int id;
    private static String nome;
    
    public Tipo(){
        
    }
    
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Tipo.id = id;
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Tipo.nome = nome;
    }
}
