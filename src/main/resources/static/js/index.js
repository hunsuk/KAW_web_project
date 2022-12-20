var map;
window.onload = getLocation;
//window.initMap = function () {
//  map = new google.maps.Map(document.getElementById("map"), {
//    center: { lat: 37.5400456, lng: 126.9921017 },
//    zoom: 7
//  });
//
//
//  const malls = [
//    { label: "", name: "강릉집하장", lat: 37.8084, lng: 128.8561 },
//    { label: "", name: "원주지점", lat: 37.3313, lng: 127.849 },
//    { label: "", name: "용인지점", lat: 37.2042, lng: 127.3175 },
//    { label: "", name: "오포지점", lat: 37.3511, lng: 127.2043 },
//    { label: "", name: "김포지점", lat: 37.6535, lng: 126.653 },
//    { label: "", name: "화성지점", lat: 37.1432, lng: 126.8268 },
//    { label: "", name: "포천지점", lat: 37.7904, lng: 127.2393 },
//    { label: "", name: "양주지점", lat: 37.8216, lng: 126.8914 },
//    { label: "", name: "시흥지점", lat: 37.3206, lng: 126.7448 },
//    { label: "", name: "사천센터", lat: 35.0726, lng: 128.0711 },
//    { label: "", name: "김해지점", lat: 35.3315, lng: 128.7102 },
//    { label: "", name: "경산지점", lat: 35.8838, lng: 128.7896 },
//    { label: "", name: "안동집하장", lat: 36.6026, lng: 128.5318 },
//    { label: "", name: "대구지점", lat: 35.9435, lng: 128.3189 },
//    { label: "", name: "WM센터", lat: 37.4925, lng: 127.1181 },
//    { label: "", name: "구로지점", lat: 37.491, lng: 126.8348 },
//    { label: "", name: "부산지점", lat: 35.5101, lng: 129.0969 },
//    { label: "", name: "인천지점", lat: 37.4978, lng: 126.6651 },
//    { label: "", name: "목포집하장", lat: 34.8246, lng: 126.3824 },
//    { label: "", name: "광주지점", lat: 35.2607, lng: 126.7212 },
//    { label: "", name: "전주지점", lat: 35.9501, lng: 127.1113 },
//    { label: "", name: "제주지점", lat: 33.522, lng: 126.5446 },
//    { label: "", name: "아산지점", lat: 36.7666, lng: 126.9453 },
//    { label: "", name: "청주지점", lat: 36.5654, lng: 127.4403 },
//    { label: "", name: "진천지점", lat: 36.9108, lng: 127.4355 },
//    { label: "나의 위치", name: "나의 위치", lat: latitude, lng: longitude }
//  ];
//
//  const bounds = new google.maps.LatLngBounds();
//  const infowindow = new google.maps.InfoWindow();
//
//
//
//  malls.forEach(({ label, name, lat, lng }) => {
//    const marker = new google.maps.Marker({
//      position: { lat, lng },
//      label,
//      map: map
//    });
//    bounds.extend(marker.position);
//
//    marker.addListener("click", () => {
//      map.panTo(marker.position);
//      infowindow.setContent(name);
//      infowindow.open({
//        anchor: marker,
//        map
//      });
//    });
//  });
//  map.fitBounds(bounds);
//  };
function getLocation(){
      if(navigator.geolocation){
          navigator.geolocation.getCurrentPosition(locationSuccess, locationError, geo_options);
      }else{
          console.log("지오 로케이션 없음")
      }
  };
  // getLocation
  var latitude, longitude;
  function locationSuccess(p){
      latitude = p.coords.latitude,
      longitude = p.coords.longitude;
      initialize();
  }
  // locationSuccess
  function locationError(error){
      var errorTypes = {
          0 : "무슨 에러냥~",
          1 : "허용 안눌렀음",
          2 : "위치가 안잡힘",
          3 : "응답시간 지남"
      };
      var errorMsg = errorTypes[error.code];
  }
  // locationError
  var geo_options = {
    enableHighAccuracy: true,
    maximumAge        : 30000,
    timeout           : 27000
  };
  // geo_options
  function initialize() {
  const malls = [
      { label: "", name: "강릉집하장", lat: 37.8084, lng: 128.8561 },
      { label: "", name: "원주지점", lat: 37.3313, lng: 127.849 },
      { label: "", name: "용인지점", lat: 37.2042, lng: 127.3175 },
      { label: "", name: "오포지점", lat: 37.3511, lng: 127.2043 },
      { label: "", name: "김포지점", lat: 37.6535, lng: 126.653 },
      { label: "", name: "화성지점", lat: 37.1432, lng: 126.8268 },
      { label: "", name: "포천지점", lat: 37.7904, lng: 127.2393 },
      { label: "", name: "양주지점", lat: 37.8216, lng: 126.8914 },
      { label: "", name: "시흥지점", lat: 37.3206, lng: 126.7448 },
      { label: "", name: "사천센터", lat: 35.0726, lng: 128.0711 },
      { label: "", name: "김해지점", lat: 35.3315, lng: 128.7102 },
      { label: "", name: "경산지점", lat: 35.8838, lng: 128.7896 },
      { label: "", name: "안동집하장", lat: 36.6026, lng: 128.5318 },
      { label: "", name: "대구지점", lat: 35.9435, lng: 128.3189 },
      { label: "", name: "WM센터", lat: 37.4925, lng: 127.1181 },
      { label: "", name: "구로지점", lat: 37.491, lng: 126.8348 },
      { label: "", name: "부산지점", lat: 35.5101, lng: 129.0969 },
      { label: "", name: "인천지점", lat: 37.4978, lng: 126.6651 },
      { label: "", name: "목포집하장", lat: 34.8246, lng: 126.3824 },
      { label: "", name: "광주지점", lat: 35.2607, lng: 126.7212 },
      { label: "", name: "전주지점", lat: 35.9501, lng: 127.1113 },
      { label: "", name: "제주지점", lat: 33.522, lng: 126.5446 },
      { label: "", name: "아산지점", lat: 36.7666, lng: 126.9453 },
      { label: "", name: "청주지점", lat: 36.5654, lng: 127.4403 },
      { label: "", name: "진천지점", lat: 36.9108, lng: 127.4355 },
      { label: "나의 위치", name: "나의 위치", lat: latitude, lng: longitude }
    ];
    console.log(malls)
      map = new google.maps.Map(document.getElementById("map"), {
          center: { lat: latitude, lng: longitude },
          zoom: 10
        });
//      const bounds = new google.maps.LatLngBounds();
      const infowindow = new google.maps.InfoWindow();



        malls.forEach(({ label, name, lat, lng }) => {
          const marker = new google.maps.Marker({
            position: { lat, lng },
            label,
            map: map
          });
//          bounds.extend(marker.position);

          marker.addListener("click", () => {
            map.panTo(marker.position);
            infowindow.setContent(name);
            infowindow.open({
              anchor: marker,
              map
            });
          });
        });
//        map.fitBounds(bounds);
  }