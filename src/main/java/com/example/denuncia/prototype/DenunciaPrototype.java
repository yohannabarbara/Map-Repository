package com.example.denuncia.prototype;

import com.example.denuncia.model.Denuncia;

public class DenunciaPrototype {

    public static Denuncia clone(Denuncia denuncia) {
        Denuncia novaDenuncia = new Denuncia();
        novaDenuncia.setTitulo(denuncia.getTitulo());
        novaDenuncia.setDescricao(denuncia.getDescricao());
        novaDenuncia.setLocalizacao(denuncia.getLocalizacao());
        novaDenuncia.setData(denuncia.getData());
        novaDenuncia.setGrauImportancia(denuncia.getGrauImportancia());
        return novaDenuncia;
    }
}
