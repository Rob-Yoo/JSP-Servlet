package user;

import util.DatabaseUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.*;
import javax.servlet.http.*;

public class UserJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserJoinServlet() {
        super();
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		getUser(request, response);
	}
	
	public void getUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			String userID = null;
			String userPassword = null;
			String userPasswordCheck = null;
			if(request.getParameter("userID") != null) {
				userID = request.getParameter("userID");
			}
			if(request.getParameter("userPassword") != null) {
				userPassword = request.getParameter("userPassword");
			}
			if(request.getParameter("userPasswordCheck") != null) {
				userPasswordCheck = request.getParameter("userPasswordCheck");
			}
			if(userID.equals("") || userPassword.equals("") || userPasswordCheck.equals("") 
					|| userID == null || userPassword == null || userPasswordCheck == null) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력이 안 된  사항이 있습니다');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
			} else if (!userPassword.equals(userPasswordCheck)) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('비밀번호가 일치하지 않습니다.');");
				script.println("history.back();");
				script.println("</script>");
				script.close();
			} else {
			    join(new UserDTO(userID, userPassword), request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void join(UserDTO user, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String SQL = "INSERT INTO USER VALUES (?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			int result = pstmt.executeUpdate();  
			if(result == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("userID", user.getUserID());
				response.sendRedirect("index");
			}
	
		} catch (Exception e) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('이미 존재하는 아이디입니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close(); 
		} finally {
			try {if(conn != null) conn.close();} catch (Exception e) {e.printStackTrace();}
			try {if(pstmt != null) conn.close();} catch (Exception e) {e.printStackTrace();}
		}
	}

}
