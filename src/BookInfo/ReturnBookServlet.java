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

public class ReturnBookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ReturnBookServlet() {
        super();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	String userID = null;
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
		int pageNumber = 0;
		if(request.getParameter("pageNumber") != null) {
			try {
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));			
			} catch (Exception e){
				System.out.println("검색 페이지 번호 오류");
			}
		}
		request.setAttribute("PageNumber", pageNumber);
		BookList bookList = getBookList(pageNumber, userID);
		request.setAttribute("BookList", bookList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("returnBook.jsp");
		dispatcher.forward(request, response);
	}
	
	public BookList getBookList (int pageNumber, String userID) throws ServletException, IOException {
			String SQL = "";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BookList list = new BookList();
			try {
				SQL = "SELECT * FROM bookList where rentAble=0 and rentPersonID='" + userID + "' ORDER BY bookID ASC LIMIT " + pageNumber * 5 + ", " + pageNumber * 5 + 6;				
					conn = DatabaseUtil.getConnection();
					pstmt = conn.prepareStatement(SQL);
					rs = pstmt.executeQuery();
					for(int cnt = 0; cnt < 6; cnt++) {
						if(!rs.next())
							break;
						list.setBookID(cnt, rs.getInt(1));
						list.setBookName(cnt, rs.getString(2));
						list.setAuthorName(cnt, rs.getString(3));
						list.setPrice(cnt, rs.getInt(4));
						list.setRentAble(cnt, rs.getBoolean(5));
						list.setRentCount(cnt, rs.getInt(6));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
				try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		    }
			return list;
	}
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		getBookID(request, response);
	}
	
	public void getBookID(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		if(request.getParameter("bookID") == null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('반납할 도서가 없습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} 
		Integer bookID = null;
		if(request.getParameter("bookID") != null) {
			bookID = Integer.parseInt(request.getParameter("bookID"));
		}
		rentBook(bookID, request, response);
	}
	
	public void rentBook (Integer bookID, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String SQL = "update bookList set rentAble=1, rentPersonID=NULL where bookID = ?;";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bookID);		
			int result = pstmt.executeUpdate();
			if(result == 1) {
				response.sendRedirect("bookList");
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
