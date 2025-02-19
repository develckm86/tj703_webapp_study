<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP - Hello World</title>
</head>
<body>
    <%
        Cookie [] cookies=request.getCookies();
        Cookie isBannerCookie=null;
        for(Cookie c : cookies){
            if(c.getName().equals("isBannerCookie")){
                isBannerCookie=c;
            }
        }
   %>


    <%
    if((isBannerCookie==null || !isBannerCookie.getValue().equals("1"))){
    %>
    <script>
        window.open("./L17Banner.jsp","_blank","width=300,height=300,left=1500,top=100,scrollbars=no,resizable=no");
    </script>
    <%}%>
    <h1>안녕~ 웹 앱 서버야~ 수정 테스트</h1>
    <br/>
    <h2>수업 링크</h2>
    <ul>
        <li><a href="hello-servlet">Hello Servlet 이동</a></li>
        <li><a href="resStudy.do">응답 수업</a></li>
        <li><a href="reqStudy.do">요청 수업</a></li>

        <li><a href="queryStringStudy.do?name=경민&age=39">쿼리스트링 수업(파라미터 name,age)</a></li>
        <li><a href="errorStudy.do">에러 처리 수업</a></li>
        <li><a href="jdbcDeptList.do">jdbc 부서리스트 수업</a></li>
        <li><a href="empCRUD.do">사원관리(CRUD) 웹앱 수업</a></li>
        <li><a href="deptCRUD.do"> 부서관리 웹앱 제작 과제</a></li>
        <li><a href="L12ViewTemplateJsp.jsp">view template jsp</a></li>
        <li><a href="L13DeptList.jsp">jsp로 구현하는 부서리스트</a></li>
        <li><a href="model2/empList.do"> 모델2로 구현한 사원 리스트 </a></li>
        <li><a href="./setCookie.do">쿠키 만들기 예제</a></li>
        <li><a href="./getCookie.do">쿠키 불러오기 예제</a></li>
        <li><a href="./setSession.do">세션 만들기 예제</a></li>
        <p>
            <%
                //HttpSession session=request.getSession();
                Object emailObj=session.getAttribute("email");
                Object nameObj=session.getAttribute("name");
            %>
            <b><%=emailObj%>(<%=nameObj%>)님 로그인 중</b>
        </p>
    </ul>
    <div>
        <h3>GET과 POST 차이점과 요청해더(request header body) 정보</h3>
        <p>GET : 브라우저에서  link 나 url 로  서버에 리소스를 요청하는 행위 -> 리소스 주세요! </p>
        <p>POST : 양식(form)을 제출해서 동적 리소스를 요청하는데 양식의 데이터를 파라미터로 전달하는 행위 -> 파라미터를 저장해 주세요!</p>
        <p>요청해더 : 서버에게 리소스를 요청할때 클라이언트 내역과 요청하는 리소스에 대한 상세한 정보
            => 서버가 어떻게 응답할 건지 판단 하도록 돕는다.
        </p>
        <p>post 방식으로 통신하면 파라미터(queryString)을 요청해더에 포함시켜 보냅니다.</p>
        <form method="post" action="postStudy.do">
            <p>
                <label>
                    유저아이디 :
                    <input type="text" name="userId" value="경민">
                </label>
            </p>
            <p>
                <label>
                    비밀번호 :
                    <input type="password" name="userPw" value="123456!@">
                </label>
            </p>
            <p>
                <button type="button">그냥 버튼</button>
                <button type="reset">양식 내부의 input 값을 최초값(default)으로 변경</button>
                <button type="submit">제출</button>
            </p>
        </form>

    </div>
    <h2>경로 수업</h2>
    <ul>
        <li>절대경로 :( https://naver.com, c://windows/.... ) 현재 위치와 무관하게 해당 경로의 리소스를 찾는다. </li>
        <li>상대경로 : (/book.com) 현재 위치를 기준으로 최상위 경로(root 경로)로 이동해서 book.com 을 찾는다
            <ol>
                <li>현재위치 https://naver.com/store/detail/web/detail.do  =>  https://naver.com/book.com</li>
                <li>현재위치 c://windows/store/detail/web/  => c://book.com </li>
                <li>현재위치 /Users/desktop/tomcat/ =>  /book.com</li>
            </ol>
        </li>
        <li>상대경로 : (./book.com  or  .book.com  or  book.com) 현재 경로에 있는 리소스 탐색
            <ol>
                <li>현재위치
                    https://naver.com/store/detail/web/detail.do
                    =>
                    https://naver.com/store/detail/web/book.com
                </li>
                <li>현재위치 c://windows/store/detail/web/  => c://windows/store/detail/web/book.com </li>
                <li>현재위치 /Users/desktop/tomcat/ =>  /Users/desktop/tomcat/book.com</li>
            </ol>
        </li>
        <li>상대경로 : (../book.com  or  ..book.com ) 현재 경로의 이전 폴더에 있는 리소스 탐색
            <ol>
                <li>현재위치
                    https://naver.com/store/detail/web/detail.do
                    =>
                    https://naver.com/store/detail/book.com
                </li>
                <li>현재위치 c://windows/store/detail/web/  => c://windows/store/detail/book.com </li>
                <li>현재위치 /Users/desktop/tomcat/ =>  /Users/desktop/book.com</li>
            </ol>
        </li>
        <li>상대경로 : (../../book.com) 현재 경로의 이전 폴더에 있는 리소스 탐색
            <ol>
                <li>현재위치
                    https://naver.com/store/detail/web/detail.do
                    =>
                    https://naver.com/store/book.com
                </li>
                <li>현재위치 c://windows/store/detail/web/  => c://windows/store/book.com </li>
                <li>현재위치 /Users/desktop/tomcat/ =>  /Users/book.com</li>
            </ol>
        </li>
    </ul>
</body>
</html>