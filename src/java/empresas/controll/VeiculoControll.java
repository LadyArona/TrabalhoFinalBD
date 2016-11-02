/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas.controll;


import empresas.model.Veiculo;
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
@Named("veiculoControll")
@ManagedBean
@SessionScoped
public class VeiculoControll implements Serializable {
    //atributos da controladora
    private Veiculo veiculo;
    private String mensagem="";
    private DataModel dados;
    private Object requestContext;

    /**
     * Creates a new instance of LivroControll
     * @throws java.lang.ClassNotFoundException
     */
    public VeiculoControll() throws ClassNotFoundException {
        //instancia um objeto da classe livro
        this.veiculo = new Veiculo(); //livro maiusculo, o segundo
        //conecta ao banco de dados
        this.veiculo.conecta();
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataModel getDados()throws ClassNotFoundException, SQLException{
        //conecta ao banco de dados
        this.veiculo.conecta();
        this.dados = new ListDataModel(this.veiculo.listarVeiculos());
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
    
    public void controllCadastraVeiculos()
    {
        this.mensagem = this.veiculo.cadastraVeiculos();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.veiculo = new Veiculo(); //limpa os dados do javabean
    }
    
    public void controllExcluiVeiculos() throws ClassNotFoundException
    {
         this.veiculo = (Veiculo) dados.getRowData(); //pega os dados do livro selecionado
         //conecta ao banco de dados
         this.veiculo.conecta();
         this.mensagem = this.veiculo.excluiVeiculos();
         //gera uma mensagem com uma caixa de dialogo
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", this.mensagem);
         //mostra a caixa de dialogo com msg
         RequestContext.getCurrentInstance().showMessageInDialog(msg); 
    }
    
    public void controllEditar() throws ClassNotFoundException
    {
        //ele perde conexão quando faz getRowData
       this.veiculo = (Veiculo) dados.getRowData(); //ele pega os dados daquela linha e converte os dados  e o formulario recebe os dados 
       //conecta ao banco de dados
       this.veiculo.conecta();
    }
    
    public void controllAlteraVeiculos()
    {
        this.mensagem = this.veiculo.alteraVeiculos();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.veiculo = new Veiculo(); //limpa os dados do javabean
    }
}

