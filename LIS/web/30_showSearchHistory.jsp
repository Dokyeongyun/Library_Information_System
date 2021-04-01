<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="firstIndex" value="1"/>
<c:set var="menu" value="1"/>

<div class="container">
    <div class="page_title_region">
        <div style="overflow: hidden; margin-bottom: 20px;">
            <img src="/img/search_icon.PNG" style="width: 50px; margin-top: 5px; float: left; margin-right: 10px;">
            <h2 class="title_txt">검색 History</h2>
        </div>
        <div class="page_navigation">
            <a href="/main.do">Home</a> >
            <a href="#">Search</a> >
            <a href="#">검색 History</a>
        </div>
    </div>
    <div class="CSH_result_region">
        <div style="height: 30px;">검색 History가 총 <strong>${shList.size()}</strong> 건 있습니다.</div>
        <hr>
        <div class="CSH_result_list">
            <c:set var="index" value="1"/>
            <form action="/collectionSearchPro.do" method="get" style="text-align: center;">
                <table class="CSH_historyTable">
                    <tr>
                        <th width="10%">선택</th>
                        <th width="10%">No.</th>
                        <th >검색키워드</th>
                        <th width="10%">검색건수</th>
                        <th width="30%">검색일자</th>
                    </tr>
                    <c:forEach var="i" items="${shList}">
                        <tr>
                            <td><input type="radio" name="sh_id" value="${i.sh_id}"></td>
                            <td>${index}</td>
                            <td>${i.sh_keyword2}</td>
                            <td>${i.sh_resultSize}</td>
                            <td>${i.sh_date}</td>
                        </tr>
                        <c:set var="index" value="${index+1}"/>
                    </c:forEach>
                </table>
                <input type="submit" value="검색" class="CSH_historyBtn"/>
            </form>
        </div>
    </div>
</div>
