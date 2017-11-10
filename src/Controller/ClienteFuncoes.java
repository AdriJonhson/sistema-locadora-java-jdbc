package Controller;

import Modelo.Cliente;

public class ClienteFuncoes {
    
    public boolean VerificarCamposCadastro(Cliente cliente){
        boolean chave = false;
        String nome = cliente.getNome();
        String nasc = cliente.getDt_nasc();
        String rg = cliente.getRg();
        String cpf = cliente.getCpf();
        String email = cliente.getEmail();
        String telefone = cliente.getTelefone();
        String bairro = cliente.getBairro();
        int numero = cliente.getNumero();
        String cep = cliente.getCep();
        
        if(!(nome.trim().equals("") || nasc.trim().equals("") || rg.trim().length() < 9 || cpf.trim().length() < 11 ||
                cpf.trim().equals("") || email.trim().equals("") || telefone.trim().length() < 13 || bairro.trim().equals("") ||
                numero <= 0 || cep.trim().equals(""))){
                    chave = true;
        }
        
        return chave;
    }
}
