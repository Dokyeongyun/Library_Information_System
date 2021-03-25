<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${isLogin == 'false'}">
    <script>
        alert('세션이 만료되었습니다. 로그인 후 이용해주세요.');
        location.href='/login.do';
    </script>
</c:if>

<c:if test="${result == 1}">
    <script>
        alert('회원정보가 수정되었습니다.');
        location.href='/myInfo.do';
    </script>
</c:if>
<c:if test="${result != 1}">
    <script>
        alert('회원정보에 실패했습니다. 잠시 후 다시 시도해주세요.');
        history.back();
    </script>
</c:if>


