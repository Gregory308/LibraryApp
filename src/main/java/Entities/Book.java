package Entities;

public class Book {
    public int bookId;
    public String bookTitle;
    public String bookAuthor;
    public int bookPages;

    public int bookLbId;

    public Book(int bookId, String bookTitle, String bookAuthor, int bookPages, int bookLbId) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPages = bookPages;
        this.bookLbId = bookLbId;
    }

    public Book(String bookTitle, String bookAuthor, int bookPages, int bookLbId) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPages = bookPages;
        this.bookLbId = bookLbId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookPages() {
        return bookPages;
    }

    public void setBookPages(int bookPages) {
        this.bookPages = bookPages;
    }

    public int getBookLbId() {
        return bookLbId;
    }

    public void setBookLbId(int bookLbId) {
        this.bookLbId = bookLbId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPages=" + bookPages +
                ", bookLbId=" + bookLbId +
                '}';
    }
}
