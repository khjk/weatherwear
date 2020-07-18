const signupButton = document.getElementById('signup-button'),
    loginButton = document.getElementById('login-button'),
    userForms = document.getElementById('user_options-forms');
var login = {
    init : function () {
        var _this = this;
        $('#btn-login').on('click', function () {
            userForms.classList.remove('bounceLeft');
            userForms.classList.add('bounceRight');
            _this.login();
        });
        $('btn-signup').on('click',function () {
              userForms.classList.remove('bounceRight');
              userForms.classList.add('bounceLeft');
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
            url : 'api/v1/validation',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(response) {
            loginSuccess(response); //return response.json();
        }).fail(function (error) {
            console.log(error.json);
        });
        function loginSuccess(user){
           alert(user.id + "님의 브라우저 위치 접근을 허락해주세요!");
           window.location.href="/";
       };
    },
    signup : function () {
        var data = {
            name : $('signup_name').val(),
            id: $('#signup_id').val(),
            password : $('#signup_password').val()
        };

        $.ajax({
            type: 'POST',
            url : 'api/v1/users',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('회원 가입 완료되었습니다.');
            window.location.href="/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

}
login.init();