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
    <div class="content_area">
        <div class="content1">
            <div class="content_top">
                <img src="/img/notice_icon.PNG">
                <h2>공지사항</h2>
                <img src="/img/more_icon.PNG" style="float: right">
            </div>
            <div class="content_bottom">
                <div class="notice_list">
                    <ul>
                        <li>
                            <a href="#">[대학혁신지원사업]  2021  ‘Book드림 (독서 후기 공모)’ 안내</a>
                            <span>March 19, 2021</span>
                        </li>
                        <li>
                            <a href="#">Book똑똑!  책배달 서비스 안내</a>
                            <span>March 19, 2021</span>
                        </li>
                        <li>
                            <a href="#">일반열람실(3열람실) 개방 안내</a>
                            <span>March 19, 2021</span>
                        </li>
                        <li>
                            <a href="#">누리미디어 DBpia X 히든그레이스가 함께 진행하는 논문 쓰기와 검색 전략 이용자 교육</a>
                            <span>March 17, 2021</span>
                        </li>
                        <li>
                            <a href="#">IEEE Quiz Contest 안내 (3월19일 ~ 4월11일)</a>
                            <span>March 16, 2021</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="content2">
            <div class="content_top">
                <img src="/img/schdule_icon.PNG">
                <h2>일정</h2>
                <img src="/img/more_icon.PNG" style="float: right">
            </div>
            <div class="content_bottom">
                <div class="schedule_list">
                    <ul>
                        <li>
                            <a>RISS 100% 활용하고 대학과제 A+! 연구성과 A+!</a>
                            <span>10:00 - 15:30 2021-03-26</span>
                        </li>
                        <li>
                            <a>[Ebsco CINAHL 온라인 교육] 간호·보건학 학술 정보 탐색을 위한 5가지 특수 제한자의 개념 및 사용 방법</a>
                            <span>14:00 - 14:40 2021-03-26</span>
                        </li>
                        <li>
                            <a>EndNote 온라인 교육 : 논문 작성부터 투고까지 원스텝 관리하기</a>
                            <span>14:00 - 15:30 2021-03-30</span>
                        </li>
                        <li>
                            <a>[온라인 교육] Web of Science, JCR, Incites</a>
                            <span>14:00 - 16:15 2021-03-31</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="content3">
            <div class="content_top">
                <img src="/img/time_icon.PNG">
                <h2>이용시간</h2>
                <img src="/img/more_icon.PNG" style="float: right">
            </div>
            <div class="content_bottom">
                <div class="open_time_table">
                    <table>
                        <tr class="row_type1"><th>DKY's Library Building 1</th><td></td></tr>
                        <tr><th>주제정보실 Information Center (2F ~ 5F)</th><td>09:00 ~ 18:00</td></tr>
                        <tr><th>라운지 Lounge (1F)</th><td>09:00 ~ 18:00</td></tr>
                        <tr><th>일반열람실 General Reading Room (B1)</th><td>24h OPEN</td></tr>
                        <tr><th>보존서고 Repository (B2)</th><td>Closed</td></tr>

                        <tr class="row_type1"><th>DKY's Library Building 2</th><td></td></tr>
                        <tr><th>의학정보실 Information Center</th><td>09:00 ~ 18:00</td></tr>
                        <tr><th>병원도서관 Hospital Library</th><td>09:00 ~ 14:00</td></tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- 이미지 슬라이드 (Slick 라이브러리) --%>
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