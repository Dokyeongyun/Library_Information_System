<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <div class="page_title_region">
        <div style="overflow: hidden; margin-bottom: 30px;">
            <img src="/img/board_icon.PNG" style="width: 50px; float: left; margin-right: 10px;">
            <h2 class="title_txt">${article.boardName}</h2>
        </div>
    </div>
    <div class="page_content_region">
        <div class="article_info_region">

            <!-- Title & Writer Info & Menu -->
            <div class="article_info_header_region">
                <div class="board_type_nav_txt"><a href="#">${article.boardName} ></a></div>
                <div class="article_title_txt">${article.title}</div>
                <div class="article_writer_info_region">
                    <img src="/img/profile.PNG" class="profile_sm"/>
                    <div class="article_writer_txt">${article.writer}</div>
                    <div class="article_reg_time_txt">${article.regDate}    조회수 : ${article.hit}</div>
                    <div class="ArticleTool">
                        <ul style="list-style: none">
                            <li style="float: left; margin-right: 10px;">
                                <button type="button" class="btn btn-primary" style="font-size: 13px">댓글  ${commentList.size()}개</button>
                            </li>
                            <li style="float: left; margin-right: 10px;">
                                <div class="dropdown">
                                    <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown" style="font-size: 13px"  >메뉴</button>
                                    <ul class="dropdown-menu">
                                        <c:if test="${article.writer == sessionScope.get('loginUser').userId}">
                                            <li><a class="dropdown-item" href="#">수정하기</a></li>
                                            <li><a class="dropdown-item" href="#">삭제하기</a></li>
                                        </c:if>
                                        <c:if test="${article.writer != sessionScope.get('loginUser').userId}">
                                            <li><a class="dropdown-item" href="#">스크랩하기</a></li>
                                            <li><a class="dropdown-item" href="#">URL복사</a></li>
                                        </c:if>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <!-- Content -->
            <div class="article_info_content_region">
                <div class="article_content_txt">${article.content}</div>
            </div>

            <!-- Comment -->
            <div class="article_comment_region">
                <div class="article_comment_header_region">
                    <div class="article_comment_header_txt">댓글  ${commentList.size()}개</div>
                </div>
                <div class="article_comment_list_region">
<%--                    <c:forEach var="i" items="${commentList}">
                        <div class="article_writer_info_region">
                            <img src="/static/img/profile.PNG" class="profile_sm"/>
                            <div class="article_writer_txt">${i.nickName}</div>
                            <div class="comment_content_txt">${i.content}</div>
                            <div class="article_reg_time_txt">${i.createTime}</div>
                            <div class="horizontal_gray"></div>
                        </div>
                    </c:forEach>--%>
                </div>
                <div class="article_comment_write_region">
                    <div class="article_writer_txt">
                        <c:if test="${sessionScope.get('loginUser')==null}">
                            손님
                        </c:if>
                        <c:if test="${sessionScope.get('loginUser')!=null}">
                            ${sessionScope.get("loginUser").userId}
                        </c:if>
                    </div>
                    <div class="article_comment_write_content">
                        <textarea class="form-control noresize" rows="5" placeholder="댓글을 남겨보세요." id="commentContent" style="padding: 20px"></textarea>
                    </div>
                    <div class="article_comment_menu">
                        <button type="button" class="btn button_right" id="writeCommentBtn">등록</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<style>
    .page_content_region {
        padding: 20px;
        border: 2px solid #62696c;
        border-radius: 20px;
    }
    .input_box {
        height: 40px;
        margin-bottom: 10px;
    }
    .ArticleTool {
        position: absolute;
        right: 70px;
        bottom: 15px;
        font-size: 13px;
        line-height: 18px;
    }
    .article_info_region {
        border-bottom: 1px solid black;
        margin: 10px 20px;
        overflow: hidden;
        padding: 0 20px;
    }
    .article_info_header_region {
        padding: 20px;
        border-bottom: 1px solid #ddd;
    }
    .board_type_nav_txt {
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 10px;
    }
    .article_title_txt {
        font-size: 30px;
        font-weight: bold;
        margin-bottom: 10px;
    }
    .article_writer_info_region {
        position: relative;
        padding: 10px 5px;
        min-height: 60px;
        height: fit-content;
    }
    .article_writer_txt {
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 5px;
    }
    .comment_content_txt {
        font-size: 14px;
        margin-bottom: 5px;
    }
    .profile_sm {
        float: left;
        width: 4%;
        margin-right: 15px;
    }
    .article_reg_time_txt {
        font-size: 13px;
        color: #8f8f8f;
        margin-left: 40px;
    }
    .article_info_content_region {
        padding: 30px;
        border-bottom: 1px solid #ddd;
    }
    .article_content_txt {
        font-size: 16px;
        font-weight: 500;
    }
    .article_comment_region {
        padding: 20px;
    }
    .article_comment_header_region {
        padding: 20px 0;
    }
    .article_comment_header_txt {
        font-size: 20px;
        font-weight: bolder;
    }
    .horizontal_gray {
        padding: 5px;
        border-bottom: 1px solid #ddd;
    }
    .article_comment_write_region {
        border: 1px solid #ddd;
        padding: 20px;
        margin: 20px 0;
        min-height: 150px;
    }
    .article_comment_write_content {
        padding: 20px 0;
        font-size: 15px;
    }
    .noresize {
        resize: none; /* 사용자 임의 변경 불가 */
        /*resize: both; !* 사용자 변경이 모두 가능 *!*/
        /*resize: horizontal; !* 좌우만 가능 *!*/
        /*resize: vertical; !* 상하만 가능 *!*/
    }
    .article_comment_menu {
        overflow: hidden;
    }
    .input_box {
        height: 40px;
        margin-bottom: 10px;
    }
</style>