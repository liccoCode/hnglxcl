$(() => {

    $("input[name='openModal']").click(function () {
        $("#modal-title").text($(this).val());
        if ($(this).val() === '上传主图') {
            $("#uploadType").val("pic");
        }

        $("#productId").val($(this).data("id"));
        $("#submitPdfModal").modal("show");
    });

});

