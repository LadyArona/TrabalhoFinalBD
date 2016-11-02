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
public class Cargo extends BancoDados implements Serializable{ //maiusculo
    //define os atributos da classe
    private int id_cargo;
    private String nome;

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String cadastraCargo()
    {
        String valores = "'"+nome+"'";
        return this.inserirDados("cargos",valores);        
    }
    
    public List<Cargo> listarCargo() throws SQLException
    {
       this.listarDados("cargos");
       List<Cargo> dados = new ArrayList<Cargo>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             Cargo l = new Cargo();
             l.setId_cargo(resultado.getInt(1));
             l.setNome(resultado.getString(2));
             dados.add(l); //adciona os livros a lista
       }
       return dados;
    }
    public String alteraCargo()
    {
        String valores ="nome='"+nome+"'";
        String condicao="id_cargo="+id_cargo;
        return this.alterarDados("cargos",valores, condicao);
    }
    
    public String excluiCargo()
    {
        String condicao="id_cargo="+id_cargo;
        return this.excluirDados("cargos", condicao);
    }
    
    public void listarUmCargo(String condicao)
    {
        try{
            this.listarUm("cargos", condicao);
            resultado.next();
            id_cargo = resultado.getInt(1);
            nome = resultado.getString(2);
        }catch(Exception e){ 
        }
   
    }
}

