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

public class EvaluationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public EvaluationServlet() {
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
		int pageNumber = 0;
		if(request.getParameter("pageNumber") != null) {
			try {
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));			
			} catch (Exception e){
				System.out.println("검색 페이지 번호 오류");
			}
		}
		request.setAttribute("PageNumber", pageNumber);
		EvaluationList evaluationList = getEvaluationList(pageNumber);
		request.setAttribute("EvaluationList", evaluationList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	public EvaluationList getEvaluationList (int pageNumber) 
				throws ServletException, IOException {
			String SQL = "";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			EvaluationList list = new EvaluationList();
			try {
				SQL = "SELECT * FROM evaluation ORDER BY evaluationID DESC LIMIT " + pageNumber * 5 + ", " + pageNumber * 5 + 6;				
					conn = DatabaseUtil.getConnection();
					pstmt = conn.prepareStatement(SQL);
					rs = pstmt.executeQuery();
					for(int cnt = 0; cnt < 6; cnt++) {
						if(!rs.next())
							break;
						list.setEvaluationID(cnt, rs.getInt(1));
						list.setUserID(cnt, rs.getString(2));
						list.setBookName(cnt, rs.getString(3));
						list.setAuthorName(cnt, rs.getString(4));
						list.setEvaluationTitle(cnt, rs.getString(5));
						list.setEvaluationContent(cnt, rs.getString(6));
						list.setTotalScore(cnt, rs.getString(7));
						list.setStandard(cnt, rs.getString(8));
						list.setContent(cnt, rs.getString(9));
						list.setBookCondition(cnt, rs.getString(10));
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
		registerEvaluation(request, response);
	}
	
	public void registerEvaluation(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String userID = null;
		String bookName = null;          
		String authorName = null;       
		String evaluationTitle = null;  
		String evaluationContent = null; 
		String totalScore = null;        
		String standard = null;          
		String content = null;         
		String bookCondition = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		if(userID == null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인이 필요합니다.');");
			script.println("location.href='userLogin.jsp';");
			script.println("</script>");
			script.close();
		}
		if(request.getParameter("bookName") != null) {
			bookName = request.getParameter("bookName");
		}
		if(request.getParameter("authorName") != null) {
			authorName = request.getParameter("authorName");
		}
		if(request.getParameter("evaluationTitle") != null) {
			evaluationTitle = request.getParameter("evaluationTitle");
		}
		if(request.getParameter("evaluationContent") != null) {
			evaluationContent = request.getParameter("evaluationContent");
		}
		if(request.getParameter("totalScore") != null) {
			totalScore = request.getParameter("totalScore");
		}
		if(request.getParameter("standard") != null) {
			standard = request.getParameter("standard");
		}
		if(request.getParameter("content") != null) {
			content = request.getParameter("content");
		}
		if(request.getParameter("bookCondition") != null) {
			bookCondition = request.getParameter("bookCondition");
		}
		if(bookName == null || authorName == null || evaluationTitle == null ||  evaluationContent == null ||  totalScore == null 
				||  standard == null ||  content == null ||  bookCondition == null || evaluationTitle.equals("")
				|| evaluationContent.equals("")) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안 된  사항이 있습니다');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} else {
			EvaluationDTO evaluationDTO = new EvaluationDTO (0, userID, bookName, authorName, evaluationTitle, 
					evaluationContent, totalScore, standard, content, bookCondition);
			evaluationRegister(evaluationDTO, request, response);
		}
	}

	public void evaluationRegister (EvaluationDTO evaluationDTO, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String SQL = "INSERT INTO EVALUATION VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, evaluationDTO.getUserID().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(2, evaluationDTO.getBookName().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(3, evaluationDTO.getAuthorName().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(4, evaluationDTO.getEvaluationTitle().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(5, evaluationDTO.getEvaluationContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(6, evaluationDTO.getTotalScore().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(7, evaluationDTO.getStandard().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(8, evaluationDTO.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(9, evaluationDTO.getBookCondition().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.executeQuery("SET NAMES 'UTF8'");
			int result = pstmt.executeUpdate();
			if(result == 1) {
				response.sendRedirect("index?pageNumber=0");				
			}
		} catch (Exception e) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('책 평가 등록에 실패했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(rs != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		}
	}
}
