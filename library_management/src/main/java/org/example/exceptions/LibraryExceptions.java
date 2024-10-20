package org.example.exceptions;

public class LibraryExceptions extends RuntimeException {
    private LibraryExceptions(String message) {
        super(message);
    }

    public static LibraryExceptions idNotNull() {
        String message = "Id is not null.";
        return new LibraryExceptions(message);
    }

    public static LibraryExceptions idIsNull() {
        String message = "Id is null!";
        return new LibraryExceptions(message);
    }

    public static LibraryExceptions notFound(String className, Object id) {
        String message = String.format("%s with id %s not found!", className, id.toString());
        return new LibraryExceptions(message);
    }

    public static LibraryExceptions existsByUsername(String username) {
        String message = String.format("User with username %s exists!", username);
        return new LibraryExceptions(message);
    }

    public static LibraryExceptions emptyListOfBooks(String title) {
        String message = String.format("You can not take book with title %s!", title);
        return new LibraryExceptions(message);
    }

    public static LibraryExceptions existsByName(String name) {
        String message = String.format("Publisher with name %s does not exists!", name);
        return new LibraryExceptions(message);
    }

    public static LibraryExceptions existsByFirstNameOrLastName(String firstName, String lastName) {
        String message = String.format("Author with firstname %s and lastname %s does not exists!", firstName, lastName);
        return new LibraryExceptions(message);
    }



}
