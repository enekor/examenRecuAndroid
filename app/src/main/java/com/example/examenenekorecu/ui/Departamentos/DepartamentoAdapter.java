package com.example.examenenekorecu.ui.Departamentos;

import android.content.Context;
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
import com.example.examenenekorecu.model.Departamento;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DepartamentoAdapter extends RecyclerView.Adapter<DepartamentoAdapter.ViewHolder> {

    List<Departamento> departamentos = new ArrayList<>();
    Context context;

    public DepartamentoAdapter(Context context){

        departamentos = BaseDeDatos.getInstance(context).departamentoDao().getAllDepartamentos();
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
        holder.nombrePreview.setText(departamentos.get(i).getNombre());
        holder.imagenPreview.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return departamentos.size();
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
                    Intercambio.getInstance().setDepartamento(departamentos.get(getAdapterPosition()));
                    Toast.makeText(context, "Departamento "+departamentos.get(getAdapterPosition()).getNombre()+" seleccionado", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
