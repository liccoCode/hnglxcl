$(() => {
    KindEditor.ready(function (K) {
        return window.editor = K.create('#outline', {
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
                $("#outline").val(htmlCode);
                return $('#previewDesc').html($('#outline').val());
            },
            items: ['source', '|', '|', 'forecolor', 'bold']
        });
    });

    KindEditor.ready(function (K) {
        return window.editor = K.create('#content', {
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
                $("#content").val(htmlCode);
                return $('#previewDesc').html($('#content').val());
            },
            items: ['source', '|', '|', 'forecolor', 'bold']
        });
    });

});