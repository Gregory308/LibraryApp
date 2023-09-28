package Mappers;

import Entities.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper {
    public static Book mapToBookObject(ResultSet rs) throws SQLException {
        return new Book(
                rs.getInt("bookId"),
                rs.getString("bookTitle"),
                rs.getString("bookAuthor"),
                rs.getInt("bookPages"),
                rs.getInt("bookLbId"));
    }
}
