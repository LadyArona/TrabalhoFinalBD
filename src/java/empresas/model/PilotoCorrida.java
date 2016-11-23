/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas.model;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
/**
 *
 * @author dielm
 */
@ManagedBean
@RequestScoped
public class PilotoCorrida extends BancoDados implements Serializable {
    private int pic_codigo;
    private int pil_codigo;
    private int cor_codigo;
    private int pic_posicao_largada;
    private int pic_posicao_chegada;
    private int pic_pontuacao;

    public int getPic_codigo() {
        return pic_codigo;
    }

    public void setPic_codigo(int pic_codigo) {
        this.pic_codigo = pic_codigo;
    }

    public int getPil_codigo() {
        return pil_codigo;
    }

    public void setPil_codigo(int pil_codigo) {
        this.pil_codigo = pil_codigo;
    }

    public int getCor_codigo() {
        return cor_codigo;
    }

    public void setCor_codigo(int cor_codigo) {
        this.cor_codigo = cor_codigo;
    }

    public int getPic_posicao_largada() {
        return pic_posicao_largada;
    }

    public void setPic_posicao_largada(int pic_posicao_largada) {
        this.pic_posicao_largada = pic_posicao_largada;
    }

    public int getPic_posicao_chegada() {
        return pic_posicao_chegada;
    }

    public void setPic_posicao_chegada(int pic_posicao_chegada) {
        this.pic_posicao_chegada = pic_posicao_chegada;
    }

    public int getPic_pontuacao() {
        return pic_pontuacao;
    }

    public void setPic_pontuacao(int pic_pontuacao) {
        this.pic_pontuacao = pic_pontuacao;
    }
    
    
    public String cadastraPilotoCorrida()
    {
      
        String valores = ""+pil_codigo+","+cor_codigo+","+pic_posicao_largada+","+pic_posicao_chegada+","+pic_pontuacao+"";
        return this.inserirDados("piloto_corrida",valores);        
    }
    
    public List<PilotoCorrida> listarPilotoCorrida() throws SQLException
    {
       this.listarDados("piloto_corrida");
       List<PilotoCorrida> dados = new ArrayList<PilotoCorrida>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             PilotoCorrida pc = new PilotoCorrida();
             pc.setPic_codigo(resultado.getInt(1));
             pc.setPil_codigo(resultado.getInt(2));
             pc.setCor_codigo(resultado.getInt(3));       
             pc.setPic_posicao_largada(resultado.getInt(4));
             pc.setPic_posicao_chegada(resultado.getInt(5));
             pc.setPic_pontuacao(resultado.getInt(6));
             
             
             dados.add(pc);
       }
       return dados;
    }
    public String alteraPilotoCorrida()
    {
       String valores = "pil_codigo="+pil_codigo+",cor_codigo="+cor_codigo+",pic_posicao_largada="+pic_posicao_largada+",pic_posicao_chegada="+pic_posicao_chegada+",pic_pontuacao="+pic_pontuacao+"";
       String condicao="pic_codigo="+pic_codigo;
        return this.alterarDados("piloto_corrida",valores, condicao);
    }
    
        
    public String excluiPilotoCorrida()
    {
        String condicao="pic_codigo="+pic_codigo;
        return this.excluirDados("piloto_corrida", condicao);
    }
    
    public void listarUmPilotoCorrida(String condicao)
    {
        try{
            this.listarUm("piloto_corrida", condicao);
            resultado.next();   
            pic_codigo= resultado.getInt(1); 
            pil_codigo = resultado.getInt(2);
            cor_codigo = resultado.getInt(3);
            pic_posicao_largada = resultado.getInt(4);
            pic_posicao_chegada = resultado.getInt(5);
            pic_pontuacao = resultado.getInt(6);
                        
        }catch(Exception e){ 
        }
   
    }
 
}

