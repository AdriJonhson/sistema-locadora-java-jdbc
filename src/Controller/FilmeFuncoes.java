package Controller;

import Modelo.Filme;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FilmeFuncoes {

    public static void copyFile(File source, File destination) throws IOException {
        if (destination.exists()) {
            destination.delete();
        }
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen()) {
                sourceChannel.close();
            }
            if (destinationChannel != null && destinationChannel.isOpen()) {
                destinationChannel.close();
            }
        }
    }

    public boolean verificarCamposVazios(Filme filme){
        boolean chave = false;
        
        String titulo = filme.getTitulo();
        int ano = filme.getAno();
        String duracao = filme.getDuracao();
        String capa = filme.getCapa();
        
        if(!(titulo.equals("") || ano <= 0 || duracao.equals("") || capa.equals(""))){
            chave = true;
        }
        
        return chave;
    }

}
