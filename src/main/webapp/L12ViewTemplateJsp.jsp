<%! //서블릿 클래스의 전역
    int a=10;
    public static final String NAME="최경민";
// %>
<%
    //public int a=20;
    int a=20;
    int b=a*this.a;
%>
<%-- doGet(request,response){--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- response.setContentType("text/html;charset=UTF-8")
    PrintWrite out=respoonse.getPrintWrite()
--%>
<html>
<%--out.println("<html>")--%>
<head>
    <title>Title</title>
</head>
<body>
    <h1>view template jsp(Java Servlet Page)</h1>
    <p>jsp 는 서블릿에서 출력 부분이 자바 코드 작성 부분과 역전된 페이지</p>
    <p>jsp 문서는 javac 가 아닌 다른 컴파일러에 의해서 실시간으로 컴파일 실행된다. </p>
    <p>실시간으로 컴파일 실행 (인터프리터 언어==js): 엔진이 실행하면서 컴파일 => 배포를 할 필요가 없다</p>
    <p>
        13*88=
        <%
            out.println(13*88);
            //자바코드 내부 (doGet()함수 내부의 자바코드)
        %>
    </p>
    <p>
        13*88=<%=13*88%>
    </p>
    <p>백엔드에서 view 를 렌더링하는 문서를 뷰템플릿(view template)이라하고 보통 서버언어(java)
        를 컴파일하거나 실행하는 엔진(javac jvm(java))이 있고 뷰템플릿을 컴파일하고 실행하는 엔진이
        따로 존재해서 뷰템플릿 엔진이라고도 부른다.
    </p>

    <h2>jsp를 사용하는 이유!</h2>
    <ul>
        <li>servlet 에서 view 를 작성하기 힘들기 때문</li>
        <li>servlet 이 자바로 되어 있어서 수정하면 매번 컴파일 후 배포해야하기 때문에 개발 속도가 느려진다.</li>
        <li>service(db model)와 servlet(요청 응답처리 controller)과 view(UI html)를 분리하기 위해서</li>
        <li>프론트엔드 개발자(html css js, 퍼블리셔)가 개발하기 용이하도록 하기 위해 </li>
    </ul>
    <h2>구현 뷰 템플릿 엔진 jsp</h2>
    <ul>
        <li>뷰템플릿 엔진을 배포가 되는 위치에 둘 수 있기 때문에(정적리소스 취급) 리소스를 갈취달할 수 있다.</li>
        <li>jsp는 java가 할수 있는 모든 코드를 작성할 수 있어서 보안에 취약 </li>
        <li>태그 외의 코드를 작성하기 때문에 (< %)  전반적으로 지저분하고 재사용이 어렵다.  </li>
    </ul>
</body>
</html>
<%--}
doPost(request,response){
    doGet(request,response); //해당 페이지를 호출해도 무조건 get() 방식이 동작함
}
--%>

