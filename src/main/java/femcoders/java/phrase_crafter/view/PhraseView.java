package femcoders.java.phrase_crafter.view;

import femcoders.java.phrase_crafter.model.Category;
import femcoders.java.phrase_crafter.model.Phrase;
import femcoders.java.phrase_crafter.service.PhraseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class PhraseView implements CommandLineRunner {
    private final PhraseService phraseService;

    public PhraseView(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String...args) throws Exception {
        System.out.println("=================================");
        System.out.println("   WELCOME TO PHRASE CRAFTER");
        System.out.println("=================================");

        while (true) {
            showMainMenu();
            String choice = scanner.nextLine().trim();

            System.out.println();

            switch (choice) {
                case "1":
                    showAllPhrases();
                    break;
                case "2":
                    showPhraseById();
                    break;
                case "3":
                    addNewPhrase();
                    break;
                case "4":
                    updatePhrase();
                    break;
                case "5":
                    deletePhrase();
                    break;
                case "6":
                    showPhrasesByCategory();
                    break;
                case "7":
                    showPhrasesByAuthor();
                    break;
                case "8":
                    searchPhrasesByText();
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Show all phrases");
        System.out.println("2. Show phrase by ID");
        System.out.println("3. Add new phrase");
        System.out.println("4. Edit phrase");
        System.out.println("5. Delete phrase");
        System.out.println("6. Show phrases by category");
        System.out.println("7. Show phrases by author");
        System.out.println("8. Search phrases by text");
        System.out.println("0. Exit");

        System.out.print("Your choice: ");
    }

    private void showAllPhrases() {
        System.out.println("\n=== ALL PHRASES ===");
        List < Phrase> phrases = phraseService.getAllPhrases();

        if (phrases.isEmpty()){
            System.out.println("No phrases saved.");
            return;
        }
        for (Phrase phrase : phrases){
            printPhrase(phrase);
        }
        System.out.println("Total phrases: " + phrases.size());
    }

    private void showPhraseById(){
        System.out.println("\n=== SHOW PHRASE BY ID ===");
        System.out.print("Enter phrase ID: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            Optional <Phrase> phrase = phraseService.getPhraseById(id);

            if (phrase.isPresent()) {
                printPhrase(phrase.get());
            }
            else {
                System.out.println("Phrase with ID " + id + " not found.");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    private void addNewPhrase() {
        System.out.println("\n=== ADD NEW PHRASE ===");
        System.out.print("Enter phrase text: ");
        String text = scanner.nextLine().trim();

        if (text.isEmpty()) {
            System.out.println("Text cannot be empty!");
            return;
        }

        System.out.println("Enter author name: ");
        String authorName = scanner.nextLine().trim();

        if (authorName.isEmpty()) {
            System.out.println("Author name cannot be empty!");
            return;
        }

        System.out.println("Choose category:");
        System.out.println("1. Inspirational");
        System.out.println("2. Jokes");
        System.out.println("3. Lorem Ipsum");
        System.out.print("Your choice: ");

        String choice = scanner.nextLine().trim();
        Category category;

        switch (choice) {
            case "1":
                category = Category.INSPIRATIONAL;
                break;
            case "2":
                category = Category.JOKES;
                break;
            case "3":
                category = Category.LOREM_IPSUM;
                break;
            default:
                System.out.println("Invalid category choice!");
                return;
        }

        try {
            Phrase newPhrase = phraseService.createPhrase(text, authorName, category);
            System.out.println("✅ Phrase successfully added!");
            printPhrase(newPhrase);
        }
        catch (Exception e) {
            System.out.println("❌ Error adding phrase: " + e.getMessage());
        }
    }

    private void updatePhrase() {
        System.out.println("\n=== EDIT PHRASE ===");
        System.out.print("Enter phrase ID to edit: ");

        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            Optional <Phrase> existingPhrase = phraseService.getPhraseById(id);

            if (existingPhrase.isEmpty()) {
                System.out.println("Phrase with ID " + id + " not found.");
                return;
            }

            System.out.println("Current phrase:");
            printPhrase(existingPhrase.get());

            System.out.print("Enter new phrase text: ");
            String text = scanner.nextLine().trim();

            System.out.print("Enter new author name: ");
            String authorName = scanner.nextLine().trim();

            System.out.println("Choose new category:");
            System.out.println("1. Inspirational");
            System.out.println("2. Jokes");
            System.out.println("3. Lorem Ipsum");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine().trim();
            Category category = null;

            switch (choice) {
                case "1":
                    category = Category.INSPIRATIONAL;
                    break;
                case "2":
                    category = Category.JOKES;
                    break;
                case "3":
                    category = Category.LOREM_IPSUM;
                    break;
                default:
                    System.out.println("Invalid category choice.");
                    return;
            }

            Optional <Phrase> updatedPhrase = phraseService.updatePhrase(id, text, authorName, category);
            if (updatedPhrase.isPresent()) {
                System.out.println("✅ Phrase successfully updated!");
                printPhrase(updatedPhrase.get());
            }
            else {
                System.out.println("❌ Error updating phrase.");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    private void deletePhrase() {
        System.out.println("\n=== DELETE PHRASE ===");
        System.out.print("Enter phrase ID to delete: ");

        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            Optional <Phrase> phrase = phraseService.getPhraseById(id);

            if (phrase.isEmpty()) {
                System.out.println("Phrase with ID " + id + " not found.");
                return;
            }
            System.out.println("Phrase to delete:");
            printPhrase(phrase.get());

            System.out.print("Are you sure you want to delete this phrase? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("yes") || confirmation.equals("y")) {
                boolean deleted = phraseService.deletePhrase(id);
                if (deleted) {
                    System.out.println("✅ Phrase successfully deleted!");
                }
                else {
                    System.out.println("❌ Error deleting phrase.");
                }
            }
            else {
                System.out.println("Deletion cancelled.");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    private void showPhrasesByCategory() {
        System.out.println("\n=== PHRASES BY CATEGORY ===");
        System.out.println("Choose category:");
        System.out.println("1. Inspirational (INSPIRATIONAL)");
        System.out.println("2. Jokes (JOKES)");
        System.out.println("3. Lorem Ipsum (LOREM_IPSUM)");
        System.out.print("Your choice: ");

        String choice = scanner.nextLine().trim();
        Category category = null;

        switch (choice) {
            case "1":
                category = Category.INSPIRATIONAL;
                break;
            case "2":
                category = Category.JOKES;
                break;
            case "3":
                category = Category.LOREM_IPSUM;
                break;
            default:System.out.println("Invalid category choice.");
            return;
        }
        List <Phrase> phrases = phraseService.getPhrasesByCategoryIgnoreCase(String.valueOf(category));

        if (phrases.isEmpty()) {
            System.out.println("No phrases in category: " + category.getDisplayName());
            return;
        }

        System.out.println("\nPhrases in category: " + category.getDisplayName());
        for (Phrase phrase : phrases) {
            printPhrase(phrase);
        }
        System.out.println("Found phrases: " + phrases.size());
    }

    private void showPhrasesByAuthor() {
        System.out.println("\n=== PHRASES BY AUTHOR ===");
        System.out.print("Enter search text: ");

        String searchAuthor = scanner.nextLine().trim();

        if (searchAuthor.isEmpty()){
            System.out.println("Name of author cannot be empty!");
            return;
        }
        List <Phrase> phrases = phraseService.getPhrasesByAuthor(searchAuthor);

        if (phrases.isEmpty()) {
            System.out.println("No phrases found with author \"" + searchAuthor + "\".");
            return;
        }
        System.out.println("Found phrases: ");
        for (Phrase phrase : phrases) {
            printPhrase(phrase);
        }
        System.out.println("Found phrases: " + phrases.size());
    }

    private void searchPhrasesByText() {
        System.out.println("\n=== SEARCH PHRASES ===");
        System.out.print("Enter search text: ");

        String searchText = scanner.nextLine().trim();

        if (searchText.isEmpty()) {
            System.out.println("Search text cannot be empty!");
            return;
        }
        List <Phrase> phrases = phraseService.getPhrasesByText(searchText);

        if (phrases.isEmpty()) {
            System.out.println("No phrases found with text \"" + searchText + "\".");
            return;
        }

        System.out.println("Found phrases: ");
        for (Phrase phrase : phrases) {
            printPhrase(phrase);
        }
        System.out.println("Found phrases: " + phrases.size());
    }

    private void printPhrase(Phrase phrase) {
        System.out.println("─────────────────────────────");
        System.out.println("ID: " + phrase.getId());
        System.out.println("Text: " + phrase.getText());
        System.out.println("Author: " + phrase.getAuthor().getName());
        System.out.println("Category: " + phrase.getCategory().getDisplayName());
        System.out.println("─────────────────────────────");
    }
}
