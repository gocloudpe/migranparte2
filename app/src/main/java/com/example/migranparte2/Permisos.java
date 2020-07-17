package com.example.migranparte2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

public class Permisos extends AppCompatActivity {

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisos);
        if ((ContextCompat.checkSelfPermission(Permisos.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED)){
            arranque();
        } else {
            Handler handler = new Handler();
            handler.postDelayed(
                    new Runnable() {
                        public void run() {
                            if (ContextCompat.checkSelfPermission(Permisos.this,
                                    android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                permisolocalizacion();
                            } else {
                                solicitarPermiso(Manifest.permission.CALL_PHONE,
                                        "Sin el permiso" + " de telefono no podemos realizar llamadas.", 0);
                            }
                        }
                    }, 2000L);
        }
    }
    void permisolocalizacion(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            permisoalmacenamiento();
        } else {
            solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION,
                    "Sin el permiso" + " de ubicacion no podremos localizarte", 1);
        }
    }
    void permisoalmacenamiento(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            permisocuenta();
        } else {
            solicitarPermiso(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    "Sin el permiso" + " de Almacenamiento no podemos guardar su informacion.", 2);
        }
    }
    void permisocuenta(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED) {
            arranque();
        } else {
            solicitarPermiso(Manifest.permission.GET_ACCOUNTS,
                    "Sin el permiso" + " de contactos no podemos vincularnos con google o facebook.", 3);
        }
    }
    public void solicitarPermiso(final String permiso, String justificacion, final int codigo) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permiso)) {
            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
            dialogo1.setTitle("Solicitud de permiso");
            dialogo1.setMessage(justificacion);
            dialogo1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    ActivityCompat.requestPermissions(Permisos.this, new String[]{permiso}, codigo);
                }
            });
            dialogo1.show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permiso}, codigo);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permisolocalizacion();
            } else {
                solicitarPermiso(Manifest.permission.CALL_PHONE,
                        "Sin el permiso" + " de telefono no podemos realizar llamadas.", 0);
            }
        }
        if (requestCode == 1) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permisoalmacenamiento();
            } else {
                solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION,
                        "Sin el permiso" + " de ubicacion no podremos localizarte", 1);
            }
        }
        if (requestCode == 2) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permisocuenta();
            } else {
                solicitarPermiso(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        "Sin el permiso" + " de Almacenamiento no podemos guardar su informacion.", 2);
            }
        }
        if (requestCode == 3) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                arranque();
            } else {
                solicitarPermiso(Manifest.permission.GET_ACCOUNTS,
                        "Sin el permiso" + " de cuentas no podemos vincularnos con google o facebook.", 3);
            }
        }
    }
    void arranque() {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("FELICITACIONES");
        dialogo1.setMessage("Usted ya tiene los permisos necesarios para usar nuestra app");
        dialogo1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                finish();
            }
        });
        dialogo1.show();
    }
}