/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas.controll;

import empresas.model.Funcionarios;
import empresas.model.Piloto;
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

@Named("pilotoControll")
@ManagedBean
@SessionScoped
public class PilotoControll implements Serializable{
    //atributos da controladora
    private Piloto piloto;
    private String mensagem="";
    private DataModel dados;
    private Object requestContext;

    /**
     * Creates a new instance of LivroControll
     * @throws java.lang.ClassNotFoundException
     */
    public PilotoControll() throws ClassNotFoundException {
        //instancia um objeto da classe livro
        this.piloto = new Piloto(); //livro maiusculo, o segundo
        //conecta ao banco de dados
        this.piloto.conecta();
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataModel getDados()throws ClassNotFoundException, SQLException{
        //conecta ao banco de dados
        this.piloto.conecta();
        this.dados = new ListDataModel(this.piloto.listarPilotos());
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
    
    public void controllCadastraPilotos()
    {
        this.mensagem = this.piloto.cadastraPilotos();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.piloto = new Piloto(); //limpa os dados do javabean
    }
    
    public void controllExcluiPiloto() throws ClassNotFoundException
    {
         this.piloto = (Piloto) dados.getRowData(); //pega os dados do livro selecionado
         //conecta ao banco de dados
         this.piloto.conecta();
         this.mensagem = this.piloto.excluiPilotos();
         //gera uma mensagem com uma caixa de dialogo
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", this.mensagem);
         //mostra a caixa de dialogo com msg
         RequestContext.getCurrentInstance().showMessageInDialog(msg); 
    }
    
    public void controllEditar() throws ClassNotFoundException
    {
        //ele perde conexão quando faz getRowData
       this.piloto = (Piloto) dados.getRowData(); //ele pega os dados daquela linha e converte os dados  e o formulario recebe os dados 
       //conecta ao banco de dados
       this.piloto.conecta();
    }
    
    public void controllAlteraPilotos()
    {
        this.mensagem = this.piloto.alteraPilotos();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.piloto = new Piloto(); //limpa os dados do javabean
    }
}


