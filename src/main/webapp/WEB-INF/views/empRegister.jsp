<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>사원 등록 폼</title>
</head>
<body>
  <h1>사원 등록 폼</h1>
  <form action="<%=request.getContextPath()%>/model2/empRegister.do" method="post">
    <p>
      <label>
        사번 : <input type="text" name="emp_no" value="11">
      </label>
    </p>
    <p>
      <label>
        이름 : <input type="text" name="first_name" value="길동">
      </label>
    </p>
    <p>
      <label>
        성씨 : <input type="text" name="last_name" value="홍">
      </label>
    </p>
    <p>
      성별 :
      <label>남성<input type="radio" name="gender" value="M" checked></label>
      <label>여성<input type="radio" name="gender" value="F"></label>
    </p>
    <p>
      <label>생일 :<input type="date" name="birth_date" value="1986-05-25" ></label>
    </p>
    <p>
      <label>입사일 :<input type="date" name="hire_date" value="2025-01-01"></label>
    </p>
    <p>
      <button type="reset">초기화</button>
      <button type="submit">제출</button>
    </p>
  </form>
</body>
</html>
