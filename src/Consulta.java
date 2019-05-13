import java.io.*;
import java.util.StringTokenizer;
public class Consulta {
    public Consulta() {
        
    }
    public void menuConsultas() {
    int op, q;
    try{
        Validar v = new Validar();
        System.out.println("-----------------Menu de Consultas-----------------");
        do{
            System.out.println("1 - Consultar numero de funcionarios Masculinos \n2 - Consultar numero de Funcionarios Femeninos \n3 - Consultar numero de Funcionarios que preferem nao dizer genero \n4 - Consultar total numero de Funcionarios \n0 - sair");
            op=v.validarInt("a opcao",0,4);
            
            switch(op){
                case 1: 
                    q = consultaGen("Masculino");
                    System.out.println("===============================================================");
                    System.out.println("A empresa tem "+q+" Funcionario(s) Masculinos.");
                    System.out.println("===============================================================");
                break;
                
                case 2: 
                    System.out.println("=====================================================================");
                    q = consultaGen("Femenino");
                    System.out.println("A empresa tem "+q+" Funcionario(s) Femeninos.");
                    System.out.println("=====================================================================");
                break;
                
                case 3: 
                    System.out.println("=====================================================================");
                    q = consultaGen("Personalizado");
                    System.out.println("A empresa tem "+q+" Funcionario(s) que preferem nao dizer o seu genero.");
                    System.out.println("=====================================================================");
                break;
                
                case 4:
                    System.out.println("=====================================================================");
                    q = consultaTotal();
                    System.out.println("A empresa tem um total de "+q+" Funcionario(s).");
                    System.out.println("=====================================================================");
                break;
                
                default: System.out.println("-----------------Fim das Consultas-----------------");
                break;
            }
        }while(op!=0);
        }catch(NumberFormatException nfe){System.out.print(nfe.getMessage());}
          
    }
    public int consultaGen(String word){
        int nr=0;
        String linha="";
        StringTokenizer str;
        try{
        FileReader fr = new FileReader("funcionarios.txt");
        BufferedReader br = new BufferedReader(fr);
        linha=br.readLine();
             while(linha!=null){
            String[] tokens = linha.split("/");
               if(tokens[4].equalsIgnoreCase(word)){
                    for(int i=0; i<tokens.length-4; i++){
                    System.out.print(tokens[i]+"\t");
                }
                     System.out.println(); 
                     nr++;
            }
            linha=br.readLine();
        }    
        br.close();
        }catch(IndexOutOfBoundsException | FileNotFoundException nfe){System.out.print(nfe.getMessage());}
          catch(IOException ios){System.out.print(ios.getMessage());}
        return nr;
    }
    public int consultaTotal(){
        int nr=0;
        String linha="";
        StringTokenizer str;
        try{
        FileReader fr = new FileReader("funcionarios.txt");
        BufferedReader br = new BufferedReader(fr);
        
        linha=br.readLine();
        while(linha!=null){
            String[] tokens = linha.split("/");
             for(int i=0; i<tokens.length-4; i++){
                    System.out.print(tokens[i]+"\t");
                }
            nr++;
            linha=br.readLine();
        }
        br.close();
         }catch(IndexOutOfBoundsException | FileNotFoundException nfe){System.out.print(nfe.getMessage());}
          catch(IOException ios){System.out.print(ios.getMessage());}
        return nr;
    }
}