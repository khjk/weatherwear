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
    }
}

$(function(){
eval.init();
});

