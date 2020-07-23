// 날짜 선택(달력) -- datepicker
$(function() {
    var today = new Date();
    var before5Day= new Date(Date.parse(today) - 5 * 1000 * 60 * 60 * 24);

    $('#date-piker-input').val($.datepicker.formatDate("yy-mm-dd", lastEnableDay));

    $( "#date-piker-input" ).datepicker({
        dateFormat: "yy-mm-dd",
        duration: "fast",
        minDate: before5Day,
        maxDate: today,
        beforeShowDay: DisableDates
    });
} );

// 이미 그 user가 옷차림을 저장한 날짜들 불러오기 -- 한 날짜당 하나의 옷차림만 등록가능
var disableDoneDay = NoEvaluatedDate();
var lastEnableDay = getEnableLastDay();

function NoEvaluatedDate() {
    var user_id = $("#user_id").val();

    var result = [];
    $.ajax({
        type: "GET",
        url: "../api/v1/wears/list/"+user_id,
        async: false,
        success: function(data) {
               result = data;
        }
    });
    return result;
}

function DisableDates(date) {
    var string = jQuery.datepicker.formatDate('yy-mm-dd', date);
    return [disableDoneDay.indexOf(string) == -1];
 }

 // select - option 뿌려지기
$(function () {
    optionAppend("outer");
    optionAppend("top");
    optionAppend("bottom");
});

// 그 날의 평균기온 가져오기
$("#date-piker-input").on('blur', function () {
    var user_id = $("#user_id").val();
    $("#avg-temp").attr("value", getAvgTemp(user_id));
});

//submit
$(function () {
    var user_id = $("#user_id").val();
    $("#submit").click(function () {
        var sendData = JSON.stringify(
            {
                user_id: user_id,
                temp_code: getTempCode(user_id),
                wear_date: getWearDate(),
                wear_code: getWearCode()
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
            }
        });
    });
});

// <select> - 옷 유형별 <option> 추가
function optionAppend(type) {
    $.get("../api/v1/clothes/"+type, function(data){
        for(i=0; i<data.length; i++) {
            $("#" + type + "-select").append('<option value=' + data[i].cid + '>' + data[i].cname + '</option>');
        }
    })
}

// 선택한 옵션으로 이미지 변경
function changeSelectImg(type){
    $.get("../api/v1/clothes/"+type, function (data) {

        var selectVal = $("#"+type+"-select").val();
        var key = -1;

        for(i=0; i<data.length; i++){
            if(data[i].cid == selectVal) {
                key = i;
            }
        }

        if(key == -1){
            $("#selected-"+type+"-img").attr("src", "../images/no-"+type+".png");
        } else{
            $("#selected-"+type+"-img").attr("src", data[key].cimg);
        }
    })
}

function getWearCode(){
    var outerCode = $("#outer-select").val();
    var topCode = $("#top-select").val();
    var bottomCode = $("#bottom-select").val();

         // 유효성 - top = null(x)
    if((topCode == 0)
        // 유효성 - bottom = null(드레스 일때만 가능)
        || (topCode != 9 && (bottomCode == 0))
        // 유호성 - 입력값 없을때
        || ((topCode == 0) &&(outerCode == 0) &&(bottomCode == 0))) {
        alert("등록할 옷차림을 다시 확인해주세요:)");
        return false;
    }

    var result = 0;

    $.ajax({
        url: "../api/v1/clothes/"+outerCode+"/"+topCode+"/"+bottomCode,
        async: false,
        success: function(data) {
            result = data;
        }
    });
    return result;
}

function getWearDate(){
    var date = $("#date-piker-input").val();

    for(i = 0; i<disableDoneDay.length; i++){
        if(disableDoneDay[i] == date){
            alert("날짜를 다시 확인해주세요:)");
            return false;
        }
    }
    if(date == null || date == undefined){
        return new Date();
    }
    return date;
}

function getTempCode(user_id) {

    var date = getWearDate();
    var latitude = 0;
    var longitude = 0;

    $.ajax({
        url: "../api/v1/users/"+user_id,
        async: false,
        success: function(data) {
            latitude = data.loc_latitude;
            longitude = data.loc_longitude;
        }
    });

    var regDate = new Date(date);

    var uRegDate = Math.floor(+regDate/1000);

    var apiURI = "https://api.openweathermap.org/data/2.5/onecall/timemachine?"
        + "lat=" + latitude
        + "&lon=" + longitude
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

    var tempCode = searchTempCode(avg);

    return tempCode;
}

function getAvgTemp(user_id) {

    var date = getWearDate();
    var latitude = 0;
    var longitude = 0;

    $.ajax({
        url: "../api/v1/users/"+user_id,
        async: false,
        success: function(data) {
            latitude = data.loc_latitude;
            longitude = data.loc_longitude;
        }
    });

    var regDate = new Date(date);

    var uRegDate = Math.floor(+regDate/1000);

    var apiURI = "https://api.openweathermap.org/data/2.5/onecall/timemachine?"
        + "lat=" + latitude
        + "&lon=" + longitude
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

    return Math.floor(avg);
}

function searchTempCode(temp) {
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


function getEnableLastDay() {
    var today = new Date();
    var before1Day= new Date(Date.parse(today) - 1 * 1000 * 60 * 60 * 24);
    var before2Day= new Date(Date.parse(today) - 2 * 1000 * 60 * 60 * 24);
    var before3Day= new Date(Date.parse(today) - 3 * 1000 * 60 * 60 * 24);
    var before4Day= new Date(Date.parse(today) - 4 * 1000 * 60 * 60 * 24);
    var before5Day= new Date(Date.parse(today) - 5 * 1000 * 60 * 60 * 24);

    var dates = [before5Day,before4Day,before3Day,before2Day,before1Day,today];


    for(i=0; i<disableDoneDay.length; i++){
        dates.splice(dates.indexOf(disableDoneDay[i]),1);
    }

    return dates.pop();
    }


