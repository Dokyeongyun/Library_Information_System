<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${result != 1}">
    <script>
        alert('글 삭제하기에 실패했습니다. 잠시 후 다시 시도해주세요.');
        history.back();
    </script>
</c:if>

<c:if test="${result == 1}">
    <script>
        location.href='/boardMain.do?boardId=${boardId}';
    </script>
</c:if>
