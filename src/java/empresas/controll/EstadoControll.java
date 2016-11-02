/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas.controll;

import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

//importa a model
import empresas.model.Estado; //maiusculo
import org.primefaces.context.RequestContext;


@Named("estadoControll")
@ManagedBean
@SessionScoped
public class EstadoControll implements Serializable {
    //atributos da controladora
    private Estado estado;
    private String mensagem="";
    private DataModel dados;
    private Object requestContext;

    /**
     * Creates a new instance of LivroControll
     * @throws java.lang.ClassNotFoundException
     */
    public EstadoControll() throws ClassNotFoundException {
        //instancia um objeto da classe livro
        this.estado = new Estado(); //livro maiusculo, o segundo
        //conecta ao banco de dados
        this.estado.conecta();
    }

    public Estado getEstado() { //Livro maiusculo
        return estado;
    }

    public void setLivro(Estado estado) { //Livro maiusculo
        this.estado = estado;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataModel getDados()throws ClassNotFoundException, SQLException{
        //conecta ao banco de dados
        this.estado.conecta();
        this.dados = new ListDataModel(this.estado.listarEstados());
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
    
    public void controllCadastraEstado()
    {
        this.mensagem = this.estado.cadastraEstado();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.estado = new Estado(); //limpa os dados do javabean
    }
    
    public void controllExcluiEstado() throws ClassNotFoundException
    {
         this.estado = (Estado) dados.getRowData(); //pega os dados do livro selecionado
         //conecta ao banco de dados
         this.estado.conecta();
         this.mensagem = this.estado.excluiEstado();
         //gera uma mensagem com uma caixa de dialogo
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", this.mensagem);
         //mostra a caixa de dialogo com msg
         RequestContext.getCurrentInstance().showMessageInDialog(msg); 
    }
    
    public void controllEditar() throws ClassNotFoundException
    {
        //ele perde conexão quando faz getRowData
       this.estado = (Estado) dados.getRowData(); //ele pega os dados daquela linha e converte os dados  e o formulario recebe os dados 
       //conecta ao banco de dados
       this.estado.conecta();
    }
    
    public void controllAlteraEstado()
    {
        this.mensagem = this.estado.alteraEstado();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.estado = new Estado(); //limpa os dados do javabean
    }
}