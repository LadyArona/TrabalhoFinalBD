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
public class VendaIngresso extends BancoDados implements Serializable{
    
    private int ven_codigo;
    private int ven_valor;
    private int ven_quantidade_max;
    private int cor_codigo;

    public int getVen_codigo() {
        return ven_codigo;
    }

    public void setVen_codigo(int ven_codigo) {
        this.ven_codigo = ven_codigo;
    }

    public int getVen_valor() {
        return ven_valor;
    }

    public void setVen_valor(int ven_valor) {
        this.ven_valor = ven_valor;
    }

    public int getVen_quantidade_max() {
        return ven_quantidade_max;
    }

    public void setVen_quantidade_max(int ven_quantidade_max) {
        this.ven_quantidade_max = ven_quantidade_max;
    }

    public int getCor_codigo() {
        return cor_codigo;
    }

    public void setCor_codigo(int cor_codigo) {
        this.cor_codigo = cor_codigo;
    }
    
    
    
  public String cadastraVendaIngresso()
    {

        String valores = ""+ven_valor+","+ven_quantidade_max+","+cor_codigo+"";
        return this.inserirDados("venda_ingresso",valores);        
    }
    
    public List<VendaIngresso> listarVendaIngressos() throws SQLException
    {
       this.listarDados("venda_ingresso");
       List<VendaIngresso> dados = new ArrayList<VendaIngresso>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             VendaIngresso vi = new VendaIngresso();
             vi.setVen_codigo(resultado.getInt(1));
             vi.setVen_valor(resultado.getInt(2));       
             vi.setVen_quantidade_max(resultado.getInt(3));
             vi.setCor_codigo(resultado.getInt(4));
             
             dados.add(vi); //adciona os livros a lista
       }
       return dados;
    }
    public String alteraVendaIngressos()
    {
        //String valores ="nome='"+nome+"'";
     // String valores =  "nome='"+nome+"',cpf='"+cpf+"',email='"+email+"',salariobase="+salariobase+",dataadmissao='"+dataadmissao+"',id_cargo="+id_cargo+",id_empresa="+id_empresa+",id_cidade="+id_cidade+",id_estado="+id_estado+"";
       String valores = "ven_valor="+ven_valor+",ven_quantidade_max="+ven_quantidade_max+"";
       String condicao="ven_codigo="+ven_codigo;
        return this.alterarDados("venda_ingresso",valores, condicao);
    }
    
        
    public String excluiVendaIngressos()
    {
        String condicao="ven_codigo="+ven_codigo;
        return this.excluirDados("venda_ingresso", condicao);
    }
    
    public void listarUmVendaIngresso(String condicao)
    {
        try{
            this.listarUm("venda_ingresso", condicao);
            resultado.next();   
            
            
            ven_codigo= resultado.getInt(1);
            ven_valor = resultado.getInt(2);
            ven_quantidade_max= resultado.getInt(3);
            cor_codigo = resultado.getInt(4);
            
             
        }catch(Exception e){ 
        }
   
    }
 
}
