package com.microservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.microservices.domain.MedecinNote;
import com.microservices.repositories.MedecinNoteRepository;
import com.microservices.services.MedecinNoteService;

@SpringBootTest
public class MedecinNoteServiceTest {

    @Test
    public void testGetNotesByPatId() {
        // Créez un objet Mock du repository
        MedecinNoteRepository medecinNoteRepository = mock(MedecinNoteRepository.class);

        // Créez un objet Service avec le repository Mock
        MedecinNoteService medecinNoteService = new MedecinNoteService(medecinNoteRepository);

        // Définissez le comportement du repository Mock lors de l'appel à findByPatId
        when(medecinNoteRepository.findByPatId(1)).thenReturn(Arrays.asList(
                new MedecinNote("1", 1, "TestNone", "Le patient déclare qu'il 'se sent très bien'. Poids égal ou inférieur au poids recommandé"),
                new MedecinNote("2", 2, "TestBorderline", "Le patient déclare qu'il ressent beaucoup de stress au travail. Il se plaint également que son audition est anormale dernièrement"),
                new MedecinNote("3", 2, "TestBorderline", "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois. Il remarque également que son audition continue d'être anormale")        ));

        // Appelez la méthode du service que vous souhaitez tester
        List<MedecinNote> notes = medecinNoteService.getNotesByPatId(1);

        // Vérifiez le résultat attendu
        assertEquals(3, notes.size());
        assertEquals("Le patient déclare qu'il 'se sent très bien'. Poids égal ou inférieur au poids recommandé", notes.get(0).getNote());
        assertEquals("Le patient déclare qu'il ressent beaucoup de stress au travail. Il se plaint également que son audition est anormale dernièrement", notes.get(1).getNote());
        assertEquals("Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois. Il remarque également que son audition continue d'être anormale", notes.get(2).getNote());
    }
}