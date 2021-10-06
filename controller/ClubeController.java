package com.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafio.exception.ResourceNotFoundException;
import com.desafio.model.Clube;
import com.desafio.repository.ClubeRepository;
import java.util.List;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class ClubeController {
	
    @Autowired
    ClubeRepository clubeRepository;

    @GetMapping("/clube")
    public List<Clube> getAllClubes() {
		return clubeRepository.findAll();
    }
    
    @PostMapping("/clube")
    public Clube createClube(@Valid @RequestBody Clube clube) {
        return clubeRepository.save(clube);
    }
    @DeleteMapping("/clube/{id}")
    public ResponseEntity<?> deleteclube(@PathVariable(value = "id") Long clubeId) {
    	Clube clube = clubeRepository.findById(clubeId)
                .orElseThrow(() -> new ResourceNotFoundException("Clube", "id", clubeId));

        clubeRepository.delete(clube);

        return ResponseEntity.ok().build();
    }
    @PutMapping("/clube/{id}")
    public Clube updateClube(@PathVariable(value = "id") Long clubeId,
                                           @Valid @RequestBody Clube clubeDetails) {

    	Clube clube = clubeRepository.findById(clubeId)
                .orElseThrow(() -> new ResourceNotFoundException("Clube", "id", clubeId));

        clube.setNome(clubeDetails.getNome());
        clube.setObservacao(clubeDetails.getObservacao());

        Clube updatedNote = clubeRepository.save(clube);
        return updatedNote;
    }
}

