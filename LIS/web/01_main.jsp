<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container" style="min-height: 100%;">
    <div class="search_area">
        <form name="searchForm" class="search_form" action="#" method="get">
            <div style="height: 50px; border: 2px solid #2F5597; width: fit-content">
                <input class="search_box" type="text" name="q" id="textTotal" placeholder="소장 인쇄자료와 구독 전자자료를 한번에 검색합니다."
                       title="소장 인쇄자료와 구독 전자자료를 한번에 검색합니다.">
                <input type="image" src="/img/search_icon_blue.PNG" style="height: 100%" alt="검색" title="검색">
            </div>
        </form>
    </div>
    <hr>
    <div class="slide-img-area">
        <div id="slider-div">
            <div class="silde-img-div"><img src="/img/notice1.PNG"></div>
            <div class="silde-img-div"><img src="/img/notice2.PNG"></div>
            <div class="silde-img-div"><img src="/img/notice3.PNG"></div>
            <div class="silde-img-div"><img src="/img/notice4.PNG"></div>
        </div>
    </div>
    <hr>
</div>

<style>
    .slide-img-area{height: 400px;padding: 0 100px;background-color: #ebebeb;border-radius: 10px;margin-bottom: 40px;}
    .silde-img-div {height: 100%;width: 100%;}
    .silde-img-div img {height: 100%;width: 100%;}
    .search_area {padding: 20px 0;margin-top: -30px;}
    .search_form {margin: 0 auto;width: 500px;}
    .search_box {width: 430px;height: 100%;padding: 0 22px 0 12px;font-size: 16px;color: #62696c;line-height: 2.7em;float: left;}
</style>
<script>
    $(function(){
        $('#slider-div').slick({
            slide: 'div',		//슬라이드 되어야 할 태그 ex) div, li
            infinite : true, 	//무한 반복 옵션
            slidesToShow : 1,		// 한 화면에 보여질 컨텐츠 개수
            slidesToScroll : 1,		//스크롤 한번에 움직일 컨텐츠 개수
            speed : 100,	 // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
            arrows : true, 		// 옆으로 이동하는 화살표 표시 여부
            dots : true, 		// 스크롤바 아래 점으로 페이지네이션 여부
            autoplay : true,			// 자동 스크롤 사용 여부
            autoplaySpeed : 2000, 		// 자동 스크롤 시 다음으로 넘어가는데 걸리는 시간 (ms)
            pauseOnHover : true,		// 슬라이드 이동시 마우스 호버하면 슬라이더 멈추게 설정
            vertical : false,		// 세로 방향 슬라이드 옵션
            prevArrow : "<button type='button' class='slick-prev'>Previous</button>",		// 이전 화살표 모양 설정
            nextArrow : "<button type='button' class='slick-next'>Next</button>",		// 다음 화살표 모양 설정
            dotsClass : "slick-dots", 	//아래 나오는 페이지네이션(점) css class 지정
            draggable : true, 	//드래그 가능 여부
            responsive: [ // 반응형 웹 구현 옵션
                {
                    breakpoint: 960, //화면 사이즈 960px
                    settings: {
                        //위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
                    slidesToShow:1
                    }
                },
                {
                    breakpoint: 768, //화면 사이즈 768px
                    settings: {
                        //위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
                    slidesToShow:1
                    }
                }
            ]
        });
    })
</script>