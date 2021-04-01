<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${result == 1}">
    <script>
        var notice = '[${loanVO.loaner}]님의 [${loanVO.bookId}] 도서 대출작업이 성공적으로 수행되었습니다.\n'
        +'대출 반납기한은 [${loanVO.returnDeadline}] 입니다.'
        alert(notice);
        location.href='/loanBookForm.do'
    </script>
</c:if>
<c:if test="${result == -2}">
    <script>
        alert('대출할 수 없는 도서입니다.');
        history.back();
    </script>
</c:if>
<c:if test="${result != 1}">
    <script>
        alert('대출작업이 중지되었습니다. 입력정보를 다시 확인 후 실행해주세요.');
        history.back();
    </script>
</c:if>
