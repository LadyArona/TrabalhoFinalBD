/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas.controll;
import empresas.model.CorridaCampeonato;
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
@Named("corridaCampeonatoControll")
@ManagedBean
@SessionScoped
public class CorridaCampeonatoControll implements Serializable{
    //atributos da controladora
    private CorridaCampeonato corridaCampeonato;
    private String mensagem="";
    private DataModel dados;
    private Object requestContext;

    /**
     * Creates a new instance of LivroControll
     * @throws java.lang.ClassNotFoundException
     */
    public CorridaCampeonatoControll() throws ClassNotFoundException {
        //instancia um objeto da classe livro
        this.corridaCampeonato = new CorridaCampeonato(); //livro maiusculo, o segundo
        //conecta ao banco de dados
        this.corridaCampeonato.conecta();
    }

    public CorridaCampeonato getCorridaCampeonato() {
        return corridaCampeonato;
    }

    public void setCorridaCampeonato(CorridaCampeonato corridaCampeonato) {
        this.corridaCampeonato = corridaCampeonato;
    }

    
   
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataModel getDados()throws ClassNotFoundException, SQLException{
        //conecta ao banco de dados
        this.corridaCampeonato.conecta();
        this.dados = new ListDataModel(this.corridaCampeonato.listarCorridaCampeonato());
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
    
    public void controllCadastraCorridaCampeonato()
    {
        this.mensagem = this.corridaCampeonato.cadastraCorridaCampeonato();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.corridaCampeonato = new CorridaCampeonato(); //limpa os dados do javabean
    }
    
    public void controllExcluiCorridaCampeonato() throws ClassNotFoundException
    {
         this.corridaCampeonato = (CorridaCampeonato) dados.getRowData(); //pega os dados do livro selecionado
         //conecta ao banco de dados
         this.corridaCampeonato.conecta();
         this.mensagem = this.corridaCampeonato.excluiCorridaCampeonato();
         //gera uma mensagem com uma caixa de dialogo
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", this.mensagem);
         //mostra a caixa de dialogo com msg
         RequestContext.getCurrentInstance().showMessageInDialog(msg); 
    }
    
    public void controllEditar() throws ClassNotFoundException
    {
        //ele perde conexão quando faz getRowData
       this.corridaCampeonato = (CorridaCampeonato) dados.getRowData(); //ele pega os dados daquela linha e converte os dados  e o formulario recebe os dados 
       //conecta ao banco de dados
       this.corridaCampeonato.conecta();
    }
    
    public void controllAlteraCorridaCampeonato()
    {
        this.mensagem = this.corridaCampeonato.alteraCorridaCampeonato();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.corridaCampeonato = new CorridaCampeonato(); //limpa os dados do javabean
    }
}



