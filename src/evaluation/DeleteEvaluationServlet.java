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

public class DeleteEvaluationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteEvaluationServlet() {
        super();
    }
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Integer evaluationID = 0;
		if(request.getParameter("evaluationID") != null) {
			evaluationID = Integer.parseInt(request.getParameter("evaluationID"));
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('오류가 발생했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
		}
		deleteAction(evaluationID, request, response);
	}
	
	public void deleteAction (Integer evaluationID, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String SQL = "DELETE FROM EVALUATION WHERE evaluationID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, evaluationID);
			int result = pstmt.executeUpdate();
			if(result == 1) {
				response.sendRedirect("index?pageNumber=0");
			}
		} catch (Exception e) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류입니다.');");
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
