package com.microservices;

import org.junit.jupiter.api.Test;

import com.microservices.domain.MedecinNote;

import static org.assertj.core.api.Assertions.assertThat;

public class MedecinNoteTest {

    @Test
    public void testMedecinNoteCreation() {
        // Créez une instance de la classe MedecinNote
        MedecinNote medecinNote = new MedecinNote("1", 1, "TestPatient", "TestNote");

        // Vérifiez si les valeurs sont correctes
        assertThat(medecinNote.getId()).isEqualTo("1");
        assertThat(medecinNote.getPatId()).isEqualTo(1);
        assertThat(medecinNote.getPatient()).isEqualTo("TestPatient");
        assertThat(medecinNote.getNote()).isEqualTo("TestNote");
    }

    @Test
    public void testMedecinNoteDefaultConstructor() {
        // Créez une instance en utilisant le constructeur par défaut
        MedecinNote medecinNote = new MedecinNote();

        // Vérifiez si l'instance n'est pas nulle
        assertThat(medecinNote).isNotNull();
    }
    
    @Test
    public void testSetters() {
        // Créez une instance de la classe MedecinNote
        MedecinNote medecinNote = new MedecinNote();

        // Utilisez les setters pour définir les valeurs
        medecinNote.setId("1");
        medecinNote.setPatId(1);
        medecinNote.setPatient("TestPatient");
        medecinNote.setNote("TestNote");

        // Vérifiez si les valeurs ont été correctement définies
        assertThat(medecinNote.getId()).isEqualTo("1");
        assertThat(medecinNote.getPatId()).isEqualTo(1);
        assertThat(medecinNote.getPatient()).isEqualTo("TestPatient");
        assertThat(medecinNote.getNote()).isEqualTo("TestNote");
    }
}
