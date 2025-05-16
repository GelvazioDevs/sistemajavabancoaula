package aula;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author Gelvazio Camargo
 */
public class ConexaoProfessor {
    static Connection conexao;

    public static Connection getConexao() {
        return conexao;
    }

    public static void setConexao(Connection conexao) {
        Conexao.conexao = conexao;
    }

    public static void criaConexaoBanco() throws IOException{
        String driver  = "org.postgresql.Driver";        
        String database= "neondb";
        String usuario = "neondb_owner";                
        String host    = "ep-bold-resonance-acilywty-pooler.sa-east-1.aws.neon.tech";
        String senha   = "npg_erCNfpA6L3Du";        
        String url     = "jdbc:postgresql://" + host + "/" + database;

        try {
            Class.forName(driver);
            
            setConexao(DriverManager.getConnection(url, usuario, senha));

            System.out.println("Conectou com o PostgreSQL!");
        } catch (ClassNotFoundException Driver) {
            String message = "Driver não localizado: " + Driver;
//            try {
//                MensagemSlack.slack("ERRO AO EXECUTAR SQL", message);
//            } catch (IOException ex) {
//                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
//            }
            JOptionPane.showMessageDialog(null, message);
        } catch (SQLException Fonte) {
            String message = "Deu erro na conexão com a fonte de dados: " + Fonte;
                MensagemSlack.slack("ERRO AO EXECUTAR SQL", message, "netbeans");
//            try {
//            } catch (IOException ex) {
//                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
//            }
            JOptionPane.showMessageDialog(null, "Deu erro na conexão com a fonte de dados: " + Fonte);
        }
    }
    
    public static int getProximoCodigo(String tabela, Object objeto) throws IOException{
        int codigo = 0;
        criaConexaoBanco();
        
        String sql = "";
        if(tabela.equals("usuario")){
            sql = "select nextval('usuario_usucodigo_seq1') as codigo";
        }
        
        Statement statement;
        ResultSet resultset;
        try {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);

            resultset = statement.executeQuery(sql);
            if (resultset.next()) {
                if(objeto instanceof Usuario){
                    codigo = resultset.getInt("codigo");
                } else {
                    throw new SQLException("OBJETO NAO ENCONTRADO!\nObjeto:" + objeto.getClass().toString(), sql);
                }
            }
        } catch (SQLException erro) {
            String message = "ERRO AO EXECUTAR SQL: \n" + erro.getMessage();
            MensagemSlack.slack("ERRO AO EXECUTAR SQL", message, "netbeans");
            JOptionPane.showMessageDialog(null, message, "ERRO AO EXECUTAR SQL", JOptionPane.ERROR_MESSAGE);            
        }

        return codigo;
    }
    
    public static ArrayList executaQuery(String sql, Object objeto) throws IOException {
        criaConexaoBanco();
        
        ArrayList<Object> lista = new ArrayList();
        
        Statement statement;
        ResultSet resultset;
        try {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);

            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                if(objeto instanceof Usuario){
                    Usuario usuario = new Usuario();
                    
                    usuario.setCodigo(resultset.getInt("usucodigo"));
                    usuario.setNome(resultset.getString("usunome"));
                    usuario.setLogin(resultset.getString("usulogin"));
                    usuario.setSenha(resultset.getString("ususenha"));
                    usuario.setEmail(resultset.getString("usuemail"));
                    
                    lista.add(usuario);
                } else {
                    throw new SQLException("OBJETO NAO ENCONTRADO!\nObjeto:" + objeto.getClass().toString(), sql);
                }
            }
        } catch (SQLException erro) {
            String message = erro.getMessage().toString();
            System.out.println("Erro ao executar sql!");
            System.out.println(message);
            
            MensagemSlack.slack("ERRO AO EXECUTAR SQL", 
                    "erro ao executar sql!" + sql, "netbeans");
            
            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR SQL151: \n" + erro.getMessage(), "ERRO AO EXECUTAR SQL", JOptionPane.ERROR_MESSAGE);                        
        }

        return lista;
    }
    
    public static boolean executeUpdate(String sql) throws IOException{
        criaConexaoBanco();
        
        Statement statement;
        try {
            statement = conexao.createStatement();
            statement.executeUpdate(sql);            
        } catch (SQLException erro) {
            String message = "ERRO AO EXECUTAR SQL: \n" + erro.getMessage();
            MensagemSlack.slack("ERRO AO EXECUTAR SQL", message, "netbeans");
            JOptionPane.showMessageDialog(null, "ERRO AO EXECUTAR SQL: \n" + erro.getMessage(), "ERRO AO EXECUTAR SQL", JOptionPane.ERROR_MESSAGE);                        
            return false;
        }
        return true;
    }    
}
