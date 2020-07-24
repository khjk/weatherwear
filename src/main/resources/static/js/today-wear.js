var _user_id = $("#user_id").val();
var _temp_code = searchTempCode(_user_id);
// 오늘 날짜
var wear_today = new Date();
var recommend_wear_code = getBestLikeTempCode(_temp_code,_user_id);


$(function () {
    wear_today = new Date();
    console.log("wear_today: "+wear_today);
    console.log(_user_id);
    console.log("_temp_code: "+_temp_code);
    console.log("recommend_wear_code: "+recommend_wear_code);

    getBestCody(recommend_wear_code);
    console.log("recommend_wear_code: "+recommend_wear_code);

    $.ajax({
        type: 'get',
        url : '../api/v1/clothes/name/'+recommend_wear_code,
        success: function (data) {
            console.log("getname: "+ data[0]);
        }
    });



})

$(function () {
    $("#submit-btn").on("click", function () {
        var result = confirm('추천 옷차림을 그대로 오늘의 옷차림으로 등록하시겠습니까?');
        if(result) {
            todayToWearSave();
        } else {
            // no -> 등록하러 가기
            location.replace('/users/save-wear');
        }

    });
})

function todayToWearSave() {
    $("#submit").click(function () {
        var sendData = JSON.stringify(
            {
                user_id: user_id,
                temp_code: _temp_code,
                wear_date: wear_today,
                wear_code: recommend_wear_code
            });

        $.ajax({
            type: "POST",
            url: "../api/v1/wears",
            data: sendData,
            contentType: "application/json;charset=UTF-8",
            async: false,
            success: function (data) {
                alert("성공적으로 등록되었습니다! 옷차림에 평가해주세요")
                window.location.href="/users/eval-wear";
            },
            error: function (data) {
                alert("이미 등록되었습니다.")
                return
            }
        });
    });
}

function getBestLikeTempCode(temp_code,user_id){
    var tempCode = 1;
    var data = {
        user_id : user_id,
        temp_code : temp_code
    };
    $.ajax({
        type: 'POST',
        url : '../api/v1/wears/best-like',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data),
        async: false,
        success: function (data) {
            tempCode = data;
        }
    });

    console.log("getBestLikeTempCode: "+ tempCode);
    return tempCode;
}

function getBestCody(best_code){
    $.ajax({
        type: 'GET',
        url : '../api/v1/clothes/img/'+best_code,
        async: false,
    }).done(function(response){
        if(response.length == 3){ //반환값 3개일 때
            console.log("반환값이 3개");
            $('#recommend-outer-img').attr("src",response[0]);
            $('#recommend-top-img').attr("src", response[1]);
            $('#recommend-bottom-img').attr("src", response[2]);
        }else if(response.length ==2){ //반환값 2개일 때
            $('#recommend-outer-img').remove();
            $('#recommend-top-img').attr("src",response[0]);
            $('#recommend-bottom-img').attr("src",response[1]);
        }else{ //반환값 1개일 때
            $('#recommend-outer-img').remove();
            $('#recommend-top-img').attr("src",response[0]);
            $('#recommend-bottom-img').remove();
        }
    }).fail(function (error) {
        alert(error.json);
    });
}

function searchTempCode(user_id) {

    var location = getUserLocation(user_id);
    var today = new Date();
    var uRegDate = Math.floor(+today/1000);
    console.log("today: "+today);
    console.log("uRegDate: "+ uRegDate);
    var apiURI = "https://api.openweathermap.org/data/2.5/onecall/timemachine?"
        + "lat=" + location[0]
        + "&lon=" + location[1]
        + "&dt="+ uRegDate
        +"&appid=6e80afc16299c60c68363797958861a6";

    var avg = 0;

    $.ajax({
        url: apiURI,
        dataType: "json",
        type: "GET",
        async: false,
        success: function(data) {
            var sum = 0;
            for(i=0; i<data.hourly.length; i++){
                sum += data.hourly[i].temp - 273.15
            }
            avg = sum/data.hourly.length;
            console.log("평균기온 : "+ avg);

        }
    });

    var tempCode = getWhichTempCode(avg);

    return tempCode;
}

function getUserLocation(userId) {
    var location = [];
    var latitude = 0;
    var longitude = 0;

    $.ajax({
        url: "../api/v1/users/"+userId,
        async: false,
        success: function(data) {
            latitude = data.loc_latitude;
            longitude = data.loc_longitude;
        }
    });

    console.log("latitude: "+latitude);
    console.log("longitude: "+longitude);
    location.push(latitude);
    location.push(longitude);

    console.log(location);
    return location;
}


function getWhichTempCode(temp) {
    var tempCode = 0;
    if(temp >= 28){ tempCode = 1; }
    else if(temp<28 && temp>=23){ tempCode = 2; }
    else if(temp<23 && temp>=20){ tempCode = 3; }
    else if(temp<20 && temp>=17){ tempCode = 4;}
    else if(temp<17 && temp>=12){ tempCode = 5;}
    else if(temp<12 && temp>=9){ tempCode = 6;}
    else if(temp<9 && temp>=5){ tempCode = 7;}
    else if(temp<5){ tempCode = 8;}

    return tempCode;
}
