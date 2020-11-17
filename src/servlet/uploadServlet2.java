package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload2.do")
@MultipartConfig(maxFileSize = 1024*1024*5)
public class uploadServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html:charset=utf-8");
		
		//뭔갈 뿌릴라고 드릉드릉 중
		PrintWriter out = response.getWriter();
		//upload폴더 경로 가져오기
		String path = request.getSession().getServletContext().getRealPath("/upload/");
//		String path = request.getRealPath("/upload/");
		System.out.println(path);
		
		//업로드한 파일 받아오기
		Collection<Part> p = request.getParts();
		
		for (Part data : p) {
			if(data.getContentType() != null) {
				String fileName = data.getSubmittedFileName();
				System.out.println(fileName);
				if(fileName != null && fileName.length() != 0) {
					data.write(path + fileName);
					out.print("<img src='./upload/"+ fileName +"'>");
				}
			}
		}
	}
}
