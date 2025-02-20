<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="./header.jsp"%>

    <h1>유저 비밀번호 변경</h1>
    <form action="./pwModify.do" name="pwModifyForm" method="post">
        <p>
            변경 할 비밀번호: <label><input type="text" name="pw"></label>
        </p>
        <p>
            <small class="msg" id="pwMsg"></small>
        </p>
        <p>
            변경 할 비밀번호: <label><input type="text" name="pwConfirm"></label>
        </p>
        <p>
            <small class="msg" id="pwConfirmMsg"></small>
        </p>
        <p>
            <button>변경하기</button>
        </p>
    </form>
</body>
</html>
