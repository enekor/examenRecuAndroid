package com.example.examenenekorecu.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.examenenekorecu.database.Dao.DepartamentoDao;
import com.example.examenenekorecu.database.Dao.TrabajadorDao;
import com.example.examenenekorecu.model.Departamento;
import com.example.examenenekorecu.model.Trabajador;

@Database(entities = {Trabajador.class, Departamento.class}, version = 1)
public abstract class BaseDeDatos extends RoomDatabase {

    private static final String DATABASENAME = "oficina";

    public abstract TrabajadorDao trabajadorDao();
    public abstract DepartamentoDao departamentoDao();

    private static volatile BaseDeDatos instance;

    public synchronized static BaseDeDatos getInstance(final Context contexto){
        if(instance==null){
            instance = Room.databaseBuilder(contexto.getApplicationContext(),
                    BaseDeDatos.class,DATABASENAME).allowMainThreadQueries().build();
        }
        return instance;
    }
}
