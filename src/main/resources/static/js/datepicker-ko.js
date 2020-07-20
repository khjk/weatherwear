//datepicker 설정

// 한글 설정
( function( factory ) {
    if ( typeof define === "function" && define.amd ) {

        // AMD. Register as an anonymous module.
        define( [ "../widgets/datepicker" ], factory );
    } else {

        // Browser globals
        factory( jQuery.datepicker );
    }
}( function( datepicker ) {

    datepicker.regional.ko = {
        closeText: "닫기",
        prevText: "이전달",
        nextText: "다음달",
        currentText: "오늘",
        monthNames: [ "1월","2월","3월","4월","5월","6월",
            "7월","8월","9월","10월","11월","12월" ],
        monthNamesShort: [ "1월","2월","3월","4월","5월","6월",
            "7월","8월","9월","10월","11월","12월" ],
        dayNames: [ "일요일","월요일","화요일","수요일","목요일","금요일","토요일" ],
        dayNamesShort: [ "일","월","화","수","목","금","토" ],
        dayNamesMin: [ "일","월","화","수","목","금","토" ],
        weekHeader: "주",
        dateFormat: "yy-mm-dd",
        firstDay: 0,
        isRTL: false,
        showMonthAfterYear: true,
        yearSuffix: "년",
        // beforeShowDay: disableDoneDay
    };

    //등록된 날짜 넣기
    // var disableDoneDay = ["2020-07-09", "2020-07-07", "2020-07-08"];
    //
    // function disableDoneDay(date){
    //     var year = date.getFullYear();
    //     var month = date.getMonth();
    //     var day = date.getDate();
    //
    //     for(i=0; i < disableDoneDay.length; i++){
    //         if($.inArray(year + '-' + (month+1)+ '-' + day, disableDoneDay) != -1) {
    //             return [false];
    //         }
    //     }
    //     return [true];
    // };


    datepicker.setDefaults( datepicker.regional.ko );

    return datepicker.regional.ko;

} ) );