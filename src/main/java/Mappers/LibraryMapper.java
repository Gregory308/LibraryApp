package Mappers;

import Entities.Book;
import Entities.Library;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryMapper {

    public static Library mapToLibraryObject(ResultSet rs) throws SQLException {
        return new Library(
                rs.getInt("libraryId"),
                rs.getString("libraryName"),
                rs.getString("libraryAddress"));
    }
}
