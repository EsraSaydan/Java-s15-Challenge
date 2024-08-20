package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.User;

public class Exit implements BookActions{
    @Override
    public void actions(Database database, User user) {
        System.out.println("Exiting the system. Goodbye!");
        System.exit(0);
    }
}
