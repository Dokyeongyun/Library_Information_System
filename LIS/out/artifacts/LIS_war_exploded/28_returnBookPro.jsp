<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${result == 1}">
    <script>
        var notice = '[${returnList[0].loaner}]님의 도서 반납 [${returnList.size()}] 건이 성공적으로 수행되었습니다.'
        alert(notice);
        location.href='/returnBookForm.do'
    </script>
</c:if>
<c:if test="${result == -2}">
    <script>
        alert('반납작업 실패');
        history.back();
    </script>
</c:if>
<c:if test="${result != 1}">
    <script>
        alert('반납작업이 중지되었습니다. 입력정보를 다시 확인 후 실행해주세요.');
        history.back();
    </script>
</c:if>
