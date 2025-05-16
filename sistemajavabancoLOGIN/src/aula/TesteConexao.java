package aula;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Senac
 */
public class TesteConexao {

    // atalho vscode - psvm + tab
    // atalho sout + tab    
    public static void main(String[] args) throws IOException {
        System.out.println("ola");
        ArrayList<Usuario> listaUsuarios = getAllUsuarios("select * from public.usuario");
    }

    // lista usuarios
    public static ArrayList<Usuario> getAllUsuarios(String sql) throws IOException {
        sql = "select * from usuario order by 1";

        Usuario usuario = new Usuario();
        ArrayList<Usuario> listaUsuarios = Conexao.executaQuery(sql, usuario);
        for (Usuario auxUsuario : listaUsuarios) {
            System.out.println("Codigo:" + auxUsuario.getCodigo());
            System.out.println("Nome:" + auxUsuario.getNome());
            System.out.println("Login:" + auxUsuario.getLogin());
            System.out.println("E-mail:" + auxUsuario.getEmail());
                                
            //para p´rocurar depois do PONTO, digitar CTRL + ESPAÇO 
            // DUPLICAR LINHA=> CTRL + SHIFT + SETA PRA BAIXO
            // ALT + SHIFT SETA PRA BAIXO, MOVE A LINHA
            System.out.println("-----------------------------");
            
//                auxUsuario.getCodigo(),
//                auxUsuario.getNome(),
//                auxUsuario.getEmail(),
//                auxUsuario.getLogin()
        }

        return listaUsuarios;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Usuario> getAll1515151(String sql) {
        ArrayList dadosArrayList = new ArrayList();

        // https://neon.tech/
        String database = "neondb";
        String host = "";
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://" + host + "/" + database;
        String usuario = "neondb_owner";
        String senha = "";

        Connection conexao;
        Statement statement;
        ResultSet resultset;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            resultset = statement.executeQuery(sql);

            while (resultset.next()) {
                int codigo = resultset.getInt("codigo");
                String nome = resultset.getString("nome");
                String endereco = resultset.getString("endereco");
                String cpf = resultset.getString("cpf");

//                Pessoa pessoa = new Pessoa();
//                pessoa.setCodigo(codigo);
//                pessoa.setNome(nome);
//                pessoa.setEndereco(endereco);
//                pessoa.setCpf(cpf);
                // dadosArrayList.add(pessoa);
            }
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver não localizado: " + Driver);
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Deu erro na conexão com a fonte de dados: " + Fonte);
        }

        return dadosArrayList;
    }
}
