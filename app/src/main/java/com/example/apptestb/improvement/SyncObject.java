package com.example.apptestb.improvement;

import com.example.apptestb.enums.Comportamento;
import com.example.apptestb.interfaces.RecebedorDeConclusoes;
import com.example.apptestb.interfaces.Receiver;

public abstract class SyncObject extends PatternComunicationObject implements Receiver, RecebedorDeConclusoes {

    public SyncObject() {
        super();
        super.comportamento = Comportamento.SYNCR;
    }
}
