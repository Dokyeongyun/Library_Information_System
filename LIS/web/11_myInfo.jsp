<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${isLogin == 'false'}">
    <script>
        alert('로그인 후 이용해주세요.');
        location.href = '/login.do';
    </script>
</c:if>

<div class="container">
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <form action="/modifyUserInfo.do" method="post">
                <h4 class="card-title mt-3 text-center" style="font-weight: bolder"> 나의 정보 </h4>
                <p class="divider-text">
                    <span class="bg-light">Account Info</span>
                </p>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="userId" class="form-control" placeholder="${userInfo.userId}" type="text" disabled>
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input name="password" class="form-control" placeholder="********" type="password" disabled>
                </div>

                <p class="divider-text">
                    <span class="bg-light">Personal Info</span>
                </p>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="name" class="form-control" placeholder="${userInfo.name}" type="text" disabled>
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                    </div>
                    <input id="email" name="email" class="form-control" placeholder="${userInfo.email}" type="email"
                           disabled>
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-phone"></i> </span>
                    </div>
                    <select id="phone_pre" name="phone_pre" class="custom-select" style="max-width: 120px;" disabled>
                        <option selected value="${userInfo.phone.substring(0,3)}">${userInfo.phone.substring(0,3)}</option>
                        <option value="010">010</option>
                        <option value="011">011</option>
                        <option value="016">016</option>
                        <option value="070">070</option>
                    </select>
                    <input id="phone" name="phone" class="form-control" placeholder="${userInfo.phone.substring(3)}"
                           type="text" disabled>
                </div>
                <p class="divider-text">
                    <span class="bg-light">University Related Info</span>
                </p>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="stuNum" class="form-control" placeholder="${userInfo.stuNum}" type="text" disabled>
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-building"></i> </span>
                    </div>
                    <input name="dept" class="form-control" placeholder="${userInfo.dept}" type="text" disabled>
                </div>

                <hr>
                <div class="form-group">
                    <input type="hidden" name="userId" value="${userInfo.userId}"/>
                    <button type="button" class="btn btn-danger btn-block" id="activate_btn" onclick="activateInputBox()">회원정보 수정하기</button>
                    <button type="submit" class="btn btn-primary btn-block" id="modify_btn" style="display: none">수정완료</button>
                    <button type="button" class="btn btn-default btn-block" id="cancel_btn" style="display: none" onclick="deActivateInputBox()">취소</button>
                </div>
            </form>
        </article>
    </div>
</div>
<script>
    function activateInputBox() {
        $("#phone_pre").removeAttr("disabled");
        $("#phone").removeAttr("disabled");
        $("#email").removeAttr("disabled");
        $("#activate_btn").css("display", "none");
        $("#modify_btn").css("display", "block");
        $("#cancel_btn").css("display", "block");
    }
    function deActivateInputBox() {
        $("#phone_pre").attr("disabled", true);
        $("#phone").attr("disabled", true);
        $("#email").attr("disabled", true);
        $("#activate_btn").css("display", "block");
        $("#modify_btn").css("display", "none");
        $("#cancel_btn").css("display", "none");
    }
</script>