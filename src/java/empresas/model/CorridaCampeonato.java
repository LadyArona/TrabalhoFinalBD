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
public class CorridaCampeonato extends BancoDados implements Serializable{
    private int cor_cam_codigo;
    private int cor_codigo;
    private int cam_codigo;
    private int aut_codigo;

    public int getCor_cam_codigo() {
        return cor_cam_codigo;
    }

    public void setCor_cam_codigo(int cor_cam_codigo) {
        this.cor_cam_codigo = cor_cam_codigo;
    }

    public int getCor_codigo() {
        return cor_codigo;
    }

    public void setCor_codigo(int cor_codigo) {
        this.cor_codigo = cor_codigo;
    }

    public int getCam_codigo() {
        return cam_codigo;
    }

    public void setCam_codigo(int cam_codigo) {
        this.cam_codigo = cam_codigo;
    }

    public int getAut_codigo() {
        return aut_codigo;
    }

    public void setAut_codigo(int aut_codigo) {
        this.aut_codigo = aut_codigo;
    }
    
    
    public String cadastraCorridaCampeonato()
    {
      
        String valores = ""+cor_codigo+","+cam_codigo+","+aut_codigo+"";
        return this.inserirDados("corrida_campeonato",valores);        
    }
    
    public List<CorridaCampeonato> listarCorridaCampeonato() throws SQLException
    {
       this.listarDados("corrida_campeonato");
       List<CorridaCampeonato> dados = new ArrayList<CorridaCampeonato>();
       while(resultado.next()) //percorre o conjunto de registros
       {
             CorridaCampeonato cc = new CorridaCampeonato();
             cc.setCor_cam_codigo(resultado.getInt(1));
             cc.setCor_codigo(resultado.getInt(2));
             cc.setCam_codigo(resultado.getInt(3));       
             cc.setAut_codigo(resultado.getInt(4));
        
             dados.add(cc);
       }
       return dados;
    }
    public String alteraCorridaCampeonato()
    {
       String valores = ""+cor_codigo+","+cam_codigo+","+aut_codigo+"";
       String condicao="cor_cam_codigo="+cor_cam_codigo;
        return this.alterarDados("corrida_campeonato"+valores+"",valores, condicao);
    }
    
        
    public String excluiCorridaCampeonato()
    {
        String condicao="cor_cam_codigo="+cor_cam_codigo;
        return this.excluirDados("corrida_campeonato", condicao);
    }
    
    public void listarUmVeiculo(String condicao)
    {
        try{
            this.listarUm("corrida_campeonato", condicao);
            resultado.next();   
           cor_cam_codigo= resultado.getInt(1);
           cor_codigo = resultado.getInt(2);
           cam_codigo= resultado.getInt(3);
           aut_codigo= resultado.getInt(4);
     
        }catch(Exception e){ 
        }
   
    }
 
}