package com.example.denuncia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.denuncia.model.Denuncia;
import com.example.denuncia.prototype.DenunciaPrototype;
import com.example.denuncia.service.DenunciaService;

@RestController
@RequestMapping("/api/denuncias")
@Validated
public class DenunciaController {

    private final DenunciaService service;

    @Autowired
    public DenunciaController(DenunciaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Denuncia> listar() {
        return service.listarDenuncias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Denuncia> criar(@Valid @RequestBody Denuncia denuncia) {
        Denuncia clonedDenuncia = DenunciaPrototype.clone(denuncia);  // Clonando a denúncia antes de salvar
        Denuncia savedDenuncia = service.save(clonedDenuncia);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDenuncia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> atualizar(@PathVariable Long id, @Valid @RequestBody Denuncia denuncia) {
        Denuncia clonedDenuncia = DenunciaPrototype.clone(denuncia);  // Clonando para evitar alterações no original
        Denuncia updatedDenuncia = service.update(id, clonedDenuncia);
        return ResponseEntity.ok(updatedDenuncia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
