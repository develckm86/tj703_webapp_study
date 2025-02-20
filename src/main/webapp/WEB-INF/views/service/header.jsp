<%@ page import="com.tj703.web_app_server_study.model2_service.dto.UserDto" %>
<%@ page pageEncoding="UTF-8" %>
<%
    Object loginUserObj=session.getAttribute("loginUser");
    //UserDto loginUser=(UserDto) loginUserObj; //loginUserObj==null 이면 오류
    Object msgObj=session.getAttribute("msg");
%>
<% if(msgObj!=null){
    session.removeAttribute("msg");
    out.println("<script>alert('"+msgObj+"')</script>");
}%>
<header style="
            padding:0 1em;
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: antiquewhite">
    <h3><a href="<%=request.getContextPath()%>/">HOME</a></h3>
    <%if(loginUserObj!=null) {//로그인된 상태
        UserDto loginUser = (UserDto) loginUserObj;
    %>
    <div>
        <span><%=loginUser.getEmail()%>(<%=loginUser.getUserId()%>)님 로그인</span>
        <a href="<%=request.getContextPath()%>/service/logout.do">로그아웃<b></b></a>
    </div>
    <%}%>
</header>
