<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="resultListSize" value="${resultList.size()}"/>
<c:set var="resultPerPage" value="10"/>
<c:set var="currentPage" value="1"/>
<c:set var="maxPage" value="${resultListSize / resultPerPage}"/>
<c:set var="firstIndex" value="${resultPerPage * (currentPage-1) + 1}"/>

<div class="container">
    <div class="page_title_region">
        <div style="overflow: hidden; margin-bottom: 20px;">
            <img src="/img/search_icon.PNG" style="width: 50px; margin-top: 5px; float: left; margin-right: 10px;">
            <h2 class="title_txt">소장자료</h2>
        </div>
        <div class="page_navigation">
            <a href="/main.do">Home</a> >
            <a href="#">Search</a> >
            <a href="/collectionSearch.do">소장자료</a>
        </div>
    </div>

    <!-- 다시 검색하기 & 결과 내 검색 -->
    <div class="CSH_form_region">
        <form id="CSH_form" action="/collectionSearchPro.do" method="get">
            <div class="CSH_keyword_region">
                <div style="margin-bottom: 15px; overflow: hidden;">
                    <strong>검색키워드 입력</strong>
                    <div style="float: right; margin-right: 130px; font-family: 'NanumBarunGothic', sans-serif">
                        <input type="checkbox" name="isRefine" value="Y" style="margin-right: 5px;">결과 내 검색
                    </div>
                </div>
                <div id="keywordInputRegion">
                    <div class="form-group input-group" id="keywordForm1">
                        <select name="search_type" class="CSH_input_box">
                            <option value="all">전체</option>
                            <option value="bookName">서명</option>
                            <option value="authors">저자</option>
                            <option value="publisher">출판사</option>
                            <option value="ISBN">ISBN</option>
                        </select>
                        <input type="hidden" name="operator" value="AND">
                        <input type="hidden" name="publicationYear1" value="0">
                        <input type="hidden" name="publicationYear2" value="9999">
                        <input type="text" name="keyword" class="CSH_input_box" style="flex: auto" placeholder="검색 키워드를 입력해주세요.">
                        <input type="submit" class="CSH_input_box" style="background: #2F5597; color: white" value="검색"/>
                        <button type="button" class="CSH_input_box" style="background: white; color: #2F5597" onclick="location.href='/collectionSearch.do'">상세검색</button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div class="CSH_result_region">
        <div style="height: 30px;">검색어: <strong>${searchInfo}</strong></div>
        <div style="height: 30px;">검색 결과: <strong>총 ${resultListSize} 건</strong></div>
        <div style="height: 30px;" id="pageInfo"></div>
        <div style="height: 30px;">페이지당 출력개수: <strong>${resultPerPage}</strong> 건</div>
        <hr>

        <div class="CSH_result_menu">
            <button type="button">전체선택</button>
            <button type="button"><img src="/img/shelf_icon.PNG" style="width: 15px; margin-right: 4px;" alt="내서재에 담기">내서재에 담기</button>
            <button type="button"><img src="/img/reservation_icon.PNG" style="width: 20px; margin-right: 4px;" alt="예약하기">예약하기</button>
        </div>
        <div class="CSH_result_list">
            <c:forEach var="book" items="${resultList}">
                <ul>
                    <li id="list${firstIndex}">
                        <dl>
                            <dt class="title">선택</dt>
                            <dd class="check"><input type="checkbox" title="선택"></dd>

                            <dt class="title">No</dt>
                            <dd class="num">${firstIndex}.</dd>
                            <c:set var="firstIndex" value="${firstIndex+1}"/>

                            <dt class="title">표지이미지</dt>
                            <dd class="book">
                                <a href="/collectionDetail.do?ISBN=${book.ISBN}">
                                    <img src="/img/book_image_sample.PNG" width="100%" height="100%" alt="표지이미지">
                                </a>
                                <c:if test="${book.bookStatus == '이용가능'}">
                                    <p class="bookStatus possible">${book.bookStatus}</p>
                                </c:if>
                                <c:if test="${book.bookStatus == '대출중'}">
                                    <p class="bookStatus rented">${book.bookStatus}</p>
                                </c:if>
                                <c:if test="${book.bookStatus == '분실도서'}">
                                    <p class="bookStatus impossible">${book.bookStatus}</p>
                                </c:if>
                            </dd>

                            <dt class="title">서명</dt>
                            <dd class="title">
                                <a href="/collectionDetail.do?ISBN=${book.ISBN}">${book.bookName}</a>
                            </dd>

                            <dt class="title">저자</dt>
                            <dd class="info">저자: ${book.authors}</dd>

                            <dt class="title">출판사</dt>
                            <dd class="info">출판사: ${book.publisher}</dd>

                            <dt class="title">출판연도</dt>
                            <dd class="info">출판연도: ${book.publicationYear}</dd>

                            <dt class="title">권</dt>
                            <c:if test="${book.vol == 0}">
                                <dd class="info">권: -</dd>
                            </c:if>
                            <c:if test="${book.vol != 0}">
                                <dd class="info">권: ${book.vol}</dd>
                            </c:if>

                            <dt class="title">소장처</dt>
                            <dd class="info">소장처: ${book.storageLocation}</dd>
                        </dl>
                    </li>
                </ul>
            </c:forEach>
        </div>
    </div>
</div>
<script>
    var maxPage = Math.ceil(${resultListSize / resultPerPage});
    $("#pageInfo").html('페이지: <strong>1 / '+maxPage+' 페이지</strong>')
</script>