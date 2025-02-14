<%@ page import="com.tj703.web_app_server_study.model2.dto.L17EmpDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>사원 상세 및 수정 폼</title>
</head>
<body>
  <h1>사원 상세 및 수정 폼</h1>
  <%L17EmpDto emp=(L17EmpDto) request.getAttribute("emp");%>

  <form action="<%=request.getContextPath()%>/model2/empModify.do" method="post">
    <p>
      <label>
        사번 : <input type="text" name="emp_no" value="<%=emp.getEmpNo()%>">
      </label>
    </p>
    <p>
      <label>
        이름 : <input type="text" name="first_name" value="<%=emp.getFirstName()%>">
      </label>
    </p>
    <p>
      <label>
        성씨 : <input type="text" name="last_name" value="<%=emp.getLastName()%>">
      </label>
    </p>
    <p>
      성별 :
      <label>남성<input type="radio" name="gender" value="M"
        <%if(emp.getGender().equals("M")){%> checked <%}%> >
      </label>
      <label>여성<input type="radio" name="gender" value="F"
        <%if(emp.getGender().equals("F")){%> checked <%}%>
      ></label>
    </p>
    <p>
      <label>생일 :<input type="date" name="birth_date" value="<%=emp.getBirthDate()%>" ></label>
    </p>
    <p>
      <label>입사일 :<input type="date" name="hire_date" value="<%=emp.getHireDate()%>"></label>
    </p>
    <p>
      <button type="reset">초기화</button>
      <button type="submit">제출</button>
      <a href="./empRemove.do?emp_no=<%=emp.getEmpNo()%>">삭제</a>
    </p>
  </form>
</body>
</html>
