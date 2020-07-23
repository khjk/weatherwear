<<<<<<< HEAD
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
=======
$(document).ready(function() {

  $(".toggle-accordion").on("click", function() {
    var accordionId = $(this).attr("accordion-id"),
      numPanelOpen = $(accordionId + ' .collapse.in').length;

    $(this).toggleClass("active");

    if (numPanelOpen == 0) {
      openAllPanels(accordionId);
    } else {
      closeAllPanels(accordionId);
    }
  })

  openAllPanels = function(aId) {
    console.log("setAllPanelOpen");
    $(aId + ' .panel-collapse:not(".in")').collapse('show');
  }
  closeAllPanels = function(aId) {
    console.log("setAllPanelclose");
    $(aId + ' .panel-collapse.in').collapse('hide');
  }

});
>>>>>>> a41aff1f47a289dcecf3e54118ac14c508e11599

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
<<<<<<< HEAD
});

=======
});
>>>>>>> a41aff1f47a289dcecf3e54118ac14c508e11599
