package femcoders.java.phrase_crafter.repository;

import femcoders.java.phrase_crafter.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
