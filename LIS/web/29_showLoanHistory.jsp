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
            <div style="float: left">대출중인 도서가 총 <strong>4</strong> 권 있습니다.</div>
            <button type="button" style="float: right; border: 1px solid black;padding: 5px 10px;background: darkblue;color: white;font-weight: bold;cursor: pointer;">내보내기</button>
        </div>
        <hr>
        <table class="loanHistoryTable">
            <tr>
                <th>번호</th>
                <th>ISBN</th>
                <th>서명</th>
                <th>저자</th>
                <th>대출일</th>
                <th>반납일</th>
            </tr>
            <tr>
                <td>1</td>
                <td>ISDFADSFKDF</td>
                <td>스프링 철저 입문</td>
                <td>주식회사 NTT 데이터 지음 / 신상재, 박윤미 옮김</td>
                <td>2021-04-02</td>
                <td>2021-04-02</td>
            </tr>
        </table>
    </div>
</div>

