import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SessionManager extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			HttpSession hs = request.getSession(true);
			Integer cn = (Integer)hs.getAttribute("count");
			Date dt = (Date)hs.getAttribute("date");

			String str1, str2;
			if (cn == null) {
				cn = new Integer(1);
				dt = new Date();
				str1 = "初めまして。";
				str2 = "";
			} else {
				cn = new Integer(cn.intValue() + 1);
				str1 = cn + "回目のおこしですね。";
				dt = new Date();
				str2 = "(前回 : " + dt + ")";
			}

			hs.setAttribute("count", cn);
			hs.setAttribute("date", dt);

			response.setContentType("text/html; charset=UTF-8");

			PrintWriter pw = response.getWriter();
			pw.println("<html><body><center><h2>ようこそ</h2><hr />" + str1 + "<br/>" + str2 + "<br/>" + "</center></body></html>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

