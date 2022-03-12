package com.example.examenenekorecu.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.examenenekorecu.model.Trabajador;

import java.util.List;

@Dao
public interface TrabajadorDao {

    @Insert
    void insertTrabajador(Trabajador trabajador);

    @Query("select * from Trabajador")
    List<Trabajador> getAllTrabajadores();

    @Query("select * from Trabajador where nombre=:nombre")
    Trabajador getByNombre(String nombre);

    @Query("Select * from Trabajador where codigo=:codigo")
    Trabajador getByCodigo(String codigo);

    @Query("select * from Trabajador where departamento=:departamento")
    List<Trabajador> selectAllByDepartamento(String departamento);

    @Query("delete from Trabajador where codigo=:codigo ")
    void deleteTrabajador(String codigo);
}
