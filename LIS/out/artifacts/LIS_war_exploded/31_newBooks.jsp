<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="firstIndex" value="1"/>
<c:set var="menu" value="1"/>

<div class="container">
    <div class="page_title_region">
        <div style="overflow: hidden; margin-bottom: 20px;">
            <img src="/img/search_icon.PNG" style="width: 50px; margin-top: 5px; float: left; margin-right: 10px;">
            <h2 class="title_txt">신착도서</h2>
        </div>
        <div class="page_navigation">
            <a href="/main.do">Home</a> >
            <a href="#">Collections</a> >
            <a href="#">신착도서</a>
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
        <div style="height: 30px;">최근 한달 이내에 입수된 신착도서가 <strong>X</strong>권 있습니다.</div>
        <hr>
        <div class="CSH_result_menu">
            <button type="button">전체선택</button>
            <button type="button"><img src="/img/shelf_icon.PNG" style="width: 15px; margin-right: 4px;" alt="내서재에 담기">내서재에 담기</button>
            <button type="button"><img src="/img/reservation_icon.PNG" style="width: 20px; margin-right: 4px;" alt="예약하기">예약하기</button>
        </div>
        <div class="CSH_result_gallery">
            <ul>
                <c:forEach begin="1" end="10">
                    <li>
                        <div><img src="/img/book_image_sample.PNG" width="100%"></div>
                        <hr>
                        <div><input type="checkbox" name="checkedBook" style="margin-right: 10px;">선택</div>
                        <div class="gallery_title_txt">책 이름일므이름일므이름일므일므이름일므이름일므일므이름일므이름일므일므이름일므이름일므이름일므이름이름</div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="pagination_region">
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

</div>

<style>
    .CSH_result_gallery ul{
        overflow: hidden;
    }
    .CSH_result_gallery ul li {
        float: left;
        margin: 10px;
        border: 1px solid #ddd;
        padding: 20px;
        width: 18%;
    }
    .gallery_title_txt {
        height: 40px;
        max-height: 50px;
        font-weight: bold;
        line-height: 20px;
        overflow: hidden;
        text-overflow: clip;
    }
</style>
