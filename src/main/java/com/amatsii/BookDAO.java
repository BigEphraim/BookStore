package com.amatsii;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;


public class BookDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/book_store";
	private String jdbcUsername = "root";
	private String jdbcPassword = "12bananas";
	private Connection jdbcConnection;
	
	public void connect(){
		 
		try {	
			Class.forName("com.mysql.cj.jdbc.Driver");
			if(jdbcConnection == null || jdbcConnection.isClosed()) {
				jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
				System.out.println("Connection Established to MySQL Database");
			}
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void disconnect(){
		
		try {
			if(jdbcConnection != null && !jdbcConnection.isClosed()) {
				jdbcConnection.close(); 
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Book> listAllBooks(){
		connect();
		ArrayList<Book> listBook = new ArrayList<>();
		try {
			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM book");
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				float price = resultSet.getFloat("price");
				Book book = new Book(id, title, author, price);
				listBook.add(book);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
		return listBook;
				 
	}
	
	public boolean insertBook(Book book) {
		connect();
		String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
		boolean rowInserted = false;
		try {
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setFloat(3, book.getPrice());
			
			rowInserted = statement.executeUpdate() > 0;
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		disconnect();
		return rowInserted;
	}
	
	//method to fetch book from DB based on ID
	public Book getBookById(int bookId) {
	    connect();
	    Book book = null;
	    
	    try {
	        String sql = "SELECT * FROM book WHERE id=?";
	        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	        statement.setInt(1, bookId);
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	            String title = resultSet.getString("title");
	            String author = resultSet.getString("author");
	            float price = resultSet.getFloat("price");
	            book = new Book(bookId, title, author, price);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        disconnect();
	    }
	    
	    return book;
	}
	
	
	public boolean updateBook(Book book) throws SQLException {
		boolean rowUpdated = false;
	    PreparedStatement statement = null;

	    try {
	        connect();
	        String sql = "UPDATE book SET title=?, author=?, price=? WHERE id=?";
	        System.out.println("updated book: " + statement);
	        statement = jdbcConnection.prepareStatement(sql);
	        statement.setString(1, book.getTitle());
	        statement.setString(2, book.getAuthor());
	        statement.setFloat(3, book.getPrice());
	        statement.setInt(4, book.getId());
	        
	        rowUpdated = statement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	       
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            disconnect(); 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return rowUpdated;
	}

	public boolean deleteBook(int id) throws SQLException{
		boolean rowDeleted = false;
	    PreparedStatement statement = null;

	    try {
	        connect();
	        String sql = "DELETE FROM book WHERE id=?";
	        System.out.println("Book deleted");
	        statement = jdbcConnection.prepareStatement(sql);
	        statement.setInt(1, id);
	        
	        rowDeleted = statement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	       
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            disconnect(); 
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return rowDeleted;
	}
}

