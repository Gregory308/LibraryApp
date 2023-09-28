import DbTools.BookDbTools;
import DbTools.ConnectionManager;
import DbTools.LibraryDbTools;
import Entities.Book;
import Entities.Library;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LibraryApp {
    public static void main(String [] args) {
        LibraryApp libraryApp = new LibraryApp();
        Integer nextMove = -1;
        try {
            do {
                System.out.println("Podaj co chcesz zrobić: ");
                System.out.println("1 - wyświetlić listę bibliotek,\n2 - dodać bibliotekę\n3 - usunąć bibliotekę\n4 - zaaktualizować dane biblioteki\n5 - wyświetlić listę książek\n6 - dodać książkę\n7 - usunąć książkę\n8 - zaaktualizować dane książki\n9 - wyświetlić książki z konkretnej biblioteki\n0 - zakończyć działanie programu");
                nextMove = libraryApp.getUserMove();
                if(!libraryApp.processNewMove(nextMove)) {
                    System.err.println("Wybrano niepoprawne działanie");
                }
            } while(nextMove != 0);
        }
        finally {
            ConnectionManager.freeConnection();;
        }
    }

    private Integer getUserMove() {
        Integer retValue = -1;
        try{
            Scanner scanner = new Scanner(System.in);
            retValue = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            System.err.println("Podaj poprawną wartość");
        }
        return retValue;
    }

    private static boolean processNewMove(Integer nextMove) {
        boolean retVal = false;
        if(nextMove >= 0 && nextMove <=9) {
            retVal = true;
            switch (nextMove) {
                case 1:
                    performDisplayLibraries(LibraryDbTools.getAllLibraries());
                    break;
                case 2:
                    performInsertLibrary();
                    break;
                case 3:
                    performDeleteLibrary();
                    break;
                case 4:
                    performUpdateLibrary();
                    break;
                case 5:
                    performDisplayBooks(BookDbTools.getAllBooks());
                    break;
                case 6:
                    performInsertBook();
                    break;
                case 7:
                    performDeleteBook();
                    break;
                case 8:
                    performUpdateBook();
                    break;
            }
        }
        return retVal;
    }
    private static void performDisplayLibraries(List<Library> libraryList){
        System.out.println("Dostępna lista bibliotek w bazie danych to:");
        libraryList.forEach(System.out::println);
        System.out.println("----------");
        System.out.println("");
    }

    private static void performDisplayBooks(List<Book> bookList){
        System.out.println("Dostępna lista książek w bazie danych to:");
        bookList.forEach(System.out::println);
        System.out.println("----------");
        System.out.println("");
    }

    private static void performDisplayBooksFromLibraries(List<Book> bookList){
        System.out.println("Dostępna lista książek w bazie danych to:");
        bookList.forEach(System.out::println);
        System.out.println("----------");
        System.out.println("");
    }

    private static void performInsertLibrary() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                Library library = tryGetLibrary();
                LibraryDbTools.insertLibrary(library.getLibraryName(), library.getLibraryAddress());
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }

    private static void performInsertBook() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                Book book = tryGetBook();
                BookDbTools.insertBook(book.getBookTitle(), book.getBookAuthor(), book.getBookPages(), book.getBookLbId());
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }


    private static void performUpdateLibrary() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                List<Library> libraryList = LibraryDbTools.getAllLibraries();
                performDisplayLibraries(libraryList);
                List<Integer> libraryIds = libraryList.stream().map(p -> p.getLibraryId()).collect(Collectors.toList());
                Integer libraryId = -1;
                Scanner scanner = new Scanner(System.in);
                while(!libraryIds.contains(libraryId)) {
                    System.out.println("----------");
                    System.out.println("Podaj poprawne id biblioteki do uaktualnienia:");
                    libraryId = scanner.nextInt();
                }
                Library library = tryGetLibrary();
                LibraryDbTools.updateLibrary(library.getLibraryName(), library.getLibraryAddress(), libraryId);
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }

    private static void performUpdateBook() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                List<Book> bookList = BookDbTools.getAllBooks();
                performDisplayBooks(bookList);
                List<Integer> bookIds = bookList.stream().map(p -> p.getBookId()).collect(Collectors.toList());
                Integer libraryId = -1;
                Scanner scanner = new Scanner(System.in);
                while(!bookIds.contains(libraryId)) {
                    System.out.println("----------");
                    System.out.println("Podaj poprawne id książki do uaktualnienia:");
                    libraryId = scanner.nextInt();
                }
                Book book = tryGetBook();
                BookDbTools.updateBook(book.getBookTitle(), book.getBookAuthor(), book.getBookPages(), book.getBookLbId(), libraryId);
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }

    private static void performDeleteLibrary() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                List<Library> libraryList = LibraryDbTools.getAllLibraries();
                performDisplayLibraries(libraryList);
                List<Integer> libraryIds = libraryList.stream().map(p -> p.getLibraryId()).collect(Collectors.toList());
                Integer libraryId = -1;
                Scanner scanner = new Scanner(System.in);
                while(!libraryIds.contains(libraryId)) {
                    System.out.println("----------");
                    System.out.println("Podaj poprawne id osoby do usunięcia:");
                    libraryId = scanner.nextInt();
                }
                LibraryDbTools.deleteLibrary(libraryId);
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }

    private static void performDeleteBook() {
        boolean correctValues = false;
        while(!correctValues) {
            try {
                List<Book> bookList = BookDbTools.getAllBooks();
                performDisplayBooks(bookList);
                List<Integer> bookIds = bookList.stream().map(p -> p.getBookId()).collect(Collectors.toList());
                Integer bookId = -1;
                Scanner scanner = new Scanner(System.in);
                while(!bookIds.contains(bookId)) {
                    System.out.println("----------");
                    System.out.println("Podaj poprawne id książki do usunięcia:");
                    bookId = scanner.nextInt();
                }
                BookDbTools.deleteBook(bookId);
                correctValues = true;
            }
            catch (InputMismatchException e) {
                System.err.println("Podaj poprawne dane");
            }
        }
    }

    private static Library tryGetLibrary() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę:");
        String libraryName = scanner.nextLine();
        System.out.println("----------");
        System.out.println("Podaj adres");
        String libraryAddress = scanner.nextLine();
        System.out.println("----------");
        return new Library(libraryName,libraryAddress);
    }

    private static Book tryGetBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj tytuł:");
        String bookTitle = scanner.nextLine();
        System.out.println("----------");
        System.out.println("Podaj autora");
        String bookAuthor = scanner.nextLine();
        System.out.println("----------");
        System.out.println("Podaj ilość stron");
        int bookPages = Integer.parseInt(scanner.nextLine());
        System.out.println("----------");
        System.out.println("Podaj bibliotekę");
        int bookLbId = Integer.parseInt(scanner.nextLine());
        System.out.println("----------");
        return new Book(bookTitle,bookAuthor,bookPages,bookLbId);
    }
}
