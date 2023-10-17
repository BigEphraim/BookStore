package com.amatsii;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
//@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;
	
    /** 
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
    	super();
    	
    	bookDAO = new BookDAO();
    	bookDAO.connect();
    	bookDAO.disconnect();        
    }
    
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		if(action.equals("/insert")) {
			insertBook(request, response);
		}
		
	}
		
		
	private void insertBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String title = request.getParameter("booktitle");
		String author = request.getParameter("bookauthor");
		String priceString = request.getParameter("bookprice");
			
		Book newBook = new Book(title, author, Float.parseFloat(priceString));
			
		bookDAO.insertBook(newBook);
			
		listBooks(request, response);
			
	}


	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getPathInfo();
		switch(action) {
		case "/new":
			addBook(request, response);
			break;
			
		case "/edit":
			showEditBookForm(request, response);
			break;
			
		case "/delete":
			deleteBook(request, response);
			break;
			
		case "/update":
			updateBook(request, response);
			break;
			
		default:
			listBooks(request, response);
			break;
		}

	}
	
	private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/BookForm.jsp");
		dispatcher.forward(request,  response);
	}
	
	private void listBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<Book> books = bookDAO.listAllBooks();
		
		request.setAttribute("book_list", books);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/BookList.jsp");
		dispatcher.forward(request,  response);
	}
	
	private void showEditBookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));
	    
	    try {
	        request.setAttribute("bookToEdit", bookDAO.getBookById(id)); 
	        request.getRequestDispatcher("/EditBookForm.jsp").forward(request, response);
	    } catch (ServletException | IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter("bookId"));

	    String title = request.getParameter("booktitle");
	    String author = request.getParameter("bookauthor");
	    Float price = Float.parseFloat(request.getParameter("bookprice"));

	    Book updatedBook = new Book(id, title, author, price); 

	    try {
	        boolean rowUpdated = bookDAO.updateBook(updatedBook);

	        if (rowUpdated) {
	            response.sendRedirect("list");
	        } else {
	        	// Setting an error message
	            request.setAttribute("errorMessage", "An error occurred while updating the book.");
	            // Forwarding to the error page
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
	            dispatcher.forward(request, response);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			bookDAO.deleteBook(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("list");
	}
	

	



}

	

