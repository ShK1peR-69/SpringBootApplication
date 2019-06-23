/*!
  * @author Igor Astafyev
  * Check passwords for equality
  */

var first_password = $('#pass_one');
var second_password = $('#pass_two');
var submit_button = $('#submit-button');

first_password.on("keyup", function () {
    var value_input1 = second_password.val();
    var value_input2 = $(this).val();

    if (value_input1 !== value_input2) {
        $("#pass1-error").html("Пароли не совпадают!")
            .css("display", "flex")
            .css("color", "red");
        $("#pass2-error").html("Пароли не совпадают!")
            .css("display", "flex")
            .css("color", "red");
        second_password.css('border', '0.15rem solid red');
        first_password.css('border', '0.15rem solid red');
        submit_button.prop('disabled', true);

    } else {
        submit_button.removeProp('disabled');
        second_password.css('border', 'none');
        first_password.css('border', 'none');
        $("#pass1-error").html("");
        $("#pass2-error").html("")
        submit_button.prop('disabled', false);
    }
});

second_password.on("keyup", function () {
    var value_input1 = first_password.val();
    var value_input2 = $(this).val();

    if (value_input1 !== value_input2) {
        $("#pass1-error").html("Пароли не совпадают!")
            .css("display", "flex")
            .css("color", "red");
        $("#pass2-error").html("Пароли не совпадают!")
            .css("display", "flex")
            .css("color", "red");
        second_password.css('border', '0.15rem solid red');
        first_password.css('border', '0.15rem solid red');
        submit_button.prop('disabled', true);
    } else {
        submit_button.removeProp('disabled');
        second_password.css('border', 'none');
        first_password.css('border', 'none');
        $("#pass1-error").html("");
        $("#pass2-error").html("")
        submit_button.prop('disabled', false);
    }
});