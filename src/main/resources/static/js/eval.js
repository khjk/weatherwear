var clothes_code_list = document.querySelectorAll(".wear_code");
var Outer_img_place = document.querySelectorAll(".outerImg");
var Top_img_place = document.querySelectorAll(".topImg");
var Bottom_img_place = document.querySelectorAll(".bottomImg");
var eval = {
    init : function() {
        var _this = this;
        $('button').on('click',function(event){
            var clickedBtn = event.target;
            var wear_no_temp = $(clickedBtn).parent();
            var wear_no = $(wear_no_temp).children().first().html();
            var del = $(clickedBtn).parent().parent();
            if(clickedBtn.id == "hot-btn"){
                _this.evaluateHot(wear_no,del);
            }else if(clickedBtn.id == "warm-btn" || clickedBtn.id == "del-btn"){
                _this.evaluateWarm(wear_no,del);
            }else if(clickedBtn.id == "cold-btn"){
                _this.evaluateCold(wear_no, del);
            }
        });
        _this.drawImage();
    },
    evaluateHot : function (wear_no, del) {
        var data = {
            like_no : 1
        };
        $.ajax({
            type: 'PUT',
            url : '../api/v1/wears/' + wear_no,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(response){
            del.remove();
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
            del.remove();
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
            del.remove();
        }).fail(function (error) {
            alert(error.json);
        });
    },drawImage : function (){ //각각 블록마다 알맞는 이미지 그려줌
        //DRAW IMAGE FOR EACH BLOCK
        for(var i=0; i<clothes_code_list.length; i++){
            console.log(clothes_code_list[i].innerHTML);
            getImageListForOneBlock(clothes_code_list[i].innerHTML, i);
        }//for
    }
}
function getImageListForOneBlock(wear_code, i) {
  $.ajax({
            type: 'GET',
            url : '../api/v1/clothes/img/' + wear_code,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(response){
            console.log(response);
            if(response.length == 3){ //반환값 3개일 때
               Outer_img_place[i].src = response[0];
               Top_img_place[i].src = response[1];
               Bottom_img_place[i].src = response[2];
            }else if(response.length ==2){ //반환값 2개일 때
                Outer_img_place[i].remove();
                Top_img_place[i].src = response[0];
                Bottom_img_place[i].src = response[1];
            }else{ //반환값 1개일 때
                Outer_img_place[i].remove();
                Top_img_place[i].src = response[0];
                Bottom_img_place[i].remove();
            }
        }).fail(function (error) {
            alert(error.json);
  });
}

$(function(){
eval.init();
});

