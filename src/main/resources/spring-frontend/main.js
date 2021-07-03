$(document).ready(function () {
    $("#locales").change(function () {
        var selectedOprion = $('#locales').val();
        if (selectedOprion != '') {
            window.location.replace('?lang=' + selectedOprion);
        }
    });
});