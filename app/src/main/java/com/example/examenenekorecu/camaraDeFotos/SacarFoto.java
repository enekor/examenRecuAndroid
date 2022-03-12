package com.example.examenenekorecu.camaraDeFotos;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.activity.result.ActivityResultLauncher;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.example.examenenekorecu.BuildConfig;
import com.example.examenenekorecu.ui.Empleados.NuevoEmpleado;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SacarFoto {

    private final static int CODIGO = 3333;
    private final Context contexto;
    private final NuevoEmpleado main;
    private Activity activity = null;
    private String imagePath = "";
    private Uri imageUri = Uri.EMPTY;

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    private ActivityResultLauncher launcher;

    public SacarFoto(Context contexto, Activity activity, NuevoEmpleado nuevoEmpleado){
        this.contexto = contexto;
        this.activity = activity;
        this.main = nuevoEmpleado;
        setLauncher();
    }
    public Uri sacarFoto(){
        if(ContextCompat.checkSelfPermission(contexto, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            String[] permissions = new String[1];
            permissions[0] = Manifest.permission.CAMERA;
            activity.requestPermissions(permissions,CODIGO);
        }else{
            openCamera();
        }
        return imageUri;
    }

    public void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File foto = null;
        try{
            foto = createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(foto!=null){
            imageUri = FileProvider.getUriForFile(contexto, BuildConfig.APPLICATION_ID + ".provider", foto);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
            launcher.launch(intent);
        }
    }

    private File createImageFile() throws IOException {
        String nombre = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File directorio = contexto.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File archivo = File.createTempFile(nombre,".jpg",directorio);

        imagePath = archivo.getAbsolutePath();
        return archivo;
    }

    private void setLauncher(){
        launcher = main.setLauncher();
    }
}


