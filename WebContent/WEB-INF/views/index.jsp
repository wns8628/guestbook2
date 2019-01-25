<%@page import="com.douzone.guestbook.vo.GuestbookVo"%>
<%@page import="java.util.List"%>
<%@page import="com.douzone.guestbook.dao.GuestbookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
 <%
 								//객체리턴? 업캐스팅할땐 명시해야됨
 	List<GuestbookVo> list = (List)request.getAttribute("list");
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/gb" method="post">
	<input type="hidden" name ="a" value ="add"/>
	<table border=1 width=500>
		<tr>
			<td>이름</td><td><input type="text" name="name"></td>
			<td>비밀번호</td><td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td colspan=4><textarea name="message" cols=60 rows=5></textarea></td>
		</tr>
		<tr>
			<td colspan=4 align=right><input type="submit" VALUE="등록 "></td>
		</tr>
	</table>
	</form>
	<br>	
	<% 
		for(int i = 0; i< list.size(); i++){
	%>
		<table width=510 border=1>
			<tr>
				<td><%=list.size()-i %></td>
				<td><%=list.get(i).getName() %></td>
				<td><%=list.get(i).getRegDate() %></td>
				<td><a href="<%=request.getContextPath()%>/gb?a=deleteform&no=<%=list.get(i).getNo() %>">삭제</a></td>
			</tr>
			<tr>
				<td colspan=4><pre><%=list.get(i).getMessage()%></pre></td>
			</tr>
		</table>
		<br>
	<%
		}
	%>
	
</body>
</html>