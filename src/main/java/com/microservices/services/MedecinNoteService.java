package com.microservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.domain.MedecinNote;
import com.microservices.repositories.MedecinNoteRepository;

import java.util.List;

@Service
public class MedecinNoteService {

    private final MedecinNoteRepository medecinNoteRepository;

    @Autowired
    public MedecinNoteService(MedecinNoteRepository medecinNoteRepository) {
        this.medecinNoteRepository = medecinNoteRepository;
    }

    public List<MedecinNote> getNotesByPatId(int patId) {
        return medecinNoteRepository.findByPatId(patId);
    }
}

