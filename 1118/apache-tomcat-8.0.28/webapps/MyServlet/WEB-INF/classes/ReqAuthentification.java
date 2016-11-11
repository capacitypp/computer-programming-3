import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ReqAuthentification extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			response.setContentType("text/html; charset=Shift_JIS");

			PrintWriter pw = response.getWriter();
			pw.println("<html><body><center>\n" + "<h2>おめでとうございます。</h2>\n" + "<hr/>認証に成功しました。<br/></center></body></html>\n");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

