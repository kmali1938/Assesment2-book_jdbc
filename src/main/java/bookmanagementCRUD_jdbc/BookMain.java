package bookmanagementCRUD_jdbc;

import java.sql.Connection;

import java.util.List;
import java.util.Scanner;

public class BookMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		BookDao bookDao = new BookDaoImplementation();
		try(Connection connection = MyConnection.getConnection()) {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		int choice = 0;
		do {
			
			System.out.println("******* MENU ******");
			System.out.println("1 - Create a book");
			System.out.println("2 - Read books");
			System.out.println("3 - Update a book");
			System.out.println("4 - Delete a book");
			System.out.println("5 - Exit");
			System.out.print("Please enter your choice: ");
			choice = sc.nextInt();
			switch(choice) {
			
			case 1:
                createBook(sc, bookDao);
                break;
            case 2:
                readAllBooks(bookDao);
                break;
            case 3:
                updateBook(sc, bookDao);
                break;
            case 4:
                deleteBook(sc, bookDao);
                break;
            case 5:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
				
			}
		} while(choice != 5);

	}

    private static void createBook(Scanner scanner, BookDao bookDao) {
        System.out.println("Enter book ID:");
        int id = scanner.nextInt();
       
        System.out.println("Enter book title:");
        String title = scanner.next();
//        scanner.nextLine();
        System.out.println("Enter book price:");
        int price = scanner.nextInt();

        Book book = new Book(id, title, price);
        bookDao.create(book);
        System.out.println("Book created successfully.");
    }

    private static void readAllBooks(BookDao bookDao) {
        List<Book> books = bookDao.readAll();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("All Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void updateBook(Scanner scanner, BookDao bookDao) {
        System.out.println("Enter book ID to update:");
        int id = scanner.nextInt();
        Book existingBook = null;
        for (Book book : bookDao.readAll()) {
            if (book.getBookId() == id) {
                existingBook = book;
                break;
            }
        }
        if (existingBook == null) {
            System.out.println("Book with ID " + id + " not found.");
            return;
        }
       
        System.out.println("Enter new book title:");
        String newTitle = scanner.next();
//        scanner.nextLine();
        System.out.println("Enter new book price:");
        int newPrice = scanner.nextInt();

        Book updatedBook = new Book(existingBook.getBookId(), newTitle, newPrice);
        bookDao.update(updatedBook);
        System.out.println("Book updated successfully.");
    }

    private static void deleteBook(Scanner scanner, BookDao bookDao) {
        System.out.println("Enter book ID to delete:");
        int id = scanner.nextInt();
        bookDao.delete(id);
        System.out.println("Book deleted successfully.");
    }

}
