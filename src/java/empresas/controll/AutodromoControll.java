/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas.controll;

import empresas.model.Autodromo;
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
@Named("autodromoControll")
@ManagedBean
@SessionScoped
public class AutodromoControll implements Serializable{
    //atributos da controladora
    private Autodromo autodromo;
    private String mensagem="";
    private DataModel dados;
    private Object requestContext;

    /**
     * Creates a new instance of LivroControll
     * @throws java.lang.ClassNotFoundException
     */
    public AutodromoControll() throws ClassNotFoundException {
        //instancia um objeto da classe livro
        this.autodromo = new Autodromo(); //livro maiusculo, o segundo
        //conecta ao banco de dados
        this.autodromo.conecta();
    }

    public Autodromo getAutodromo() {
        return autodromo;
    }

    public void setAutodromo(Autodromo autodromo) {
        this.autodromo = autodromo;
    }

    

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataModel getDados()throws ClassNotFoundException, SQLException{
        //conecta ao banco de dados
        this.autodromo.conecta();
        this.dados = new ListDataModel(this.autodromo.listarAutodromos());
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
    
    public void controllCadastraAutodromos()
    {
        this.mensagem = this.autodromo.cadastraAutodromos();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.autodromo = new Autodromo(); //limpa os dados do javabean
    }
    
    public void controllExcluiAutodromos() throws ClassNotFoundException
    {
         this.autodromo= (Autodromo) dados.getRowData(); //pega os dados do livro selecionado
         //conecta ao banco de dados
         this.autodromo.conecta();
         this.mensagem = this.autodromo.excluiAutodromos();
         //gera uma mensagem com uma caixa de dialogo
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", this.mensagem);
         //mostra a caixa de dialogo com msg
         RequestContext.getCurrentInstance().showMessageInDialog(msg); 
    }
    
    public void controllEditar() throws ClassNotFoundException
    {
        //ele perde conexão quando faz getRowData
       this.autodromo = (Autodromo) dados.getRowData(); //ele pega os dados daquela linha e converte os dados  e o formulario recebe os dados 
       //conecta ao banco de dados
       this.autodromo.conecta();
    }
    
    public void controllAlteraAutodromos()
    {
        this.mensagem = this.autodromo.alteraAutodromos();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.autodromo = new Autodromo(); //limpa os dados do javabean
    }
}
