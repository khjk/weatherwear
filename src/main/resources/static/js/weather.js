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
function init(){
    getYoilDateMonth();
    navigator.geolocation.getCurrentPosition(handleGeoSuccess, handleGeoError);
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
            console.log(response);
            var current = response.current;
            console.log("현재 온도" + current.temp);
            console.log("현재 습도:"+ current.humidity +"%");
            console.log("현재 구름:"+ current.clouds +"%");
            console.log("현재 풍속:"+ current.wind_speed + "m/s");
            console.log("현재 Summary:"+ current.weather[0].main);
            var CurrentWeatherIcon = "http://openweathermap.org/img/wn/" + current.weather[0].icon + "@2x.png";
            $("#currentWeatherIcon").attr("src",CurrentWeatherIcon);
            weather_text.innerText = `${current.temp}°C`;
            location_text.innerText = `${response.timezone}`;
            summary_text.innerHTML = `${current.weather[0].main}`;
		    cloudy_text.innerText = `${current.clouds}%`;
		    humidity_text.innerText = `${current.humidity }%`;
            wind_text.innerText = `${current.wind_speed} m/s`;

            var oneDayAfter = response.daily[1];
            var twoDayAfter = response.daily[2];
            var threeDayAfter = response.daily[3];
            var fourDayAfter = response.daily[4];
            var fiveDayAfter = response.daily[5];
            console.log("내일 온도:" + oneDayAfter.temp.day);
            console.log("내일 습도:" + oneDayAfter.humidity);
            console.log("내일 구름:" + oneDayAfter.clouds);
            console.log("내일 풍속:" + oneDayAfter.wind_speed);
            console.log("내일 강우량:" + oneDayAfter.rain + "mm");
            console.log("내일 Summary:" + oneDayAfter.weather[0].main);
            var OneDayWeatherIcon =  "http://openweathermap.org/img/wn/" + oneDayAfter.weather[0].icon + "@2x.png";
            var twoDayWeatherIcon =  "http://openweathermap.org/img/wn/" + twoDayAfter.weather[0].icon + "@2x.png";
            var threeDayWeatherIcon =  "http://openweathermap.org/img/wn/" + threeDayAfter.weather[0].icon + "@2x.png";
            var fourDayWeatherIcon =  "http://openweathermap.org/img/wn/" + fourDayAfter.weather[0].icon + "@2x.png";
            $("#onedayIcon").attr("src",OneDayWeatherIcon);
            $("#twodayIcon").attr("src",twoDayWeatherIcon);
            $("#threedayIcon").attr("src",threeDayWeatherIcon);
            $("#fourdayIcon").attr("src",fourDayWeatherIcon);
            initAnimation();
        }
    })
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
    switch (day) {
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

init();
