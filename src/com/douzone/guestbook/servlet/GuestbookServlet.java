package com.douzone.guestbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.guestbook.dao.GuestbookDao;
import com.douzone.guestbook.vo.GuestbookVo;
import com.douzone.mvc.util.WebUtils;


@WebServlet("/gb")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 request.setCharacterEncoding("utf-8");
		  
		//요청 분리(식별)
		String action = request.getParameter("a");
		
		if("index".equals(action)) {
			
		} else if("deleteform".equals(action)) {
			
			WebUtils.forward(request, response, "/WEB-INF/views/deleteform.jsp");

		} else if("delete".equals(action)) { 
			
			  String no = request.getParameter("no");
			  String password = request.getParameter("password");
			   
			  GuestbookVo vo = new GuestbookVo();
			  vo.setNo(Long.parseLong(no));
			  vo.setPassword(password);
			  new GuestbookDao().delete(vo);
			  
			  WebUtils.redirect(request, response , request.getContextPath()+"/gb");
			  
		} else if("add".equals(action)) { 

			  String name = request.getParameter("name");
			  String password =request.getParameter("password");
			  String message = request.getParameter("message");
			  
			  GuestbookVo vo = new GuestbookVo();
			  vo.setName(name);
			  vo.setPassword(password);
			  vo.setMessage(message);
			  new GuestbookDao().insert(vo);
			  
			  WebUtils.redirect(request, response ,request.getContextPath()+"/gb");
			
		} else {
			/* default action 디폴트요청처리 */
			/* list */			
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> list = dao.getList();		
			
			//서블릿들에대한 정보들 -톰캣이가지고있다.
			//들어온 리퀘스트를 분기하기위해 분기객체를 얻어야됨
			
			//데이터를 request 범위에 저장.
			request.setAttribute("list", list);
			
			//forwarding
			WebUtils.forward(request, response,"/WEB-INF/views/index.jsp");
		}
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
