const weather_text = document.querySelector(".js-weather");
const location_text = document.querySelector(".js-location");
const date_text = document.getElementById("date");

const API_KEY = "f6c6e99c31msh914f92a9d0106afp15872ejsna05a25c5d6d0";
const COORDS = 'coords';

function getWeather(lat, lng){
	console.log(lat, lng);
	fetch("https://community-open-weather-map.p.rapidapi.com/weather?units=metric&lat=${lat}&lon={lng}&q=Seoul", {
		"method": "GET",
		"headers": {
			"x-rapidapi-host": "community-open-weather-map.p.rapidapi.com",
			"x-rapidapi-key": "f6c6e99c31msh914f92a9d0106afp15872ejsna05a25c5d6d0"
		}
	})
	.then(function(response){
		return response.json();
	}).then(function(json){
		console.log(json);
		const temperature = json.main.temp;
		const place = json.name;
		console.log(temperature, place);
		weather_text.innerText = `${temperature}°C`;
		location_text.innerText = `${place}`;
	})
	.catch(err => {
		console.log(err);
	});
}

function saveCoords(coordsObj){
	localStorage.setItem(COORDS, JSON.stringify(coordsObj));
}

function handleGeoSuccess(position){
	const latitude = position.coords.latitude;
	const longitude = position.coords.longitude;
	const coordsObj = {
			latitude : latitude,
			longitude : longitude
	};
	saveCoords(coordsObj);
	getWeather(latitude, longitude);
}

function handleGeoError(){
	console.log("Can't access geo location");
}

function askForCoords(){
	navigator.geolocation.getCurrentPosition(handleGeoSuccess, handleGeoError);
}

function loadCoords(){
    const loadedCoords = localStorage.getItem(COORDS);
    getYoilDateMonth();
	if(loadedCoords === null){
	//	askForCoords(); //좌표요청
	}else{
	//	const parsedCoords = JSON.parse(loadedCoords);
	//	getWeather(parsedCoords.latitude, parsedCoords.longitude);
	}
}

function getYoilDateMonth(){
    const date = new Date();
    const month = date.getMonth() + 1;
    const date_num = date.getDate();
    const day = date.getDay();
    var monthName = getMonthName(month);
    var dayName = getDayName(day);
    date_text.innerHTML = `${dayName} ${monthName} ${date_num}`;
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

function init(){
	loadCoords();
}

init();