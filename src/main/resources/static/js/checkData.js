/*!
  * @author Igor Astafyev
  * Check data for correctness
  */

var submit_button = $('#submit-button');
var email = $('#email');
var fio = $('#full_name');
var age = $('#age');

submit_button.on("click", function () {
        // Проверка возраста
        if (Number.parseInt(age.val()) < 16 || Number.parseInt(age.val()) > 99) {
            age.css('border', '0.15rem solid red');
            $("#age-error").html("Укажите возраст правильно")
                .css("display", "flex")
                .css("color", "red");
            submit_button.prop('disabled', true);
        } else {
            age.css('border', 'none');
            $("#age-error").html("");
            submit_button.prop('disabled', false);
        }

        // Проверка длины ФИО
        if (fio.val().length > 70) {
            fio.css('border', '0.15rem solid red');
            $("#name-error").html("Слишком длинное имя")
                .css("display", "flex")
                .css("color", "red");
            submit_button.prop('disabled', true);
        } else {
            fio.css('border', 'none');
            $("#name-error").html("");
            submit_button.prop('disabled', false);
        }

        var regEx = /^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/i;
        if (regEx.test(email.val().toString())) {
            email.css('border', 'none');
            $("#email-error").html("");
            submit_button.prop('disabled', false);

            $.ajax({
                type: "POST",
                url: "/check-mail",
                data: {"email": email.val()},
                success: function (data) {
                    if (data === 'ok') {
                        email.css('border', 'none');
                        $("#email-error").html("");
                        submit_button.prop('disabled', false);
                    }
                    if (data === 'error') {
                        event.preventDefault();
                        email.css('border', '0.15rem solid red');
                        $("#email-error").html("E-mail занят другим пользователем")
                            .css("display", "flex")
                            .css("color", "red");
                    }
                },
                error: function () {
                    alert("На сервере произошла ошибка. Повторите попытку позже");
                }
            });
        } else {
            event.preventDefault();
            email.css('border', '0.15rem solid red');
            $("#email-error").html("Некорректный e-mail")
                .css("display", "flex")
                .css("color", "red");
        }
    }
);