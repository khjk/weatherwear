const weather_text = document.querySelector(".js-weather");
const location_text = document.querySelector(".js-location");
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
	if(loadedCoords === null){
		askForCoords(); //좌표요청
	}else{
		const parsedCoords = JSON.parse(loadedCoords);
		getWeather(parsedCoords.latitude, parsedCoords.longitude);
	}
}

function init(){
	loadCoords();
}

init();