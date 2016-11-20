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
public class Autodromo extends BancoDados implements Serializable{
    private int aut_codigo;
    private String aut_descricao;
    private String aut_nome_pista;
    private String aut_uf;
    private String aut_cidade;

    public int getAut_codigo() {
        return aut_codigo;
    }

    public void setAut_codigo(int aut_codigo) {
        this.aut_codigo = aut_codigo;
    }

    public String getAut_descricao() {
        return aut_descricao;
    }

    public void setAut_descricao(String aut_descricao) {
        this.aut_descricao = aut_descricao;
    }

    public String getAut_nome_pista() {
        return aut_nome_pista;
    }

    public void setAut_nome_pista(String aut_nome_pista) {
        this.aut_nome_pista = aut_nome_pista;
    }

    public String getAut_uf() {
        return aut_uf;
    }

    public void setAut_uf(String aut_uf) {
        this.aut_uf = aut_uf;
    }

    public String getAut_cidade() {
        return aut_cidade;
    }

    public void setAut_cidade(String aut_cidade) {
        this.aut_cidade = aut_cidade;
    }

  
    
    public String cadastraAutodromos()
    {
      
        String valores = "'"+aut_descricao+"','"+aut_nome_pista+"','"+aut_uf+"','"+aut_cidade+"'";
        return this.inserirDados("autodromo",valores);        
    }
    
    public List<Autodromo> listarAutodromos() throws SQLException
    {
       this.listarDados("autodromo");
       List<Autodromo> dados = new ArrayList<Autodromo>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             Autodromo a = new Autodromo();

             a.setAut_codigo(resultado.getInt(1));
             a.setAut_descricao(resultado.getString(2));       
             a.setAut_nome_pista(resultado.getString(3));
             a.setAut_uf(resultado.getString(4));
             a.setAut_cidade(resultado.getString(5));
             dados.add(a);
       }
       return dados;
    }
    public String alteraAutodromos()
    {
        String valores = "aut_descricao='"+aut_descricao+"',aut_nome_pista='"+aut_nome_pista+"',aut_uf='"+aut_uf+"',aut_cidade='"+aut_cidade+"'";
       String condicao="aut_codigo="+aut_codigo;
        return this.alterarDados("autodromo",valores, condicao);
    }
    
        
    public String excluiAutodromos()
    {
        String condicao="aut_codigo="+aut_codigo;
        return this.excluirDados("autodromo", condicao);
    }
    
    public void listarUmAutodromo(String condicao)
    {
        try{
            this.listarUm("autodromo", condicao);
            resultado.next();   

            aut_codigo = resultado.getInt(1);
            aut_nome_pista = resultado.getString(2);
            aut_uf = resultado.getString(3);
            aut_cidade = resultado.getString(4);
            
             
        }catch(Exception e){ 
        }
   
    }
}
