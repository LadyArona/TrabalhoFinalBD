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
public class Funcionarios extends BancoDados implements Serializable {
 private int id_funcionario;
 private String nome;
 private String cpf;
 private String email;
 private  float salariobase;
 private String dataadmissao;
 private int id_cargo;
 private int id_empresa;
 private int id_cidade;
 private int id_estado;

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSalariobase() {
        return salariobase;
    }

    public void setSalariobase(float salariobase) {
        this.salariobase = salariobase;
    }

    public String getDataadmissao() {
        return dataadmissao;
    }

    public void setDataadmissao(String dataadmissao) {
        this.dataadmissao = dataadmissao;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public int getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(int id_cidade) {
        this.id_cidade = id_cidade;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    
   
   public String cadastraFuncionarios()
    {
        
        String valores = "'"+nome+"','"+cpf+"','"+email+"',"+salariobase+",'"+dataadmissao+"',"+id_cargo+","+id_empresa+","+id_cidade+","+id_estado+"";
        return this.inserirDados("funcionarios",valores);        
    }
    
    public List<Funcionarios> listarFuncionarios() throws SQLException
    {
       this.listarDados("funcionarios");
       List<Funcionarios> dados = new ArrayList<Funcionarios>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             Funcionarios f = new Funcionarios();
             f.setId_funcionario(resultado.getInt(1));
             f.setNome(resultado.getString(2));
             f.setCpf(resultado.getString(3));
             f.setEmail(resultado.getString(4));
             f.setSalariobase(resultado.getFloat(5));
             f.setDataadmissao(resultado.getString(6));
             f.setId_cargo(resultado.getInt(7));
             f.setId_empresa(resultado.getInt(8));
             f.setId_cidade(resultado.getInt(9));
             f.setId_estado(resultado.getInt(10));
             dados.add(f); //adciona os livros a lista
       }
       return dados;
    }
    public String alteraFuncionarios()
    {
        //String valores ="nome='"+nome+"'";
      String valores =  "nome='"+nome+"',cpf='"+cpf+"',email='"+email+"',salariobase="+salariobase+",dataadmissao='"+dataadmissao+"',id_cargo="+id_cargo+",id_empresa="+id_empresa+",id_cidade="+id_cidade+",id_estado="+id_estado+"";
        String condicao="id_funcionario="+id_funcionario;
        return this.alterarDados("funcionarios",valores, condicao);
    }
    
        
    public String excluiFuncionarios()
    {
        String condicao="id_funcionario="+id_funcionario;
        return this.excluirDados("funcionarios", condicao);
    }
    
    public void listarUmFuncionario(String condicao)
    {
        try{
            this.listarUm("funcionarios", condicao);
            resultado.next();
                      
            id_funcionario= resultado.getInt(1);
             nome = resultado.getString(2);
             cpf = resultado.getString(3);
             email= resultado.getString(4);
             salariobase= resultado.getFloat(5);
             dataadmissao= resultado.getString(6);
             id_cargo=resultado.getInt(7);
             id_empresa= resultado.getInt(8);
             id_cidade= resultado.getInt(9);
             id_estado= resultado.getInt(10);
        }catch(Exception e){ 
        }
   
    }
 
}
