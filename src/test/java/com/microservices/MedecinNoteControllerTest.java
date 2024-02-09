package com.microservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.microservices.controllers.MedecinNoteController;
import com.microservices.domain.MedecinNote;
import com.microservices.services.MedecinNoteService;

@SpringBootTest
public class MedecinNoteControllerTest {

    @Test
    public void testGetNotesByPatId() {
        // Créez un objet Mock du service
        MedecinNoteService medecinNoteService = mock(MedecinNoteService.class);

        // Créez un objet Controller avec le service Mock
        MedecinNoteController medecinNoteController = new MedecinNoteController(medecinNoteService);

        // Définissez le comportement du service Mock lors de l'appel à getNotesByPatId
        when(medecinNoteService.getNotesByPatId(1)).thenReturn(Arrays.asList(
                new MedecinNote("1", 1, "TestNone", "Le patient déclare qu'il 'se sent très bien'. Poids égal ou inférieur au poids recommandé"),
                new MedecinNote("2", 2, "TestBorderline", "Le patient déclare qu'il ressent beaucoup de stress au travail. Il se plaint également que son audition est anormale dernièrement")
        ));

        // Appelez la méthode du contrôleur pour le test
        List<MedecinNote> notes = medecinNoteController.getNotesByPatId(1);

        // Vérifiez le résultat attendu
        assertEquals(2, notes.size());
        assertEquals("Le patient déclare qu'il 'se sent très bien'. Poids égal ou inférieur au poids recommandé", notes.get(0).getNote());
        assertEquals("Le patient déclare qu'il ressent beaucoup de stress au travail. Il se plaint également que son audition est anormale dernièrement", notes.get(1).getNote());
    }
    
    @Test
    public void testAddMedecinNote() {
        // Créez un objet Mock du service
        MedecinNoteService medecinNoteService = mock(MedecinNoteService.class);

        // Créez un objet Controller avec le service Mock
        MedecinNoteController medecinNoteController = new MedecinNoteController(medecinNoteService);

        // Créez un objet MedecinNote à ajouter
        MedecinNote medecinNoteToAdd = new MedecinNote("1", 1, "TestAddNote", "This is a test note to add");

        // Définissez le comportement du service Mock lors de l'appel à addMedecinNote
        when(medecinNoteService.addMedecinNote(any(MedecinNote.class))).thenReturn(medecinNoteToAdd);

        // Appelez la méthode du contrôleur pour le test
        ResponseEntity<MedecinNote> response = medecinNoteController.addMedecinNote(medecinNoteToAdd, 1);

        // Vérifiez le résultat attendu
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medecinNoteToAdd, response.getBody());
    }
    
    @Test
    public void testAddMedecinNoteWithException() {
        // Créez un objet Mock du service
        MedecinNoteService medecinNoteService = mock(MedecinNoteService.class);

        // Créez un objet Controller avec le service Mock
        MedecinNoteController medecinNoteController = new MedecinNoteController(medecinNoteService);

        // Créez un objet MedecinNote à ajouter
        MedecinNote medecinNoteToAdd = new MedecinNote("1", 1, "TestAddNote", "This is a test note to add");

        // Définissez le comportement du service Mock lors de l'appel à addMedecinNote pour lancer une exception
        when(medecinNoteService.addMedecinNote(medecinNoteToAdd)).thenThrow(new RuntimeException());

        // Appelez la méthode du contrôleur pour le test
        ResponseEntity<MedecinNote> response = medecinNoteController.addMedecinNote(medecinNoteToAdd, 1);

        // Vérifiez le résultat attendu
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(null, response.getBody()); // Le corps de la réponse doit être null 
    }
}
