package femcoders.java.phrase_crafter.repository;

import femcoders.java.phrase_crafter.model.Category;
import femcoders.java.phrase_crafter.model.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, Long> {
    @Query("SELECT p FROM Phrase p WHERE UPPER(p.category) = UPPER(:categoryName)")
    List<Phrase> findByCategoryIgnoreCase(@Param("categoryName") String categoryName);

    List<Phrase> findByAuthor_NameContainingIgnoreCase(String authorName);

    List<Phrase> findByTextContainingIgnoreCase(String text);
}
