package Controller;

public class FuncionarioFuncoes {
    
    public boolean verificarCamposLogin(String login, String senha){
        boolean chave = false;
        
        if(!(login.equals("") || senha.equals(""))){
           chave = true;
        }
        
        return chave;
    }
    
    public boolean verificarCamposCadastro(String nome, String login, String senha){
        boolean chave = false;
        
        if(!(nome.equals("") || login.equals("") || senha.equals(""))){
           chave = true;
        }
        
        return chave;
    }
}
