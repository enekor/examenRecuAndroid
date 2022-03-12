package com.example.examenenekorecu.ui.Empleados.verEmpleado;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.example.examenenekorecu.Intercambio;
import com.example.examenenekorecu.database.BaseDeDatos;
import com.example.examenenekorecu.databinding.EditarTrabajadorBinding;
import com.example.examenenekorecu.model.Trabajador;
import org.jetbrains.annotations.NotNull;

public class EditarEmpleado extends DialogFragment {

    private EditarTrabajadorBinding binding;
    private Context contexto;

    public EditarEmpleado(Context contexto){
        this.contexto = contexto;
    }
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = EditarTrabajadorBinding.inflate(inflater,container,false);

        binding.editarGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkFields()){
                    guardarTrabajador();
                }else{
                    Toast.makeText(contexto, "No se ha guardado el nuevo nombre", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return binding.getRoot();
    }
    private String getNacionalidad(){
        if(binding.nuevoFilipinas.isSelected()){
            return "Filipinas";
        }else if(binding.nuevoUruguay.isSelected()){
            return "Uruguay";
        }else{
            return "Islandia";
        }
    }

    private void guardarTrabajador(){

        Trabajador t = Intercambio.getInstance().getTrabajador();
        t.setNacionalidad(getNacionalidad());
        t.setNombre(binding.uevoNombre.getText().toString());

        BaseDeDatos.getInstance(contexto).trabajadorDao().deleteTrabajador(t.getCodigo());

        BaseDeDatos.getInstance(contexto).trabajadorDao().insertTrabajador(t);

        Toast.makeText(contexto, "Trabajador guardado, reinicie el maestro detalle para verlo actualizado", Toast.LENGTH_SHORT).show();
    }

    private boolean checkFields(){
        return !binding.uevoNombre.getText().toString().equalsIgnoreCase("");
    }
}
