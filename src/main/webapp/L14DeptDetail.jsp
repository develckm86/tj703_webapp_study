<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>부서 상세 및 수정</title>
</head>
<%//req.setAttribute("dept",dept) : forward(렌더링)한 jsp에  dept 라는 객체를 전달
  Object deptObj=request.getAttribute("dept");
  Map<String,String> dept= (Map<String,String>) deptObj;
%>
<body>
  <h1>부서 상세 및 수정</h1>
  <form action="l05deptModifyAction.do" method="post">
    <p>
      <label>
        부서 번호  : <input type="text" name="dept_no" readonly value="<%=dept.get("dept_no")%>">
      </label>
    </p>
    <p>
      <label>
        부서 이름  : <input type="text" name="dept_name" value="<%=dept.get("dept_name")%>">
      </label>
    </p>
    <p>
      <button type="reset">초기화</button>
      <button type="submit">제출</button>
    </p>
  </form>



</body>
</html>
