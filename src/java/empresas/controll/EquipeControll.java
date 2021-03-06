/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas.controll;

import empresas.model.Equipe;
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
@Named("equipeControll")
@ManagedBean
@SessionScoped
public class EquipeControll implements Serializable {
    //atributos da controladora
    private Equipe equipe;
    private String mensagem="";
    private DataModel dados;
    private Object requestContext;

    /**
     * Creates a new instance of LivroControll
     * @throws java.lang.ClassNotFoundException
     */
    public EquipeControll() throws ClassNotFoundException {
        //instancia um objeto da classe livro
        this.equipe = new Equipe(); //livro maiusculo, o segundo
        //conecta ao banco de dados
        this.equipe.conecta();
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Object getRequestContext() {
        return requestContext;
    }

    public void setRequestContext(Object requestContext) {
        this.requestContext = requestContext;
    }

   

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataModel getDados()throws ClassNotFoundException, SQLException{
        //conecta ao banco de dados
        this.equipe.conecta();
        this.dados = new ListDataModel(this.equipe.listarEquipes());
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
    
    public void controllCadastraEquipes()
    {
        this.mensagem = this.equipe.cadastraEquipes();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.equipe = new Equipe(); //limpa os dados do javabean
    }
    
    public void controllExcluiEquipes() throws ClassNotFoundException
    {
         this.equipe = (Equipe) dados.getRowData(); //pega os dados do livro selecionado
         //conecta ao banco de dados
         this.equipe.conecta();
         this.mensagem = this.equipe.excluiEquipes();
         //gera uma mensagem com uma caixa de dialogo
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", this.mensagem);
         //mostra a caixa de dialogo com msg
         RequestContext.getCurrentInstance().showMessageInDialog(msg); 
    }
    
    public void controllEditar() throws ClassNotFoundException
    {
        //ele perde conexão quando faz getRowData
       this.equipe = (Equipe) dados.getRowData(); //ele pega os dados daquela linha e converte os dados  e o formulario recebe os dados 
       //conecta ao banco de dados
       this.equipe.conecta();
    }
    
    public void controllAlteraEquipes()
    {
        this.mensagem = this.equipe.alteraEquipes();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.equipe = new Equipe(); //limpa os dados do javabean
    }
}
