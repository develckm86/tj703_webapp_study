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
    <script>
        //form.name == id
        //console.log(pwModifyForm); //없으면 undefined
        const pwModifyForm=document.forms['pwModifyForm']; //없으면 null
        //console.log(pwMsg,pwConfirmMsg);
        const pwMsg=document.getElementById('pwMsg');
        const pwConfirmMsg=document.getElementById('pwConfirmMsg');
        //js 는 타입이 불분명한 언어기 때문에 객체를 바뀌지 않도록 상수로 정의
        //pwMsg=1;
        //form 내의 input 요소는 이름을 필드처럼 참조가능
        console.log(pwModifyForm.pw, pwModifyForm.pwConfirm);

        //input 요소 이벤트
        //onfocus : 마우스로 input 요소를 찍거나 tab으로 input요소를 갔을 때 (input 선택되었을 때)
        //onblur : focus 상태에서 벗어날 때
        //onchange : blur 인데 value 가 바꼈을 때
        //oninput : 입력할때 마다
        //input 키보드 이벤트 : 키보드의 어떤 키를 누렀는지 확인 가능
        //onkeydown
        //onkeyup
        //onkeypress

        pwModifyForm.pw.oninput=function (e){
            //this == pw == e.target
            console.log(this.value);
            if(this.value.length<4){
                pwMsg.innerText="비밀번호는 4글자 이상입니다,";
                pwMsg.style.color="red";
            }else{
                pwMsg.innerText="사용할 수 있는 비밀번호 입니다.";
                pwMsg.style.color="green";
            }
        }



    </script>
</body>
</html>
