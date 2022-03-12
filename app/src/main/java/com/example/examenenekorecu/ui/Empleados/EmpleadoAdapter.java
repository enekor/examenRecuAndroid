package com.example.examenenekorecu.ui.Empleados;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examenenekorecu.Intercambio;
import com.example.examenenekorecu.R;
import com.example.examenenekorecu.database.BaseDeDatos;
import com.example.examenenekorecu.mapper.UriMapper;
import com.example.examenenekorecu.model.Trabajador;
import com.example.examenenekorecu.ui.Empleados.verEmpleado.VerTrabajador;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EmpleadoAdapter extends RecyclerView.Adapter<EmpleadoAdapter.ViewHolder> {

    List<Trabajador> trabajadores = new ArrayList<>();
    Context context;

    public EmpleadoAdapter(Context context, Activity activity){

        if(Intercambio.getInstance().getDepartamento()==null){
            activity.onBackPressed();
            Toast.makeText(activity, "seleccione una en la pantalla de categorias", Toast.LENGTH_SHORT).show();
        }

        if(Intercambio.getInstance().getDepartamento()!=null) {
            trabajadores = BaseDeDatos.getInstance(context).trabajadorDao().selectAllByDepartamento(Intercambio.getInstance().getDepartamento().getNombre());
        }

        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.empleado_preview,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int i) {
        holder.nombrePreview.setText(trabajadores.get(i).getNombre());
        holder.imagenPreview.setImageURI(UriMapper.getInstance().fromStringToUri(trabajadores.get(i).getFoto()));
    }

    @Override
    public int getItemCount() {
        return trabajadores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layout;
        ImageView imagenPreview;
        TextView nombrePreview;

        public ViewHolder(@NonNull @NotNull View v) {
            super(v);

            layout = v.findViewById(R.id.layoutPreview);
            imagenPreview = v.findViewById(R.id.fotoPreview);
            nombrePreview = v.findViewById(R.id.nombrePreview);
            onClick();
        }

        private void onClick(){
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posicion = getAdapterPosition();
                    Intercambio.getInstance().setTrabajador(trabajadores.get(posicion));
                    context.startActivity(new Intent(context, VerTrabajador.class));
                }
            });
        }
    }
}

