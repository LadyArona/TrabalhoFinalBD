/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas.controll;
import empresas.model.PilotoCorrida;
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
@Named("pilotoCorridaControll")
@ManagedBean
@SessionScoped
public class PilotoCorridaControll implements Serializable{
    //atributos da controladora
    private PilotoCorrida pilotoCorrida;
    private String mensagem="";
    private DataModel dados;
    private Object requestContext;

    /**
     * Creates a new instance of LivroControll
     * @throws java.lang.ClassNotFoundException
     */
    public PilotoCorridaControll() throws ClassNotFoundException {
        //instancia um objeto da classe livro
        this.pilotoCorrida = new PilotoCorrida(); //livro maiusculo, o segundo
        //conecta ao banco de dados
        this.pilotoCorrida.conecta();
    }

    public PilotoCorrida getPilotoCorrida() {
        return pilotoCorrida;
    }

    public void setPilotoCorrida(PilotoCorrida pilotoCorrida) {
        this.pilotoCorrida = pilotoCorrida;
    }

     public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataModel getDados()throws ClassNotFoundException, SQLException{
        //conecta ao banco de dados
        this.pilotoCorrida.conecta();
        this.dados = new ListDataModel(this.pilotoCorrida.listarPilotoCorrida());
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
    
    public void controllCadastraPilotoCorrida()
    {
        this.mensagem = this.pilotoCorrida.cadastraPilotoCorrida();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.pilotoCorrida = new PilotoCorrida(); //limpa os dados do javabean
    }
    
    public void controllExcluiPilotoCorrida() throws ClassNotFoundException
    {
         this.pilotoCorrida = (PilotoCorrida) dados.getRowData(); //pega os dados do livro selecionado
         //conecta ao banco de dados
         this.pilotoCorrida.conecta();
         this.mensagem = this.pilotoCorrida.excluiPilotoCorrida();
         //gera uma mensagem com uma caixa de dialogo
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", this.mensagem);
         //mostra a caixa de dialogo com msg
         RequestContext.getCurrentInstance().showMessageInDialog(msg); 
    }
    
    public void controllEditar() throws ClassNotFoundException
    {
        //ele perde conexão quando faz getRowData
       this.pilotoCorrida = (PilotoCorrida) dados.getRowData(); //ele pega os dados daquela linha e converte os dados  e o formulario recebe os dados 
       //conecta ao banco de dados
       this.pilotoCorrida.conecta();
    }
    
    public void controllAlteraPilotoCorrida()
    {
        this.mensagem = this.pilotoCorrida.alteraPilotoCorrida();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.pilotoCorrida = new PilotoCorrida(); //limpa os dados do javabean
    }
}

