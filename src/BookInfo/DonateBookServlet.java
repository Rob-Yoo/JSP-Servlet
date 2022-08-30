package BookInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DatabaseUtil;

public class DonateBookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public DonateBookServlet() {
        super();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String userID = null;
    	HttpSession session = request.getSession();
    	if(session.getAttribute("userID") != null) {
    			userID = (String) session.getAttribute("userID");
    			request.setAttribute("userID", userID);
    			RequestDispatcher dispatcher = request.getRequestDispatcher("donateBook.jsp");
    			dispatcher.forward(request, response);
    	} else {
    		response.setCharacterEncoding("UTF-8");
    		response.setContentType("text/html; charset=UTF-8");
    		PrintWriter script = response.getWriter();
        	script.println("<script>");
        	script.println("alert('로그인이 필요합니다.');");
        	script.println("location.href='userLogin.jsp'");
        	script.println("</script>");
        	script.close();
    	}
	}
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		registerBookInfo(request, response);
	}
	
	public void registerBookInfo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		if(request.getParameter("bookID").equals("") || request.getParameter("bookName").equals("") || request.getParameter("authorName").equals("") 
				|| request.getParameter("price").equals("")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("FillError.jsp");
			dispatcher.forward(request, response);
		} 
		Integer bookID = null;
		String bookName = null;          
		String authorName = null;       
		Integer price = null;
		
		if(request.getParameter("bookID") != null) {
			bookID = Integer.parseInt(request.getParameter("bookID"));
		}
		if(request.getParameter("bookName") != null) {
			bookName = request.getParameter("bookName");
		}
		if(request.getParameter("authorName") != null) {
			authorName = request.getParameter("authorName");
		}
		if(request.getParameter("price") != null) {
			price = Integer.parseInt(request.getParameter("price"));
		}
		donateBook(new BookListDTO(bookID, bookName, authorName, price, true, 0, null), request, response);
	}
	
	
	
	public void donateBook (BookListDTO bookListDTO, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String SQL = "INSERT INTO bookList VALUES (?, ?, ?, ?, true, 0, NULL)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bookListDTO.getBookID());
			pstmt.setString(2, bookListDTO.getBookName());
			pstmt.setString(3, bookListDTO.getAuthorName());
			pstmt.setInt(4, bookListDTO.getPrice());		
			pstmt.executeQuery("SET NAMES 'UTF8'");
			int result = pstmt.executeUpdate();
			if(result == 1) {
				response.sendRedirect("bookList");
			} 
		} catch (Exception e) {
			request.setAttribute("bookID", bookListDTO.getBookID());
			RequestDispatcher dispatcher = request.getRequestDispatcher("IDError.jsp");
			dispatcher.forward(request, response);
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		}
	}
}
