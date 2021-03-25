package LIS.Model.VO;

public class BookVO {
    private int bookId;
    private String bookName;
    private String authors;
    private String publisher;
    private int publicationYear;
    private String ISBN;
    private String bookImageURL;
    private int vol;
    private String category;
    private String storageLocation;
    private String bookStatus;
    private String regDate;

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }
    public String getAuthors() { return authors; }
    public void setAuthors(String authors) { this.authors = authors; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public int getPublicationYear() { return publicationYear;}
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }
    public String getISBN() { return ISBN; }
    public void setISBN(String ISBN) { this.ISBN = ISBN; }
    public String getBookImageURL() { return bookImageURL; }
    public void setBookImageURL(String bookImageURL) { this.bookImageURL = bookImageURL; }
    public int getVol() { return vol; }
    public void setVol(int vol) { this.vol = vol; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getStorageLocation() { return storageLocation; }
    public void setStorageLocation(String storageLocation) { this.storageLocation = storageLocation; }
    public String getBookStatus() { return bookStatus; }
    public void setBookStatus(String bookStatus) { this.bookStatus = bookStatus; }
    public String getRegDate() { return regDate; }
    public void setRegDate(String regDate) { this.regDate = regDate; }

    @Override
    public String toString() {
        return getBookId()+" "+getBookName()+" "+getAuthors()+" "+getPublisher()+" "
                +getPublicationYear()+" "+getISBN()+" "+getBookImageURL()+" "+getVol()+" "+
                getCategory()+" "+getStorageLocation()+"\n";
    }
}
