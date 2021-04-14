<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="firstIndex" value="1"/>
<c:set var="menu" value="1"/>

<div class="container">
    <div class="page_title_region">
        <div style="overflow: hidden; margin-bottom: 20px;">
            <img src="/img/search_icon.PNG" style="width: 50px; margin-top: 5px; float: left; margin-right: 10px;">
            <h2 class="title_txt">베스트대출</h2>
        </div>
        <div class="page_navigation">
            <a href="/main.do">Home</a> >
            <a href="#">Collections</a> >
            <a href="#">베스트대출</a>
        </div>
    </div>
    <div class="bestLoanRegion">
        <div style=" overflow: hidden">
            <div style="float: left"><b>TOP 100 목록 (최근 30일 자료집계)</b></div>
            <button type="button" class="exportBtn" onclick="location.href='#'">
                <img src="/img/excel_icon.PNG" style="width: 30px; margin-right: 5px;">내보내기</button>
        </div>
        <hr>
        <table class="bestLoanTable">
            <tr>
                <th width="7%">순위</th>
                <th width="40%">서명</th>
                <th width="23%">저자</th>
                <th width="20%">출판사</th>
                <th width="10%">출판연도</th>
            </tr>
            <c:set var="index" value="1"/>
            <c:forEach items="${bestLoanBookList}" var="i">
                <tr>
                    <td>${index}</td>
                    <td style="text-align: left; cursor:pointer;" onclick="location.href='/collectionDetail.do?ISBN=${i.ISBN}';">${i.bookName}</td>
                    <td>${i.authors}</td>
                    <td>${i.publisher}</td>
                    <td>${i.publicationYear}</td>
                    <c:set var="index" value="${index+1}"/>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

