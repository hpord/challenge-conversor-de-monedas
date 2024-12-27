package com.hpord.app.conversiondemonedas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class APICambio {
    private String apikey;

    public APICambio(String apikey) {
        this.apikey = apikey;
    }

    public String obtenerRespuestaServidor(double monto, String origen, String destino) throws IOException, InterruptedException {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("#.##",simbolos);

        String direccion = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%s", 
        apikey, origen, destino, formateador.format(monto));
        HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        return json;
    }
}
