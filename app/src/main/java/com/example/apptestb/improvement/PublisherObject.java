package com.example.apptestb.improvement;

import com.example.apptestb.enums.Comportamento;
import com.example.apptestb.enums.TipoPacote;
import com.example.apptestb.interfaces.Sender;

import java.util.ArrayList;

public abstract class PublisherObject extends PatternComunicationObject implements Sender{
    private ArrayList<String> endpointIDsSubscribed;

    public PublisherObject() {
        super();
        super.comportamento = Comportamento.PUBLISHER;
        endpointIDsSubscribed = new ArrayList<>();
    }

    public void addNovaSubscricao(String endpointID){
        if(!endpointIDsSubscribed.contains(endpointID)){
            endpointIDsSubscribed.add(endpointID);
        }
    }

    public void removeSubscricao(String endpointID){
        if(endpointIDsSubscribed.contains(endpointID)){
            endpointIDsSubscribed.remove(endpointID);
        }
    }

    @Override
    public void send(byte[] dados, String endPointID) {
        for(String endpoint : endpointIDsSubscribed){
            nearbyAccessObject.send(endpoint, dados, TipoPacote.CONTENT);
        }
    }
}
