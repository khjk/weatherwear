//$(document).ready(function() {
//
//  $(".toggle-accordion").on("click", function() {
//    var accordionId = $(this).attr("accordion-id"),
//      numPanelOpen = $(accordionId + ' .collapse.in').length;
//
//    $(this).toggleClass("active");
//
//    if (numPanelOpen == 0) {
//      openAllPanels(accordionId);
//    } else {
//      closeAllPanels(accordionId);
//    }
//  })
//
//  openAllPanels = function(aId) {
//    console.log("setAllPanelOpen");
//    $(aId + ' .panel-collapse:not(".in")').collapse('show');
//  }
//  closeAllPanels = function(aId) {
//    console.log("setAllPanelclose");
//    $(aId + ' .panel-collapse.in').collapse('hide');
//  }
//
//});
var clothes_code_list = document.querySelectorAll(".wear_code");
var Outer_img_place = document.querySelectorAll(".outerImg");
var Top_img_place = document.querySelectorAll(".topImg");
var Bottom_img_place = document.querySelectorAll(".bottomImg");
var temp_code_list = document.querySelectorAll(".eval-date-temp");

function getImageListForOneBlock(wear_code, i) {
  $.ajax({
            type: 'GET',
            url : '../api/v1/clothes/img/' + wear_code,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(response){
        var average_temperature = getAvgTempByTempCode(temp_code_list[i].innerHTML);
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
            temp_code_list[i].innerHTML = average_temperature + "°C";
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

$(function(){
deleteUser.init();
});

