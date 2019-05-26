import com.google.gson.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class EngRu implements Strategy{

    public String translate(String text){
        HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        // API-key для запроса.
        String apiToken = "trnsl.1.1.20190524T102219Z.128d534e779a3130.c28c75f1686f00a092d63f64b794fc441ae2d418";
        // переводим текст в URL кодировку
        String textURL = "";
        try {
            textURL = URLEncoder.encode(text, "utf-8");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        // собираем запрос
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("https://translate.yandex.net/api/v1.5/tr.json/translate?key=%s&text=%s&lang=%s",
                        apiToken,
                        textURL,
                        "en-ru")))
                .GET()
                .build();
        HttpResponse<String> response = null;

        // отправляем запрос, сохраняем ответ
        try{
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        // парсим JSON, вытаскиваем результат перевода по ключу "text"
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(response.body());
        text = jsonObject.getAsJsonArray("text").getAsString();
        return text;
    }

    @Override
    public String toString(){
        return "Eng-Ru";
    }
}
