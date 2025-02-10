<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mysql.cj.jdbc.Driver" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<%!
  public static final String URL="jdbc:mysql://localhost:3306/employees";
  public static final String USER="root";
  public static final String PASS="mysqlmysql";
  public static final String driver="com.mysql.cj.jdbc.Driver";
%>
<html>
<head>
    <title>부서리스트</title>
</head>
<body>
<%
  Class.forName(driver);
  String sql="SELECT * FROM departments";
  List<Map<String,String>> deptList=null;
  try(
          Connection conn=DriverManager.getConnection(URL,USER,PASS);
          Statement stmt=conn.createStatement();
          ResultSet rs=stmt.executeQuery(sql);
  ){
    deptList=new ArrayList<>();
    while (rs.next()){
      String deptNo=rs.getString("dept_no");
      String deptName=rs.getString("dept_name");
      Map<String,String> dept=new HashMap<>();
      dept.put("dept_no",deptNo);
      dept.put("dept_name",deptName);
      deptList.add(dept);
    }
    //out.print(deptList);
  } catch (Exception e) {
    //throw new RuntimeException(e); //접속 문제가 있다면 500 에러 발생
    response.sendError(500);
    e.printStackTrace();
    return;
  }
%>
  <h1>부서리스트</h1>
  <table>
    <thead>
      <tr>
        <th>부서번호</th>
        <th>부서이름</th>
        <th>부서상세(servlet+jsp)</th>
      </tr>
    </thead>
    <tbody>
      <%for(Map<String,String> dept : deptList){%>
      <tr>
        <td><%=dept.get("dept_no")%></td>
        <td><%=dept.get("dept_name")%></td>
        <td>
          <a href='l14deptDetail.do?dept_no=<%=dept.get("dept_no")%>'>
            부서상세
          </a>
        </td>
      </tr>
      <%}%>
    </tbody>
  </table>
</body>
</html>
