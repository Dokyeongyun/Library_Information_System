<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="firstIndex" value="1"/>
<c:set var="menu" value="1"/>

<div class="container">
    <div class="page_title_region">
        <div style="overflow: hidden; margin-bottom: 20px;">
            <img src="/img/loanBook_icon.PNG" style="width: 50px; margin-top: 5px; float: left; margin-right: 10px;">
            <h2 class="title_txt">대출/예약/신청도서 관리</h2>
        </div>
        <div class="page_navigation">
            <a href="/main.do">Home</a> >
            <a href="#">My Library</a> >
            <a href="#">대출/예약/신청도서 관리</a>
        </div>
    </div>
    <div id="boardTabMenu" style="position: relative; height: 50px;">
        <div class="bTM_wrapper bTM_horizontal">
            <ul class="bTM_container">
                <li class="selected bTM_thumbContainer" value=""><a href="#" title="대출도서">대출도서</a></li>
                <li class="bTM_thumbContainer" value=""><a href="#" title="예약도서">예약도서</a></li>
                <li class="bTM_thumbContainer" value=""><a href="#" title="신청도서">신청도서</a></li>
            </ul>
        </div>
    </div>
    <div class="CSH_result_region">
        <div style="height: 30px;">대출중인 도서가 총 <strong>${loanBookList.size()}</strong> 권 있습니다.</div>
        <hr>

        <div class="CSH_result_menu">
            <button type="button">전체선택</button>
            <button type="button"><img src="/img/shelf_icon.PNG" style="width: 15px; margin-right: 4px;" alt="내서재에 담기">내서재에 담기</button>
            <button type="button"><img src="/img/refresh_icon.PNG" style="width: 25px; margin-right: 4px;" alt="내서재에 담기">연장하기</button>
        </div>
        <div class="CSH_result_list">
            <c:forEach var="i" items="${loanBookList}">
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
                                <a href="/collectionDetail.do?ISBN=${i.book.ISBN}">
                                    <img src="/img/book_image_sample.PNG" width="100%" height="100%" alt="표지이미지">
                                </a>
                                <p class="loanDateBox_txt">대출일자</p>
                                <p class="loanDateBox">${i.loan.loanDate.substring(0,10)}</p>
                                <p class="returnDeadlineBox_txt">반납일자</p>
                                <p class="returnDeadlineBox">${i.loan.returnDeadline.substring(0,10)}</p>
                                <c:if test="${i.loan.isExtended == 0}">
                                    <a href="/extendLoanPeriod.do?loanId=${i.loan.loanId}">
                                        <p class="possible extendBox">연장하기</p>
                                    </a>
                                </c:if>
                                <c:if test="${i.loan.isExtended != 0}">
                                    <p class="impossible extendBox">연장불가</p>
                                </c:if>
                            </dd>

                            <dt class="title">서명</dt>
                            <dd class="title">
                                <a href="/collectionDetail.do?ISBN=${i.book.ISBN}">${i.book.bookName}</a>
                            </dd>

                            <dt class="title">저자</dt>
                            <dd class="info">저자: ${i.book.authors}</dd>

                            <dt class="title">출판사</dt>
                            <dd class="info">출판사: ${i.book.publisher}</dd>

                            <dt class="title">출판연도</dt>
                            <dd class="info">출판연도: ${i.book.publicationYear}</dd>

                            <dt class="title">권</dt>
                            <c:if test="${i.book.vol == 0}">
                                <dd class="info">권: -</dd>
                            </c:if>
                            <c:if test="${i.book.vol != 0}">
                                <dd class="info">권: ${i.book.vol}</dd>
                            </c:if>

                            <dt class="title">소장처</dt>
                            <dd class="info">소장처: ${i.book.storageLocation}</dd>
                        </dl>
                    </li>
                </ul>
            </c:forEach>
        </div>
    </div>
</div>
