package com.example.demo2;

import java.util.ArrayList;

public class CochesModel {
    public ArrayList<Coche> coches = new ArrayList<Coche>();

    public void anadirCoches(Coche c){
        this.coches.add(c);
    }

    public boolean validar(int tipoMotor, int anoCompra, String nivelContaminacion){
        boolean resultado = false;

        if(tipoMotor == 1 && anoCompra >= 2005 && anoCompra >= 2010 && nivelContaminacion.equals("A")){
            if(cantidadDiesel() < 3){
                resultado = true;
            }
        }else if(tipoMotor == 2 && anoCompra >= 2010 && anoCompra >= 2020 && nivelContaminacion.equals("C")){
            if(cantidadGasolina() < 2){
                resultado = true;
            }
        }

        return resultado;
    }

    public int cantidadDiesel() {
        int diesel = 0;

        for (Coche c : this.coches) {
            if (c.getTipoMotor() == 1) {
                diesel++;
            }
        }

        return diesel;
    }

    public int cantidadGasolina(){
        int gasolina = 0;

        for(Coche c : this.coches){
            if(c.getTipoMotor() == 2){
                gasolina++;
            }
        }

        return gasolina;
    }
}
