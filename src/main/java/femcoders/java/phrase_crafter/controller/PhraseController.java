package femcoders.java.phrase_crafter.controller;

import femcoders.java.phrase_crafter.model.Phrase;
import femcoders.java.phrase_crafter.service.PhraseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/phrases")
@CrossOrigin(origins = "*")
public class PhraseController {

    private PhraseService phraseService;

    public PhraseController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping
    public ResponseEntity<List<Phrase>> getAllPhrases() {
        List<Phrase> phrases = phraseService.getAllPhrases();
        return ResponseEntity.ok(phrases);
    }
}
