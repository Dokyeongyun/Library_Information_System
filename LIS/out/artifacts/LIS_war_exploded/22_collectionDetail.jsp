<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="bookNo" value="1"/>

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

        <!-- 메뉴 -->
        <div class="CSH_result_menu">
            <button type="button"><img src="/img/shelf_icon.PNG" style="width: 15px; margin-right: 4px;" alt="내서재에 담기">내서재에 담기</button>
            <button type="button"><img src="/img/reservation_icon.PNG" style="width: 20px; margin-right: 4px;" alt="예약하기">예약하기</button>
        </div>
        <hr>

        <!-- 도서 상세정보 -->
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

        <!-- 도서 소장정보 -->
        <div class="CSH_storageInfo">
            <div class="storageInfoHeader">
                <h3>소장정보</h3>
            </div>
            <div class="storageInfoContent">
                <table>
                    <tr>
                        <th width="5%">No</th>
                        <th width="10%">등록번호</th>
                        <th width="20%">청구기호</th>
                        <th>소장처</th>
                        <th width="10%">도서상태</th>
                        <th width="10%">반납예정일</th>
                        <th width="10%">예약</th>
                    </tr>
                    <c:forEach var="i" items="${bookList}">
                        <tr>
                            <td>${bookNo}</td>
                            <td>${i.bookId}</td>
                            <td>${i.category}</td>
                            <td>${i.storageLocation}</td>
                            <c:if test="${i.bookStatus == '이용가능'}">
                                <td class="bookStatusTd possible">${i.bookStatus}</td>
                            </c:if>
                            <c:if test="${i.bookStatus == '대출중'}">
                                <td class="bookStatusTd rented">${i.bookStatus}</td>
                            </c:if>
                            <c:if test="${i.bookStatus == '분실도서'}">
                                <td class="bookStatusTd impossible">${i.bookStatus}</td>
                            </c:if>
                            <td></td>
                            <td>X</td>
                        </tr>
                        <c:set var="bookNo" value="${bookNo+1}"/>
                    </c:forEach>
                </table>
            </div>
        </div>

        <!-- 도서 관련링크 -->
        <div class="CSH_relatedLink">
            <div class="relatedLinkHeader">
                <h3>관련링크</h3>
            </div>
            <div class="relatedLinkContent">
                <table>
                    <tr><td><a href="https://book.naver.com/search/search.nhn?sm=sta_hty.book&query=${bookList[0].ISBN}">국내도서 책소개/목차 (Naver) > </a></td></tr>
                    <tr><td><a href="http://www.kyobobook.co.kr/product/detailViewKor.laf?barcode=${bookList[0].ISBN}">국내도서 책소개/목차 (교보문고) > </a></td></tr>
                    <tr><td><a href="https://www.amazon.com/s?k=${bookList[0].ISBN}&i=stripbooks&ref=nb_sb_ss_c_0_12">해외도서 책소개/목차 (Amazon) > </a></td></tr>
                </table>
            </div>
        </div>
    </div>
</div>