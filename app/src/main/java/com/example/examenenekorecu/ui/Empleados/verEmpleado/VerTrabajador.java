package com.example.examenenekorecu.ui.Empleados.verEmpleado;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.examenenekorecu.Intercambio;
import com.example.examenenekorecu.database.BaseDeDatos;
import com.example.examenenekorecu.databinding.ActivityVerTrabajadorBinding;
import com.example.examenenekorecu.mapper.UriMapper;

public class VerTrabajador extends AppCompatActivity {

    private ActivityVerTrabajadorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerTrabajadorBinding.inflate(getLayoutInflater());

        binding.foto.setImageURI(UriMapper.getInstance().fromStringToUri(Intercambio.getInstance().getTrabajador().getFoto()));
        binding.codigo.setText("Codigo de empleado: "+Intercambio.getInstance().getTrabajador().getCodigo());
        binding.departamento.setText("Departamento: "+Intercambio.getInstance().getTrabajador().getDepartamento());
        binding.nombreCompleto.setText(""+Intercambio.getInstance().getTrabajador().getNombre());

        onClick();
        setContentView(binding.getRoot());
    }

    private void onClick(){
        binding.borrarTrabajador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarTrabajador();
            }
        });
        binding.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditarEmpleado ee = new EditarEmpleado(VerTrabajador.this);
                ee.show(getSupportFragmentManager(),"eitar");
            }
        });
    }

    private void borrarTrabajador() {
        Dialog dialog = null;

        AlertDialog.Builder builder = new AlertDialog.Builder(VerTrabajador.this);
        builder.setMessage("Desea borrar el usuario?")
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteAccount();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
        AlertDialog alert = builder.create();

        dialog = alert;
        dialog.show();
    }

    private void deleteAccount(){
        BaseDeDatos.getInstance(this).trabajadorDao().deleteTrabajador(Intercambio.getInstance().getTrabajador().getCodigo());
        finish();
    }

}