package LibraryManagementSystem.BookActions;

import LibraryManagementSystem.Database.Database;
import LibraryManagementSystem.userAccount.User;

public interface BookActions {

    public void actions(Database database, User user);
}
