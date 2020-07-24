var login = {
    init : function () {
        var _this = this;
        $('#btn-login').on('click', function () {
            _this.login();
        });
        $('#btn-signup').on('click',function () {
             _this.signup();
        });
    },
    login : function () {
        var data = {
            id : $('#login_id').val(),
            password : $('#login_password').val()
        };

        $.ajax({
            type: 'POST',
            url : '../api/v1/users/validation',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(response) {
            loginSuccess(response); //return response.json();
        }).fail(function (error) {
            alert(error.json);
        });
        function loginSuccess(user){
           alert(user.id + "님의 브라우저 위치 접근을 허락해주세요!");
           window.location.href="/";
       };
    },
    signup : function () {
        var data = {
            name : $('#signup_name').val(),
            id: $('#signup_id').val(),
            password : $('#signup_password').val()
        };

        $.ajax({
            type: 'POST',
            url : '../api/v1/users',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (response) {
            alert('회원 가입 완료되었습니다.');
            window.location.href="/";
        }).fail(function (error) {
            alert(error.json);
        });
    }

}
login.init();

//$(function(){
//$("#signup_id").on("blur",function() {
//		var id = $('#signup_id').val();
//		$.ajax({
//		    type : 'GET',
//			url : 'api/v1/users/id-check/'+id,
//			success : function(data) {
//			console.log(data);
//			     if(data == 0) {
//			        $("#id_check").text("사용가능한 아이디입니다.");
//                    $("#id_check").css("color", "blue");
//
//			    } else if(data == 1) {
//			        $("#id_check").text("사용중인 아이디입니다.");
//                    $("#id_check").css("color", "red");
//
//			    }
//				}, error : function() {
//						console.log("실패");
//				}
//			});
//		});
//});

//$(function(){
//$("#signup_id").on("blur",function(){
//    var id = $('#signup_id').val();
//    if(id == "") {
//    	$("#id_check").text("아이디를 입력해주세요.");
//        $("#id_check").css("color", "red");
//     } else{
//        $("#id_check").text("");
//     }
//});
//$("#signup_password").on("blur",function(){
//    var id = $('#signup_password').val();
//    if(id == "") {
//    	$("#pwd_check").text("패스워드를 입력해주세요.");
//        $("#pwd_check").css("color", "red");
//     } else{
//        $("#pwd_check").text("");
//     }
//});
//
//});



//
//window.onload = function() {
//    var id = document.querySelector("input[name='signup_id']");
//    var password = document.querySelector("input[name='signup_password']");
//    var btn-signup = document.getElementById("btn-signup");
//    btn-signup.onclick = function() {
//        if(id.value == "") {
//            document.getElementById("id_check").innerText="아이디를 입력하세요.";
//            document.getElementById("id_check").style.color="red";
//            return;
//        } else {
//            document.getElementById("id_check").innerText="";
//        }
//        if(password.value == "") {
//            document.getElementById("pwd_check").innerText="패스워드를 입력하세요."
//            document.getElementById("pwd_check").style.color="red";
//            return;
//        } else {
//            document.getElementById("pwd_check").innerText="";
//        }
//        document.getElementById("signupForm").submit();
//    }
//}
