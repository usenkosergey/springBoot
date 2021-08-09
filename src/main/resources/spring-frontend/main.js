$(document).ready(function () {
    $("#locales").change(function () {
        let selectedOption = $('#locales').val();
        if (selectedOption !== '') {
            window.location.replace('?lang=' + selectedOption);
        }
    });
});

$('#uploadButton').on('click', function () {
    $('#dialog').trigger('click');
});

$('#dialog').on('change', function () {
    $('#imgForm').submit();
});
