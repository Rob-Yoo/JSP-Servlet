package user;
import util.DatabaseUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.*;
import javax.servlet.http.*;

public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserLoginServlet() {
        super();
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		getUser(request, response);
	}
	
	public void getUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			String userID = null;
			String userPassword = null;
			if(request.getParameter("userID") != null) {
				userID = request.getParameter("userID");
			}
			if(request.getParameter("userPassword") != null) {
				userPassword = request.getParameter("userPassword");
			}
			if(userID.equals("") || userPassword.equals("") || userID == null || userPassword == null ) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력이 안 된  사항이 있습니다');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
			} else {
			    login(userID, userPassword, request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void login(String userID, String userPassword, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).contentEquals(userPassword)) {
					HttpSession session = request.getSession();
					session.setAttribute("userID", userID);
					response.sendRedirect("index");
				} else {
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('비밀번호가 틀립니다.');");
					script.println("history.back();");
					script.println("</script>");
					script.close();
				}
			} else {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('계정이 존재하지 않습니다.');");
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
	}

}
