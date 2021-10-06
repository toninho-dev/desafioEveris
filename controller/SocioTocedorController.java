package com.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafio.exception.ResourceNotFoundException;
import com.desafio.model.SocioTorcedor;
import com.desafio.repository.SocioTorcedorRepository;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class SocioTocedorController {
	
    @Autowired
    SocioTorcedorRepository sociotorcedorRepository;

    @GetMapping("/sociotorcedor")
    public List<SocioTorcedor> getAllNotes() {
        return sociotorcedorRepository.findAll();
    }

    @PostMapping("/sociotorcedor")
    public SocioTorcedor createNote(@Valid @RequestBody SocioTorcedor note) {
        return sociotorcedorRepository.save(note);
    }

    @GetMapping("/sociotorcedor/{id}")
    public SocioTorcedor getNoteById(@PathVariable(value = "id") Long noteId) {
        return sociotorcedorRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/sociotorcedor/{id}")
    public SocioTorcedor updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody SocioTorcedor noteDetails) {

    	SocioTorcedor note = sociotorcedorRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setNome(noteDetails.getNome());
        note.setObservacao(noteDetails.getObservacao());

        SocioTorcedor updatedNote = sociotorcedorRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/sociotorcedor/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
    	SocioTorcedor note = sociotorcedorRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        sociotorcedorRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
