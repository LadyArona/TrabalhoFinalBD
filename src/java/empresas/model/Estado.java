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
public class Estado extends BancoDados implements Serializable{ //maiusculo
    //define os atributos da classe
    private int id_estado;
    private String nome;

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String cadastraEstado()
    {
        String valores = "'"+nome+"'";
        return this.inserirDados("estados",valores);        
    }
    
    public List<Estado> listarEstados() throws SQLException
    {
       this.listarDados("estados");
       List<Estado> dados = new ArrayList<Estado>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             Estado l = new Estado();
             l.setId_estado(resultado.getInt(1));
             l.setNome(resultado.getString(2));
             dados.add(l); //adciona os livros a lista
       }
       return dados;
    }
    public String alteraEstado()
    {
        String valores ="nome='"+nome+"'";
        String condicao="id_estado="+id_estado;
        return this.alterarDados("estados",valores, condicao);
    }
    
    public String excluiEstado()
    {
        String condicao="id_estado="+id_estado;
        return this.excluirDados("estados", condicao);
    }
    
    public void listarUmEStado(String condicao)
    {
        try{
            this.listarUm("estados", condicao);
            resultado.next();
            id_estado = resultado.getInt(1);
            nome = resultado.getString(2);
        }catch(Exception e){ 
        }
   
    }
}
