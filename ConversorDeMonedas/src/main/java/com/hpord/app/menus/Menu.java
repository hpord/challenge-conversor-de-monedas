package com.hpord.app.menus;

import java.util.List;

public class Menu {
    private int numOpciones;
    private List<String> opciones;

    public Menu(List<String> opciones) {
        this.opciones = opciones;
        this.numOpciones = opciones.size();
    }

    public int getNumOpciones() {
        return numOpciones;
    }

    public List<String> getOpciones() {
        return opciones;
    }
}
