<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${isModify}">
    <c:set var="formURL" value="/modifyArticlePro.do"/>
    <c:set var="title" value="${article.title}"/>
    <c:set var="content" value="${article.content}"/>
    <c:set var="pageTitle" value="글 수정하기"/>
</c:if>
<c:if test="${!isModify}">
    <c:set var="formURL" value="/writeArticlePro.do"/>
    <c:set var="title" value="제목을 입력해 주세요."/>
    <c:set var="content" value="내용을 입력해 주세요."/>
    <c:set var="pageTitle" value="글 작성하기"/>
</c:if>

<div class="container">
    <div class="page_title_region">
        <div style="overflow: hidden; margin-bottom: 30px;">
            <img src="/img/board_icon.PNG" style="width: 50px; float: left; margin-right: 10px;">
            <h2 class="title_txt">${pageTitle}</h2>
        </div>
    </div>
    <div class="page_content_region">
        <form action="${formURL}" method="post">
            <select name="boardId" id="boardId" class="form-control input_box" style="width: 50%">
                <option value="-">게시판을 선택해 주세요.</option>
                <c:forEach var="i" items="${boardList}">
                    <c:if test="${i.boardId == article.boardId}">
                        <option selected value="${i.boardId}">${i.boardName}</option>
                    </c:if>
                    <c:if test="${i.boardId != article.boardId}">
                        <option value="${i.boardId}">${i.boardName}</option>
                    </c:if>
                </c:forEach>
            </select>
            <input name="title" id="title" type="text" class="form-control input_box" placeholder="${title}"/>
            <textarea name="content" id="content" class="form-control" rows="20" placeholder="${content}" style="padding: 20px; margin-bottom: 20px;"></textarea>

            <div style="overflow:hidden;">
                <c:if test="${isModify}">
                    <input type="hidden" name="articleId" value="${article.articleId}">
                </c:if>
                <input type="submit" class="btn" value="작성하기" style="float: right; margin-right: 50px;"/>
                <button class="btn" style="float: right; margin-right: 20px;">목록으로</button>
            </div>
        </form>
    </div>
</div>
