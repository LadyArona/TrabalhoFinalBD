/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresas.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BancoDados {
    protected Connection conexao;
    protected Statement s;//executa executequery e update
    protected ResultSet resultado; //percorre os registros e acessa os campos 
    
    public String conecta() throws ClassNotFoundException
    {
        try{
          //define o driver a ser usaado
          Class.forName("com.mysql.jdbc.Driver");//banco de dados mysql, se fosse usar outro ai definiria aqui
          //conecta com O BD
          conexao = DriverManager.getConnection("jdbc:mysql://localhost/carros?user=root&password=");
          //cria o objeto do tipo statement, este objeto para manutenção das tabelas
          s = conexao.createStatement();
          //retorna o resultado
          return "Conectado";
        }catch(SQLException erro){
            return "Erro na conexão. " + erro.getMessage();
        }
    }
    public String inserirDados(String tabela,String valores)// passando a tabela e os valores a serem inseridos tanto na tabela quanto os valores nela
    {
        try{
            s.executeUpdate("insert into " +tabela+" values (NULL,"+valores+")");//NULL por que eu quero inserir autoincremento de chave primaria
            return "Cadastrado com sucesso.";
        }catch(SQLException erro){
            return "Erro no cadastro. " + erro.getMessage();
        }
    }
    
    public String alterarDados(String tabela,String valores, String condicao)
    {
        try{
            s.executeUpdate("update "+tabela+" set "+valores+" where "+condicao);
            return "Alterado com sucesso.";
        }catch(SQLException erro){
            return "Erro na alteração. " + erro.getMessage();
        }
    }
    
    public String excluirDados(String tabela, String condicao)
    {
        try{
            s.executeUpdate("delete from "+tabela+" where "+condicao); 
            return "Excluido com sucesso";
        }catch(SQLException erro){
            return "Erro na exclusão. " + erro.getMessage();
        }
    }
    
    public void listarDados(String tabela)
    {
        try{
            resultado = s.executeQuery("select * from "+tabela);
        }catch(SQLException erro)
            {
                System.out.println("Erro na conexão. " + erro.getMessage());//aparece na excução do editor
            }
    }
    
    public void listarUm(String tabela, String condicao)// vou precisar buscar uma alteração 
    {
        try{
            resultado = s.executeQuery("select * from "+tabela+" where "+condicao);
        }catch(SQLException erro)
            {
                System.out.println("Erro na conexão. " + erro.getMessage());//aparece na excução do editor
            }
    }
}


