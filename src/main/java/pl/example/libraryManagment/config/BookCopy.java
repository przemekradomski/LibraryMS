package pl.example.libraryManagment.config;

public class BookCopy {
    private long copyID;
    private long bookID;
    private String status;
    private String location;

    public BookCopy() {
    }

    public BookCopy(long copyID, long bookID, String status, String location) {
        this.copyID = copyID;
        this.bookID = bookID;
        this.status = status;
        this.location = location;
    }

    public long getCopyID() {
        return copyID;
    }

    public void setCopyID(long copyID) {
        this.copyID = copyID;
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
