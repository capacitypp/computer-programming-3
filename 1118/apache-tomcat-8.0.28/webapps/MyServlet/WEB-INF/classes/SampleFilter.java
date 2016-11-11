import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SampleFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter pw = response.getWriter();
		pw.println("<html><body><center><h2>こんにちは</h2><hr/>");

		chain.doFilter(request, response);

		pw.println("<hr/>ありがとうございました。</center></body></html>");
	}

	public void init(FilterConfig filterConfig) {}
	public void destroy() {}
}

