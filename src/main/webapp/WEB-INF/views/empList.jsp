<%@ page import="java.util.List" %>
<%@ page import="com.tj703.web_app_server_study.model2.dto.L17EmpDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>사원 리스트</title>
</head>
<body>
  <h1>사원 리스트 </h1>
  <%
    Object empListObj=request.getAttribute("empList");
    if( empListObj!=null && empListObj instanceof List ){
      List<L17EmpDto> empList=(List<L17EmpDto> )request.getAttribute("empList");
    }else {
      response.sendRedirect("./index.html");
    }

  %>

</body>
</html>
