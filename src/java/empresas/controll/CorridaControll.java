/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas.controll;

import empresas.model.Corrida;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
/**
 *
 * @author dielm
 */
@Named("corridaControll")
@ManagedBean
@SessionScoped
public class CorridaControll implements Serializable  {
    //atributos da controladora
    private Corrida corrida;
    private String mensagem="";
    private DataModel dados;
    private Object requestContext;

    /**
     * Creates a new instance of LivroControll
     * @throws java.lang.ClassNotFoundException
     */
    public CorridaControll() throws ClassNotFoundException {
        //instancia um objeto da classe livro
        this.corrida= new Corrida(); //livro maiusculo, o segundo
        //conecta ao banco de dados
        this.corrida.conecta();
    }

    public Corrida getCorrida() {
        return corrida;
    }

    public void setCorrida(Corrida corrida) {
        this.corrida = corrida;
    }

    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataModel getDados()throws ClassNotFoundException, SQLException{
        //conecta ao banco de dados
        this.corrida.conecta();
        this.dados = new ListDataModel(this.corrida.listarCorridas());
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
    
    public void controllCadastraCorridas()
    {
        this.mensagem = this.corrida.cadastraCorridas();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.corrida = new Corrida(); //limpa os dados do javabean
    }
    
    public void controllExcluiCorridas() throws ClassNotFoundException
    {
         this.corrida= (Corrida) dados.getRowData(); //pega os dados do livro selecionado
         //conecta ao banco de dados
         this.corrida.conecta();
         this.mensagem = this.corrida.excluiCorridas();
         //gera uma mensagem com uma caixa de dialogo
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", this.mensagem);
         //mostra a caixa de dialogo com msg
         RequestContext.getCurrentInstance().showMessageInDialog(msg); 
    }
    
    public void controllEditar() throws ClassNotFoundException
    {
        //ele perde conexão quando faz getRowData
       this.corrida = (Corrida) dados.getRowData(); //ele pega os dados daquela linha e converte os dados  e o formulario recebe os dados 
       //conecta ao banco de dados
       this.corrida.conecta();
    }
    
    public void controllAlteraCorridas()
    {
        this.mensagem = this.corrida.alteraCorridas();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.corrida= new Corrida(); //limpa os dados do javabean
    }
}
