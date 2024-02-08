package com.microservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.domain.MedecinNote;
import com.microservices.services.MedecinNoteService;
import java.util.List;

@RestController
public class MedecinNoteController {

	private final MedecinNoteService medecinNoteService;

	@Autowired
	public MedecinNoteController(MedecinNoteService medecinNoteService) {
		this.medecinNoteService = medecinNoteService;
	}

	@GetMapping("/medecin/notes/{patId}")
	public List<MedecinNote> getNotesByPatId(@PathVariable int patId) {
		return medecinNoteService.getNotesByPatId(patId);
	}
	
	@PostMapping("/medecin/notes/{patId}")
	public ResponseEntity<MedecinNote> addMedecinNote(@RequestBody MedecinNote medecinNote, @PathVariable int patId) {
	    try {
	        medecinNote.setPatId(patId);
	        MedecinNote addedNote = medecinNoteService.addMedecinNote(medecinNote);
	        return ResponseEntity.ok(addedNote);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
}
