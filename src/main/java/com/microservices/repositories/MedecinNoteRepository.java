package com.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservices.domain.MedecinNote;

import java.util.List;

public interface MedecinNoteRepository extends MongoRepository<MedecinNote, String> {

    List<MedecinNote> findByPatId(int patId);
}
