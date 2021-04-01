<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
<c:set var="resultListSize" value="${resultList.size()}"/>
<c:set var="resultPerPage" value="10"/>
<c:set var="currentPage" value="1"/>
<c:set var="maxPage" value="${resultListSize / resultPerPage}"/>
<c:set var="firstIndex" value="${resultPerPage * (currentPage-1) + 1}"/>
--%>

<div class="container">
    <div class="page_title_region">
        <div style="overflow: hidden; margin-bottom: 20px;">
            <img src="/img/search_icon.PNG" style="width: 50px; margin-top: 5px; float: left; margin-right: 10px;">
            <h2 class="title_txt">상세정보</h2>
        </div>
        <div class="page_navigation">
            <a href="/main.do">Home</a> >
            <a href="#">Search</a> >
            <a href="/collectionSearch.do">소장자료</a> >
            <a href="#" onclick="history.back();">검색결과</a>
        </div>
    </div>

    <div class="CSH_detail_region">
        <div class="CSH_result_menu">
            <button type="button"><img src="/img/shelf_icon.PNG" style="width: 15px; margin-right: 4px;" alt="내서재에 담기">내서재에 담기</button>
            <button type="button"><img src="/img/reservation_icon.PNG" style="width: 20px; margin-right: 4px;" alt="예약하기">예약하기</button>
        </div>
        <hr>
        <div class="CSH_profile">
            <div class="profileHeader">
                <h3>${bookList[0].bookName}</h3>
                <p>${bookList[0].authors}</p>
            </div>
            <hr>
            <div class="profileContent">
                <div class="leftContent">
                    <img src="/img/book_image_sample.PNG" width="100%"/>
                </div>
                <div class="rightContent">
                    <table>
                        <tr>
                            <th>서명</th>
                            <td>${bookList[0].bookName}</td>
                        </tr>
                        <tr>
                            <th>저자</th>
                            <td>${bookList[0].authors}</td>
                        </tr>
                        <tr>
                            <th>출판사</th>
                            <td>${bookList[0].publisher}</td>
                        </tr>
                        <tr>
                            <th>출판연도</th>
                            <td>${bookList[0].publicationYear}</td>
                        </tr>
                        <tr>
                            <th>ISBN</th>
                            <td>${bookList[0].ISBN}</td>
                        </tr>
                        <tr>
                            <th>권</th>
                            <c:if test="${bookList[0].vol == 0}">
                                <td>-</td>
                            </c:if>
                            <c:if test="${bookList[0].vol != 0}">
                                <td>${bookList[0].vol}</td>
                            </c:if>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

        <div class="CSH_storageInfo">
            <div class="storageInfoHeader">
                <h3>소장정보</h3>
            </div>
            <div class="storageInfoContent">
                <table>
                    <tr>
                        <th>No</th>
                        <th>등록번호</th>
                        <th>청구기호</th>
                        <th>소장처</th>
                        <th>도서상태</th>
                        <th>반납예정일</th>
                        <th>예약</th>
                        <th>예약정보</th>
                    </tr>
                    <c:forEach var="i" items="${bookList}">
                        <tr>
                            <td>No</td>
                            <td>등록번호</td>
                            <td>청구기호</td>
                            <td>소장처</td>
                            <td>도서상태</td>
                            <td>반납예정일</td>
                            <td>예약</td>
                            <td>예약정보</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
