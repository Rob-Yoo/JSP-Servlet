package evaluation;
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

public class SearchActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchActionServlet() {
        super();
    }
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    	String userID = null;
    	HttpSession session = request.getSession();
    	if(session.getAttribute("userID") != null) {
    			userID = (String) session.getAttribute("userID");
    			request.setAttribute("userID", userID);
    	} 
		String bookDivide = null;
		String search = null;
		int pageNumber = 0;
		if(request.getParameter("pageNumber") != null) {
			try {
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));			
			} catch (Exception e){
				System.out.println("검색 페이지 번호 오류");
			}
		}
		if(request.getParameter("bookDivide") != null) {
			bookDivide = request.getParameter("bookDivide");
		}
		if(request.getParameter("search") != null) {
			search = request.getParameter("search");
		}
		request.setAttribute("PageNumber", pageNumber);
		EvaluationList searchList = searchAction(bookDivide, search, pageNumber, request, response);
		request.setAttribute("SearchList", searchList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("SearchResult.jsp");
		dispatcher.forward(request, response);
	}
	
	public EvaluationList searchAction(String bookDivide, String search, Integer pageNumber, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String SQL = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EvaluationList searchList = new EvaluationList();
		try {
			if(bookDivide.equals("책 제목")) {
				SQL = "select * from evaluation where bookName like '%" + search + "%' order by evaluationID desc limit " 
						+ pageNumber * 5 + ", " + pageNumber * 5 + 6;
			} else if(bookDivide.equals("저자명")) {
				SQL = "SELECT * FROM EVALUATION WHERE authorName LIKE '%" + search + "%' ORDER BY evaluationID DESC LIMIT "
						+ pageNumber * 5 + ", " + pageNumber * 5 + 6;
			} 
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			int cnt = -1;
			while(rs.next()) {
				++cnt;
				searchList.setEvaluationID(cnt, rs.getInt(1));
				searchList.setUserID(cnt, rs.getString(2));
				searchList.setBookName(cnt, rs.getString(3));
				searchList.setAuthorName(cnt, rs.getString(4));
				searchList.setEvaluationTitle(cnt, rs.getString(5));
				searchList.setEvaluationContent(cnt, rs.getString(6));
				searchList.setTotalScore(cnt, rs.getString(7));
				searchList.setStandard(cnt, rs.getString(8));
				searchList.setContent(cnt, rs.getString(9));
				searchList.setBookCondition(cnt, rs.getString(10));
			}
			if(cnt == -1) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('해당 검색어에 대한 결과가 없습니다.');");
				script.println("history.back();");
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
		return searchList;
	}
}
