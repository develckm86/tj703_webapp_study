<%@ page import="java.util.List" %>
<%@ page import="com.tj703.web_app_server_study.model2.dto.L17EmpDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>사원 리스트</title>
</head>
<body>
  <h1>사원 리스트 </h1>
  <p><a href="<%=request.getContextPath()%>/model2/empRegister.do">사원 등록</a></p>
<%--
 http://localhost:9999/ : root
 /web_app_server_study_war_exploded/ : contextPath (여러 웹앱을 동시에 실행하기 위해 생성한 경로)
 /model2/empRegister.do : 리소스 경로
 <%=request.getContextPath()%>/model2/empRegister.do
 /web_app_server_study_war_exploded/model2/empRegister.do

 http://localhost:9999/web_app_server_study_war_exploded/model2/model2/empRegister.do--%>
  
  <%
    List<L17EmpDto> empList=(List<L17EmpDto> )request.getAttribute("empList");
  %>
  <table>
    <thead>
      <tr>
        <th>사번</th>
        <th>이름(이름+성)</th>
        <th>탄생일</th>
        <th>상세보기(수정)</th>
      </tr>
    </thead>
    <tbody>
      <% for(L17EmpDto e:empList){ %>
        <tr>
          <td><%=e.getEmpNo()%></td>
          <td><%=e.getFirstName()%>&nbsp;<%=e.getLastName()%></td>
          <td><%=e.getBirthDate()%></td>
          <td>
            <a href="./model2/empDetail.do?emp_no=<%=e.getEmpNo()%>">상세</a>
          </td>
        </tr>
      <%}%>
    </tbody>
  </table>

</body>
</html>
