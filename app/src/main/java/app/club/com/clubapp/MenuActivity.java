package app.club.com.clubapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import app.club.com.clubapp.player.PlaybackStatus;
import app.club.com.clubapp.player.RadioManager;

public class MenuActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        btn_play.setVisibility(View.INVISIBLE);
        btn_play.setImageResource(R.drawable.img_play);
        flag = 1;
        radioManager.bind();

    }

    RadioManager radioManager;

    @Override
    protected void onDestroy() {

        radioManager.unbind();

        super.onDestroy();
    }

    @Override
    protected void onStop() {

        EventBus.getDefault().unregister(this);

        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);

    }

    @Subscribe
    public void onEvent(String status){

        switch (status){

            case PlaybackStatus.LOADING:

                // loading

                break;

            case PlaybackStatus.ERROR:

                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();

                break;

        }


    }

    ImageButton btn_play;
    int flag = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        radioManager = RadioManager.with(this);


        btn_play = (ImageButton)findViewById(R.id.btn_play);


        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(flag == 1){
                    btn_play.setImageResource(R.drawable.img_stop);




                    flag = 0;
                }else{
                    btn_play.setImageResource(R.drawable.img_play);


                    flag = 1;
                }
                String streamURL = "http://sidechannels.tritondigital.com/api/listen?sid=93599&m=sc&rid=168169";
                radioManager.playOrPause(streamURL);

            }
        });


        ImageButton btn_radio = (ImageButton)findViewById(R.id.btn_radio);

        btn_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_play.setVisibility(View.VISIBLE);

            }
        });

        ImageButton btn_noticias = (ImageButton)findViewById(R.id.btn_noticias);

        btn_noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,DetailsActivity.class);
                intent.putExtra("button","noticias");
                startActivity(intent);
            }
        });

        ImageButton btn_miembros = (ImageButton)findViewById(R.id.btn_miembros);

        btn_miembros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,DetailsActivity.class);
                intent.putExtra("button","miembros");
                startActivity(intent);
            }
        });

        ImageButton btn_radionotas = (ImageButton)findViewById(R.id.btn_radionotas);

        btn_radionotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,DetailsActivity.class);
                intent.putExtra("button","radionotas");
                startActivity(intent);
            }
        });



        ImageButton btn_eventos = (ImageButton)findViewById(R.id.btn_eventos);

        btn_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,DetailsActivity.class);
                intent.putExtra("button","eventos");
                startActivity(intent);
            }
        });

        ImageButton btn_mensajes = (ImageButton)findViewById(R.id.btn_mensajes);

        btn_mensajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,DetailsActivity.class);
                intent.putExtra("button","mensajes");
                startActivity(intent);
            }
        });
    }
}
