<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <div class="page_title_region">
        <div style="overflow: hidden; margin-bottom: 20px;">
            <img src="/img/loanBook_icon.PNG" style="width: 50px; margin-top: 5px; float: left; margin-right: 10px;">
            <h2 class="title_txt">반납 도서정보 입력</h2>
        </div>
        <div class="page_navigation">
            <a href="/main.do">Home</a> >
            <a href="#">Return</a>
        </div>
    </div>
    <div class="loanBookContent">
        <form action="/returnBookPro.do" method="post">
            <table>
                <tr>
                    <th>도서 ID</th>
                    <td><input type="text" name="bookId"/></td>
                </tr>
                <tr>
                    <th>사용자 ID</th>
                    <td><input type="text" name="loaner"/></td>
                </tr>
                <tr>
                    <th></th>
                    <td><input type="submit" value="반납실행" style="width: 100%"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<style>
    .loanBookContent{padding: 20px;}
    .loanBookContent table th {padding: 20px;}
</style>