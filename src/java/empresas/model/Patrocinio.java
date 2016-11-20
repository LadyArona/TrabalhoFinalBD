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
public class Patrocinio extends BancoDados implements Serializable {
    private int eqp_codigo;
    private int pat_codigo;
    private int peq_valor_investimento;
    private String pat_data_inicial;
    private String pat_data_final;

    public int getEqp_codigo() {
        return eqp_codigo;
    }

    public void setEqp_codigo(int eqp_codigo) {
        this.eqp_codigo = eqp_codigo;
    }

    public int getPat_codigo() {
        return pat_codigo;
    }

    public void setPat_codigo(int pat_codigo) {
        this.pat_codigo = pat_codigo;
    }

    public int getPeq_valor_investimento() {
        return peq_valor_investimento;
    }

    public void setPeq_valor_investimento(int peq_valor_investimento) {
        this.peq_valor_investimento = peq_valor_investimento;
    }

    public String getPat_data_inicial() {
        return pat_data_inicial;
    }

    public void setPat_data_inicial(String pat_data_inicial) {
        this.pat_data_inicial = pat_data_inicial;
    }

    public String getPat_data_final() {
        return pat_data_final;
    }

    public void setPat_data_final(String pat_data_final) {
        this.pat_data_final = pat_data_final;
    }
    
    public String cadastraPatrocinio()
    {
      
        String valores = ""+eqp_codigo+","+pat_codigo+","+peq_valor_investimento+",'"+pat_data_inicial+"','"+pat_data_final+"'";
        return this.inserirDados("patrocinio",valores);        
    }
    
    public List<Patrocinio> listarPatrocinio() throws SQLException
    {
       this.listarDados("patrocinio");
       List<Patrocinio> dados = new ArrayList<Patrocinio>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             Patrocinio p = new Patrocinio();

             p.setEqp_codigo(resultado.getInt(1));
             p.setPat_codigo(resultado.getInt(2));       
             p.setPeq_valor_investimento(resultado.getInt(3));
             p.setPat_data_inicial(resultado.getString(4));
             p.setPat_data_final(resultado.getString(5));
             
             dados.add(p);
       }
       return dados;
    }
    public String alteraPatrocinio()
    {
         String valores = ""+eqp_codigo+","+pat_codigo+","+peq_valor_investimento+",'"+pat_data_inicial+"','"+pat_data_final+"'";
       String condicao="eqp_codigo="+eqp_codigo;
        return this.alterarDados("patrocinio",valores, condicao);
    }
    
        
    public String excluiPatrocinio()
    {
        String condicao="eqp_codigo="+eqp_codigo;
        return this.excluirDados("patrocinio", condicao);
    }
    
    public void listarUmPatrocinio(String condicao)
    {
        try{
            this.listarUm("patrocinio", condicao);
            resultado.next();  
            

            eqp_codigo = resultado.getInt(1);
            pat_codigo = resultado.getInt(2);
            peq_valor_investimento = resultado.getInt(3);
            pat_data_inicial = resultado.getString(4);
            pat_data_final = resultado.getString(5);
            
             
        }catch(Exception e){ 
        }
   
    }
 
}
