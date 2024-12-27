package com.hpord.app.conversiondemonedas;

import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Conversor {
    private APICambio apiCambio;
    private Gson gson;
    private HashMap<String, String> map;
    private Type mapType;

    public Conversor() {
        this.apiCambio = new APICambio("3179d7fff24957593c083711");
        this.gson = new Gson();
        this.mapType = new TypeToken<HashMap<String, String>>(){}.getType();
    }

    public double convertir(double monto, Moneda origen, Moneda destino) {
        try {
            String json = apiCambio.obtenerRespuestaServidor(monto, origen.getCodigo(), destino.getCodigo());
            this.map = this.gson.fromJson(json, this.mapType);
        } catch (Exception e) {
            e.getMessage();
            return -1.0;
        }
        return Double.parseDouble(map.get("conversion_result"));
        //return 1.0;
    }
}
