<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <div>
    <h1>UserManagement login Form</h1>
    <form action="./login.do" method="post">
      <p>
        <label>
          <b>이메일</b><input type="text" name="email" value="user1@example.com" />
        </label>
      </p>
      <p>
        <label>
          <b>비밀번호</b><input type="password" name="password" value="9999" />
        </label>
      </p>
      <p>
        <label>한달간 로그인 유지(쿠키)<input type="checkbox" name="auto_login" value="1"></label>
      </p>
      <p>
        <label>이메일 기억하기(쿠키)<input type="checkbox" name="auto_email" value="1"></label>
      </p>
      <p>
        <%--<button type="button" onclick="window.location.href='./signup.do'">회원가입</button>--%>
        <a href="./signup.do">회원가입</a>
        <%--     a태그 -> nav 웹에서 네비게이션의 역할 (검색엔진 봇에게 사이트의 ui 를 알려주는 역할)  --%>
        <button type="submit">로그인</button>
      </p>
    </form>
  </div>
</body>
</html>
