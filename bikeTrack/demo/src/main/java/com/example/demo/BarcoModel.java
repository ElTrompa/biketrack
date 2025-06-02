package com.example.demo;

import java.util.ArrayList;

public class BarcoModel {
    public ArrayList<Barco> barcos = new ArrayList<Barco>();

    public void añadirBarcos(Barco b){
        this.barcos.add(b);
    }

    public boolean añadir(int añoCompra, String provincia){
        boolean resultado = false;

        if(añoCompra >= 2000 && añoCompra <= 2005 && provincia.equals("Valencia")){
            resultado = true;
        }else if(añoCompra >= 1990 && añoCompra <= 2000 && provincia.equals("Castellon")){
            resultado = true;
        }

        return resultado;
    }

    public int cantidadMotor(){
        int motor = 0;

        for(Barco b : this.barcos){
            if(b.getTipoMotor() == 1){
                motor++;
            }
        }

        return motor;
    }

    public int cantidadVelero(){
        int velero = 0;

        for(Barco b : this.barcos){
            if(b.getTipoMotor() == 2){
                velero++;
            }
        }

        return velero;
    }
}
