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
public class Equipe extends BancoDados implements Serializable{
    private int eqp_codigo;
    private String eqp_nome;
    private String eqp_data_formacao;

    public int getEqp_codigo() {
        return eqp_codigo;
    }

    public void setEqp_codigo(int eqp_codigo) {
        this.eqp_codigo = eqp_codigo;
    }

    public String getEqp_nome() {
        return eqp_nome;
    }

    public void setEqp_nome(String eqp_nome) {
        this.eqp_nome = eqp_nome;
    }

    public String getEqp_data_formacao() {
        return eqp_data_formacao;
    }

    public void setEqp_data_formacao(String eqp_data_formacao) {
        this.eqp_data_formacao = eqp_data_formacao;
    }
    
    public String cadastraEquipes()
    {
      
        String valores = "'"+eqp_nome+"','"+eqp_data_formacao+"'";
        return this.inserirDados("equipe",valores);        
    }
    
    public List<Equipe> listarEquipes() throws SQLException
    {
       this.listarDados("equipe");
       List<Equipe> dados = new ArrayList<Equipe>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             Equipe e = new Equipe();

             e.setEqp_codigo(resultado.getInt(1));
             e.setEqp_nome(resultado.getString(2));       
             e.setEqp_data_formacao(resultado.getString(3));
             dados.add(e);
       }
       return dados;
    }
    public String alteraEquipes()
    {
        String valores = "eqp_nome='"+eqp_nome+"',eqp_data_formacao='"+eqp_data_formacao+"'";
       String condicao="eqp_codigo="+eqp_codigo;
        return this.alterarDados("equipe",valores, condicao);
    }
    
        
    public String excluiEquipes()
    {
        String condicao="eqp_codigo="+eqp_codigo;
        return this.excluirDados("equipe", condicao);
    }
    
    public void listarUmaEquipe(String condicao)
    {
        try{
            this.listarUm("equipe", condicao);
            resultado.next();   

            eqp_codigo = resultado.getInt(1);
            eqp_nome = resultado.getString(2);
            eqp_data_formacao = resultado.getString(3);
            
             
        }catch(Exception e){ 
        }
   
    }
}
