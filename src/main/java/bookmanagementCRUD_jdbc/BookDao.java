package bookmanagementCRUD_jdbc;

import java.util.List;

public interface BookDao {
    void create(Book book);
    List<Book> readAll();
    void update(Book book);
    void delete(int id);

}
