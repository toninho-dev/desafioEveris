package com.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafio.exception.ResourceNotFoundException;
import com.desafio.model.Campanha;
import com.desafio.repository.CampanhaRepository;
import java.util.List;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class CampanhaController {
	
    @Autowired
    CampanhaRepository campanhaRepository;

    @GetMapping("/campanha")
    public List<Campanha> getAllCampanha() {
		return campanhaRepository.findAll();
    }
    
    @PostMapping("/campanha")
    public Campanha createCampanha(@Valid @RequestBody Campanha campanha) {
        return campanhaRepository.save(campanha);
    }
    
    @PutMapping("/campanha/{id}")
    public Campanha updateCampanha(@PathVariable(value = "id") Long campanhaId,
       @Valid @RequestBody Campanha campanhaDetails) {

    	Campanha campanha = campanhaRepository.findById(campanhaId)
                .orElseThrow(() -> new ResourceNotFoundException("Campanha", "id", campanhaId));

        campanha.setNome(campanhaDetails.getNome());
        campanha.setObservacao(campanhaDetails.getObservacao());

        Campanha updatedCampanha = campanhaRepository.save(campanha);
        return updatedCampanha;
    }
    @DeleteMapping("/campanha/{id}")
    public ResponseEntity<?> deletecampanha(@PathVariable(value = "id") Long campanhaId) {
    	Campanha campanha = campanhaRepository.findById(campanhaId)
                .orElseThrow(() -> new ResourceNotFoundException("Campanha", "id", campanhaId));

        campanhaRepository.delete(campanha);

        return ResponseEntity.ok().build();
    }

}
