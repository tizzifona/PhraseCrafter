package femcoders.java.phrase_crafter.service;

import femcoders.java.phrase_crafter.model.Phrase;
import femcoders.java.phrase_crafter.repository.AuthorRepository;
import femcoders.java.phrase_crafter.repository.PhraseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhraseService {
    private final PhraseRepository phraseRepository;
    private final AuthorRepository authorRepository;

    public PhraseService(PhraseRepository phraseRepository, AuthorRepository authorRepository) {
        this.phraseRepository = phraseRepository;
        this.authorRepository = authorRepository;
    }

    public List<Phrase> getAllPhrases() {
        return phraseRepository.findAll();
    }
}
