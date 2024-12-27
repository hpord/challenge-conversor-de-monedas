package com.hpord.app.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hpord.app.conversiondemonedas.Conversor;
import com.hpord.app.conversiondemonedas.Moneda;
import com.hpord.app.menus.Menu;

public  class UI {
    private Menu menu;
    private String bienvenida;
    private String despedida;
    private double montoOriginal;
    private double montoConvertido;
    private Moneda monedaOrigen;
    private Moneda monedaDestino;
    private Scanner lectura;
    private List<String> monedas;
    private List<String> codigos;
    private Conversor conversor;

    public UI() {
        this.monedas = new ArrayList<String>();
        this.codigos = new ArrayList<String>();
        this.bienvenida = "Bienvenidos al conversor de monedas";
        this.despedida = "Gracias por utilizar el conversor de monedas";
        monedas.add("Dolares");
        monedas.add("Soles");
        monedas.add("Pesos argentinos");
        monedas.add("Reales brasileños");
        monedas.add("Pesos colombianos");
        monedas.add("Pesos mexicanos");
        monedas.add("Euros");
        monedas.add("Libras Esterlinas");
        monedas.add("Rublos");
        codigos.add("USD");
        codigos.add("PEN");
        codigos.add("ARS");
        codigos.add("BRL");
        codigos.add("COP");
        codigos.add("MXN");
        codigos.add("EUR");
        codigos.add("GBP");
        codigos.add("RUB");
        this.menu = new Menu(monedas);
        lectura = new Scanner(System.in);
        conversor = new Conversor();
    }
    
    public void mostrarMenu() {
        System.out.println("Elija una opción:");
        for (int i = 0; i < menu.getNumOpciones(); i++) {
            System.out.println(String.format("%d.- %s", i+1, menu.getOpciones().get(i)));
        }
        System.out.println("0.- Salir \n");
    }

    public void  mostrarBienvenida() {
        System.out.println(this.bienvenida);
        System.out.println();
    }

    public void  mostrarDespedida() {
        System.out.println(this.despedida);
        System.out.println();
    }

    public boolean validarEleccion() {
        return false;
    }

    public int ingresarOpcionMoneda(String tipo) {
        int opcion = -1;
        while(opcion < 0 || opcion > menu.getNumOpciones()) {
            try {
                System.out.println("Ingrese opción de moneda de " + tipo + ": ");
                opcion = lectura.nextInt();
                if (opcion < 0 || opcion > menu.getNumOpciones()) {
                    System.out.println("INGRESE UNA OPCION VÁLIDA");
                }
            } catch (NumberFormatException e) {
                System.out.println("INGRESE UN NÚMERO");
            }
        } 
        if (opcion > 0) {
            if (tipo == "origen") {
                this.monedaOrigen = new Moneda(this.monedas.get(opcion-1), this.codigos.get(opcion-1));
            }
            else {
                this.monedaDestino = new Moneda(this.monedas.get(opcion-1), this.codigos.get(opcion-1));
            }
        }
        return opcion;
    }

    public int ingresarMonedaOrigen() {
        return this.ingresarOpcionMoneda("origen");
    }

    public int ingresarMonedaDestino() {
        return this.ingresarOpcionMoneda("destino");
    }

    public void ingresarMonto() {
        do {
            try {
                System.out.println("Ingrese la cantidad que desea convertir:");
                this.montoOriginal = lectura.nextDouble();
                if (this.montoOriginal < 0) {
                    System.out.println("INGRESE UNA CANTIDAD VÁLIDA");
                }
            } catch (NumberFormatException e) {
                System.out.println("INGRESE UN NÚMERO DECIMAL");
                this.montoOriginal = -1;
            }
        } while (this.montoOriginal < 0);
    }

    public void llamarConversor() {
        this.montoConvertido = conversor.convertir(montoOriginal, monedaOrigen, monedaDestino);
    }

    public int mostrarMontoConvertido() {
        if (montoConvertido < 0) {
            System.out.println("No se pudo acceder a la base de datos");
            return 0;
        }
        System.out.println(String.format("%.2f %s SON EQUIVALENTES A %.2f %s\n", montoOriginal, monedaOrigen.getNombre().toUpperCase(),
        montoConvertido, monedaDestino.getNombre().toUpperCase()));
        return 1;
    }
}