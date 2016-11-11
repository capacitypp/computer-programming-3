import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FormReceiver extends HttpServlet {
	static String keys[] = {"buying", "maint", "doors", "persons", "luggage", "safety", "eval"};

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		PrintWriter pw = null;
		try {
			String text1 = request.getParameter("text1");
			String text2 = request.getParameter("text2");
			String radio1 = request.getParameter("radio1");
			text1 = new String(text1.getBytes("8859_1"), "UTF-8");
			text2 = new String(text2.getBytes("8859_2"), "UTF-8");
			radio1 = new String(radio1.getBytes("8859_1"), "UTF-8");

			response.setContentType("text/html; charset=UTF-8");

			pw = response.getWriter();
			/* 受け取った検索条件のチェック */
			boolean flag = false;
			if (text1.length() == 0)
				flag = true;
			if (text2.length() == 0)
				flag = true;
			if (radio1.length() == 0)
				flag = true;
			if (!flag) {
				Car car = new Car("car.csv");
				pw.println("<center>全車種数 : " + car.size() + "</center><br>");
				/* Car.analyze()に渡す引数の作成 */
				HashMap<String, String> query = new HashMap<String, String>();
				String[] cmd = text1.split("=");
				query.put(cmd[0], cmd[1]);
				cmd = text2.split("=");
				query.put(cmd[0], cmd[1]);

				/* 検索を行う */
				ArrayList<ArrayList<String>> retrieved = car.analyze(query, radio1);
				pw.println("<center>ヒットした車種数 : " + retrieved.size() + "</center><br>");

				/* 表の表示 */
				pw.println("<table border=\"1\" align=\"center\">");
				pw.println("<tr>");
				for (String key : keys)
					pw.println("<td>" + key + "</td>");
				pw.println("</tr>");
				for (ArrayList<String> row : retrieved) {
					pw.println("<tr>");
					for (String value : row)
						pw.println("<td>" + value + "</td>");
					pw.println("</tr>");
				}
				pw.println("</table>");
				pw.println("<br>");
				pw.println("<br>");
			} else
				pw.println("<center><h2>エラー</h2>テキストを入力してください</center>");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

