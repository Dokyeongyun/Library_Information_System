<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <div class="page_title_region">
        <div style="overflow: hidden; margin-bottom: 30px;">
            <img src="/img/board_icon.PNG" style="width: 50px; float: left; margin-right: 10px;">
            <h2 class="title_txt">글 작성하기</h2>
        </div>
    </div>
    <div class="page_content_region">
        <form action="/writeArticlePro.do" method="post">
            <select name="boardId" id="boardId" class="form-control input_box" style="width: 50%">
                <option value="-">게시판을 선택해 주세요.</option>
                <c:forEach var="i" items="${boardList}">
                    <option value="${i.boardId}">${i.boardName}</option>
                </c:forEach>
            </select>
            <input name="title" id="title" type="text" class="form-control input_box" placeholder="제목을 입력해 주세요."/>
            <textarea name="content" id="content" class="form-control" rows="20" placeholder="내용을 입력해 주세요." style="padding: 20px; margin-bottom: 20px;"></textarea>

            <div style="overflow:hidden;">
                <input type="submit" class="btn" value="작성하기" style="float: right; margin-right: 50px;"/>
                <button class="btn" style="float: right; margin-right: 20px;">목록으로</button>
            </div>
        </form>
    </div>
</div>

<style>
    .page_content_region {
        padding: 20px;
        border: 2px solid #62696c;
        border-radius: 20px;
    }
    .input_box {
        height: 40px;
        margin-bottom: 10px;
    }
</style>