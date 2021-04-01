<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="firstIndex" value="1"/>
<c:set var="menu" value="1"/>

<div class="container">
    <div class="page_title_region">
        <div style="overflow: hidden; margin-bottom: 20px;">
            <img src="/img/loanBook_icon.PNG" style="width: 50px; margin-top: 5px; float: left; margin-right: 10px;">
            <h2 class="title_txt">독서이력 조회</h2>
        </div>
        <div class="page_navigation">
            <a href="/main.do">Home</a> >
            <a href="#">My Library</a> >
            <a href="#">독서이력 조회</a>
        </div>
    </div>
    <div class="loanHistoryRegion">
        <div style=" overflow: hidden">
            <div style="float: left">독서이력이 총 <strong>${loanList.size()}</strong> 건 조회되었습니다.</div>
            <button type="button" class="exportBtn" onclick="location.href='/exportLoanHistory.do'">
                <img src="/img/excel_icon.PNG" style="width: 30px; margin-right: 5px;">내보내기</button>
        </div>
        <hr>
        <table class="loanHistoryTable">
            <tr>
                <th width="5%">No.</th>
                <th width="10%">ISBN</th>
                <th width="40%">서명</th>
                <th width="15%">저자</th>
                <th width="15%">대출일</th>
                <th width="15%">반납일</th>
            </tr>
            <c:set var="index" value="1"/>
            <c:forEach var="i" items="${loanList}">
                <tr>
                    <td>${index}</td>
                    <td>${i.book.ISBN}</td>
                    <td>${i.book.bookName}</td>
                    <td>${i.book.authors}</td>
                    <td>${i.loan.loanDate}</td>
                    <td>${i.loan.returnDate}</td>
                    <c:set var="index" value="${index+1}"/>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

