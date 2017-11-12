package Controller;

import Modelo.Cliente;

public class ClienteFuncoes {

    public boolean VerificarCamposCadastro(Cliente cliente) {
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
        //VERIFICAR CAMPOS: CPF;RG;TELEFONE;CEP;
        if (!(nome.trim().equals("") || nasc.trim().equals("") || email.trim().equals("") || bairro.trim().equals("")
                || numero <= 0)) {
            chave = true;
        }

        return chave;
    }
}
