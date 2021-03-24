<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center" style="font-weight: bolder"> 로그인 </h4>
            <p class="divider-text">
                <span class="bg-light">Please Fill Input Boxes</span>
            </p>
            <form action="/loginPro.do" method="post">
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
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> 로그인 Login </button>
                </div>
                <p class="text-center"> 이미 계정이 있으신가요? <a href="">로그인</a> </p>
            </form>
        </article>
    </div>
</div>
