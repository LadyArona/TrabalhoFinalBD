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
import empresas.model.Empresa; //maiusculo
import org.primefaces.context.RequestContext;

@Named("empresaControll")
@ManagedBean
@SessionScoped
public class EmpresaControll implements Serializable {
    //atributos da controladora
    private Empresa empresa;
    private String mensagem="";
    private DataModel dados;
    private Object requestContext;

    /**
     * Creates a new instance of LivroControll
     * @throws java.lang.ClassNotFoundException
     */
    public EmpresaControll() throws ClassNotFoundException {
        //instancia um objeto da classe livro
        this.empresa = new Empresa(); //livro maiusculo, o segundo
        //conecta ao banco de dados
        this.empresa.conecta();
    }

    public Empresa getEmpresa() { //Livro maiusculo
        return empresa;
    }

    public void setEmpresa(Empresa empresa) { //Livro maiusculo
        this.empresa = empresa;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataModel getDados()throws ClassNotFoundException, SQLException{
        //conecta ao banco de dados
        this.empresa.conecta();
        this.dados = new ListDataModel(this.empresa.listarEmpresa());
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
    
    public void controllCadastraEmpresa()
    {
        this.mensagem = this.empresa.cadastraEmpresa();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.empresa = new Empresa(); //limpa os dados do javabean
    }
    
    public void controllExcluiEmpresa() throws ClassNotFoundException
    {
         this.empresa = (Empresa) dados.getRowData(); //pega os dados do livro selecionado
         //conecta ao banco de dados
         this.empresa.conecta();
         this.mensagem = this.empresa.excluiEmpresa();
         //gera uma mensagem com uma caixa de dialogo
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", this.mensagem);
         //mostra a caixa de dialogo com msg
         RequestContext.getCurrentInstance().showMessageInDialog(msg); 
    }
    
    public void controllEditar() throws ClassNotFoundException
    {
        //ele perde conexão quando faz getRowData
       this.empresa = (Empresa) dados.getRowData(); //ele pega os dados daquela linha e converte os dados  e o formulario recebe os dados 
       //conecta ao banco de dados
       this.empresa.conecta();
    }
    
    public void controllAlteraEmpresa()
    {
        this.mensagem = this.empresa.alteraEmpresa();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.empresa = new Empresa(); //limpa os dados do javabean
    }
}