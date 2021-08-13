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

$(document).ready(function () {
    $('a.btn').click(function (event) {
        event.preventDefault();
        $('#myOverlay').fadeIn(297, function () {
            $('#myPopup').css('display', 'block').animate({opacity: 1}, 198);
        });
    });

    $('#myPopup__close, #myOverlay').click(function () {
        $('#myPopup').animate({opacity: 1}, 198, function () {
            $(this).css('display', 'none');
            $('#myOverlay').fadeOut(297);
        });
    });
});
