package com.example.examenenekorecu.database.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.examenenekorecu.model.Departamento;

import java.util.List;

@Dao
public interface DepartamentoDao {

    @Insert
    void insertDepartamento(Departamento departamento);

    @Query("select * from departamento")
    List<Departamento> getAllDepartamentos();
}
