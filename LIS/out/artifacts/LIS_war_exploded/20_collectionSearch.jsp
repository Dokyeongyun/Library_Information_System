<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

    <!-- Collection Search Form Region -->
    <form id="CSH_form">
        <div class="CSH_form_region">
            <div class="CSH_keyword_region">
                <div style="margin-bottom: 15px;"><strong>검색키워드 입력</strong></div>
                <div id="keywordInputRegion">
                    <div class="form-group input-group" id="keywordForm1">
                        <select name="search_type1" class="CSH_input_box">
                            <option value="전체">전체</option>
                            <option value="서명">서명</option>
                            <option value="저자">저자</option>
                            <option value="출판사">출판사</option>
                            <option value="ISBN">ISBN</option>
                        </select>
                        <input type="text" name="keyword" class="CSH_input_box" style="flex: auto" placeholder="검색 키워드를 입력해주세요.">
                        <select name="operator1" class="CSH_input_box">
                            <option selected value="AND">AND</option>
                            <option value="OR">OR</option>
                        </select>
                    </div>
                </div>
                <div class="CSH_menu_region">
                    <button type="button" class="btn" id="removeFormBtn" >-</button>
                    <button type="button" class="btn" id="addFormBtn" >+</button>
                </div>
            </div>
            <hr>
            <div class="CSH_limiter_region">
                <div style="margin-bottom: 15px;"><strong>검색제한자 설정</strong></div>
                <div class="limiter_table_wrapper">
                    <table id="limiter_table">
                        <tr>
                            <th>소장처</th>
                            <td>
                                <select id="storageLocation" class="CSH_input_box">
                                    <option value="Building1">Building1</option>
                                    <option value="Building2">Building2</option>
                                    <option value="Building3">Building3</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>발행년도</th>
                            <td>
                                <div style="overflow: hidden">
                                    <input name="publicationYear1" class="CSH_input_box" type="number" placeholder="발행 시작연도"> -
                                    <input name="publicationYear2" class="CSH_input_box" type="number" placeholder="발행 종료연도">
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
                <hr>
                <div class="limiter_menu">
                    <input type="submit" value="검색" title="검색">
                    <a href="#" title="검색 History">검색 History</a>
                    <input type="reset" value="다시쓰기" title="다시쓰기">
                </div>
            </div>
        </div>
    </form>

</div>

<style>

</style>

<script>
    var keywordFormCount = 1;

    $("#addFormBtn").click(function (){
        if(keywordFormCount >= 3) {
            alert('검색키워드는 최대 3개까지만 입력하실 수 있습니다.');
            return false;
        }
        keywordFormCount++;
        var keywordForm =
            '<div class="form-group input-group" id="keywordForm'+keywordFormCount+'">'+
            '<select name="search_type'+keywordFormCount+'" class="CSH_input_box">'+
            '<option value="전체">전체</option>'+
            '<option value="서명">서명</option>'+
            '<option value="저자">저자</option>'+
            '<option value="출판사">출판사</option>'+
            '<option value="ISBN">ISBN</option>'+
            '</select>'+
            '<input type="text" name="keyword" class="CSH_input_box" style="flex: auto" placeholder="검색 키워드를 입력해주세요.">'+
            '<select name="operator'+keywordFormCount+'" class="CSH_input_box">'+
            '<option selected value="AND">AND</option>'+
            '<option value="OR">OR</option>'+
            '</select>'+
            '</div>';
        $('#keywordInputRegion').append(keywordForm);
    });

    $("#removeFormBtn").click(function (){
        if(keywordFormCount <= 1) {
            return false;
        }
        $("#keywordForm"+keywordFormCount).remove();
        keywordFormCount--;
    });
</script>