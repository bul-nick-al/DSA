/**
 * Created by Nastya on 28.03.2017.
 */
$(document).ready(function () {
    // alert("Welcome to EiffelForms!")
    $("#js-next-button").on("click", function () {
        $.ajax({
            type: "POST",
            url: "Page1.html",
            success: function (html) {
                $("#container-main-field").html(html);
            }
        });

        // function loadNext(data) {
        //     $("#next-page").html(data)
        // }
    });
});