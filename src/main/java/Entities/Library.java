package Entities;

public class Library {
    public int libraryId;
    public String libraryName;
    public String libraryAddress;

    public Library(int libraryId, String libraryName, String libraryAddress) {
        this.libraryId = libraryId;
        this.libraryName = libraryName;
        this.libraryAddress = libraryAddress;
    }

    public Library(String libraryName, String libraryAddress) {
        this.libraryName = libraryName;
        this.libraryAddress = libraryAddress;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryAddress() {
        return libraryAddress;
    }

    public void setLibraryAddress(String libraryAddress) {
        this.libraryAddress = libraryAddress;
    }

    @Override
    public String toString() {
        return "Library{" +
                "libraryId=" + libraryId +
                ", libraryName='" + libraryName + '\'' +
                ", libraryAddress='" + libraryAddress + '\'' +
                '}';
    }
}
