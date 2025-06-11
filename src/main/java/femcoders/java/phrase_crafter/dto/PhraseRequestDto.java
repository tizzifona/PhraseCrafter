package femcoders.java.phrase_crafter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import femcoders.java.phrase_crafter.model.Category;

public class PhraseRequestDto {
    private String text;
    @JsonProperty("author")
    private String authorName;
    private Category category;

    public PhraseRequestDto() {
    }

    public PhraseRequestDto(String text, String authorName, Category category) {
        this.text = text;
        this.authorName = authorName;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isValid() {
        return text != null && !text.trim().isEmpty() && authorName != null && !authorName.trim().isEmpty() && category != null;
    }

    public String getValidationErrors() {
        StringBuilder errors = new StringBuilder();

        if (text == null || text.trim().isEmpty()){
            errors.append("Text cannot be empty!");
        }

        if (authorName == null || authorName.trim().isEmpty()){
            errors.append("Author name cannot be empty!");
        }

        if (category == null){
            errors.append("Category must be specified!");
        }

            return !errors.isEmpty() ? errors.toString().trim() : null;
    }

    @Override
    public String toString() {
        return "PhraseRequestDto{" +
                "text='" + text + '\'' +
                ", authorName='" + authorName + '\'' +
                ", category=" + category +
                '}';
    }
}
