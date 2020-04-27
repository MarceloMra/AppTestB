package com.example.apptestb.improvement;

import android.widget.Toast;

import com.example.apptestb.enums.Comportamento;
import com.example.apptestb.interfaces.Receiver;

public abstract class SubscriberObject extends PatternComunicationObject implements Receiver {


    public SubscriberObject() {
        super.comportamento = Comportamento.SUBSCRIBER;
    }

    public void subscrever(String endpointID){
        nearbyAccessObject.comunicacaoSubscricao(endpointID, "-@-subscribe-@-");
        Toast.makeText(GlobalApplication.getContext().getApplicationContext(), "requisição de Subscrição efetuada! ", Toast.LENGTH_LONG).show();
    }

    public abstract void onOkSubscription(String endpointID);
    public abstract void onFailSubscription(String endpointID);
}
