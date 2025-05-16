package aula;

import java.io.IOException;
import java.util.ArrayList;
import principal.BCrypt;


/**
 *
 * @author Gelvazio Camargo
 */
public class ControllerUsuario {
    
    public boolean validaLoginSenha(String login, String senha) throws IOException{
        String sql = " select usucodigo,  "
                    + "       usunome,    "
                    + "       usulogin,   "
                    + "       ususenha,   "
                    + "       usuemail    "
                    + "  from usuario     "
                    + " where usulogin = '" + login + "'";
        
        ArrayList usuarios = Conexao.executaQuery(sql, new Usuario());
        
        // Se houver usuario, valida a senha
        if(!usuarios.isEmpty()){
            // Valida a senha
            Usuario usuarioBanco = (Usuario) usuarios.getFirst();
            String senhaBancoDados = usuarioBanco.getSenha();  
            
            // NOVO CODIGO DE VALIDAR LOGIN E SENHA
            if(senha.equalsIgnoreCase(senhaBancoDados)){
                return true;
            }
            
            // EXPLICAR BCRYPT PROXIMA AULA
//            if(BCrypt.checkpw(senha, senhaBancoDados)){
//               return true;
//            }
        }
        return false;
    }    
    
    public boolean existeRegistro(int codigo) throws IOException{
        String sql = "select * from usuario where usucodigo = " + codigo;
        
        ArrayList usuarios = Conexao.executaQuery(sql, new Usuario());
        
        // Verifica se existe usuario para o codigo passado por parametro
        return !usuarios.isEmpty();
    }

    public boolean gravarAlteracao(Usuario usuario) throws IOException {
        String sqlUpdate = "UPDATE usuario SET "
                         + "       usunome = '" + usuario.getNome() + "'"
                         + "      ,usulogin = '" + usuario.getLogin() + "'"
                         + "      ,usuemail = '" + usuario.getEmail() + "'"
                         + "      ,ususenha = '" + BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()) + "'"
                         + " where usucodigo = " + usuario.getCodigo();
        
        return Conexao.executeUpdate(sqlUpdate);
    }

    public boolean gravarInclusao(Usuario usuario) throws IOException {
        // usucodigo gera sozinho pelo campo usucodigo que é serial
        String sqlInsert = "INSERT INTO usuario (usucodigo, usunome, usulogin, ususenha, usuemail) VALUES ("
                         + usuario.getCodigo()
                         + ",'" + usuario.getNome() + "'"
                         + ",'" + usuario.getLogin() + "'"
                         + ",'" + BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()) + "'"
                         + ",'" + usuario.getEmail() + "'"
                         + ")";
        
        return Conexao.executeUpdate(sqlInsert);
    }

    public boolean excluirRegistro(int codigo) throws IOException {
        String sqlExcluir = "DELETE FROM usuario WHERE usucodigo = " + codigo;
        return Conexao.executeUpdate(sqlExcluir);
    }

    public Usuario getRegistro(int codigo) throws IOException {
        String sql = " select usucodigo,  "
                    + "       usunome,    "
                    + "       usulogin,   "
                    + "       ususenha,   "
                    + "       usuemail    "
                    + "  from usuario     "
                    + " where usucodigo = '" + codigo + "'";
        
        ArrayList usuarios = Conexao.executaQuery(sql, new Usuario());
        
        // Se houver usuario, valida a senha
        Usuario usuarioBanco = new Usuario();
        if(!usuarios.isEmpty()){
            usuarioBanco = (Usuario) usuarios.getFirst();
        }
        return usuarioBanco;
    }
}
