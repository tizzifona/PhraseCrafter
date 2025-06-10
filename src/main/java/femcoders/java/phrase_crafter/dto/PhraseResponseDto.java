package femcoders.java.phrase_crafter.dto;

import femcoders.java.phrase_crafter.model.Phrase;

public class PhraseResponseDto {
    private Long id;
    private String text;
    private String authorName;
    private String category;
    private String categoryDisplayName;

    public PhraseResponseDto() {
    }

    public PhraseResponseDto(Phrase phrase) {
        this.id = phrase.getId();
        this.text = phrase.getText();
        this.authorName = phrase.getAuthor().getName();
        this.category = phrase.getCategory().name();
        this.categoryDisplayName = phrase.getCategory().getDisplayName();
    }

    public static PhraseResponseDto from (Phrase phrase){
        return new PhraseResponseDto(phrase);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryDisplayName() {
        return categoryDisplayName;
    }

    public void setCategoryDisplayName(String categoryDisplayName) {
        this.categoryDisplayName = categoryDisplayName;
    }

    @Override
    public String toString() {
        return "PhraseResponseDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", authorName='" + authorName + '\'' +
                ", category='" + category + '\'' +
                ", categoryDisplayName='" + categoryDisplayName + '\'' +
                '}';
    }
}
