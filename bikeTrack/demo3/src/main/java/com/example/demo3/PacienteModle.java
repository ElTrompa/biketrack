package com.example.demo3;

import java.util.ArrayList;

public class PacienteModle {
    ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

    public void añadirPacientes(Paciente p){
        this.pacientes.add(p);
    }

    public boolean añadir(int sexo, int edad){
        boolean resultado = false;

        if(sexo == 0 && edad >= 15 && edad <= 30){
            if(numeroPacientesHombre() < 3){
                resultado = true;
            }
        } else if (sexo == 1 && edad >= 20 && edad <= 40) {
            if(numeroPacienteMujer() < 2){
                resultado = true;
            }
        }

        return resultado;
    }

    public int numeroPacientesHombre(){
        int hombre = 0;

        for(Paciente p : this.pacientes){
            if(p.getSexo() == 0){
                hombre++;
            }
        }

        return hombre;
    }

    public int numeroPacienteMujer(){
        int mujer = 0;

        for(Paciente p : this.pacientes){
            if(p.getSexo() == 1){
                mujer++;
            }
        }

        return mujer;
    }
}
