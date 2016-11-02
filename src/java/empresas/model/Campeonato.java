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
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
/**
 *
 * @author dielm
 */

@ManagedBean
@RequestScoped
public class Campeonato extends BancoDados implements Serializable {
    
 private int cam_codigo; 
 private String cam_descricao;
 private String cam_data_inicial; 
 private String cam_data_final; 

    public int getCam_codigo() {
        return cam_codigo;
    }

    public void setCam_codigo(int cam_codigo) {
        this.cam_codigo = cam_codigo;
    }
 
    public String getCam_descricao() {
        return cam_descricao;
    }

    public void setCam_descricao(String cam_descricao) {
        this.cam_descricao = cam_descricao;
    }

    public String getCam_data_inicial() {
        return cam_data_inicial;
    }

    public void setCam_data_inicial(String cam_data_inicial) {
        this.cam_data_inicial = cam_data_inicial;
    }

    public String getCam_data_final() {
        return cam_data_final;
    }

    public void setCam_data_final(String cam_data_final) {
        this.cam_data_final = cam_data_final;
    }

   public String cadastraCampeonato()
    {
      
        String valores = "'"+cam_descricao+"','"+cam_data_inicial+"','"+cam_data_final+"'";
        return this.inserirDados("campeonato",valores);        
    }
    
    public List<Campeonato> listarCampeonato() throws SQLException
    {
       this.listarDados("campeonato");
       List<Campeonato> dados = new ArrayList<Campeonato>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             Campeonato p = new Campeonato();
             p.setCam_codigo(resultado.getInt(1));
             p.setCam_descricao(resultado.getString(2));
             p.setCam_data_inicial(resultado.getString(3));       
             p.setCam_data_final(resultado.getString(4));
             dados.add(p); //adciona os livros a lista
       }
       return dados;
    }
    public String alteraCampeonato()
    {
        //String valores ="nome='"+nome+"'";
     // String valores =  "nome='"+nome+"',cpf='"+cpf+"',email='"+email+"',salariobase="+salariobase+",dataadmissao='"+dataadmissao+"',id_cargo="+id_cargo+",id_empresa="+id_empresa+",id_cidade="+id_cidade+",id_estado="+id_estado+"";
       String valores = "cam_descricao='"+cam_descricao+"', cam_data_inicial='"+cam_data_inicial+"',cam_data_final='"+cam_data_final+"'"; 
       String condicao="cam_codigo="+cam_codigo;
        return this.alterarDados("campeonato",valores, condicao);
    }    
        
    public String excluiCampeonato()
    {
        String condicao="cam_codigo="+cam_codigo;
        return this.excluirDados("campeonato", condicao);
    }
    
    public void listarUmCampeonato(String condicao)
    {
        try{
            this.listarUm("campeonato", condicao);
            resultado.next();     
            cam_descricao = resultado.getString(1);
            cam_data_inicial = resultado.getString(2);
            cam_data_final = resultado.getString(3);
             
        }catch(Exception e){ 
        }
   
    }
 
}

    

