package Controller;

import Modelo.Cliente;

public class ClienteFuncoes {

    public boolean VerificarCamposCadastro(Cliente cliente) {
        boolean chave = true;
        String nome = cliente.getNome();
        String nasc = cliente.getDt_nasc();
        String rg = cliente.getRg();
        String cpf = cliente.getCpf();
        String email = cliente.getEmail();
        String telefone = cliente.getTelefone();
        String bairro = cliente.getBairro();
        int numero = cliente.getNumero();
        String cep = cliente.getCep();
        
        if (nome.trim().equals("") || nasc.trim().length() < 10 || rg.trim().length() < 12 || cpf.trim().length() < 14
                || email.trim().equals("") || bairro.trim().equals("") || numero <= 0 || telefone.trim().length() < 14 ||
                cep.trim().length() < 9) {
            chave = false;
        }

        return chave;
    }
}
