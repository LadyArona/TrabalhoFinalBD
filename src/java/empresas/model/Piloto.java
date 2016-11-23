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
public class Piloto extends BancoDados implements Serializable {
 
 private int pil_codigo; 
 private String pil_data_nascimento; 
 private String pil_uf_naturalidade;
 private String pil_cpf;
 private String pil_nome;
 private int eqp_codigo;

    public int getPil_codigo() {
        return pil_codigo;
    }

    public void setPil_codigo(int pil_codigo) {
        this.pil_codigo = pil_codigo;
    }

    public String getPil_data_nascimento() {
        return pil_data_nascimento;
    }

    public void setPil_data_nascimento(String pil_data_nascimento) {
        this.pil_data_nascimento = pil_data_nascimento;
    }

    public String getPil_uf_naturalidade() {
        return pil_uf_naturalidade;
    }

    public void setPil_uf_naturalidade(String pil_uf_naturalidade) {
        this.pil_uf_naturalidade = pil_uf_naturalidade;
    }

    public String getPil_cpf() {
        return pil_cpf;
    }

    public void setPil_cpf(String pil_cpf) {
        this.pil_cpf = pil_cpf;
    }

    public String getPil_nome() {
        return pil_nome;
    }

    public void setPil_nome(String pil_nome) {
        this.pil_nome = pil_nome;
    }

    public int getEqp_codigo() {
        return eqp_codigo;
    }

    public void setEqp_codigo(int eqp_codigo) {
        this.eqp_codigo = eqp_codigo;
    }

  
 
   public String cadastraPilotos()
    {        
         String valores = "'"+pil_data_nascimento+"','"+pil_uf_naturalidade+"','"+pil_cpf+"','"+pil_nome+"',"+eqp_codigo+"";
        return this.inserirDados("piloto",valores);   
    }
    
    public List<Piloto> listarPilotos() throws SQLException
    {
       this.listarDados("piloto");
       List<Piloto> dados = new ArrayList<Piloto>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             Piloto p = new Piloto();
             p.setPil_codigo(resultado.getInt(1));
             p.setPil_data_nascimento(resultado.getString(2));       
             p.setPil_uf_naturalidade(resultado.getString(3));
             p.setPil_cpf(resultado.getString(4));
             p.setPil_nome(resultado.getString(5));
             p.setEqp_codigo(resultado.getInt(6));
             dados.add(p); //adciona os livros a lista
       }
       return dados;
    }
    public String alteraPilotos()
    {
        //String valores ="nome='"+nome+"'";
     // String valores =  "nome='"+nome+"',cpf='"+cpf+"',email='"+email+"',salariobase="+salariobase+",dataadmissao='"+dataadmissao+"',id_cargo="+id_cargo+",id_empresa="+id_empresa+",id_cidade="+id_cidade+",id_estado="+id_estado+"";
       String valores = "pil_data_nascimento='"+pil_data_nascimento+"', pil_uf_naturalidade='"+pil_uf_naturalidade+"',pil_cpf='"+pil_cpf+"',pil_nome='"+pil_nome+"',eqp_codigo="+eqp_codigo+""; 
       String condicao="pil_codigo="+pil_codigo;
        return this.alterarDados("piloto",valores, condicao);
    }
    
        
    public String excluiPilotos()
    {
        String condicao="pil_codigo="+pil_codigo;
        return this.excluirDados("piloto", condicao);
    }
    
    public void listarUmPiloto(String condicao)
    {
        try{
            this.listarUm("piloto", condicao);
            resultado.next();     
            pil_codigo= resultado.getInt(1);
            pil_data_nascimento = resultado.getString(2);
            pil_uf_naturalidade= resultado.getString(3);
             pil_cpf = resultado.getString(4);
             pil_nome = resultado.getString(5);
             eqp_codigo = resultado.getInt(6);
             
        }catch(Exception e){ 
        }
   
    }
 
}

    

