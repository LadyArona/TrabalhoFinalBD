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
public class Patrocinador extends BancoDados implements Serializable{ //maiusculo
    //define os atributos da classe
    private int pat_codigo;
    private String pat_nome;
    private String pat_cnpj;

    public int getPat_codigo() {
        return pat_codigo;
    }

    public void setPat_codigo(int pat_codigo) {
        this.pat_codigo = pat_codigo;
    }

    public String getPat_nome() {
        return pat_nome;
    }

    public void setPat_nome(String pat_nome) {
        this.pat_nome = pat_nome;
    }

    public String getPat_cnpj() {
        return pat_cnpj;
    }

    public void setPat_cnpj(String pat_cnpj) {
        this.pat_cnpj = pat_cnpj;
    }

   
    
    public String cadastraPatrocinador()
    {
        String valores = "'"+pat_nome+"','"+pat_cnpj+"'";
        return this.inserirDados("patrocinador",valores);        
    }
    
    public List<Patrocinador> listarPatrocinador() throws SQLException
    {
       this.listarDados("patrocinador");
       List<Patrocinador> dados = new ArrayList<Patrocinador>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             Patrocinador l = new Patrocinador();
             l.setPat_codigo(resultado.getInt(1));
             l.setPat_nome(resultado.getString(2));
             l.setPat_cnpj(resultado.getString(3));
             dados.add(l); //adciona os livros a lista
       }
       return dados;
    }
    public String alteraPatrocinador()
    {
        String valores = "pat_nome='"+pat_nome+"',pat_cnpj='"+pat_cnpj+"'";
        String condicao="pat_codigo="+pat_codigo;
        return this.alterarDados("patrocinador",valores, condicao);
    }
    
    public String excluiPatrocinador()
    {
        String condicao="pat_codigo="+pat_codigo;
        return this.excluirDados("patrocinador", condicao);
    }
    
    public void listarUmPatrocinador(String condicao)
    {
        try{
            this.listarUm("patrocinador", condicao);
            resultado.next();
            pat_codigo = resultado.getInt(1);
            pat_nome = resultado.getString(2);
            pat_cnpj = resultado.getString(3);
        }catch(Exception e){ 
        }
   
    }
}

