var login = {
    init : function () {
        var _this = this;
        $('#signup_name').on('blur', _this.validation);
        $('#signup_name').on('focus',_this.erase);
        $('#signup_id').on('focus',_this.erase);
        $('#signup_password').on('focus',_this.erase);
        $('#signup_password').on('blur',_this.password_validation);
        $('#btn-login').on('click', function () {
            _this.login();
        });
        $('#btn-signup').on('click',function () {
             _this.signup();
        });
        $('#check').on('click',function() {
            var VAL_ID = $('#signup_id').val();
            _this.emailCheck(VAL_ID);
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
            alert("이메일과 패스워드를 확인해주세요.");
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
            alert("양식을 다시 확인해주세요.");
        });
    },
    validation : function (event) {
         var inputValue = $(this).val();
         if(inputValue.search(/\s/) != -1) {
                var error_span = $(this).next();
                error_span.text('공백을 포함할 수 없습니다.');
                $(this).val('');
         }
    },
    password_validation : function (event) {
        var passwordValue = $(this).val();
        if(passwordValue.length < 5){
            var error_span = $(this).next();
            error_span.text('패스워드의 길이가 너무 짧습니다.');
            $(this).val('');
        }
    },
    emailCheck : function (VAL_ID) { //이메일 중복검사
        $.ajax({
           type: 'GET',
           url : '../api/v1/users/' + VAL_ID,
           dataType: 'json',
           contentType: 'application/json; charset=utf-8',
           }).done(function (response) {
               // $('#check').css('left','120px');
                $('#email_val_text').css("color", 'red');
               $('#email_val_text').text('이미 가입된 이메일입니다.');
               $('#signup_id').val('');
           }).fail(function (jqXHR, textStatus) {
                if(VAL_ID.search('@') == -1){
                   alert('이메일의 형식이 올바르지 않습니다.');
                    $('#email_val_text').css("color", 'red');
                   $('#email_val_text').text('이메일의 형식이 올바르지 않습니다.');
                   $('#signup_id').val('');
                   return;
                }
               if(jqXHR.status == '404'){
                alert('사용가능한 아이디입니다.');
                $('#email_val_text').css("color", 'green');
                $('#email_val_text').text('사용가능한 아이디입니다.');
               }
           });
    },
    erase : function () {
        var error_span = $(this).next();
        error_span.text('');
    }
}
login.init();

