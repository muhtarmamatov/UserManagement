/*$(document).ready(
    function () {
        changePageAndSize();
    }
);

function changePageAndSize() {
    $('#pageSizeSelect').change(
        function (evt) {
            window.location.replace(`/?pageSize=${evt.target.value}&page=1`);
        }
    );
}*/
$(document).ready(() => {
    changePageAndSize();
    deleteUser();
});

changePageAndSize = () => {
    $('#pageSizeSelect').change(evt => {
        window.location.replace(`/?pageSize=${evt.target.value}&page=1`);
    });
};

deleteUser = () => {
    $('.table .delBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#removeModalCenter #delRef').attr('href', href);
        $('#removeModalCenter').modal();
    });
};
