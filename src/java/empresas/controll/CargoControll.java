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
import empresas.model.Cargo; //maiusculo
import org.primefaces.context.RequestContext;


@Named("cargoControll")
@ManagedBean
@SessionScoped
public class CargoControll implements Serializable {
    //atributos da controladora
    private Cargo cargo;
    private String mensagem="";
    private DataModel dados;
    private Object requestContext;

    /**
     * Creates a new instance of LivroControll
     * @throws java.lang.ClassNotFoundException
     */
    public CargoControll() throws ClassNotFoundException {
        //instancia um objeto da classe livro
        this.cargo = new Cargo(); //livro maiusculo, o segundo
        //conecta ao banco de dados
        this.cargo.conecta();
    }

    public Cargo getCargo() { //Livro maiusculo
        return cargo;
    }

    public void setLivro(Cargo cargo) { //Livro maiusculo
        this.cargo = cargo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataModel getDados()throws ClassNotFoundException, SQLException{
        //conecta ao banco de dados
        this.cargo.conecta();
        this.dados = new ListDataModel(this.cargo.listarCargo());
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
    
    public void controllCadastraCargo()
    {
        this.mensagem = this.cargo.cadastraCargo();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.cargo = new Cargo(); //limpa os dados do javabean
    }
    
    public void controllExcluiCargo() throws ClassNotFoundException
    {
         this.cargo = (Cargo) dados.getRowData(); //pega os dados do livro selecionado
         //conecta ao banco de dados
         this.cargo.conecta();
         this.mensagem = this.cargo.excluiCargo();
         //gera uma mensagem com uma caixa de dialogo
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", this.mensagem);
         //mostra a caixa de dialogo com msg
         RequestContext.getCurrentInstance().showMessageInDialog(msg); 
    }
    
    public void controllEditar() throws ClassNotFoundException
    {
        //ele perde conexão quando faz getRowData
       this.cargo = (Cargo) dados.getRowData(); //ele pega os dados daquela linha e converte os dados  e o formulario recebe os dados 
       //conecta ao banco de dados
       this.cargo.conecta();
    }
    
    public void controllAlteraCargo()
    {
        this.mensagem = this.cargo.alteraCargo();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.cargo = new Cargo(); //limpa os dados do javabean
    }
}