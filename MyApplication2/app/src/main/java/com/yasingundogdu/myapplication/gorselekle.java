package com.yasingundogdu.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.yasingundogdu.myapplication.databinding.ActivityGorselekleBinding;

public class gorselekle extends AppCompatActivity {
    private ActivityGorselekleBinding binding;

    Bitmap selectedImage;

    // Galeriye gitmek için, izni istemek için.
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<String> permissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGorselekleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        registerLauncher();
    }

    // Görsel yükleme işlemi
    public void yukle(View view) {
        // Yükleme işlemi burada yapılacaksa yazılabilir.

    }

    // Galeriye gitmek için butona tıklama
    public void image(View view) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            //33+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                // İzin verilmemişse
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_MEDIA_IMAGES)) {
                    // Kullanıcıya açıklama göster
                    Snackbar.make(view, "GALERİYE GİTMEK İÇİN İZİN LAZIM", Snackbar.LENGTH_INDEFINITE)
                            .setAction("İZİN VER", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // İzin iste
                                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
                                }
                            }).show();
                } else {
                    // İlk kez izin iste
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
                }
            } else {
                // İzin verilmişse galeriyi aç
                Intent intentGaleriGit = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intentGaleriGit);
            }


        }else{
            //-32
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // İzin verilmemişse
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Kullanıcıya açıklama göster
                    Snackbar.make(view, "GALERİYE GİTMEK İÇİN İZİN LAZIM", Snackbar.LENGTH_INDEFINITE)
                            .setAction("İZİN VER", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // İzin iste
                                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                                }
                            }).show();
                } else {
                    // İlk kez izin iste
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                }
            } else {
                // İzin verilmişse galeriyi aç
                Intent intentGaleriGit = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intentGaleriGit);
            }
        }


    }

    // İzin isteme ve galeriye gitme işlemi
    private void registerLauncher() {
        // Galeriye gitme işlemi için
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    // Kullanıcı galeriden seçim yapmış
                    Intent cevapIntent = result.getData();  // Seçilen görselin data'sı
                    if (cevapIntent != null) {
                        Uri gorselData = cevapIntent.getData();  // Seçilen görselin URI'si
                        try {
                            if (Build.VERSION.SDK_INT >= 28) {
                                ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), gorselData);
                                selectedImage = ImageDecoder.decodeBitmap(source);
                                binding.imageSec.setImageBitmap(selectedImage); // Seçilen resmi ImageView'da göster
                            } else {
                                selectedImage = MediaStore.Images.Media.getBitmap(gorselekle.this.getContentResolver(), gorselData);
                                binding.imageSec.setImageBitmap(selectedImage);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        // İzin isteme işlemi
        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if (result) {
                    // İzin verildiğinde galeriye git
                    Intent intentGaleriGit = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intentGaleriGit);
                } else {
                    // İzin verilmediğinde kullanıcıya uyarı göster
                    Toast.makeText(gorselekle.this, "İZİN VERİNİZ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
