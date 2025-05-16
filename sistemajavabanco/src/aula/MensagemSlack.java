package aula;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/** 
 * @author Gelvazio Camargo
 */
public class MensagemSlack {

    public static void slack(String assunto, String message, String channel) throws MalformedURLException, IOException {
        assunto = "PROFESSOR-" + assunto;
        
        // CARREGA AS VARIAVEIS DE AMBIENTE
        ManipTXTMain m = new ManipTXTMain();        
        String url = "https://hooks.slack.com/services/" + m.getUrl_slack();
        
        // String url = "https://hooks.slack.com/services/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";

        try {
            // Criando a conexão com a URL
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST"); // Definindo método POST
            connection.setRequestProperty("Content-Type", "application/json; utf-8"); // Define o tipo de conteúdo do corpo
            connection.setRequestProperty("Accept", "application/json"); // Define o tipo de resposta esperada
            connection.setDoOutput(true); // Necessário para enviar corpo na requisição POST

            // Criando o body
            // Escapando corretamente as aspas e a nova linha para JSON
            String jsonInputString = "{\"channel\": \"" + channel + "\", \"text\": \"[" + assunto + "] \\n" + message + "\"}";

            // Escrevendo o corpo da requisição
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Verificando o código de resposta APÓS configurar e enviar os dados
            int responseCode = connection.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            // Ler a resposta da API, independentemente do código de sucesso ou erro
            InputStream inputStream;
            if (responseCode >= 200 && responseCode < 300) {
                inputStream = connection.getInputStream();
            } else {
                inputStream = connection.getErrorStream();
            }

            if (inputStream != null) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(inputStream, "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Resposta API:");
                    System.out.println(response.toString());
                    
                    if (responseCode != 200) {
//                        try {
//                            MensagemSlack.slack("ERRO AO CHAMAR SLACK - ", 
//                                    "Erro:" + response.toString());
//                        } catch (IOException ex) {
//                            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                    }
                }
            } else {
                System.out.println("Nenhuma resposta recebida da API.");
            }

            connection.disconnect();

        } catch (Exception e) {
//            try {
//                MensagemSlack.slack("ERRO AO CHAMAR SLACK SQL", "Erro:" + e.getMessage() + " \n Trace:" +  e.getStackTrace());
//            } catch (IOException ex) {
//                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
//            }
            e.printStackTrace();
        }
    }
}
