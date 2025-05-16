package aula;

import static aula.Conexao.conexao;
import static aula.Conexao.setConexao;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 * @author Gelvazio Camargo
 */
//public class ManipTXTMain extends Pessoa {
public class ManipTXTMain {

    private String url_slack;
    
//    ManipTXT manip = new ManipTXT("Clientes.txt");
//    ManipTXT manip = new ManipTXT(".env");
    private ManipTXT manip = new ManipTXT("src/aula/.env");
    private Vector<String> v = manip.ler();

//    String database  = "neondb";
//    String host    = "ep-damp-waterfall-a5s23fvv.us-east-2.aws.neon.tech";
//    String url     = "jdbc:postgresql://" + host + "/" + database;
//    String usuario = "neondb_owner";
//    String senha   = "I1rEsX7tUqCm";
    private String database = "";
    private String host = "";
    private String url     = "jdbc:postgresql://" + host + "/" + database;
    private String usuario = "";
    private String senha = "";

    public ManipTXTMain() {
        listarVariaveisAmbiente();
    }

    
    public boolean gravarCliente() {
        // Seta os dados
//        setCodigo(1);
//        setCpf("06102314577");
//        setEndereco("Rua das Flores");
//        setNome("Joao da Silva");
//        
        StringBuilder builder = new StringBuilder();
        builder.append(1);
        builder.append(";");
        builder.append("06102314577");
        builder.append(";");
        builder.append("Rua das Flores");
        builder.append(";");
        builder.append("Joao da Silva");

        try {
            StringBuilder texto = new StringBuilder();

            texto.append(builder.toString());

            v.add(texto.toString());

            manip.abrirArquivo();

            for (int x = 0; x < v.size(); x++) {
                manip.escrever(v.elementAt(x));
            }

            manip.fecharArquivo();

            return true;
        } catch (Exception e) {
            System.out.println("ERRO (gravarCliente): " + e.toString());
        }

        return false;
    }

    public String listarVariaveisAmbiente() {

        try {

            // StringBuilder texto = new StringBuilder();

            v = manip.ler();

            // LE AS VARIAVEIS DO DATABASE
            for (int x = 0; x < v.size(); x++) {

                String[] linhaResul = v.get(x).split("=");

                if (linhaResul[0].equals("#DATABASE") 
                    || linhaResul[0].equals("#SLACK")
                    || linhaResul[0].equals("#AMBIENTE")) {
                    continue;
                }

                String var = linhaResul[0];
                String valor = linhaResul[1];
//                System.out.println("Valor da linha na posicao 0:" + var);
//                System.out.println("Valor da linha na posicao 1:" + valor);

                switch (var) {
                    case "DATABASE_NAME":
                        database = valor;
                        break;
                    case "DATABASE_HOST":
                        host = valor;
                        break;
                    case "DATABASE_URL":
                        url = valor;
                        break;
                    case "DATABASE_USUARIO":
                        usuario = valor;
                        break;
                    case "DATABASE_SENHA":
                        senha = valor;
                        break;
                    case "SLACK_URL_HOOK_CHANNEL_DEV":
                        this.url_slack = valor;
                        break;
                    case "SLACK_URL_HOOK_CHANNEL_NETBEANS":
                        this.url_slack = valor;
                        break;
                }
            }

            //return texto.toString();
        } catch (Exception e) {
            System.out.println("ERRO (Listar Cliente): " + e.toString());
        }

        // SETA OS VALORES NA URL
        url = "jdbc:postgresql://" + host + "/" + database;
        return "";
    }

    public String conectaBancoTeste() {
        String driver = "org.postgresql.Driver";

//        String database= "neondb";
//        String host    = "ep-damp-waterfall-a5s23fvv.us-east-2.aws.neon.tech";
//        String url     = "jdbc:postgresql://" + host + "/" + database;
//        String usuario = "neondb_owner";
//        String senha   = "I1rEsX7tUqCm";
        String lista_dados = "";
        String lista_dados_json = "";

        Statement statement;
        ResultSet resultset;
        try {
            Class.forName(driver);

            setConexao(DriverManager.getConnection(url, usuario, senha));

            System.out.println("Conectou com o PostgreSQL!");

            System.out.println("Conectou com o PostgreSQL!");

            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            resultset = statement.executeQuery("select * from usuario limit 10");

            while (resultset.next()) {
                int usucodigo = resultset.getInt("usucodigo");
                String usuemail = resultset.getString("usucodigo");
                String ususenha = resultset.getString("ususenha");

                lista_dados = lista_dados + "Codigo .: " + resultset.getInt("usucodigo");
                lista_dados = lista_dados + "\nE-mail .: " + resultset.getString("usuemail") + "\n";
                lista_dados = lista_dados + "\nSenha .: " + resultset.getString("ususenha") + "\n";

                lista_dados_json = lista_dados_json + "{"
                        + "\"usucodigo\":\"" + usucodigo + "\","
                        + "\"usuemail\":\"" + usuemail + "\","
                        + "\"ususenha\":\"" + ususenha + "\""
                        + "}";
            }

            // JOptionPane.showMessageDialog(null, lista_dados);
            System.out.println(lista_dados);

        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver não localizado: " + Driver);
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Deu erro na conexão com a fonte de dados: " + Fonte);
        }

        return lista_dados_json;
    }

    public String listarCliente(int codigo) {

        try {

            StringBuilder texto = new StringBuilder();

            v = manip.ler();

            for (int x = 0; x < v.size(); x++) {

                String[] linhaResul = v.get(x).split(";");

                if (linhaResul[0].equals(String.valueOf(codigo))) {

                    texto.append("Código da Venda: ");
                    texto.append(linhaResul[0]);

                    texto.append("Data da Venda: ");
                    texto.append(linhaResul[3]);

                    texto.append("Cliente da Venda: ");
                    texto.append(linhaResul[1]);

                    texto.append("Vendedor da Venda: ");
                    texto.append(linhaResul[2]);

                    texto.append("\n");
                    texto.append("Produtos:");
                    texto.append("\n");
                }
            }

            return texto.toString();

        } catch (Exception e) {
            System.out.println("ERRO (Listar Cliente): " + e.toString());
        }

        return "";
    }

    public String listarVenda(int codigo) {

        try {

            StringBuilder texto = new StringBuilder();

            v = manip.ler();

            for (int x = 0; x < v.size(); x++) {

                String[] linhaResul = v.get(x).split(";");

                if (linhaResul[0].equals(String.valueOf(codigo))) {

                    texto.append("Código da Venda: ");
                    texto.append(linhaResul[0]);

                    texto.append("Data da Venda: ");
                    texto.append(linhaResul[3]);

                    texto.append("Cliente da Venda: ");
                    texto.append(linhaResul[1]);

                    texto.append("Vendedor da Venda: ");
                    texto.append(linhaResul[2]);

                    texto.append("\n");
                    texto.append("Produtos:");
                    texto.append("\n");
                }
            }

            return texto.toString();

        } catch (Exception e) {
            System.out.println("ERRO (Listar Venda): " + e.toString());
        }

        return "";
    }

    public static void main(String[] args) {
        // ManipTXTMain m = new ManipTXTMain();
//        m.gravarCliente();

       // m.listarVariaveisAmbiente(1);
    }

    public String getUrl_slack() {
        return url_slack;
    }

    public void setUrl_slack(String url_slack) {
        this.url_slack = url_slack;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
