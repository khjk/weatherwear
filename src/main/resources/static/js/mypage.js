var My_clothes_code_list = document.querySelectorAll(".wear_code");
var My_Outer_img_place = document.querySelectorAll(".outerImg");
var My_Top_img_place = document.querySelectorAll(".topImg");
var My_Bottom_img_place = document.querySelectorAll(".bottomImg");
var My_temp_code_list = document.querySelectorAll(".eval-date-temp");
var mypage = {
    init : function () {
        var _this = this;
        for(var i=0; i<My_clothes_code_list.length; i++){
           drawImageListForOneBlock( My_clothes_code_list[i].innerHTML, i);
        }//for
        $('.change-eval').on('click',function () {
                $(this).addClass('hidden');
                var del = $(this).next();
                del.removeClass('hidden');
        });
        $('.btn').on('click',function(event){
            var getBtnID= $(this)[0];
            if(getBtnID.id =="del-btn"){
            //아예 DB에서 지운다.
               var del = $(this).parent();
               var del_par = del.parent();
               var del_par_par = del_par.parent();
               del_par_par.remove(); //영영 데이터에서 삭제
            }
            var del = $(this).parent();
            del.addClass('hidden');
            var show = del.prev();
            show.removeClass('hidden');

             if($(this).id == "hot-btn"){
                 _this.evaluateHot(wear_no);


             }else if($(this).id == "warm-btn"){
                 _this.evaluateWarm(wear_no);


             }else if($(this).id == "cold-btn"){
                _this.evaluateCold(wear_no);


             }
         });
        }, //init
        evaluateHot : function (wear_no) {
                var data = {
                    like_no : -1
                };
                $.ajax({
                    type: 'PUT',
                    url : '../api/v1/wears/' + wear_no,
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function(response){
                    alert('success');
                }).fail(function (error) {
                    alert(error.json);
                });
            },
            evaluateWarm : function (wear_no,del) {
                var data = {
                    like_no : 0
                };
                $.ajax({
                    type: 'PUT',
                    url : '../api/v1/wears/' + wear_no,
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function(response){
                     alert('success');
                }).fail(function (error) {
                    alert(error.json);
                });
            },
            evaluateCold : function (wear_no,del) {
                var data = {
                    like_no : -1
                };
                $.ajax({
                    type: 'PUT',
                    url : '../api/v1/wears/' + wear_no,
                    dataType: 'json',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify(data)
                }).done(function(response){
                     alert('success');
                }).fail(function (error) {
                    alert(error.json);
            });
        }
    }

function drawImageListForOneBlock(wear_code, i) {
  $.ajax({
            type: 'GET',
            url : '../api/v1/clothes/img/' + wear_code,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(response){
        var average_temperature = getMyAvgTempByTempCode(My_temp_code_list[i].innerHTML);
            if(response.length == 3){ //반환값 3개일 때
               My_Outer_img_place[i].src = response[0];
               My_Top_img_place[i].src = response[1];
               My_Bottom_img_place[i].src = response[2];
            }else if(response.length ==2){ //반환값 2개일 때
                My_Outer_img_place[i].remove();
                My_Top_img_place[i].src = response[0];
                My_Bottom_img_place[i].src = response[1];
            }else{ //반환값 1개일 때
                My_Outer_img_place[i].remove();
                My_Top_img_place[i].src = response[0];
                My_Bottom_img_place[i].remove();
            }
            My_temp_code_list[i].innerHTML = average_temperature + "°C";
        }).fail(function (error) {
            alert(error.json);
  });
}

var deleteUser = {
    init : function(){
        var _this = this;
        $("#btn-delete").on('click',function(){
            _this.deleteUser();
        });
    },
    deleteUser : function () {
        var id = $("#user_id").attr("value");
        console.log(id);
        $.ajax({
            type:'DELETE',
            url: '../api/v1/users/'+id,
        }).done(function(response){
            alert('탈퇴했습니다. 그동안 이용해주셔서 감사합니다.');
            window.location.href= "/";
        }).fail(function(error){
            alert(error.json);
        });
    }
}

function getMyAvgTempByTempCode(tempCode){
    var AVG_TEMP = "";
    switch(tempCode){
        case "1" :
            AVG_TEMP = "30";
            break;
        case "2" :
            AVG_TEMP = "26";
            break;
        case "3" :
            AVG_TEMP = "22";
            break;
        case "4" :
            AVG_TEMP = "18";
            break;
        case "5" :
            AVG_TEMP = "14";
            break;
        case "6" :
            AVG_TEMP = "10";
            break;
        case "7" :
            AVG_TEMP = "6";
            break;
        case "8" :
            AVG_TEMP = "0";
    }
    return AVG_TEMP;
}
$(function(){
    mypage.init();
    deleteUser.init();
});