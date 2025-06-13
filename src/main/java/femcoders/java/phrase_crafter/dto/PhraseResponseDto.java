package femcoders.java.phrase_crafter.dto;

import femcoders.java.phrase_crafter.model.Phrase;

public record PhraseResponseDto(
    Long id,
    String text,
    String authorName,
    String category,
    String categoryDisplayName
) {
    public static PhraseResponseDto from(Phrase phrase) {
        return new PhraseResponseDto(
            phrase.getId(),
            phrase.getText(),
            phrase.getAuthor().getName(),
            phrase.getCategory().name(),
            phrase.getCategory().getDisplayName()
        );
    }
}
