package com.example.examenenekorecu;

import android.content.Context;
import androidx.fragment.app.Fragment;
import com.example.examenenekorecu.model.Departamento;
import com.example.examenenekorecu.model.Trabajador;

public class Intercambio {

    private static Intercambio intercambio;
    private  Intercambio(){}

    public static Intercambio getInstance(){
        if(intercambio == null)
            intercambio = new Intercambio();
        return intercambio;
    }

    private Trabajador trabajador = null;
    private Departamento departamento = null;

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}

