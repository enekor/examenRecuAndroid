package com.example.examenenekorecu.ui.Departamentos.nuevoDepartamento;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.example.examenenekorecu.database.BaseDeDatos;
import com.example.examenenekorecu.databinding.NuevoDepartamentoBinding;
import com.example.examenenekorecu.model.Departamento;
import org.jetbrains.annotations.NotNull;

public class NuevoDepartamento extends DialogFragment {

    private NuevoDepartamentoBinding binding;
    private Context contexto;
    private Fragment fragmento;

    public NuevoDepartamento(Context contexto, Fragment fragmento){
        this.contexto = contexto;
        this.fragmento = fragmento;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = NuevoDepartamentoBinding.inflate(inflater,container,false);

        binding.guardarDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.editTextTextPersonName.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(contexto, "No se ha decidido el nombre del departamento", Toast.LENGTH_SHORT).show();
                }else{

                    Departamento d = new Departamento();
                    d.setNombre(binding.editTextTextPersonName.getText().toString());
                    BaseDeDatos.getInstance(contexto).departamentoDao().insertDepartamento(d);

                    fragmento.onResume();
                    dismiss();
                }
            }
        });
        return binding.getRoot();

    }
}
