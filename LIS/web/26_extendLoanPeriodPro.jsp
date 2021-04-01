<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${result == 1}">
    <script>
        alert('대출기간이 1주 연장되었습니다.');
        location.href='/manageMyLoanInfo.do';
    </script>
</c:if>
<c:if test="${result != 1}">
    <script>
        alert('대출 연장에 실패했습니다. 잠시 후 다시 시도해주세요.');
        history.back();
    </script>
</c:if>
