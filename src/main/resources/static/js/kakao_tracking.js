if (navigator.geolocation) {

    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {

        var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도


        var car_lat = new Array();
        var car_lon = new Array();

        var car_markers = new Array();
        car_lat.push(37.623010237639974,37.69433943042704,37.77841280118573,37.736323288806204,37.582003859843226,37.2618892201563,36.57232189799761)
        car_lon.push(126.8022433746701,126.82049939110969,126.93932080359242,127.04441267181421,126.81755871356326,127.09855986866326,128.70549999308136)

        var positions = [
            {
                title: '강릉집하장',
                latlng: new kakao.maps.LatLng(37.8084, 128.8561),
            },
            {
                title: '원주지점',
                latlng: new kakao.maps.LatLng(37.3313, 127.849),
            },
            {
                title: '용인지점',
                latlng: new kakao.maps.LatLng(37.2042, 127.3175),
            },{
                title: '김포지점',
                latlng: new kakao.maps.LatLng(37.6535, 126.653),
            },{
                title: '화성지점',
                latlng: new kakao.maps.LatLng(37.1432, 126.8268),
            },{
                title: '포천지점',
                latlng: new kakao.maps.LatLng(37.7904, 127.2393),
            },{
                title: '양주지점',
                latlng: new kakao.maps.LatLng(37.8216, 126.8914),
            },{
                title: '시흥지점',
                latlng: new kakao.maps.LatLng(37.3206, 126.7448),
            },{
                title: '사천센터',
                latlng: new kakao.maps.LatLng(35.0726, 128.0711),
            },{
                title: '김해지점',
                latlng: new kakao.maps.LatLng(35.3315, 128.7102),
            },{
                title: '경산지점',
                latlng: new kakao.maps.LatLng(35.8838, 128.7896),
            },{
                title: '안동집하장',
                latlng: new kakao.maps.LatLng(36.6026, 128.5318),
            },{
                title: '대구지점',
                latlng: new kakao.maps.LatLng(35.9435, 128.3189),
            },{
                title: 'WM센터',
                latlng: new kakao.maps.LatLng(37.4925, 127.1181),
            },{
                title: '구로지점',
                latlng: new kakao.maps.LatLng(37.491, 126.8348),
            },{
                title: '부산지점',
                latlng: new kakao.maps.LatLng(35.5101, 129.0969),
            },{
                title: '인천지점',
                latlng: new kakao.maps.LatLng(37.4978, 126.6651),
            },{
                title: '목포집하장',
                latlng: new kakao.maps.LatLng(34.8246, 126.3824),
            },{
                title: '광주지점',
                latlng: new kakao.maps.LatLng(35.2607, 126.7212),
            },{
                title: '전주지점',
                latlng: new kakao.maps.LatLng(35.9501, 127.1113),
            },{
                title: '제주지점',
                latlng: new kakao.maps.LatLng(33.522, 126.5446),
            },{
                title: '아산지점',
                latlng: new kakao.maps.LatLng(36.7666, 126.9453),
            },{
                title: '청주지점',
                latlng: new kakao.maps.LatLng(36.5654, 127.4403),
            },{
                title: '진천지점',
                latlng: new kakao.maps.LatLng(36.9108, 127.4355),
            },
            {
                title: '오포지점',
                latlng: new kakao.maps.LatLng(37.3511, 127.2043),
            },

        ];

        var mapContainer = document.getElementById('map'), // 지도를 표시할 div
            mapOption = {
                center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
                level: 12 // 지도의 확대 레벨
            };

        var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
        // 마커 이미지의 이미지 주소입니다
        var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
        var imageCar = "/img/sendCar.png"

        for (var i = 0; i < positions.length; i ++) {

            // 마커 이미지의 이미지 크기 입니다
            var imageSize = new kakao.maps.Size(24, 35);

            // 마커 이미지를 생성합니다
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);




            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                map: map, // 마커를 표시할 지도
                position: positions[i].latlng, // 마커를 표시할 위치
                title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                image : markerImage // 마커 이미지
            });

            // var iwContent = '<div class="customoverlay">' +
            //     '    <span class="title">'+positions[i].title+'</span>' +
            //     '</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
            //
            // var customOverlay = new kakao.maps.CustomOverlay({
            //     map: map,
            //     position : positions[i].latlng,
            //     content : iwContent
            // });
        }
        imageSrc = "img/my.png";
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        var sendCarImage = new kakao.maps.MarkerImage(imageCar, new kakao.maps.Size(40, 50));


        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: new kakao.maps.LatLng(lat,lon), // 마커를 표시할 위치
            title : '나의 위치', // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image : markerImage, // 마커 이미지
        });


        //
        // var iwContent = '<div class="customoverlay">' +
        //     '    <span class="title">나의 위치</span>' +
        //     '</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        //
        // var customOverlay = new kakao.maps.CustomOverlay({
        //     map: map,
        //     position : new kakao.maps.LatLng(lat,lon),
        //     content : iwContent
        // });
        console.log(car_lat.length);
        for (var i = 0; i < car_lat.length; i++){

            var marker = new kakao.maps.Marker({
                map: map, // 마커를 표시할 지도
                position: new kakao.maps.LatLng(car_lat[i],car_lon[i]), // 마커를 표시할 위치
                title : 'ID:100' + i + ' 상품 배송중', // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                image : sendCarImage, // 마커 이미지
                clickable: true,
                id : i.toString()
            });
            car_markers.push(marker);

            var iwContent = '<div class="customoverlay">' +
                '    <span class="title">트럭' + (i+1) + '번 차량</span>' +
                '</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

            var customOverlay = new kakao.maps.CustomOverlay({
                map: map,
                position : new kakao.maps.LatLng(car_lat[i],car_lon[i]),
                content : iwContent
            });

        }
        car_markers.forEach(marker => makeClickListener(marker));


        function makeClickListener(marker){
            kakao.maps.event.addListener(marker, 'click', function() {
                // 마커 위에 인포윈도우를 표시합니다
                var ID = marker.Gb.split(' ')[0];
                ID = ID.substr(3,ID.length -1);
                window.name = "parentForm";
                var url= "/request_tracking_popup?id=" + ID;    //팝업창에 출력될 페이지 URL
                var winWidth = 700;
                var winHeight = 600;
                var popupX = (window.screen.width / 2) - (winWidth / 2);

                var popupY= (window.screen.height / 2) - (winHeight / 2);

                var popupOption= "width="+winWidth+", height="+winHeight+", left="+popupX + ", top="+popupY;    //팝업창 옵션(optoin)


                var myWindow = window.open(url,"childForm",popupOption);

            });




        }


    });
}else{
    var markers = [
        {
            position: new kakao.maps.LatLng(33.450701, 126.570667)
        },
        {
            position: new kakao.maps.LatLng(33.450001, 126.570467),
            text: '텍스트를 표시할 수 있어요!' // text 옵션을 설정하면 마커 위에 텍스트를 함께 표시할 수 있습니다
        }
    ];
    var staticMapContainer  = document.getElementById('map'), // 이미지 지도를 표시할 div
        staticMapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 이미지 지도의 중심좌표
            level: 3, // 이미지 지도의 확대 레벨
            marker: markers // 이미지 지도에 표시할 마커
        };

// 이미지 지도를 생성합니다
    var staticMap = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);
}



