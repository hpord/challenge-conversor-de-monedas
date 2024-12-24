package com.hpord.app.conversiondemonedas;

import java.util.Map;

import com.google.gson.Gson;

public class Conversor {
    private APICambio apiCambio;
    private Gson gson;
    private Map map;

    public Conversor() {
        apiCambio = new APICambio("3179d7fff24957593c083711");
    }

    public double convertir(double monto, Moneda origen, Moneda destino) {
        try {
            String json = apiCambio.obtenerRespuestaServidor(monto, origen.getCodigo(), destino.getCodigo());
            map = gson.fromJson(json, Map.class);
        } catch (Exception e) {
            e.getMessage();
            return -1.0;
        }
        return 1.0;
    }
}
