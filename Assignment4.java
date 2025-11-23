import java.io.*;
import java.util.*;

class Book implements Comparable<Book> {

    int id;
    String title;
    String author;

    Book(int bookId, String bookTitle, String bookAuthor) {
        id = bookId;
        title = bookTitle;
        author = bookAuthor;
    }

    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }
}

class LibraryManager {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Book> bookList = new ArrayList<>();
    HashMap<Integer, Integer> issuedBooks = new HashMap<>();

    public void addBook() {
        try {
            System.out.print("Enter Book ID: ");
            int enteredId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Title: ");
            String enteredTitle = scanner.nextLine();

            System.out.print("Enter Author: ");
            String enteredAuthor = scanner.nextLine();

            bookList.add(new Book(enteredId, enteredTitle, enteredAuthor));
            System.out.println("Book Added\n");
        } catch (Exception e) {
            System.out.println("Invalid Input");
            scanner.nextLine();
        }
    }

    public void searchBook() {
        try {
            System.out.print("Enter Book ID to search: ");
            int searchId = scanner.nextInt();

            int index = 0;
            while (index < bookList.size()) {
                Book currentBook = bookList.get(index);

                if (currentBook.id == searchId) {
                    System.out.println("ID: " + currentBook.id);
                    System.out.println("Title: " + currentBook.title);
                    System.out.println("Author: " + currentBook.author);

                    if (issuedBooks.containsKey(searchId))
                        System.out.println("Status: Issued");
                    else
                        System.out.println("Status: Available");

                    return;
                }
                index++;
            }

            System.out.println("Book not found");
        } catch (Exception e) {
            System.out.println("Invalid Input");
            scanner.nextLine();
        }
    }

    public void sortBooks() {
        Collections.sort(bookList);

        int index = 0;
        System.out.println("\nSorted Books:");
        while (index < bookList.size()) {
            Book sortedBook = bookList.get(index);
            System.out.println(sortedBook.id + " - " + sortedBook.title + " - " + sortedBook.author);
            index++;
        }
        System.out.println();
    }

    public void issueBook() {
        try {
            System.out.print("Enter Book ID to issue: ");
            int issueId = scanner.nextInt();

            System.out.print("Enter Member ID: ");
            int memberId = scanner.nextInt();

            int index = 0;
            while (index < bookList.size()) {
                Book currentBook = bookList.get(index);

                if (currentBook.id == issueId) {

                    if (issuedBooks.containsKey(issueId)) {
                        System.out.println("Already issued");
                        return;
                    }

                    issuedBooks.put(issueId, memberId);
                    System.out.println("Book Issued\n");
                    return;
                }
                index++;
            }

            System.out.println("Book not found");
        } catch (Exception e) {
            System.out.println("Invalid Input");
            scanner.nextLine();
        }
    }

    public void returnBook() {
        try {
            System.out.print("Enter Book ID to return: ");
            int returnId = scanner.nextInt();

            if (issuedBooks.containsKey(returnId)) {
                issuedBooks.remove(returnId);
                System.out.println("Book Returned\n");
            } else {
                System.out.println("Book is not issued");
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
            scanner.nextLine();
        }
    }

    public void menu() {
        int choice = 0;

        while (choice != 6) {
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Sort Books");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid Input");
                scanner.nextLine();
                continue;
            }

            if (choice == 1) addBook();
            else if (choice == 2) searchBook();
            else if (choice == 3) sortBooks();
            else if (choice == 4) issueBook();
            else if (choice == 5) returnBook();
            else if (choice == 6) System.out.println("Exiting");
            else System.out.println("Invalid Choice");
        }
    }

    public static void main(String[] args) {
        LibraryManager obj = new LibraryManager();
        obj.menu();
    }
}