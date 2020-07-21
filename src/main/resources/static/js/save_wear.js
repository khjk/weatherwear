// 날짜 선택(달력) -- datepicker
$(function() {
    var today = new Date();
    // $('#date-piker-input').val($.datepicker.formatDate("yy-mm-dd", today));

    $( "#date-piker-input" ).datepicker({
        dateFormat: "yy-mm-dd",
        duration: "fast",
        // maxDate: today,
        beforeShowDay: DisableDates
    });
} );

// 이미 그 user가 옷차림을 저장한 날짜들 불러오기 -- 한 날짜당 하나의 옷차림만 등록가능
var disableDoneDay = NoEvaluatedDate();

function NoEvaluatedDate() {
    var user_id = $("#user_id").val();

    var result = [];
    $.ajax({
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

//submit
$(function () {
    $("#submit").click(function () {
        var sendData = JSON.stringify(
            {
                // *수정* session 처리
                user_id: $("#user_id").val(),
                // *수정* temp 코드 얻어오기
                temp_code: 1,
                // wear_date: $("#date-piker-input").val(),
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
                confirm("등록 하시겠습니까?");
                window.location.href="/";
                //location.href = "../";
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
        var key = 0;

        for(i=0; i<data.length; i++){
            if(data[i].cid == selectVal) {
                key = i;
            }
        }
        $("#selected-"+type+"-img").attr("src", data[key].cimg);
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

    return date;
}