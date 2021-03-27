<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${result == 1}">
    <script>
        location.href='/showArticle.do?articleId=${articleId}';
    </script>
</c:if>

<c:if test="${result != 1}">
    <script>
        alert('댓글 작성에 실패했습니다. 잠시 후 다시 시도해주세요.');
        history.back();
    </script>
</c:if>