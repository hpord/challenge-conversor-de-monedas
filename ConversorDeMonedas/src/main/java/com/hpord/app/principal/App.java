package com.hpord.app.principal;

import com.hpord.app.ui.UI;

public class App 
{
    public static void main( String[] args ) {
        int opcion;
        UI ui = new UI();
        ui.mostrarBienvenida();
        do {
            ui.mostrarMenu();
            opcion = ui.ingresarMonedaOrigen();
            if (opcion == 0) continue;
            opcion = ui.ingresarMonedaDestino();
            if (opcion == 0) continue;
            ui.ingresarMonto();
            ui.llamarConversor();
            opcion = ui.mostrarMontoConvertido();
        } while (opcion !=0);
        ui.mostrarDespedida();
    }
}
