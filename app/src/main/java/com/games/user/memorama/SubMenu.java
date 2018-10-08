package com.games.user.memorama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SubMenu extends AppCompatActivity {
ImageView imgsiguiente,imgmenuprincipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);

        imgmenuprincipal=(ImageView) findViewById(R.id.menuprincipal);
        imgsiguiente = (ImageView) findViewById(R.id.siguiente);


    }
    public void siguiente (View view){
        Intent intent = new Intent(SubMenu.this, Memoram.class);
        startActivity(intent);
    }

    public void menuprincipal (View view){
        Intent intento = new Intent(SubMenu.this, MainActivity.class);
        startActivity(intento);


}
}
