package com.example.apptestb.interfaces;

import com.example.apptestb.model.Mensagem;

public interface RecebedorMensagens {
    public void receberMensagem(Mensagem mensagem);
    public void receberEndpointID(String endpointID);
}
