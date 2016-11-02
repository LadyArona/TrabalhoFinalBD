/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas.controll;

import empresas.model.Campeonato;
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

@Named("campeonatoControll")
@ManagedBean
@SessionScoped
public class CampeonatoControll implements Serializable{
    //atributos da controladora
    private Campeonato campeonato;
    private String mensagem="";
    private DataModel dados;
    private Object requestContext;

    /**
     * Creates a new instance of LivroControll
     * @throws java.lang.ClassNotFoundException
     */
    public CampeonatoControll() throws ClassNotFoundException {
        //instancia um objeto da classe livro
        this.campeonato = new Campeonato(); //livro maiusculo, o segundo
        //conecta ao banco de dados
        this.campeonato.conecta();
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataModel getDados()throws ClassNotFoundException, SQLException{
        //conecta ao banco de dados
        this.campeonato.conecta();
        this.dados = new ListDataModel(this.campeonato.listarCampeonato());
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
    
    public void controllCadastraCampeonato()
    {
        this.mensagem = this.campeonato.cadastraCampeonato();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.campeonato = new Campeonato(); //limpa os dados do javabean
    }
    
    public void controllExcluiCampeonato() throws ClassNotFoundException
    {
         this.campeonato = (Campeonato) dados.getRowData(); //pega os dados do livro selecionado
         //conecta ao banco de dados
         this.campeonato.conecta();
         this.mensagem = this.campeonato.excluiCampeonato();
         //gera uma mensagem com uma caixa de dialogo
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", this.mensagem);
         //mostra a caixa de dialogo com msg
         RequestContext.getCurrentInstance().showMessageInDialog(msg); 
    }
    
    public void controllEditar() throws ClassNotFoundException
    {
        //ele perde conexão quando faz getRowData
       this.campeonato = (Campeonato) dados.getRowData(); //ele pega os dados daquela linha e converte os dados  e o formulario recebe os dados 
       //conecta ao banco de dados
       this.campeonato.conecta();
    }
    
    public void controllAlteraCampeonato()
    {
        this.mensagem = this.campeonato.alteraCampeonato();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.campeonato = new Campeonato(); //limpa os dados do javabean
    }
}


