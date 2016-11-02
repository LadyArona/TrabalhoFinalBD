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
import empresas.model.Cidade; //maiusculo
import org.primefaces.context.RequestContext;


@Named("cidadeControll")
@ManagedBean
@SessionScoped
public class CidadeControll implements Serializable {
    //atributos da controladora
    private Cidade cidade;
    private String mensagem="";
    private DataModel dados;
    private Object requestContext;

    /**
     * Creates a new instance of LivroControll
     * @throws java.lang.ClassNotFoundException
     */
    public CidadeControll() throws ClassNotFoundException {
        //instancia um objeto da classe livro
        this.cidade = new Cidade(); //livro maiusculo, o segundo
        //conecta ao banco de dados
        this.cidade.conecta();
    }

    public Cidade getCidade() { //Livro maiusculo
        return cidade;
    }

    public void setLivro(Cidade cidade) { //Livro maiusculo
        this.cidade = cidade;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataModel getDados()throws ClassNotFoundException, SQLException{
        //conecta ao banco de dados
        this.cidade.conecta();
        this.dados = new ListDataModel(this.cidade.listarCidade());
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
    
    public void controllCadastraCidade()
    {
        this.mensagem = this.cidade.cadastraCidade();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.cidade = new Cidade(); //limpa os dados do javabean
    }
    
    public void controllExcluiCidade() throws ClassNotFoundException
    {
         this.cidade = (Cidade) dados.getRowData(); //pega os dados do livro selecionado
         //conecta ao banco de dados
         this.cidade.conecta();
         this.mensagem = this.cidade.excluiCidade();
         //gera uma mensagem com uma caixa de dialogo
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", this.mensagem);
         //mostra a caixa de dialogo com msg
         RequestContext.getCurrentInstance().showMessageInDialog(msg); 
    }
    
    public void controllEditar() throws ClassNotFoundException
    {
        //ele perde conexão quando faz getRowData
       this.cidade = (Cidade) dados.getRowData(); //ele pega os dados daquela linha e converte os dados  e o formulario recebe os dados 
       //conecta ao banco de dados
       this.cidade.conecta();
    }
    
    public void controllAlteraCidade()
    {
        this.mensagem = this.cidade.alteraCidade();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.cidade = new Cidade(); //limpa os dados do javabean
    }
}