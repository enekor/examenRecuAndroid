package com.example.examenenekorecu.ui.Departamentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.strictmode.GetTargetFragmentRequestCodeUsageViolation;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.examenenekorecu.databinding.FragmentHomeBinding;
import com.example.examenenekorecu.ui.Departamentos.nuevoDepartamento.NuevoDepartamento;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.recyclerDepartamentos.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerDepartamentos.setAdapter(new DepartamentoAdapter(getActivity()));

        onClick();

        return root;
    }

    private void onClick(){
        binding.nuevoDepartamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NuevoDepartamento nd = new NuevoDepartamento(getActivity(), HomeFragment.this);
                nd.show(getActivity().getSupportFragmentManager(),"nuevo departamento");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.recyclerDepartamentos.setAdapter(new DepartamentoAdapter(getActivity()));
    }
}