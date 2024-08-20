package LibraryManagementSystem.Book;

import java.util.Objects;

public class Book {
    private long bookID;
    private String name;
    private String author;
    private String status;
    private String publisher;
    private Double price;
    private int borrowerId;  // Hangi kullanıcıya ödünç verildiğini belirtir
    private String borrowedDate;
    private BookType type;  // Yeni eklenen tür alanı

    // Varsayılan Yapıcı
    public Book() {
    }

    // Parametreli Yapıcı
    public Book(long bookID, String name, String author, String status, String publisher, Double price, BookType type) {
        this.bookID = bookID;
        this.name = name;
        this.author = author;
        this.status = status;
        this.publisher = publisher;
        this.price = price;
        this.borrowerId = -1;  // Kitap başlangıçta ödünç alınmadı
        this.type = type;  // Tür bilgisini başlatıyoruz
    }

    // Getter ve Setter Metotlar
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public String getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(String borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    // Eşitlik kontrolü (ID bazında)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookID == book.bookID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID);
    }

    // Kitap bilgilerini String olarak döndüren metot
    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", Book Name='" + name + '\'' +
                ", Book Author='" + author + '\'' +
                ", Book Status='" + status + '\'' +
                ", Book Publisher='" + publisher + '\'' +
                ", Book Price=" + price +
                ", Borrower ID=" + borrowerId +
                ", Type=" + type +  // Tür bilgisini de ekliyoruz
                '}';
    }
}