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
});

changePageAndSize = () => {
    $('#pageSizeSelect').change(evt => {
        window.location.replace(`/?pageSize=${evt.target.value}&page=1`);
    });
}
