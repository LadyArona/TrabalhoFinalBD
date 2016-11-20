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
public class Corrida extends BancoDados implements Serializable{
    private int cor_codigo;
    private String cor_data;
    private String cor_hora;
    private String cor_descricao;

    public int getCor_codigo() {
        return cor_codigo;
    }

    public void setCor_codigo(int cor_codigo) {
        this.cor_codigo = cor_codigo;
    }

    public String getCor_data() {
        return cor_data;
    }

    public void setCor_data(String cor_data) {
        this.cor_data = cor_data;
    }

    public String getCor_hora() {
        return cor_hora;
    }

    public void setCor_hora(String cor_hora) {
        this.cor_hora = cor_hora;
    }

    public String getCor_descricao() {
        return cor_descricao;
    }

    public void setCor_descricao(String cor_descricao) {
        this.cor_descricao = cor_descricao;
    }
    
    public String cadastraCorridas()
    {
      
        String valores = "'"+cor_data+"','"+cor_hora+"','"+cor_descricao+"'";
        return this.inserirDados("corrida",valores);        
    }
    
    public List<Corrida> listarCorridas() throws SQLException
    {
       this.listarDados("corrida");
       List<Corrida> dados = new ArrayList<Corrida>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             Corrida c = new Corrida();

             c.setCor_codigo(resultado.getInt(1));
             c.setCor_data(resultado.getString(2));       
             c.setCor_hora(resultado.getString(3));
             c.setCor_descricao(resultado.getString(4));
             dados.add(c);
       }
       return dados;
    }
    public String alteraCorridas()
    {
        String valores = "cor_data='"+cor_data+"',cor_hora='"+cor_hora+"',cor_descricao='"+cor_descricao+"'";
       String condicao="cor_codigo="+cor_codigo;
        return this.alterarDados("corrida",valores, condicao);
    }
    
        
    public String excluiCorridas()
    {
        String condicao="cor_codigo="+cor_codigo;
        return this.excluirDados("corrida", condicao);
    }
    
    public void listarUmaCorrida(String condicao)
    {
        try{
            this.listarUm("corrida", condicao);
            resultado.next();   

            cor_codigo = resultado.getInt(1);
            cor_data = resultado.getString(2);
            cor_hora = resultado.getString(3);
            cor_descricao = resultado.getString(4);
                        
        }catch(Exception e){ 
        }
   
    }
    
}
