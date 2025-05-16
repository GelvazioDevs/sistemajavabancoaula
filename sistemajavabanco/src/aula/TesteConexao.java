package aula;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Senac
 */
public class TesteConexao {

    // atalho vscode - psvm + tab
    // atalho sout + tab    
    public static void main(String[] args) throws IOException {
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
                                
            // para procurar depois do PONTO, digitar CTRL + ESPAÇO 
            // DUPLICAR LINHA=> CTRL + SHIFT + SETA PRA BAIXO
            // ALT + SHIFT SETA PRA BAIXO, MOVE A LINHA
            System.out.println("-----------------------------");            
        }

        return listaUsuarios;
    }
}