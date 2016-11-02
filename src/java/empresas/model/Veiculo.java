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
public class Veiculo extends BancoDados implements Serializable{
 private int vei_codigo; 
 private String vei_marca;
 private String vei_modelo;
 private int vei_ano;
 private int vei_velocidade_max; 
 private String vei_placa;
 private int pil_codigo;/*pegar automaticamente pelo autoincremento mas pegar no código*/

    public int getVei_codigo() {
        return vei_codigo;
    }

    public void setVei_codigo(int vei_codigo) {
        this.vei_codigo = vei_codigo;
    }

    public String getVei_marca() {
        return vei_marca;
    }

    public void setVei_marca(String vei_marca) {
        this.vei_marca = vei_marca;
    }

    public String getVei_modelo() {
        return vei_modelo;
    }

    public void setVei_modelo(String vei_modelo) {
        this.vei_modelo = vei_modelo;
    }

    public int getVei_ano() {
        return vei_ano;
    }

    public void setVei_ano(int vei_ano) {
        this.vei_ano = vei_ano;
    }

    public int getVei_velocidade_max() {
        return vei_velocidade_max;
    }

    public void setVei_velocidade_max(int vei_velocidade_max) {
        this.vei_velocidade_max = vei_velocidade_max;
    }

    public String getVei_placa() {
        return vei_placa;
    }

    public void setVei_placa(String vei_placa) {
        this.vei_placa = vei_placa;
    }

    public int getPil_codigo() {
        return pil_codigo;
    }

    public void setPil_codigo(int pil_codigo) {
        this.pil_codigo = pil_codigo;
    }
  
public String cadastraVeiculos()
    {
      
        String valores = "'"+vei_marca+"','"+vei_modelo+"',"+vei_ano+","+vei_velocidade_max+",'"+vei_placa+"',"+pil_codigo+"";
        return this.inserirDados("veiculo",valores);        
    }
    
    public List<Veiculo> listarVeiculos() throws SQLException
    {
       this.listarDados("veiculo");
       List<Veiculo> dados = new ArrayList<Veiculo>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             Veiculo v = new Veiculo();

             v.setVei_codigo(resultado.getInt(1));
             v.setVei_marca(resultado.getString(2));       
             v.setVei_modelo(resultado.getString(3));
             v.setVei_ano(resultado.getInt(4));
             v.setVei_velocidade_max(resultado.getInt(5));
             v.setVei_placa(resultado.getString(6));
             v.setPil_codigo(resultado.getInt(7));
             dados.add(v);
       }
       return dados;
    }
    public String alteraPilotos()
    {
        String valores = "'"+vei_marca+"','"+vei_modelo+"',"+vei_ano+","+vei_velocidade_max+",'"+vei_placa+"',"+pil_codigo+"";
       String condicao="vei_codigo="+vei_codigo;
        return this.alterarDados("veiculo",valores, condicao);
    }
    
        
    public String excluiPilotos()
    {
        String condicao="vei_codigo="+vei_codigo;
        return this.excluirDados("veiculo", condicao);
    }
    
    public void listarUmPiloto(String condicao)
    {
        try{
            this.listarUm("veiculo", condicao);
            resultado.next();   

            vei_codigo = resultado.getInt(1);
            vei_marca = resultado.getString(2);
            vei_modelo = resultado.getString(3);
            vei_ano = resultado.getInt(4);
            vei_velocidade_max = resultado.getInt(5);
            vei_placa = resultado.getString(6);
            pil_codigo= resultado.getInt(7);
             
        }catch(Exception e){ 
        }
   
    }
 
}
