

package empresas.controll;
import empresas.model.Patrocinio;
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
@Named("patrocinioControll")
@ManagedBean
@SessionScoped
public class PatrocinioControll {
 //atributos da controladora
    private Patrocinio patrocinio;
    private String mensagem="";
    private DataModel dados;
    private Object requestContext;

    /**
     * Creates a new instance of LivroControll
     * @throws java.lang.ClassNotFoundException
     */
    public PatrocinioControll() throws ClassNotFoundException {
        //instancia um objeto da classe livro
        this.patrocinio = new Patrocinio(); //livro maiusculo, o segundo
        //conecta ao banco de dados
        this.patrocinio.conecta();
    }

    public Patrocinio getPatricinio() {
        return patrocinio;
    }

    public void setPatricinio(Patrocinio patricinio) {
        this.patrocinio = patricinio;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public DataModel getDados()throws ClassNotFoundException, SQLException{
        //conecta ao banco de dados
        this.patrocinio.conecta();
        this.dados = new ListDataModel(this.patrocinio.listarPatrocinio());
        return dados;
    }

    public void setDados(DataModel dados) {
        this.dados = dados;
    }
    
    public void controllCadastraPatrocinio()
    {
        this.mensagem = this.patrocinio.cadastraPatrocinio();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.patrocinio = new Patrocinio(); //limpa os dados do javabean
    }
    
    public void controllExcluiPatrocinio() throws ClassNotFoundException
    {
         this.patrocinio = (Patrocinio) dados.getRowData(); //pega os dados do livro selecionado
         //conecta ao banco de dados
         this.patrocinio.conecta();
         this.mensagem = this.patrocinio.excluiPatrocinio();
         //gera uma mensagem com uma caixa de dialogo
         FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso", this.mensagem);
         //mostra a caixa de dialogo com msg
         RequestContext.getCurrentInstance().showMessageInDialog(msg); 
    }
    
    public void controllEditar() throws ClassNotFoundException
    {
        //ele perde conexão quando faz getRowData
       this.patrocinio = (Patrocinio) dados.getRowData(); //ele pega os dados daquela linha e converte os dados  e o formulario recebe os dados 
       //conecta ao banco de dados
       this.patrocinio.conecta();
    }
    
    public void controllAlteraPatrocinio()
    {
        this.mensagem = this.patrocinio.alteraPatrocinio();
        //dispara uma mensagem para a aplicação
        FacesContext msg = FacesContext.getCurrentInstance();
        //mostrar essa mensagem no componente growl
        msg.addMessage(null, new FacesMessage("Aviso", this.mensagem));
        this.patrocinio = new   Patrocinio(); //limpa os dados do javabean
    }
}
