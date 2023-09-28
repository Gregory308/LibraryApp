package DbTools;

import Entities.Book;
import Mappers.BookMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDbTools {

    private final static String ALL_BOOKS_QUERY = "SELECT bookId, bookTitle, bookAuthor, bookPages, bookLbId FROM book";

    private final static String ALL_BOOKS_FROM_LIBRARY_QUERY = "SELECT bookId, bookTitle, bookAuthor, bookPages, bookLbId FROM book where bookLbId = ?";

    private final static String INSERT_BOOKS_QUERY = "INSERT INTO book (bookTitle, bookAuthor, bookPages, bookLbId) VALUES (?, ?, ?, ?)";

    private final static String UPDATE_BOOKS_QUERY = "UPDATE book SET bookTitle = ?, bookAuthor = ?, bookPages = ?, bookLbId = ? where bookId = ?";

    private final static String DELETE_BOOKS_QUERY = "DELETE FROM book where bookId = ?";

    public static List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            try {
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(ALL_BOOKS_QUERY);
                while(rs.next()) {
                    bookList.add(BookMapper.mapToBookObject(rs));
                }
            }
            catch (SQLException e) {
                System.err.println("Exception getAllBooks" + e.getMessage() + e.getStackTrace());
            }
        }
        return bookList;
    }

    public static List<Book> getAllBooksFromLibrary() {
        List<Book> bookList = new ArrayList<>();
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            try {
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(ALL_BOOKS_FROM_LIBRARY_QUERY);
                while(rs.next()) {
                    bookList.add(BookMapper.mapToBookObject(rs));
                }
            }
            catch (SQLException e) {
                System.err.println("Exception getAllBooks" + e.getMessage() + e.getStackTrace());
            }
        }
        return bookList;
    }

    public static void insertBook(String bookTitle, String bookAuthor, int bookPages, int bookLbId){
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(INSERT_BOOKS_QUERY);
                stm.setString(1,bookTitle);
                stm.setString(2,bookAuthor);
                stm.setInt(3,bookPages);
                stm.setInt(4,bookLbId);
                stm.executeUpdate();
            }
            catch (SQLException e) {
                System.err.println("Exception insertBook" + e.getMessage() + e.getStackTrace());
            }
            finally {
                try{
                    stm.close();
                }catch (SQLException e) {
                    System.err.println("Nie udało się zamknąć statementu");
                }
            }
        }
    }

    public static void updateBook(String bookTitle, String bookAuthor, int bookPages, int bookLbId, int bookId){
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(UPDATE_BOOKS_QUERY);
                stm.setString(1,bookTitle);
                stm.setString(2,bookAuthor);
                stm.setInt(3,bookPages);
                stm.setInt(4,bookLbId);
                stm.setInt(5,bookId);
                stm.executeUpdate();
            }
            catch (SQLException e) {
                System.err.println("Exception updateBook" + e.getMessage() + e.getStackTrace());
            }
            finally {
                try{
                    stm.close();
                }catch (SQLException e) {
                    System.err.println("Nie udało się zamknąć statementu");
                }
            }
        }
    }

    public static void deleteBook(int bookId){
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(DELETE_BOOKS_QUERY);
                stm.setInt(1,bookId);
                stm.executeUpdate();
            }
            catch (SQLException e) {
                System.err.println("Exception deleteBook" + e.getMessage() + e.getStackTrace());
            }
            finally {
                try{
                    stm.close();
                }catch (SQLException e) {
                    System.err.println("Nie udało się zamknąć statementu");
                }
            }
        }
    }



}
