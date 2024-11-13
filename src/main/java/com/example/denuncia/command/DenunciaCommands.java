package com.example.denuncia.command;

import com.example.denuncia.model.Denuncia;
import com.example.denuncia.service.DenunciaService;

public class DenunciaCommands {
    public static Command saveCommand(DenunciaService service, Denuncia denuncia) {
        return () -> service.saveDenuncia(denuncia);
    }

    public static Command deleteCommand(DenunciaService service, Long id) {
        return () -> {
            service.deleteDenuncia(id);
        };
    }
}