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
            width: 100%;
            box-sizing: border-box;
            padding:0 1em;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: antiquewhite">
    <h3><a href="<%=request.getContextPath()%>/">HOME</a></h3>
    <%if(loginUserObj!=null) {//로그인된 상태
        UserDto loginUser = (UserDto) loginUserObj;
    %>
    <div>
        <%-- 해더에 작성하는 링크는 무조건 loot(/) 상대 경로를 사용해야한다.(해더가 모든 페이지에 포함되기 때문)

            ~~~~/user/list.do 현재 경로
            ./service/logout.do 가려는 경로

            /user/service/logout.do

            ~~~~/service/logout.do
            --%>
        <span><%=loginUser.getEmail()%>(<%=loginUser.getUserId()%>)님 로그인</span>
            &nbsp;/&nbsp;
        <a href="<%=request.getContextPath()%>/service/logout.do">로그아웃<b></b></a>
            &nbsp;/&nbsp;
        <a href="<%=request.getContextPath()%>/service/pwModify.do"><b>패스워드 수정</b></a>
    </div>
    <%}else{%>
    <div><a href="<%=request.getContextPath()%>/service/login.do"><b>로그인</b></a></div>
    <%}%>
</header>
