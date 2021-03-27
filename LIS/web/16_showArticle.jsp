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
                <!-- Comment Header -->
                <div class="article_comment_header_region">
                    <div class="article_comment_header_txt">댓글  ${commentList.size()}개</div>
                </div>

                <!-- Comment List -->
                <div class="article_comment_list_region">

                </div>

                <!-- Comment Write Region -->
                <div class="article_comment_write_region">
                    <div class="article_writer_txt">
                        <c:if test="${sessionScope.get('loginUser')==null}">손님</c:if>
                        <c:if test="${sessionScope.get('loginUser')!=null}">${sessionScope.get("loginUser").userId}</c:if>
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