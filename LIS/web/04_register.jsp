<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center" style="font-weight: bolder"> 회원가입 </h4>
            <form>
                <p class="divider-text">
                    <span class="bg-light">Account Info</span>
                </p>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="userId" class="form-control" placeholder="아이디" type="text">
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input name="password" class="form-control" placeholder="비밀번호" type="password">
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input name="passwordChk" class="form-control" placeholder="비밀번호 확인" type="password">
                </div>

                <p class="divider-text">
                    <span class="bg-light">Personal Info</span>
                </p>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="name" class="form-control" placeholder="이름" type="text">
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                    </div>
                    <input name="email" class="form-control" placeholder="이메일" type="email">
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-phone"></i> </span>
                    </div>
                    <select class="custom-select" style="max-width: 120px;">
                        <option selected="">010</option>
                        <option value="1">011</option>
                        <option value="2">016</option>
                        <option value="3">070</option>
                    </select>
                    <input name="phone" class="form-control" placeholder="휴대폰 번호" type="text">
                </div>
                <p class="divider-text">
                    <span class="bg-light">University Related Info</span>
                </p>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="stuNum" class="form-control" placeholder="학번" type="text">
                </div>
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-building"></i> </span>
                    </div>
                    <%--
                    <select class="form-control">
                        <option selected=""> 학과/학부 </option>
                        <option>Designer</option>
                        <option>Manager</option>
                        <option>Accaunting</option>
                    </select>
                    --%>
                    <input name="dept" class="form-control" placeholder="학과/학부" type="text">
                </div>

                <hr>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> 회원가입 Register </button>
                </div>
                <p class="text-center"> 이미 계정이 있으신가요? <a href="">로그인</a> </p>
            </form>
        </article>
    </div>
</div>
