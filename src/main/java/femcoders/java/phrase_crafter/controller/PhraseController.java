package femcoders.java.phrase_crafter.controller;

import femcoders.java.phrase_crafter.dto.PhraseRequestDto;
import femcoders.java.phrase_crafter.model.Phrase;
import femcoders.java.phrase_crafter.service.PhraseService;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<?> createPhrase(@RequestBody PhraseRequestDto request){
        if (!request.isValid()) {
            return ResponseEntity.badRequest().body(request.getValidationErrors());
        }

        Phrase createdPhrase = phraseService.createPhrase(
                request.getText().trim(),
                request.getAuthorName().trim(),
                request.getCategory());
                return ResponseEntity.status(HttpStatus.CREATED).body(createdPhrase);
    }
}
