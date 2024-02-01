package com.microservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
