package com.games.user.memorama;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Memoram extends AppCompatActivity implements View.OnClickListener{

    ImageView imbCarta1, imbCarta2, imbCarta3, imbCarta4, imbCarta5, imbCarta6, imbCarta7, imbCarta8;
    int[] imagenes = {R.drawable.primavera1,R.drawable.verano1,R.drawable.otono1,R.drawable.invierno1};
    int[] juego = new int[8];
    int[] cartas_selecionadas = new int[2];
    int[] imagenes_selecionadas = new int[2];
    int[] juego_terminado = new int[8];
    int turno=0, ganador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoram);

        imbCarta1 = (ImageView) findViewById(R.id.imbCarta1);
        imbCarta1.setOnClickListener(this);
        imbCarta2 = (ImageView) findViewById(R.id.imbCarta2);
        imbCarta2.setOnClickListener(this);
        imbCarta3 = (ImageView) findViewById(R.id.imbCarta3);
        imbCarta3.setOnClickListener(this);
        imbCarta4 = (ImageView) findViewById(R.id.imbCarta4);
        imbCarta4.setOnClickListener(this);
        imbCarta5 = (ImageView) findViewById(R.id.imbCarta5);
        imbCarta5.setOnClickListener(this);
        imbCarta6 = (ImageView) findViewById(R.id.imbCarta6);
        imbCarta6.setOnClickListener(this);
        imbCarta7 = (ImageView) findViewById(R.id.imbCarta7);
        imbCarta7.setOnClickListener(this);
        imbCarta8 = (ImageView) findViewById(R.id.imbCarta8);
        imbCarta8.setOnClickListener(this);

        if (savedInstanceState == null)
        {
            asignarImagenes();
            for (int i=0;i<8;i++)
                juego_terminado[i]=0;

            cartas_selecionadas[0]=8;
        }
    }


    private void asignarImagenes()
    {
        int posicion, contador = 0;
        for (int i=0;i<4;)
        {
            posicion = (int)(Math.random()*8);

            if(juego[posicion]==0) {
                juego[posicion] = imagenes[i];
                contador++;

                if(contador == 2)
                {
                    i++;
                    contador=0;
                }

            }
        }
    }

    @Override
    public void onClick(View v){

        switch (v.getId())
        {
            case R.id.imbCarta1:
                imbCarta1.setImageResource(juego[0]);
                imagenes_selecionadas[turno]=juego[0];
                cartas_selecionadas[turno]=0;
                imbCarta1.setEnabled(false);
                break;
            case R.id.imbCarta2:
                imbCarta2.setImageResource(juego[1]);
                imagenes_selecionadas[turno]=juego[1];
                cartas_selecionadas[turno]=1;
                imbCarta2.setEnabled(false);
                break;
            case R.id.imbCarta3:
                imbCarta3.setImageResource(juego[2]);
                imagenes_selecionadas[turno]=juego[2];
                cartas_selecionadas[turno]=2;
                imbCarta3.setEnabled(false);
                break;
            case R.id.imbCarta4:
                imbCarta4.setImageResource(juego[3]);
                imagenes_selecionadas[turno]=juego[3];
                cartas_selecionadas[turno]=3;
                imbCarta4.setEnabled(false);
                break;
            case R.id.imbCarta5:
                imbCarta5.setImageResource(juego[4]);
                imagenes_selecionadas[turno]=juego[4];
                cartas_selecionadas[turno]=4;
                imbCarta5.setEnabled(false);
                break;
            case R.id.imbCarta6:
                imbCarta6.setImageResource(juego[5]);
                imagenes_selecionadas[turno]=juego[5];
                cartas_selecionadas[turno]=5;
                imbCarta6.setEnabled(false);
                break;
            case R.id.imbCarta7:
                imbCarta7.setImageResource(juego[6]);
                imagenes_selecionadas[turno]=juego[6];
                cartas_selecionadas[turno]=6;
                imbCarta7.setEnabled(false);
                break;
            case R.id.imbCarta8:
                imbCarta8.setImageResource(juego[7]);
                imagenes_selecionadas[turno]=juego[7];
                cartas_selecionadas[turno]=7;
                imbCarta8.setEnabled(false);
        }

        if(turno==0) {
            turno = 1;
        }else{
            new Hilo().execute();
            turno=0;
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle estado) {
        super.onSaveInstanceState(estado);
        estado.putInt("TURNO", turno);
        estado.putInt("GANADOR", ganador);
        estado.putIntArray("ARREGLO_JUEGO", juego);
        estado.putIntArray("ARREGLO_JUEGO_TERMINADO", juego_terminado);
        estado.putIntArray("ARREGLO_CARTAS_SELECCIONADAS", cartas_selecionadas);
        estado.putIntArray("ARREGLO_IMAGENES_SELECCIONADAS", imagenes_selecionadas);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        turno = savedInstanceState.getInt("TURNO");
        ganador = savedInstanceState.getInt("GANADOR");
        juego = savedInstanceState.getIntArray("ARREGLO_JUEGO");
        juego_terminado = savedInstanceState.getIntArray("ARREGLO_JUEGO_TERMINADO");
        cartas_selecionadas = savedInstanceState.getIntArray("ARREGLO_CARTAS_SELECCIONADAS");
        imagenes_selecionadas = savedInstanceState.getIntArray("ARREGLO_IMAGENES_SELECCIONADAS");

        if (juego_terminado[0] == 1 || cartas_selecionadas[0]==0)
        {
            imbCarta1.setImageResource(juego[0]);
            imbCarta1.setEnabled(false);
        }

        if (juego_terminado[1] == 1 || cartas_selecionadas[0]==1)
        {
            imbCarta2.setImageResource(juego[1]);
            imbCarta2.setEnabled(false);
        }

        if (juego_terminado[2] == 1 || cartas_selecionadas[0]==2)
        {
            imbCarta3.setImageResource(juego[2]);
            imbCarta3.setEnabled(false);
        }

        if (juego_terminado[3] == 1 || cartas_selecionadas[0]==3)
        {
            imbCarta4.setImageResource(juego[3]);
            imbCarta4.setEnabled(false);
        }

        if (juego_terminado[4] == 1 || cartas_selecionadas[0]==4)
        {
            imbCarta5.setImageResource(juego[4]);
            imbCarta5.setEnabled(false);
        }

        if (juego_terminado[5] == 1 || cartas_selecionadas[0]==5)
        {
            imbCarta6.setImageResource(juego[5]);
            imbCarta6.setEnabled(false);
        }

        if (juego_terminado[6] == 1 || cartas_selecionadas[0]==6)
        {
            imbCarta7.setImageResource(juego[6]);
            imbCarta7.setEnabled(false);
        }

        if (juego_terminado[7] == 1 || cartas_selecionadas[0]==7)
        {
            imbCarta8.setImageResource(juego[7]);
            imbCarta8.setEnabled(false);
        }
    }

    class Hilo extends AsyncTask<Void,Integer,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            imbCarta1.setEnabled(false);
            imbCarta2.setEnabled(false);
            imbCarta3.setEnabled(false);
            imbCarta4.setEnabled(false);
            imbCarta5.setEnabled(false);
            imbCarta6.setEnabled(false);
            imbCarta7.setEnabled(false);
            imbCarta8.setEnabled(false);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(300);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(imagenes_selecionadas[0]==imagenes_selecionadas[1])
            {
                juego_terminado[cartas_selecionadas[0]]=1;
                juego_terminado[cartas_selecionadas[1]]=1;

                ganador++;

                if(ganador==4){


                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    View customToast = inflater.inflate(R.layout.custom_toast, null);
                    Toast toast = new Toast(Memoram.this);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(customToast);
                    toast.show();


                }

                if (ganador==4){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Memoram.this, SubMenu.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 3100);

                }


            }

            if (juego_terminado[0] == 0)
            {
                imbCarta1.setImageResource(R.drawable.estacion1);
                imbCarta1.setEnabled(true);
            }

            if (juego_terminado[1] == 0)
            {
                imbCarta2.setImageResource(R.drawable.estacion1);
                imbCarta2.setEnabled(true);
            }

            if (juego_terminado[2] == 0)
            {
                imbCarta3.setImageResource(R.drawable.estacion1);
                imbCarta3.setEnabled(true);
            }

            if (juego_terminado[3] == 0)
            {
                imbCarta4.setImageResource(R.drawable.estacion1);
                imbCarta4.setEnabled(true);
            }

            if (juego_terminado[4] == 0)
            {
                imbCarta5.setImageResource(R.drawable.estacion1);
                imbCarta5.setEnabled(true);
            }

            if (juego_terminado[5] == 0)
            {
                imbCarta6.setImageResource(R.drawable.estacion1);
                imbCarta6.setEnabled(true);
            }

            if (juego_terminado[6] == 0)
            {
                imbCarta7.setImageResource(R.drawable.estacion1);
                imbCarta7.setEnabled(true);
            }

            if (juego_terminado[7] == 0)
            {
                imbCarta8.setImageResource(R.drawable.estacion1);
                imbCarta8.setEnabled(true);
            }

            cartas_selecionadas[0]=8;

        }
    }

}