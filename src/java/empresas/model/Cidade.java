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


@ManagedBean
@RequestScoped
public class Cidade extends BancoDados implements Serializable{ //maiusculo
    //define os atributos da classe
    private int id_cidade;
    private String nome;

    public int getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(int id_cidade) {
        this.id_cidade = id_cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String cadastraCidade()
    {
        String valores = "'"+nome+"'";
        return this.inserirDados("cidades",valores);        
    }
    
    public List<Cidade> listarCidade() throws SQLException
    {
       this.listarDados("cidades");
       List<Cidade> dados = new ArrayList<Cidade>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             Cidade l = new Cidade();
             l.setId_cidade(resultado.getInt(1));
             l.setNome(resultado.getString(2));
             dados.add(l); //adciona os livros a lista
       }
       return dados;
    }
    public String alteraCidade()
    {
        String valores ="nome='"+nome+"'";
        String condicao="id_cidade="+id_cidade;
        return this.alterarDados("cidades",valores, condicao);
    }
    
    public String excluiCidade()
    {
        String condicao="id_cidade="+id_cidade;
        return this.excluirDados("cidades", condicao);
    }
    
    public void listarUmCidade(String condicao)
    {
        try{
            this.listarUm("cidades", condicao);
            resultado.next();
            id_cidade = resultado.getInt(1);
            nome = resultado.getString(2);
        }catch(Exception e){ 
        }
   
    }
}

