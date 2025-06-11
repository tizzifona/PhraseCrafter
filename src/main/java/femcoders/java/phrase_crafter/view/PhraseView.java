package femcoders.java.phrase_crafter.view;

import femcoders.java.phrase_crafter.model.Category;
import femcoders.java.phrase_crafter.model.Phrase;
import femcoders.java.phrase_crafter.service.PhraseService;
import femcoders.java.phrase_crafter.util.ColorConstants;
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
    public void run(String... args) throws Exception {
        System.out.println(ColorConstants.MENU_COLOR + "=================================" + ColorConstants.RESET);
        System.out.println(ColorConstants.MENU_COLOR + "  🎨 WELCOME TO PHRASE CRAFTER 📝" + ColorConstants.RESET);
        System.out.println(ColorConstants.MENU_COLOR + "=================================" + ColorConstants.RESET);

        while (true) {
            showMainMenu();
            System.out.print(ColorConstants.OPTION_COLOR + "Your choice: " + ColorConstants.RESET);
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
                    System.out.println(ColorConstants.MENU_COLOR + "👋 Goodbye! Thanks for using Phrase Crafter! 🌟"
                            + ColorConstants.RESET);
                    return;
                default:
                    System.out.println(
                            ColorConstants.ERROR_COLOR + "❌ Invalid choice. Please try again." + ColorConstants.RESET);
            }
        }
    }

    private void showMainMenu() {
        System.out.println("\n" + ColorConstants.MENU_COLOR + "=== 📋 MAIN MENU ===" + ColorConstants.RESET);
        System.out.println("1. 📖 Show all phrases");
        System.out.println("2. 🔍 Show phrase by ID");
        System.out.println("3. ➕ Add new phrase");
        System.out.println("4. ✏️ Edit phrase");
        System.out.println("5. 🗑️ Delete phrase");
        System.out.println("6. 🏷️ Show phrases by category");
        System.out.println("7. 👤 Show phrases by author");
        System.out.println("8. 🔎 Search phrases by text");
        System.out.println("0. 🚪 Exit");
        System.out.println();
    }

    private void showAllPhrases() {
        System.out.println("\n" + ColorConstants.MENU_COLOR + "=== 📖 ALL PHRASES ===" + ColorConstants.RESET);
        List<Phrase> phrases = phraseService.getAllPhrases();

        if (phrases.isEmpty()) {
            System.out.println(ColorConstants.ERROR_COLOR + "📭 No phrases saved." + ColorConstants.RESET);
            return;
        }
        for (Phrase phrase : phrases) {
            printPhrase(phrase);
        }
        System.out.println(
                ColorConstants.SUCCESS_COLOR + "Total phrases: " + phrases.size() + " 📊" + ColorConstants.RESET);
    }

    private void showPhraseById() {
        System.out.println("\n" + ColorConstants.MENU_COLOR + "=== 🔍 SHOW PHRASE BY ID ===" + ColorConstants.RESET);
        System.out.print(ColorConstants.OPTION_COLOR + "Enter phrase ID: " + ColorConstants.RESET);
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            Optional<Phrase> phrase = phraseService.getPhraseById(id);

            if (phrase.isPresent()) {
                printPhrase(phrase.get());
            } else {
                System.out.println(
                        ColorConstants.ERROR_COLOR + "❌ Phrase with ID " + id + " not found." + ColorConstants.RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ColorConstants.ERROR_COLOR + "❌ Invalid ID format!" + ColorConstants.RESET);
        }
    }

    private void addNewPhrase() {
        System.out.println("\n" + ColorConstants.MENU_COLOR + "=== ➕ ADD NEW PHRASE ===" + ColorConstants.RESET);
        System.out.print(ColorConstants.OPTION_COLOR + "Enter phrase text: " + ColorConstants.RESET);
        String text = scanner.nextLine().trim();

        if (text.isEmpty()) {
            System.out.println(ColorConstants.ERROR_COLOR + "❌ Text cannot be empty!" + ColorConstants.RESET);
            return;
        }

        System.out.print(ColorConstants.OPTION_COLOR + "Enter author name: " + ColorConstants.RESET);
        String authorName = scanner.nextLine().trim();

        if (authorName.isEmpty()) {
            System.out.println(ColorConstants.ERROR_COLOR + "❌ Author name cannot be empty!" + ColorConstants.RESET);
            return;
        }

        System.out.println("Choose category:");
        System.out.println("1. ✨ Inspirational");
        System.out.println("2. 😄 Jokes");
        System.out.println("3. 📝 Lorem Ipsum");
        System.out.print(ColorConstants.OPTION_COLOR + "Your choice: " + ColorConstants.RESET);

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
                System.out.println(ColorConstants.ERROR_COLOR + "❌ Invalid category choice!" + ColorConstants.RESET);
                return;
        }

        try {
            Phrase newPhrase = phraseService.createPhrase(text, authorName, category);
            System.out.println(ColorConstants.SUCCESS_COLOR + "✅ Phrase successfully added! 🎉" + ColorConstants.RESET);
            printPhrase(newPhrase);
        } catch (Exception e) {
            System.out.println(
                    ColorConstants.ERROR_COLOR + "❌ Error adding phrase: " + e.getMessage() + ColorConstants.RESET);
        }
    }

    private void updatePhrase() {
        System.out.println("\n" + ColorConstants.MENU_COLOR + "=== ✏️ EDIT PHRASE ===" + ColorConstants.RESET);
        System.out.print(ColorConstants.OPTION_COLOR + "Enter phrase ID to edit: " + ColorConstants.RESET);

        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            Optional<Phrase> existingPhrase = phraseService.getPhraseById(id);

            if (existingPhrase.isEmpty()) {
                System.out.println(
                        ColorConstants.ERROR_COLOR + "❌ Phrase with ID " + id + " not found." + ColorConstants.RESET);
                return;
            }

            Phrase phrase = existingPhrase.get();

            System.out.println("Current phrase:");
            printPhrase(phrase);

            System.out.println(ColorConstants.MENU_COLOR + "ℹ️ Tip: Press Enter to keep current value unchanged"
                    + ColorConstants.RESET);

            System.out.print(ColorConstants.OPTION_COLOR + "Enter new phrase text (or press Enter to keep current): "
                    + ColorConstants.RESET);
            String text = scanner.nextLine().trim();
            if (text.isEmpty()) {
                text = phrase.getText();
            }

            System.out.print(ColorConstants.OPTION_COLOR + "Enter new author name (or press Enter to keep current): "
                    + ColorConstants.RESET);
            String authorName = scanner.nextLine().trim();
            if (authorName.isEmpty()) {
                authorName = phrase.getAuthor().getName();
            }

            System.out.println("Choose new category (or press Enter to keep current):");
            System.out.println("1. ✨ Inspirational");
            System.out.println("2. 😄 Jokes");
            System.out.println("3. 📝 Lorem Ipsum");
            System.out.print(ColorConstants.OPTION_COLOR + "Your choice: " + ColorConstants.RESET);

            String choice = scanner.nextLine().trim();
            Category category = phrase.getCategory();

            if (!choice.isEmpty()) {
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
                        System.out.println(
                                ColorConstants.ERROR_COLOR + "❌ Invalid category choice." + ColorConstants.RESET);
                        return;
                }
            }

            Optional<Phrase> updatedPhrase = phraseService.updatePhrase(id, text, authorName, category);
            if (updatedPhrase.isPresent()) {
                System.out.println(
                        ColorConstants.SUCCESS_COLOR + "✅ Phrase successfully updated! 🎉" + ColorConstants.RESET);
                printPhrase(updatedPhrase.get());
            } else {
                System.out.println(ColorConstants.ERROR_COLOR + "❌ Error updating phrase." + ColorConstants.RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ColorConstants.ERROR_COLOR + "❌ Invalid ID format!" + ColorConstants.RESET);
        }
    }

    private void deletePhrase() {
        System.out.println("\n" + ColorConstants.MENU_COLOR + "=== 🗑️ DELETE PHRASE ===" + ColorConstants.RESET);
        System.out.print(ColorConstants.OPTION_COLOR + "Enter phrase ID to delete: " + ColorConstants.RESET);

        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            Optional<Phrase> phrase = phraseService.getPhraseById(id);

            if (phrase.isEmpty()) {
                System.out.println(
                        ColorConstants.ERROR_COLOR + "❌ Phrase with ID " + id + " not found." + ColorConstants.RESET);
                return;
            }
            System.out.println("Phrase to delete:");
            printPhrase(phrase.get());

            System.out.print(ColorConstants.OPTION_COLOR + "Are you sure you want to delete this phrase? (yes/no): "
                    + ColorConstants.RESET);
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("yes") || confirmation.equals("y")) {
                boolean deleted = phraseService.deletePhrase(id);
                if (deleted) {
                    System.out.println(
                            ColorConstants.SUCCESS_COLOR + "✅ Phrase successfully deleted! 🗑️" + ColorConstants.RESET);
                } else {
                    System.out.println(ColorConstants.ERROR_COLOR + "❌ Error deleting phrase." + ColorConstants.RESET);
                }
            } else {
                System.out.println(ColorConstants.MENU_COLOR + "⏹️ Deletion cancelled." + ColorConstants.RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(ColorConstants.ERROR_COLOR + "❌ Invalid ID format!" + ColorConstants.RESET);
        }
    }

    private void showPhrasesByCategory() {
        System.out.println("\n" + ColorConstants.MENU_COLOR + "=== 🏷️ PHRASES BY CATEGORY ===" + ColorConstants.RESET);
        System.out.println("Choose category:");
        System.out.println("1. ✨ Inspirational (INSPIRATIONAL)");
        System.out.println("2. 😄 Jokes (JOKES)");
        System.out.println("3. 📝 Lorem Ipsum (LOREM_IPSUM)");
        System.out.print(ColorConstants.OPTION_COLOR + "Your choice: " + ColorConstants.RESET);

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
                System.out.println(ColorConstants.ERROR_COLOR + "❌ Invalid category choice." + ColorConstants.RESET);
                return;
        }
        List<Phrase> phrases = phraseService.getPhrasesByCategoryIgnoreCase(String.valueOf(category));

        if (phrases.isEmpty()) {
            System.out.println(ColorConstants.ERROR_COLOR + "📭 No phrases in category: " + category.getDisplayName()
                    + ColorConstants.RESET);
            return;
        }

        System.out.println("\n" + ColorConstants.SUCCESS_COLOR + "Phrases in category: " + category.getDisplayName()
                + " 🎯" + ColorConstants.RESET);
        for (Phrase phrase : phrases) {
            printPhrase(phrase);
        }
        System.out.println(
                ColorConstants.SUCCESS_COLOR + "Found phrases: " + phrases.size() + " 📊" + ColorConstants.RESET);
    }

    private void showPhrasesByAuthor() {
        System.out.println("\n" + ColorConstants.MENU_COLOR + "=== 👤 PHRASES BY AUTHOR ===" + ColorConstants.RESET);
        System.out.print(ColorConstants.OPTION_COLOR + "Enter author name to search: " + ColorConstants.RESET);

        String searchAuthor = scanner.nextLine().trim();

        if (searchAuthor.isEmpty()) {
            System.out.println(ColorConstants.ERROR_COLOR + "❌ Name of author cannot be empty!" + ColorConstants.RESET);
            return;
        }
        List<Phrase> phrases = phraseService.getPhrasesByAuthor(searchAuthor);

        if (phrases.isEmpty()) {
            System.out.println(ColorConstants.ERROR_COLOR + "📭 No phrases found with author \"" + searchAuthor + "\"."
                    + ColorConstants.RESET);
            return;
        }
        System.out.println(ColorConstants.SUCCESS_COLOR + "Found phrases: 🎯" + ColorConstants.RESET);
        for (Phrase phrase : phrases) {
            printPhrase(phrase);
        }
        System.out.println(
                ColorConstants.SUCCESS_COLOR + "Found phrases: " + phrases.size() + " 📊" + ColorConstants.RESET);
    }

    private void searchPhrasesByText() {
        System.out.println("\n" + ColorConstants.MENU_COLOR + "=== 🔎 SEARCH PHRASES ===" + ColorConstants.RESET);
        System.out.print(ColorConstants.OPTION_COLOR + "Enter search text: " + ColorConstants.RESET);

        String searchText = scanner.nextLine().trim();

        if (searchText.isEmpty()) {
            System.out.println(ColorConstants.ERROR_COLOR + "❌ Search text cannot be empty!" + ColorConstants.RESET);
            return;
        }
        List<Phrase> phrases = phraseService.getPhrasesByText(searchText);

        if (phrases.isEmpty()) {
            System.out.println(ColorConstants.ERROR_COLOR + "📭 No phrases found with text \"" + searchText + "\"."
                    + ColorConstants.RESET);
            return;
        }

        System.out.println(ColorConstants.SUCCESS_COLOR + "Found phrases: 🎯" + ColorConstants.RESET);
        for (Phrase phrase : phrases) {
            printPhrase(phrase);
        }
        System.out.println(
                ColorConstants.SUCCESS_COLOR + "Found phrases: " + phrases.size() + " 📊" + ColorConstants.RESET);
    }

    private void printPhrase(Phrase phrase) {
        System.out.println(ColorConstants.MENU_COLOR + "─────────────────────────────" + ColorConstants.RESET);
        System.out.println(ColorConstants.BOLD + "ID: " + ColorConstants.RESET + phrase.getId());
        System.out.println(ColorConstants.BOLD + "Text: " + ColorConstants.RESET + phrase.getText());
        System.out.println(ColorConstants.BOLD + "Author: " + ColorConstants.RESET + phrase.getAuthor().getName());
        System.out.println(
                ColorConstants.BOLD + "Category: " + ColorConstants.RESET + phrase.getCategory().getDisplayName());
        System.out.println(ColorConstants.MENU_COLOR + "─────────────────────────────" + ColorConstants.RESET);
    }
}
