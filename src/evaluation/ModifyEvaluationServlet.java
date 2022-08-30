package evaluation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DatabaseUtil;

public class ModifyEvaluationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ModifyEvaluationServlet() {
        super();
    }
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		getData(request, response);
	}
	
	public void getData(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Integer evaluationID = null;
		String bookName = null;          
		String authorName = null;       
		String evaluationTitle = null;  
		String evaluationContent = null; 
		String totalScore = null;        
		String standard = null;          
		String content = null;         
		String bookCondition = null;

		if(request.getParameter("evaluationID") != null) {
			evaluationID = Integer.parseInt(request.getParameter("evaluationID"));
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
			modifyEvaluation(evaluationID, bookName, authorName, evaluationTitle, 
					evaluationContent, totalScore, standard, content, bookCondition, response);
		}
	}

	public void modifyEvaluation (Integer evaluationID, String bookName, String authorName, 
									String evaluationTitle, String evaluationContent, String totalScore, String standard, String content, 
									String bookCondition, HttpServletResponse response) 
			throws ServletException, IOException {
		String SQL = "UPDATE evaluation SET bookName=?, authorName=?, evaluationTitle=?, evaluationContent=?, totalScore=?, standard=?, content=?, bookCondition=?"
						+ " WHERE evaluationID=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bookName.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(2, authorName.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(3, evaluationTitle.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(4, evaluationContent.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(5, totalScore.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(6, standard.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(7, content.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(8, bookCondition.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			pstmt.setInt(9, evaluationID); 
			pstmt.executeQuery("SET NAMES 'UTF8'");
			pstmt.executeUpdate();
			response.sendRedirect("index");					
	
		} catch (Exception e) {
			e.printStackTrace();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('대여 평가 수정에 실패했습니다.');");
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
