package femcoders.java.phrase_crafter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import femcoders.java.phrase_crafter.model.Category;

public record PhraseRequestDto(
    String text,
    @JsonProperty("author") String authorName,
    Category category
) {
    public boolean isValid() {
        return text != null && !text.trim().isEmpty() && 
               authorName != null && !authorName.trim().isEmpty() && 
               category != null;
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
}
