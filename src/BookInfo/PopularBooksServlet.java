package BookInfo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DatabaseUtil;

public class PopularBooksServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public PopularBooksServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int pageNumber = 0;
		if(request.getParameter("pageNumber") != null) {
			try {
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));			
			} catch (Exception e){
				System.out.println("검색 페이지 번호 오류");
			}
		}
		request.setAttribute("PageNumber", pageNumber);
		BookList bookList = getPopBookList(pageNumber);
		request.setAttribute("BookList", bookList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("popularBooks.jsp");
		dispatcher.forward(request, response);
	}
	
	public BookList getPopBookList (int pageNumber) throws ServletException, IOException {
			String SQL = "";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BookList list = new BookList();
			try {
				SQL = "SELECT * FROM bookList ORDER BY rentCount DESC LIMIT " + pageNumber * 5 + ", " + pageNumber * 5 + 6;				
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
}
