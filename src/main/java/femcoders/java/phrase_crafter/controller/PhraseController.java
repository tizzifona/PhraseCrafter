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
        return phrase.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPhrase(@RequestBody PhraseRequestDto request) {
        if (!request.isValid()) {
            return ResponseEntity.badRequest().body(request.getValidationErrors());
        }

        Phrase createdPhrase = phraseService.createPhrase(
                request.text().trim(),
                request.authorName().trim(),
                request.category());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPhrase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePhrase(@PathVariable Long id, @RequestBody PhraseRequestDto request) {
        if (!request.isValid()) {
            return ResponseEntity.badRequest().body(request.getValidationErrors());
        }
        Optional<Phrase> updatedPhrase = phraseService.updatePhrase(
                id,
                request.text().trim(),
                request.authorName().trim(),
                request.category());
        if (updatedPhrase.isPresent()) {
            return ResponseEntity.ok(updatedPhrase.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhrase(@PathVariable Long id) {
        boolean deleted = phraseService.deletePhrase(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Phrase>> getPhrasesByCategoryIgnoreCase(@PathVariable String categoryName) {
        List<Phrase> phrases = phraseService.getPhrasesByCategoryIgnoreCase(categoryName);
        return ResponseEntity.ok(phrases);
    }

    @GetMapping("/author/{authorName}")
    public ResponseEntity<List<Phrase>> getPhrasesByAuthor(@PathVariable String authorName) {
        List<Phrase> phrases = phraseService.getPhrasesByAuthor(authorName);
        return ResponseEntity.ok(phrases);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Phrase>> getPhrasesByText(@RequestParam String text) {
        if (text == null || text.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Phrase> phrases = phraseService.getPhrasesByText(text.trim());
        return ResponseEntity.ok(phrases);
    }
}
