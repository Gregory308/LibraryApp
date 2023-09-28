package DbTools;

import Entities.Book;
import Entities.Library;
import Mappers.BookMapper;
import Mappers.LibraryMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDbTools {

    private final static String ALL_LIBRARIES_QUERY = "SELECT libraryId, libraryName, libraryAddress FROM library";

    private final static String INSERT_LIBRARIES_QUERY = "INSERT INTO library (libraryName, libraryAddress) VALUES (?, ?)";

    private final static String UPDATE_LIBRARIES_QUERY = "UPDATE library SET libraryName = ?, libraryAddress = ? where libraryId = ?";

    private final static String DELETE_LIBRARIES_QUERY = "DELETE library, book FROM library LEFT JOIN book on bookLbId = libraryId where libraryId = ?";

    public static List<Library> getAllLibraries() {
        List<Library> libraryList = new ArrayList<>();
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            try {
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery(ALL_LIBRARIES_QUERY);
                while(rs.next()) {
                    libraryList.add(LibraryMapper.mapToLibraryObject(rs));
                }
            }
            catch (SQLException e) {
                System.err.println("Exception getAllLibraries" + e.getMessage() + e.getStackTrace());
            }
        }
        return libraryList;
    }

    public static void insertLibrary(String libraryName, String libraryAddress){
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(INSERT_LIBRARIES_QUERY);
                stm.setString(1,libraryName);
                stm.setString(2,libraryAddress);
                stm.executeUpdate();
            }
            catch (SQLException e) {
                System.err.println("Exception insertLibrary" + e.getMessage() + e.getStackTrace());
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

    public static void updateLibrary(String libraryName, String libraryAddress, int libraryId){
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(UPDATE_LIBRARIES_QUERY);
                stm.setString(1,libraryName);
                stm.setString(2,libraryAddress);
                stm.setInt(3,libraryId);
                stm.executeUpdate();
            }
            catch (SQLException e) {
                System.err.println("Exception updateLibrary" + e.getMessage() + e.getStackTrace());
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

    public static void deleteLibrary(int libraryId){
        Connection conn = ConnectionManager.getConnection();
        if(conn != null) {
            PreparedStatement stm = null;
            try {
                stm = conn.prepareStatement(DELETE_LIBRARIES_QUERY);
                stm.setInt(1,libraryId);
                stm.executeUpdate();
            }
            catch (SQLException e) {
                System.err.println("Exception deleteLibrary" + e.getMessage() + e.getStackTrace());
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
