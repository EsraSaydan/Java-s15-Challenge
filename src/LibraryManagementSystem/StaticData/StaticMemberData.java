package LibraryManagementSystem.StaticData;

import LibraryManagementSystem.userAccount.Member;
import LibraryManagementSystem.userAccount.User;
import LibraryManagementSystem.userAccount.MemberType;

import java.util.HashMap;
import java.util.Map;

public class StaticMemberData {
    public static final Map<Integer, User> users = new HashMap<>();

    static {
        // Sabit Üyeler
        users.put(1, new Member(1, "merve@gmail.com", "Merve Yılmaz", "05434762572", MemberType.STUDENT, "Ankara, Türkiye"));
        users.put(2, new Member(2, "ayse@wit.com", "Ayşe Çiçek", "0534762573", MemberType.FACULTY, "İstanbul, Türkiye"));
        users.put(3, new Member(3, "mehmet@wit.com", "Mehmet Aksu", "0534762574", MemberType.OTHER, "İzmir, Türkiye"));
    }
}