/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas.controll;

import empresas.model.Funcionarios;
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
@Named("funcionariosControll")
@ManagedBean
@SessionScoped
public class FuncionariosControll implements Serializable {
    //atributos da controladora
    private Funcionarios funcionario;
    private String mensagem="";
    private DataModel dados;
    private Object requestContext;

    /**
     * Creates a new instance of LivroControll
     * @throws java.lang.ClassNotFoundException
     */
    public FuncionariosControll() throws ClassNotFoundException {
        //instancia um objeto da classe livro
        this.funcionario = new Funcionarios(); //livro maiusculo, o segundo
        //conecta ao banco de dados
        this.funcionario.conecta();
    }

    public Funcionarios getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionarios funcionario) {
        this.funcionario = funcionario;
    }

    

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataModel getDados()throws ClassNotFoundException, SQLException{
        //conecta ao banco de dados
        this.funcionario.conecta();
        this.dados = new ListDataModel(this.funcionario.listarFuncionarios());
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
    
    public void controllCadastraFuncionarios()
    {
        this.mensagem = this.funcionario.cadastraFuncionarios();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.funcionario = new Funcionarios(); //limpa os dados do javabean
    }
    
    public void controllExcluiFuncionario() throws ClassNotFoundException
    {
         this.funcionario = (Funcionarios) dados.getRowData(); //pega os dados do livro selecionado
         //conecta ao banco de dados
         this.funcionario.conecta();
         this.mensagem = this.funcionario.excluiFuncionarios();
         //gera uma mensagem com uma caixa de dialogo
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", this.mensagem);
         //mostra a caixa de dialogo com msg
         RequestContext.getCurrentInstance().showMessageInDialog(msg); 
    }
    
    public void controllEditar() throws ClassNotFoundException
    {
        //ele perde conexão quando faz getRowData
       this.funcionario = (Funcionarios) dados.getRowData(); //ele pega os dados daquela linha e converte os dados  e o formulario recebe os dados 
       //conecta ao banco de dados
       this.funcionario.conecta();
    }
    
    public void controllAlteraFuncionarios()
    {
        this.mensagem = this.funcionario.alteraFuncionarios();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.funcionario = new Funcionarios(); //limpa os dados do javabean
    }
}
