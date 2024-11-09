package com.example.denuncia.service;

import com.example.denuncia.command.Command;
import com.example.denuncia.model.Denuncia;

public class DenunciaCommandExecutor {

    private final DenunciaService service;

    public DenunciaCommandExecutor(DenunciaService service) {
        this.service = service;
    }

    public void executarComando(Command comando) {
        comando.execute();
    }

    public void listarDenuncias() {
        service.listarDenuncias();
    }

    public void criarDenuncia(Denuncia denuncia) {
        service.criarDenuncia(denuncia);
    }
}
