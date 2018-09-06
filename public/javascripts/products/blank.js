$(() => {
    KindEditor.ready(function (K) {
        return window.editor = K.create('#feature', {
            resizeType: 1,
            allowPreviewEmoticons: false,
            allowImageUpload: false,
            newlineTag: 'br',
            afterChange: function () {
                var count, div, htmlCode;
                div = $('<div>').html($("<div>").html(this.html()).text());
                div.find('div').replaceWith(function () {
                    return $(this).contents();
                });
                div.find('span').replaceWith(function () {
                    return $(this).contents();
                });
                htmlCode = div.html();
                count = htmlCode.length;
                $("#feature").val(htmlCode);
                return $('#previewDesc').html($('#feature').val());
            },
            items: ['source', '|', '|', 'forecolor', 'bold']
        });
    });

    KindEditor.ready(function (K) {
        return window.editor = K.create('#application', {
            resizeType: 1,
            allowPreviewEmoticons: false,
            allowImageUpload: false,
            newlineTag: 'br',
            afterChange: function () {
                var count, div, htmlCode;
                div = $('<div>').html($("<div>").html(this.html()).text());
                div.find('div').replaceWith(function () {
                    return $(this).contents();
                });
                div.find('span').replaceWith(function () {
                    return $(this).contents();
                });
                htmlCode = div.html();
                count = htmlCode.length;
                $("#application").val(htmlCode);
                return $('#previewDesc').html($('#application').val());
            },
            items: ['source', '|', '|', 'forecolor', 'bold']
        });
    });




});