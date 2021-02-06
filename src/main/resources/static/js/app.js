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
    updateUser();
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

updateUser = () =>{
    $('.nBtn, .table .eBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        var roles;
         $.getJSON('/roles',function (roleName) {
            $.each(roleName, function (key,val) {
              roles +=  `<option value="${val}">${val}</option>`
            })
        });
        console.log(roles);
        //for update user
        if (text == 'New User') {
            //for creating user
            $('.myFormCreate #username').val('');
            $('.myFormCreate #password').val('');
            $('.myFormCreate #email').val('');
            $('.myFormCreate #myModalCreate').modal();
        } else {
            $.getJSON(href, function (user, status) {
                $('.myFormUpdate #id').val(user.id);
                $('.myFormUpdate #firstName').val(user.firstName);
                $('.myFormUpdate #lastName').val(user.lastName);
                $('.myFormUpdate #email').val(user.email);
                $('.myFormUpdate #role').append(`<option selected="selected" value = "${user.roleName}">${user.roleName}</option>`,roles);
                $('.myFormUpdate #password').val(user.password);
                $('.myFormUpdate #updateModal').modal();
            });
        }
    });
};
