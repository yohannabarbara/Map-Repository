package com.example.denuncia.decorator;

import com.example.denuncia.model.Denuncia;

public class ErrorHandlingDecorator extends DenunciaDecorator {

    @Override
    public Denuncia enhance(Denuncia denuncia) {
        if (denuncia.getTitulo() == null || denuncia.getTitulo().isEmpty()) {
            throw new RuntimeException("O título da denúncia é obrigatório!");
        }
        if (denuncia.getDescricao() == null || denuncia.getDescricao().isEmpty()) {
            throw new RuntimeException("A descrição da denúncia é obrigatória!");
        }
        if (denuncia.getLocalizacao() == null || denuncia.getLocalizacao().isEmpty()) {
            throw new RuntimeException("A localização da denúncia é obrigatória!");
        }
        // Verifique o grau de importância
        if (denuncia.getGrauImportancia() == null || denuncia.getGrauImportancia().isEmpty()) {
            throw new RuntimeException("O grau de importância da denúncia é obrigatório!");
        }
        return denuncia;
    }
}
