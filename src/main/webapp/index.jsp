<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2020/3/4
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="mytid" prefix="SimpleTagLibrary" %>
<%@ taglib uri="myparm" prefix="parm" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
heeloo1<SimpleTagLibrary:hello /><br>
heeloo2<parm:fors  map="${map}" b="${bbb}"/>
<c:if></c:if>

</body>
</html>
