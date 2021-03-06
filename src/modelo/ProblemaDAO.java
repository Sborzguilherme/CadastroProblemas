/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Micro Solution
 */
public class ProblemaDAO {
    public static ArrayList<Problema> obterLista(String local){ //   REPETIÇÃO METODO obterListaArquivos
        //return MeioArmazenamento.MEIO_ARMAZENAMENTO_EQUIPAMENTOS;
        ArrayList<Problema> retorno = new ArrayList<>();
        try{
            
            Path caminhoArquivo = Paths.get(local);
            if(caminhoArquivo.toFile().exists()){
                for(String linhaAtual : Files.readAllLines(caminhoArquivo)){
                String[] dado = linhaAtual.split(";");
                Problema objeto = new Problema();
                SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
                objeto.setCodigo(Integer.parseInt(dado[0]));
                objeto.setDescricao(dado[1]);
                objeto.setData(formatadorData.parse(dado[2]));
                objeto.setSituacao(dado[3]);
                
                retorno.add(objeto);
            }
            
            }

            }catch(Exception e){ // NECESSARIO MUDAR POR CAUSA DA TRANSFORMAÇÃO DA DATA
                Logger.getLogger(Problema.class.getName()).log(Level.SEVERE, null, e);
            }
        return retorno;
        
    }
    public static ArrayList<Problema> obterListaAbertos(String local){ //   REPETIÇÃO METODO obterListaArquivos
        //return MeioArmazenamento.MEIO_ARMAZENAMENTO_EQUIPAMENTOS;
        ArrayList<Problema> retorno = new ArrayList<>();
        try{
            
            Path caminhoArquivo = Paths.get(local);
            if(caminhoArquivo.toFile().exists()){
                for(String linhaAtual : Files.readAllLines(caminhoArquivo)){
                String[] dado = linhaAtual.split(";");
                Problema objeto = new Problema();
                SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
                objeto.setCodigo(Integer.parseInt(dado[0]));
                objeto.setDescricao(dado[1]);
                objeto.setData(formatadorData.parse(dado[2]));
                objeto.setSituacao(dado[3]);
                
                if(objeto.getSituacao().equals("ABERTO")){
                    retorno.add(objeto);
                
                }
                
            }
            
            }

            }catch(Exception e){ // NECESSARIO MUDAR POR CAUSA DA TRANSFORMAÇÃO DA DATA
                Logger.getLogger(Problema.class.getName()).log(Level.SEVERE, null, e);
            }
        return retorno;
        
    }
    
    public static void salvar(Problema paraSalvar, String local){
        //MeioArmazenamento.MEIO_ARMAZENAMENTO_EQUIPAMENTOS.add(this);
         
        try{
            Path caminhoArquivo = Paths.get(local);
            //String separadorLinha = System.getProperty("Line.separator");
            String linhaEquip;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            linhaEquip =(paraSalvar.getCodigo()+";"+paraSalvar.getDescricao()+";"+sdf.format(paraSalvar.getData())+";"+paraSalvar.getSituacao())+"\r\n";
            
             
            
            Files.write(caminhoArquivo, linhaEquip.getBytes(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);
            
            
        
        }catch(Exception e){
            
        }
    }
    
   public static void copy(File s, File t) throws IOException {
      FileChannel in = (new FileInputStream(s)).getChannel();
      FileChannel out = (new FileOutputStream(t)).getChannel();
      in.transferTo(0, s.length(), out);
      in.close();
      out.close();
}

    
public static int contaObjetos(){
        int contador =0;
        try{
            Path caminhoArquivo = Paths.get("Problema.txt");
            if(caminhoArquivo.toFile().exists()){
                for(String linhaAtual : Files.readAllLines(caminhoArquivo)){
                    contador++;
                }
            }
        }catch(Exception e){
            Logger.getLogger(Problema.class.getName()).log(Level.SEVERE, null, e);
        }
        return contador;
    }
    @SuppressWarnings("null")
    public static void mudaSituacao(int op) throws IOException{
        
        File arquivo = new File("Auxiliar.txt");
        File arquivoProblema = new File("Problema.txt");
        ProblemaDAO.copy(arquivoProblema, arquivo);
        
        arquivoProblema.delete();
        
         try{
            Path caminhoArquivo = Paths.get("Auxiliar.txt");
            
            if(caminhoArquivo.toFile().exists()){
                for(String linhaAtual : Files.readAllLines(caminhoArquivo)){ //MONTAGEM DO OBJETO À PARTIR DOS DADOS DO ARQUIVO
                String[] dado = linhaAtual.split(";");
                Problema objeto = new Problema();
                SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
                objeto.setCodigo(Integer.parseInt(dado[0]));
                objeto.setDescricao(dado[1]);
                objeto.setData(formatadorData.parse(dado[2]));
                
                if(op == objeto.getCodigo()){
                    objeto.setSituacao("RESOLVIDO");
                }else{
                    objeto.setSituacao(dado[3]);
                }
                ProblemaDAO.salvar(objeto, "Problema.txt");
            }
            
                File arquivo2 = caminhoArquivo.toFile();
                arquivo.delete();
            }

            }catch(Exception e){ // NECESSARIO MUDAR POR CAUSA DA TRANSFORMAÇÃO DA DATA
                Logger.getLogger(Problema.class.getName()).log(Level.SEVERE, null, e);
            }

    }

}
