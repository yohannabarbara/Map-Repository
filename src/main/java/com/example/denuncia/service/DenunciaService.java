package com.example.denuncia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.denuncia.command.Command;
import com.example.denuncia.command.DenunciaCommands;
import com.example.denuncia.decorator.DenunciaDecorator;
import com.example.denuncia.decorator.ErrorHandlingDecorator;
import com.example.denuncia.model.Denuncia;
import com.example.denuncia.repository.DenunciaRepository;

@Service
public class DenunciaService {

    private final DenunciaRepository denunciaRepository;
    private final DenunciaDecorator decorator;

    @Autowired
    public DenunciaService(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
        this.decorator = new ErrorHandlingDecorator();  // Usando o Validator como Decorator
    }

    public Denuncia criarDenuncia(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

    public Denuncia atualizarDenuncia(Long id, Denuncia denunciaAtualizada) {
        return denunciaRepository.findById(id)
            .map(denuncia -> {
                denuncia.setDescricao(denunciaAtualizada.getDescricao());
                denuncia.setLocalizacao(denunciaAtualizada.getLocalizacao());
                return denunciaRepository.save(denuncia);
            })
            .orElseThrow(() -> new RuntimeException("Denúncia não encontrada com id " + id));
    }

    public List<Denuncia> listarDenuncias() {
        return denunciaRepository.findAll();
    }

    public Denuncia findById(Long id) {
        return denunciaRepository.findById(id).orElseThrow(() -> new RuntimeException("Denúncia não encontrada"));
    }

    public Denuncia save(Denuncia denuncia) {
        Denuncia enhancedDenuncia = decorator.enhance(denuncia);  // Decorando a denúncia
        return denunciaRepository.save(enhancedDenuncia);
    }

    public Denuncia update(Long id, Denuncia denunciaAtualizada) {
        Denuncia denuncia = findById(id);
        denuncia.setTitulo(denunciaAtualizada.getTitulo());
        denuncia.setDescricao(denunciaAtualizada.getDescricao());
        denuncia.setLocalizacao(denunciaAtualizada.getLocalizacao());
        denuncia.setData(denunciaAtualizada.getData());
        denuncia.setGrauImportancia(denunciaAtualizada.getGrauImportancia());
        return denunciaRepository.save(denuncia);
    }

    public void delete(Long id) {
        Command deleteCommand = DenunciaCommands.deleteCommand(this, id);
        deleteCommand.execute();
    }

    public void deleteDenuncia(Long id) {
        if (denunciaRepository.existsById(id)) {
            denunciaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Denúncia não encontrada com id " + id);
        }
    }
}
