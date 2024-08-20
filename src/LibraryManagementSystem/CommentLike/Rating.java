package LibraryManagementSystem.CommentLike;

public class Rating {
    private int rating;        // Verilen puan (1-5 arası bir değer)
    private String memberName; // Puanı veren üyenin adı
    private long bookId;       // Puan verilen kitabın ID'si

    // Constructor
    public Rating(int rating, String memberName, long bookId) {
        this.rating = rating;
        this.memberName = memberName;
        this.bookId = bookId;
    }

    // Getters
    public int getRating() {
        return rating;
    }

    public String getMemberName() {
        return memberName;
    }

    public long getBookId() {
        return bookId;
    }

    // toString method
    @Override
    public String toString() {
        return "Rating{" +
                "rating=" + rating +
                ", memberName='" + memberName + '\'' +
                ", bookId=" + bookId +
                '}';
    }
}

