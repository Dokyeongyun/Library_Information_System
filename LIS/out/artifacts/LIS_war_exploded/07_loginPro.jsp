<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${result == 1}">
    <script>
        location.href='/main.do';
    </script>
</c:if>
<c:if test="${result != 1}">
    <script>
        alert('로그인에 실패했습니다. 입력정보를 확인해주세요.');
        history.back();
    </script>
</c:if>
