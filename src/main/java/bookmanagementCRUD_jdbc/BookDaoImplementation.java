package bookmanagementCRUD_jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImplementation implements BookDao{
	
    private static final String INSERT_QUERY = "INSERT INTO book (bookId, bookTitle, bookPrice) VALUES (?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM book";
    private static final String UPDATE_QUERY = "UPDATE book SET bookTitle = ?, bookPrice = ? WHERE bookId = ?";
    private static final String DELETE_QUERY = "DELETE FROM book WHERE bookId = ?";
    
	@Override
	public void create(Book book) {
		// TODO Auto-generated method stub
        try (Connection connection = MyConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
               statement.setInt(1, book.getBookId());
               statement.setString(2, book.getBookTitle());
               statement.setInt(3, book.getBookPrice());
               statement.executeUpdate();
           } catch (SQLException e) {
               e.printStackTrace();
           }
	}

	@Override
    public List<Book> readAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int bookId = resultSet.getInt("bookId");
                String bookTitle = resultSet.getString("bookTitle");
                int bookPrice = resultSet.getInt("bookPrice");
                books.add(new Book(bookId, bookTitle, bookPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub
        try (Connection connection = MyConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
               statement.setString(1, book.getBookTitle());
               statement.setInt(2, book.getBookPrice());
               statement.setInt(3, book.getBookId());
               statement.executeUpdate();
           } catch (SQLException e) {
               e.printStackTrace();
           }
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
        try (Connection connection = MyConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
               statement.setInt(1, id);
               statement.executeUpdate();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
}
