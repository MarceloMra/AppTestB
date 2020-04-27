package com.example.apptestb.model;

import android.widget.Toast;

import com.example.apptestb.activities.MainActivity;
import com.example.apptestb.enums.Comportamento;
import com.example.apptestb.improvement.EndpointInfo;
import com.example.apptestb.improvement.GlobalApplication;
import com.example.apptestb.improvement.ReqReplyObject;
import com.example.apptestb.interfaces.RecebedorMensagens;

import java.nio.charset.StandardCharsets;

public class MyReqReplyObject extends ReqReplyObject {
    private RecebedorMensagens recebedorMensagens;

    public MyReqReplyObject(Comportamento c, RecebedorMensagens recebedorMensagens) {
        super(c);
        this.recebedorMensagens = recebedorMensagens;
    }

    @Override
    protected void novaConexaoEfetuada(EndpointInfo endpointInfo) {
        Toast.makeText(GlobalApplication.getContext().getApplicationContext(), "Nova conexão : "+endpointInfo.getComportamento()+" : "+endpointInfo.getEndpointID(), Toast.LENGTH_LONG).show();
        recebedorMensagens.receberEndpointID(endpointInfo.getEndpointID());
    }

    @Override
    protected void conexaoEncerrada(EndpointInfo endpointInfo) {
        Toast.makeText(GlobalApplication.getContext().getApplicationContext(), "Conexão Encerrada : "+endpointInfo.getComportamento()+" : "+endpointInfo.getEndpointID(), Toast.LENGTH_LONG).show();
        recebedorMensagens.receberEndpointID(null);
    }

    @Override
    public void onSuccessStartAdvertising() {
        Toast.makeText(GlobalApplication.getContext().getApplicationContext(), "Anunciamento iniciado!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFeilureStartAdvertising(Exception e) {
        Toast.makeText(GlobalApplication.getContext().getApplicationContext(), "O anunciamento falhou "+e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccessStartDiscovery() {
        Toast.makeText(GlobalApplication.getContext().getApplicationContext(), "Descobrimento iniciado!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFeilureStartDiscovery(Exception e) {
        Toast.makeText(GlobalApplication.getContext().getApplicationContext(), "O descobrimento falhou "+e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void receive(byte[] dados, String endpointID) {
        Mensagem m = new Mensagem(new String(dados, StandardCharsets.UTF_8),"recebido",endpointID);
        recebedorMensagens.receberMensagem(m);
    }
}
