package com.example.examenenekorecu.ui.Empleados;

import android.net.Uri;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.examenenekorecu.camaraDeFotos.SacarFoto;
import com.example.examenenekorecu.database.BaseDeDatos;
import com.example.examenenekorecu.databinding.ActivityNuevoEmpleadoBinding;
import com.example.examenenekorecu.mapper.UriMapper;
import com.example.examenenekorecu.model.Departamento;
import com.example.examenenekorecu.model.Trabajador;
import org.jetbrains.annotations.NotNull;

public class NuevoEmpleado extends AppCompatActivity {

    private ActivityNuevoEmpleadoBinding binding;
    private SacarFoto sacarFotoAction;
    private Uri imageUri = Uri.EMPTY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNuevoEmpleadoBinding.inflate(getLayoutInflater());

        sacarFotoAction = new SacarFoto(this,this,this);
        setSpinner();
        onClick();

        setContentView(binding.getRoot());
    }

    private void onClick(){
        binding.sacarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageUri = sacarFotoAction.sacarFoto();
            }
        });

        binding.nuevoEmpoleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkFields()){
                    guardarTrabajador();
                }else{
                    Toast.makeText(NuevoEmpleado.this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public ActivityResultLauncher setLauncher() {
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                //result.getData().getExtras(MediaStore.)
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==3333){
            sacarFotoAction.openCamera();
        }else{
            Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.imagenPreviewNuevoEmpleado.setImageURI(imageUri);
    }

    private boolean checkFields(){
        return !binding.codigoNuevoEmpleado.getText().toString().equalsIgnoreCase("") &&
                !binding.nombreNuevoEmpleado.getText().toString().equalsIgnoreCase("") &&
                !imageUri.equals(Uri.EMPTY);
    }

    private String getNacionalidad(){
        if(binding.rFilipinas.isSelected()){
            return "Filipinas";
        }else if(binding.rUruguay.isSelected()){
            return "Uruguay";
        }else{
            return "Islandia";
        }
    }

    private void guardarTrabajador(){
        Trabajador t = new Trabajador();
        t.setNacionalidad(getNacionalidad());
        t.setNombre(binding.nombreNuevoEmpleado.getText().toString());
        t.setCodigo(binding.codigoNuevoEmpleado.getText().toString());
        t.setFoto(UriMapper.getInstance().fromUriToString(imageUri));
        t.setDepartamento(getDepartamento((Departamento)binding.spinnerNuevoEmpleado.getSelectedItem()));

        BaseDeDatos.getInstance(this).trabajadorDao().insertTrabajador(t);
        Toast.makeText(this, "Trabajador guardado", Toast.LENGTH_SHORT).show();
    }

    private String getDepartamento(Departamento d){
        return d.getNombre();
    }

    private void setSpinner(){
        ArrayAdapter adapter = new ArrayAdapter(
                this.getApplicationContext(),android.R.layout.simple_spinner_item,
                BaseDeDatos.getInstance(this).departamentoDao().getAllDepartamentos());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerNuevoEmpleado.setAdapter(adapter);
    }
}