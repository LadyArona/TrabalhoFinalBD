/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

//Empresa não está funcionando direito
@ManagedBean
@RequestScoped
public class Empresa extends BancoDados implements Serializable { //maiusculo
    //define os atributos da classe

    private int id_empresa;
    private String nome;
    private String razao;
    private String cnpj;
    private String telefone;
    private int id_cidade;
    private int id_estado;

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(int id_cidade) {
        this.id_cidade = id_cidade;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String cadastraEmpresa() {
        //String valores = "'"+nome+"','"+razao+"','"+cnpj+"','"+telefone+"',"+","+id_cidade+","+id_estado+"";
        String valores = "'" + nome + "','" + razao + "','" + cnpj + "','" + telefone + "'," + id_cidade + "," + id_estado + "";
        return this.inserirDados("empresas", valores);
    }

    public List<Empresa> listarEmpresa() throws SQLException {
        this.listarDados("empresas");
        List<Empresa> dados = new ArrayList<Empresa>();
        while (resultado.next()) //percorre o conjunto de registros
        {
            Empresa l = new Empresa();
            l.setId_empresa(resultado.getInt(1));
            l.setNome(resultado.getString(2));
            l.setRazao(resultado.getString(3));
            l.setCnpj(resultado.getString(4));
            l.setTelefone(resultado.getString(5));
            l.setId_cidade(resultado.getInt(6));
            l.setId_estado(resultado.getInt(7));
            dados.add(l); //adciona os livros a lista
        }
        return dados;
    }

    public String alteraEmpresa() {
        String valores = "nome='" + nome + "',razao='" + razao + "',cnpj='" + cnpj + "',telefone='" + telefone + "',id_cidade=" + id_cidade + ",id_estado=" + id_estado + "";
        String condicao = "id_empresa=" + id_empresa;
        return this.alterarDados("empresas", valores, condicao);
    }

    public String excluiEmpresa() {
        String condicao = "id_empresa=" + id_empresa;
        return this.excluirDados("empresas", condicao);
    }

    public void listarUmEmpresa(String condicao) {
        try {
            this.listarUm("empresas", condicao);
            resultado.next();
            id_empresa = resultado.getInt(1);
            nome = resultado.getString(2);
            razao = resultado.getString(3);
            cnpj = resultado.getString(4);
            telefone = resultado.getString(5);
            id_cidade = resultado.getInt(6);
            id_estado = resultado.getInt(7);

        } catch (Exception e) {
        }
    }
}
