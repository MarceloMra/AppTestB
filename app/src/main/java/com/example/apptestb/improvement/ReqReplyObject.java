package com.example.apptestb.improvement;

import com.example.apptestb.enums.Comportamento;
import com.example.apptestb.enums.TipoPacote;
import com.example.apptestb.interfaces.Receiver;
import com.example.apptestb.interfaces.Sender;

public abstract class ReqReplyObject extends PatternComunicationObject implements Receiver, Sender {

    public ReqReplyObject(Comportamento c) {
        super.comportamento = c;
    }

    @Override
    public void send(byte[] dados, String endPointID) {
        nearbyAccessObject.send(endPointID, dados, TipoPacote.CONTENT);
    }
}
