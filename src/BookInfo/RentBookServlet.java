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

public class RentBookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public RentBookServlet() {
        super();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String userID = null;
    	Integer bookID = null;
    	HttpSession session = request.getSession();
    	if(session.getAttribute("userID") != null) {
    		userID = (String) session.getAttribute("userID");
    		request.setAttribute("userID", userID);
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
    	if(request.getParameter("bookID") != null) {
    		bookID = Integer.parseInt(request.getParameter("bookID"));
    	}
    	request.setAttribute("bookID", bookID);
		RequestDispatcher dispatcher = request.getRequestDispatcher("RentBook.jsp");
		dispatcher.forward(request, response);
	}
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		getBookID(request, response);
	}
	
	public void getBookID(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		if(request.getParameter("bookID").equals("")) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('도서 ID가 입력되지 않았습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} 
		Integer bookID = null;
		
		if(request.getParameter("bookID") != null) {
			bookID = Integer.parseInt(request.getParameter("bookID"));
		}
		getBook(bookID, request, response);
	}
	
	public void getBook (Integer bookID ,HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String SQL = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean rentAble = null;
		String rentPersonID = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("userID") != null) {
			rentPersonID = (String) session.getAttribute("userID");
		}
		try {
			SQL = "SELECT * FROM bookList where bookID=?;";				
				conn = DatabaseUtil.getConnection();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, bookID);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					rentAble = rs.getBoolean(5);
				}
				if(rentAble == false) {
					request.setAttribute("bookID", bookID);
					RequestDispatcher dispatcher =
							request.getRequestDispatcher("RentError.jsp");
					dispatcher.forward(request, response);
				} 
				if(rentAble == true)
					rentBook(bookID, rentPersonID ,request, response);					
		} catch (Exception e) {
			request.setAttribute("bookID", bookID);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("RentError.jsp");
			dispatcher.forward(request, response);
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
	    }
}
	
	public void rentBook (Integer bookID, String rentPersonID, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String SQL = "update bookList set rentAble=0, rentCount = rentCount + 1, rentPersonID= ? where bookID = ?;";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, rentPersonID);
			pstmt.setInt(2, bookID);
			pstmt.executeQuery("SET NAMES 'UTF8'");
			int result = pstmt.executeUpdate();
			if(result == 1) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('대여가 완료되었습니다.');");
				script.println("location.href='rentedBook';");
				script.println("</script>");
				script.close();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		}
	}
}
