<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="resultListSize" value="${resultList.size()}"/>
<c:set var="resultPerPage" value="10"/>
<c:set var="currentPage" value="1"/>
<c:set var="maxPage" value="${resultListSize / resultPerPage}"/>

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
        <div style="height: 30px;">페이지: <strong>1 / ${maxPage} 페이지</strong></div>
        <hr>

        <div class="CSH_result_menu">
            <button type="button">전체선택</button>
            <button type="button"><img src="/img/shelf_icon.PNG" style="width: 15px; margin-right: 4px;" alt="내서재에 담기">내서재에 담기</button>
            <button type="button"><img src="/img/reservation_icon.PNG" style="width: 20px; margin-right: 4px;" alt="예약하기">예약하기</button>
        </div>
        <div class="CSH_result_list">
            <ul>
                <li id="">
                    <dl>
                        <dt class="title">선택</dt>
                        <dd class="check"><input type="checkbox" title="선택"></dd>

                        <dt class="title">No</dt>
                        <dd class="num">1.</dd>

                        <dt class="title">책이미지</dt>
                        <dd class="book">
                            <a href="#">
                                <img id="#" src="/img/book_image_sample.PNG" width="100%" height="100%" alt="표지이미지">
                            </a>
                        </dd>

                        <dt class="title">서명</dt>
                        <dd class="title">
                            <a href="#">책 제목</a>
                        </dd>

                        <dt class="title">저자</dt>
                        <dd class="info">저자: XXX</dd>

                        <dt class="title">출판사</dt>
                        <dd class="info">출판사: XXX</dd>

                        <dt class="title">출판연도</dt>
                        <dd class="info">출판연도: 2021</dd>

                        <dt class="title">소장처</dt>
                        <dd class="info">소장처: Building1</dd>
                    </dl>
                </li>
            </ul>
        </div>

    </div>
</div>

<style>
    /* 도서 검색결과 메뉴 */
    .CSH_result_region {padding: 20px;border: 1px solid #ddd;overflow: hidden;line-height: 2.3em;}
    .CSH_result_menu {overflow: hidden;margin-bottom: 20px;}
    .CSH_result_menu span input {margin-right: 7px;vertical-align: middle;}
    .CSH_result_menu span {display: inline-block;height: 33px;margin-bottom: 5px;padding: 0 10px;border: 1px solid #ccc;color: #4c4c4c;font-size: 13px;}
    .CSH_result_menu button {display: inline-block;height: 33px;margin-bottom: 5px;padding: 0 10px;border: 1px solid #ccc;color: #4c4c4c;font-size: 13px;}

    /* 도서 검색결과 리스트 */
    dl, dt, dd, ul {list-style: none;margin: 0;padding: 0;}
    .CSH_result_list {}
    .CSH_result_list a {font-size: 18px;color: black;font-family: "NanumBarunGothic", sans-serif;}
    .CSH_result_list ul{width: 100%;margin-bottom: 30px;}
    .CSH_result_list ul li{position: relative;overflow: hidden;margin-bottom: 10px;padding: 20px 30px;border: 1px solid #e8e8e8;}
    .CSH_result_list ul li dl dt.title {display: none;}
    .CSH_result_list ul li dl dd.check {float: left;display: inline-block;margin: 0 15px 0 0;}
    .CSH_result_list ul li dl dd.num {position: relative;top: 3px;display: inline-block;color: #1a1a1a;font-size: 14px;float: left;margin-left: 0;}
    .CSH_result_list ul li dl dd.book {float: left;margin: 0 15px 0 10px;}
    .CSH_result_list ul .book {width: 150px;height: 200px;}
    .CSH_result_list ul li dl dd.title {position: relative;left: 0;margin-right: 70px;overflow: hidden;font-size: 16px;color: #1a1a1a;line-height: 1.2; width: 60%; font-weight: bold; margin-bottom: 10px; padding-bottom: 15px; border-bottom: 2px solid #1a1a1a}
    .CSH_result_list ul li dl dd {margin-bottom: 5px;margin-left: 170px; font-size: 14px;}
    .CSH_result_list ul li dl dd.info {margin-bottom: 7px;color: #919191;}
</style>
