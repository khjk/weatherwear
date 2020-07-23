const weather_text = document.querySelector(".js-weather");
const location_text = document.querySelector(".js-location");
const date_text = document.getElementById("date");
const summary_text = document.getElementById("summary");
const cloudy_text = document.getElementById("clouds");
const humidity_text = document.getElementById("humidity");
const wind_text = document.getElementById("wind");
const oneday_text = document.getElementById("oneDayAfter");
const twoday_text = document.getElementById("twoDayAfter");
const threeday_text = document.getElementById("threeDayAfter");
const fourday_text = document.getElementById("fourDayAfter");
const onedayIcon = document.getElementById("onedayIcon");
const twodayIcon = document.getElementById("twodayIcon");
const threedayIcon = document.getElementById("threedayIcon");
const fourdayIcon = document.getElementById("fourdayIcon");
const onedayTemp = document.getElementById("oneday-temp");
const twodayTemp = document.getElementById("twoday-temp");
const threedayTemp = document.getElementById("threeday-temp");
const fourdayTemp = document.getElementById("fourday-temp");
const changeLocBtn = document.getElementById("changeLocBtn");
var login_flag = 0; //0일떄는 로그인 안한거
var user_id_ = "";
function init(){
    getYoilDateMonth();
   navigator.geolocation.getCurrentPosition(handleGeoSuccess, handleGeoError);
   changeLocBtn.addEventListener('click',handleChangeLocation);
}

function handleGeoSuccess(position){
	const latitude = position.coords.latitude;
    const longitude = position.coords.longitude;
    console.log(latitude, longitude);
    var apiURI = "https://api.openweathermap.org/data/2.5/onecall?lat=" + latitude + "&lon="
    + longitude + "&units=metric&lang=kr&appid=6e80afc16299c60c68363797958861a6";
    $.ajax({
        url: apiURI,
        dataType: "json",
        type: "GET",
        async: "false",
        success: function(response) {
            var current = response.current;
            var CurrentWeatherIcon = "http://openweathermap.org/img/wn/" + current.weather[0].icon + "@2x.png";
            $("#currentWeatherIcon").attr("src",CurrentWeatherIcon);
            weather_text.innerText = `${current.temp}°C`;
            var temp_loc = response.timezone;
            var temp_loc_arr = temp_loc.split("/");
            location_text.innerText = `${temp_loc_arr[1]}`;
            summary_text.innerHTML = `${current.weather[0].main}`;
		    cloudy_text.innerText = `${current.clouds}%`;
		    humidity_text.innerText = `${current.humidity }%`;
            wind_text.innerText = `${current.wind_speed} m/s`;
            var oneDayAfter = response.daily[1];
            var twoDayAfter = response.daily[2];
            var threeDayAfter = response.daily[3];
            var fourDayAfter = response.daily[4];
            var fiveDayAfter = response.daily[5];
            var OneDayWeatherIcon =  "http://openweathermap.org/img/wn/" + oneDayAfter.weather[0].icon + "@2x.png";
            var twoDayWeatherIcon =  "http://openweathermap.org/img/wn/" + twoDayAfter.weather[0].icon + "@2x.png";
            var threeDayWeatherIcon =  "http://openweathermap.org/img/wn/" + threeDayAfter.weather[0].icon + "@2x.png";
            var fourDayWeatherIcon =  "http://openweathermap.org/img/wn/" + fourDayAfter.weather[0].icon + "@2x.png";
            $("#onedayIcon").attr("src",OneDayWeatherIcon);
            $("#twodayIcon").attr("src",twoDayWeatherIcon);
            $("#threedayIcon").attr("src",threeDayWeatherIcon);
            $("#fourdayIcon").attr("src",fourDayWeatherIcon);
            onedayTemp.innerText = `${oneDayAfter.temp.day}°C`;
            twodayTemp.innerText = `${twoDayAfter.temp.day}°C`;
            threedayTemp.innerText = `${threeDayAfter.temp.day}°C`;
            fourdayTemp.innerText = `${fourDayAfter.temp.day}°C`;
            initAnimation();
            user_id_ = $('#user_id_js').html();
           if(user_id_ == undefined || user_id_ == null || user_id_ == ""){ //로그인 안했을때
                $('.today-recommend').addClass('hidden');
                $('.today-info').removeClass('hidden');
                login_flag = 0;
           }else{ //로그인 했을때
                $('.today-info').addClass('hidden');
                $('.today-recommend').removeClass('hidden');
                login_flag = 1;
           }
            if(login_flag == 1){
                var temp_code = getTempCode(current.temp);
                console.log("템프코드"+ temp_code);
                console.log("유저아이디" + user_id_);
                getBestLike(temp_code, user_id_);
            }
             // current.temp //지금 온도
                    //console.log("지금 온도"+_temp_to_code);
                   // console.log("변환 코드"+getTempCode(_temp_to_code));
                    //로그인시에 -> current.temp -> temp_code로 변환하고,,, -> 로그인 아이디와 temp_code 사용해서 API(WEAR) 옷코드 받고
                   // -> 옷코드 받은걸 -> api(CLOTHES) -> 이미지로 변환
        }
    })
}
//로그인 아이디와 temp_code 사용해서 WEAR 코드 받기
function getBestLike(temp_code,user_id_){
   var data = {
       user_id : user_id_,
       temp_code : temp_code
   };
  $.ajax({
            type: 'POST',
            url : '../api/v1/wears/best-like',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(response){
            console.log(response);
            getImgByBestCode(response);
        }).fail(function (error) {
            alert(error.json);
  });
}
function getImgByBestCode(best_code){
  $.ajax({
            type: 'GET',
            url : '../api/v1/clothes/img/' + best_code,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(response){
            console.log("옷url" + response);
            if(response.length == 3){ //반환값 3개일 때
             console.log("반환값이 3개");
                $('#OUTER_BEST').attr("src",response[0]);
                $('#TOP_BEST').attr("src", response[1]);
                $('#BOTTOM_BEST').attr("src", response[2]);
            }else if(response.length ==2){ //반환값 2개일 때
             console.log("반환값이 2개");
                $('#OUTER_BEST').remove();
                $('#TOP_BEST').attr("src",response[0]);
                $('#BOTTOM_BEST').attr("src",response[1]);
            }else{ //반환값 1개일 때
                 console.log("반환값이 1개");
                $('#OUTER_BEST').remove();
                $('#TOP_BEST').attr("src",response[0]);
                $('#BOTTOM_BEST').remove();
            }
        }).fail(function (error) {
            alert(error.json);
  });
}

function handleGeoError(){
	console.log("Can't access geo location");
}

function getYoilDateMonth(){
    const date = new Date();
    const month = date.getMonth() + 1;
    const date_num = date.getDate();
    const day = date.getDay();
    var monthName = getMonthName(month);
    var dayName = getDayName(day);
    var onedayName = getDayName(day+1);
    var twodayName = getDayName(day+2);
    var threedayName = getDayName(day+3);
    var fourdayName = getDayName(day+4);
    date_text.innerHTML = `${dayName} ${monthName} ${date_num}`;
    oneday_text.innerHTML = `${onedayName}`;
    twoday_text.innerHTML = `${twodayName}`;
    threeday_text.innerHTML = `${threedayName}`;
    fourday_text.innerHTML = `${fourdayName}`;
}
function getTempCode(temp) {
    var tempCode = 0;
    var temperature = Number(temp);
    if(temperature >= 28){ tempCode = 1; }
    else if(temperature<28 && temperature>=23){ tempCode = 2; }
    else if(temperature<23 && temperature>=20){ tempCode = 3; }
    else if(temperature<20 && temperature>=17){ tempCode = 4;}
    else if(temperature<17 && temperature>=12){ tempCode = 5;}
    else if(temperature<12 && temperature>=9){ tempCode = 6;}
    else if(temperature<9 && temperature>=5){ tempCode = 7;}
    else if(temperature<5){ tempCode = 8;}

    return tempCode;
}
function getMonthName(month) {
    var tempMonthName = "";
    switch(month){
        case 1:
        tempMonthName = "Jan";
        break;
        case 2:
            tempMonthName = "FEB";
        break;
        case 3:
            tempMonthName = "Mar";
        break;
        case 4:
            tempMonthName = "Apr";
        break;
        case 5:
            tempMonthName = "May";
        break;
        case 6:
            tempMonthName = "Jun";
        break;
        case 7:
            tempMonthName = "Jul";
        break;
        case 8:
            tempMonthName = "Aug";
        break;
        case 9:
            monthName = "Sep";
        break;
        case 10:
            tempMonthName = "Oct";
        break;
        case 11:
            tempMonthName = "Nov";
        break;
        case 12:
            tempMonthName = "Dec";
    }
    return tempMonthName;
}

function getDayName(day) {
    var dayName = "";
    var temp_day = day % 7;
    switch (temp_day) {
        case 0:
            dayName = "Sunday";
          break;
        case 1:
            dayName = "Monday";
          break;
        case 2:
            dayName = "Tuesday";
          break;
        case 3:
            dayName = "Wednesday";
          break;
        case 4:
            dayName = "Thursday";
          break;
        case 5:
            dayName = "Friday";
          break;
        case 6:
            dayName = "Saturday";
      }
      return dayName;
}
function handleChangeLocation(){
    navigator.geolocation.getCurrentPosition(handleGeoSuccess, handleGeoError);
    alert('현재 사용자의 위치로 update하였습니다.');
}
init();
