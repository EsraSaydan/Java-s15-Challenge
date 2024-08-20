package LibraryManagementSystem.CommentLike;

public class Comment {
    private String commentText; // Yorum metni
    private String memberName;  // Yorumu yapan üyenin adı
    private long bookId;        // Yorum yapılan kitabın ID'si

    // Constructor
    public Comment(String commentText, String memberName, long bookId) {
        this.commentText = commentText;
        this.memberName = memberName;
        this.bookId = bookId;
    }

    // Getters
    public String getCommentText() {
        return commentText;
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
        return "Comment{" +
                "commentText='" + commentText + '\'' +
                ", memberName='" + memberName + '\'' +
                ", bookId=" + bookId +
                '}';
    }
}

