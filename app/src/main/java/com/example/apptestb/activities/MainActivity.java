package com.example.apptestb.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptestb.R;
import com.example.apptestb.enums.Comportamento;
import com.example.apptestb.improvement.GlobalApplication;
import com.example.apptestb.improvement.NearbyAccessObject;
import com.example.apptestb.improvement.PatternComunicationObject;
import com.example.apptestb.interfaces.RecebedorMensagens;
import com.example.apptestb.model.AdapterMensagens;
import com.example.apptestb.model.Mensagem;
import com.example.apptestb.model.MyReqReplyObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecebedorMensagens {
    private RecyclerView rvMensagens;
    private Button btnEnviar;
    private EditText txtMensagem;
    private ArrayList<Mensagem> mensagens;
    private AdapterMensagens adapt;
    private NearbyAccessObject nearbyAccessObject;
    private PatternComunicationObject patternComunicationObject;
    private String endpointIDConectado;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);

        rvMensagens = (RecyclerView) findViewById(R.id.rvMensagens);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        txtMensagem = (EditText) findViewById(R.id.txtMensagem);

        mensagens = new ArrayList<>();

        adapt = new AdapterMensagens(mensagens);
        rvMensagens.setAdapter(adapt);
        LinearLayoutManager layoutManager = new LinearLayoutManager(GlobalApplication.getContext().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        layoutManager.setStackFromEnd(true);
        rvMensagens.setLayoutManager(layoutManager);

        patternComunicationObject = new MyReqReplyObject(Comportamento.REPLYER, this);
        nearbyAccessObject = new NearbyAccessObject(patternComunicationObject, "nicknameb");
        patternComunicationObject.setNearbyAccessObject(nearbyAccessObject);
        patternComunicationObject.startAdvertising();
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(endpointIDConectado != null){
                    ((MyReqReplyObject) patternComunicationObject).send(txtMensagem.getText().toString().getBytes(), endpointIDConectado);

                    txtMensagem.setText("");
                    Mensagem m = new Mensagem(txtMensagem.getText().toString(),"enviada",endpointIDConectado);
                    mensagens.add(m);
                    adapt.notifyDataSetChanged();
                }else{
                    Toast.makeText(GlobalApplication.getContext().getApplicationContext(), "Não é possivel enviar mensagem!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void receberMensagem(Mensagem mensagem) {
        mensagens.add(mensagem);
        adapt.notifyDataSetChanged();
    }

    @Override
    public void receberEndpointID(String endpointID) {
        endpointIDConectado = endpointID;
    }
}
