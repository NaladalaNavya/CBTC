package task3;
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int ISBN;
    private boolean isAvailable;

    public Book(String title, String author, int ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getISBN() {
        return ISBN;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getISBN());
            System.out.println("Availability: " + (book.isAvailable() ? "Available" : "Not Available"));
            System.out.println();
        }
    }

    public Book findBookByISBN(int ISBN) {
        for (Book book : books) {
            if (book.getISBN() == ISBN) {
                return book;
            }
        }
        return null;
    }
}

public class LibraryManagementApp {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Java Programming", "John Smith", 123456);
        Book book2 = new Book("Python for Beginners", "Alice Johnson", 789012);

        library.addBook(book1);
        library.addBook(book2);

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Library Management System");
            System.out.println("1. Display Books");
            System.out.println("2. Check Out a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Available Books:");
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter ISBN of the book you want to check out: ");
                    int checkOutISBN = scanner.nextInt();
                    Book checkOutBook = library.findBookByISBN(checkOutISBN);
                    if (checkOutBook != null && checkOutBook.isAvailable()) {
                        checkOutBook.setAvailable(false);
                        System.out.println("You've checked out the book: " + checkOutBook.getTitle());
                    } else {
                        System.out.println("Book not available or ISBN is invalid.");
                    }
                    break;
                case 3:
                    System.out.print("Enter ISBN of the book you want to return: ");
                    int returnISBN = scanner.nextInt();
                    Book returnBook = library.findBookByISBN(returnISBN);
                    if (returnBook != null && !returnBook.isAvailable()) {
                        returnBook.setAvailable(true);
                        System.out.println("You've returned the book: " + returnBook.getTitle());
                    } else {
                        System.out.println("Invalid ISBN or book is already available.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }
}
