<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <div class="page_title_region">
        <div style="overflow: hidden; margin-bottom: 30px;">
            <img src="/img/board_icon.PNG" style="width: 50px; float: left; margin-right: 10px;">
            <h2 class="title_txt">게시판</h2>
        </div>
        <div id="boardTabMenu" style="position: relative; height: 50px;">
            <div class="bTM_wrapper bTM_horizontal">
                <ul class="bTM_container">
                    <c:set var="idx" value="1"/>
                    <c:forEach var="i" items="${boardList}">
                        <c:if test="${idx==boardId}">
                            <li class="selected bTM_thumbContainer" value="${i.boardId}"><a href="#" title="${i.boardName}">${i.boardName}</a></li>
                        </c:if>
                        <c:if test="${idx!=boardId}">
                            <li class="bTM_thumbContainer" value="${i.boardId}"><a href="#" title="${i.boardName}">${i.boardName}</a></li>
                        </c:if>
                        <c:set var="idx" value="${idx+1}"/>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    <div class="article_list_region">
        <div class="small_txt">총 <span style="font-weight: 600">${articleList.size()}</span> 건, <span style="font-weight: 600">1</span>/1페이지</div>
        <table class="article_list_table">
            <thead>
            <tr>
                <th width="5%">No.</th>
                <th width="30%">제목</th>
                <th width="30%">작성자</th>
                <th width="15%">작성일</th>
                <th width="10%">조회수</th>
                <th width="10%">첨부파일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="i" items="${articleList}">
                <tr>
                    <td>${i.articleId}</td>
                    <td class="article_title_tr"><a href="/showArticle.do?articleId=${i.articleId}">${i.title}</a></td>
                    <td>${i.writer}</td>
                    <td>${i.regDate}</td>
                    <td>${i.hit}</td>
                    <c:if test="${i.attachFile == ''}">
                        <td>O</td>
                    </c:if>
                    <c:if test="${i.attachFile != ''}">
                        <td>X</td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pagination_region">
        <div style="overflow: hidden;">
            <button class="btn writeBtn" onclick="location.href='/writeArticle.do'" style="font-size: 14px;">
                <img src="/img/pen__icon.PNG" style="width: 20px; margin-right: 4px;">
                글쓰기
            </button>
        </div>
        <ul class="pagination" style="width: fit-content; margin: 0 auto;">
            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">4</a></li>
            <li class="page-item"><a class="page-link" href="#">5</a></li>
            <li class="page-item"><a class="page-link" href="#">Next</a></li>
        </ul>
    </div>
    <div class="article_search_region">
        <select id="searchDate" class="form-control" style="width: 150px; display: inline-block">
            <option value="all">전체기간</option>
            <option value="1d">1일</option>
            <option value="1w">1주</option>
            <option value="1m">1개월</option>
            <option value="6m">6개월</option>
            <option value="1y">1년</option>
        </select>
        <select id="searchBy" class="form-control" style="width: 150px; display: inline-block">
            <option value="titleAndContent">제목+내용</option>
            <option value="title">제목만</option>
            <option value="writer">글작성자</option>
            <option value="comment">댓글내용</option>
            <option value="comment_writer">댓글작성자</option>
        </select>
        <input type="text" id="searchKeyword" class="form-control" placeholder="검색어를 입력해주세요"
               style="width: 200px; display: inline-block"/>
        <button class="btn" type="button" id="searchArticleBtn" style="background-color: #77b7b4; color: white">검색
        </button>
    </div>
</div>

<script>
    $(".bTM_thumbContainer").click(function(){
        location.href='boardMain.do?boardId='+$(this).val();
    });
    <!-- 게시판검색 -->
    $("#searchArticleBtn").click(function () {
        if (!invalidate_check()) {
            return false;
        }
        var keyword = $("#searchKeyword").val();
        location.href = '#';
    });
    <!-- 유효성 검사 -->
    function invalidate_check() {
        // 게시판 검색어 내용 공백 확인
        if ($("#searchKeyword").val() == "") {
            alert("검색어을 입력해주세요.");
            $("#searchKeyword").focus();
            return false;
        }
        return true;
    }
</script>