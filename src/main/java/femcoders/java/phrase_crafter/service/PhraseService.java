package femcoders.java.phrase_crafter.service;

import femcoders.java.phrase_crafter.model.Author;
import femcoders.java.phrase_crafter.model.Category;
import femcoders.java.phrase_crafter.model.Phrase;
import femcoders.java.phrase_crafter.repository.AuthorRepository;
import femcoders.java.phrase_crafter.repository.PhraseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Phrase> getPhraseById(Long id) {
        return phraseRepository.findById(id);
    }

    public boolean phraseTextExists(String text) {
        return phraseRepository.existsByTextIgnoreCase(text);
    }

    public Phrase createPhrase(String text, String authorName, Category category) {
        if (phraseTextExists(text)) {
            throw new IllegalArgumentException(
                    "A phrase with this text already exists. Please try a different phrase.");
        }

        Author author = authorRepository.findByName(authorName)
                .orElseGet(() -> authorRepository.save(new Author(authorName)));

        Phrase phrase = new Phrase(text, author, category);
        return phraseRepository.save(phrase);
    }

    public Optional<Phrase> updatePhrase(Long id, String text, String authorName, Category category) {
        return phraseRepository.findById(id)
                .map(
                        phrase -> {
                            phrase.setText(text);
                            phrase.setCategory(category);

                            if (!phrase.getAuthor().getName().equals(authorName)) {
                                Author author = authorRepository.findByName(authorName)
                                        .orElseGet(() -> authorRepository.save(new Author(authorName)));
                                phrase.setAuthor(author);
                            }
                            return phraseRepository.save(phrase);
                        });
    }

    public boolean deletePhrase(Long id) {
        if (phraseRepository.existsById(id)) {
            phraseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Phrase> getPhrasesByCategoryIgnoreCase(String categoryName) {
        return phraseRepository.findByCategoryIgnoreCase(categoryName);
    }

    public List<Phrase> getPhrasesByAuthor(String authorName) {
        return phraseRepository.findByAuthor_NameContainingIgnoreCase(authorName);
    }

    public List<Phrase> getPhrasesByText(String searchText) {
        return phraseRepository.findByTextContainingIgnoreCase(searchText);
    }
}
