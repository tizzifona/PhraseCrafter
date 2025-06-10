package femcoders.java.phrase_crafter.controller;

import femcoders.java.phrase_crafter.model.Phrase;
import femcoders.java.phrase_crafter.service.PhraseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/phrases")
@CrossOrigin(origins = "*")
public class PhraseController {

    private final PhraseService phraseService;

    public PhraseController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping
    public ResponseEntity<List<Phrase>> getAllPhrases() {
        List<Phrase> phrases = phraseService.getAllPhrases();
        return ResponseEntity.ok(phrases);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Phrase> getPhraseById(@PathVariable Long id) {
        Optional<Phrase> phrase = phraseService.getPhraseById(id);
        if (phrase.isPresent()) {
            return ResponseEntity.ok(phrase.get());
        }
        else {
           return ResponseEntity.notFound().build();
        }
    }
}
