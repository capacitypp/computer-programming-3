import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FormReceiver extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			String tmp = request.getParameter("message");
			String msgStr = new String(tmp.getBytes("8859_1"), "UTF-8");

			response.setContentType("text/html; charset=UTF-8");

			PrintWriter pw = response.getWriter();
			if (msgStr.length() != 0)
				pw.println("<html><body><center>" + msgStr + "を受け取りました</center></body></html>");
			else
				pw.println("<html><body><center><h2>エラー</h2>テキストを入力してください</center></body></html>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

