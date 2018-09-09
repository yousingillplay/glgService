/*
 * $Id: jquery.springFormModal.js,v 1.4 2016/04/19 17:05:11 a0199948 Exp $
 * Jquery Spring Form (Bootstrap) Modal
 *
 * Usage example:
 * HTML:
 * <span class='editrow glyphicon glyphicon-pencil'></span>
 *
 * Script:
 * $('#maintable tbody').on('click', '.editrow', function () {
 * var data = mainTable.row($(this).parents('tr')).data();
 * var params = $.param(data);
 * var formId = '${fn:split(editURL,"/")[fn:length(fn:split(editURL,"/"))-1]}Form';
 * $(this).attr("data-source", '${editURL}?'+params);
 * $(this).attr("data-target", '#' + formId);
 * $(this).springFormModal({
 *           title : '${editTitle}',
 *           postSubmit: function() {mainTable.ajax.reload();}
 *      });
 * });
 */
(function ($) {
    $.fn.springFormModal = function (options) {
        var defaults = {
            title: 'Modal Window',
            modalId: 'myModal',
            cancelText: 'Cancel',
            submitText: 'Submit',
            action: null,
            postLoad: function() {},
            postSubmit: function () {},
        };
        // Extend our default options with those provided.
        var opts = $.extend({}, defaults, options);
        if (typeof opts.buttons === 'undefined') {
            opts.buttons = [
                {
                    name: opts.cancelText,
                    cssClass: 'btn-default',
                    attributes: 'data-dismiss="modal"'
                },
                {
                    name: opts.submitText,
                    cssClass: 'btn-primary',
                    attributes: 'data-submit="modal" data-modal="#' + opts.modalId + '"'
                }
            ];
        }

        return this.each(function () {
            var id = $(this).attr('data-target');
            if (typeof id !== 'undefined') {
                if ($(id).length === 0) {
                    opts.modalId = id.replace('#', '');
                    createDialog(id);
                }
                var url = $(this).attr('data-source');
                $(id + ' .modal-body').empty().load(url, function (response, status, xhr) {
                    if ( status === "error" ) {
                        // Nuke the whole page and replace with the response.
                        document.open();
                        document.write(response);
                        document.close();
                    }
                    if (opts.action !== null) {
                        $(id).find("form").attr("action", opts.action);
                    }
                    else {
                        // Work around browsers that filter the action
                        // attribute from form tags
                        var action = response.match("action=\"([^\"]*)\"")[1];
                        if (action !== null) {
                            $(id).find("form").attr("action", action);
                        }
                    }

                    $(id).modal()
                        .find('.modal-dialog')
                        .draggable({handle: ".modal-header"});
                    if (typeof opts.postLoad !== 'undefined') {
                        opts.postLoad();
                    }
                });
            }
        });

        function createDialog(id) {
            var modalContent = $('<div class="modal-content"></div>');
            modalContent.append(createModalHeader());
            modalContent.append(createModalBody());
            modalContent.append(createModalFooter());
            var modalDialog = $('<div class="modal-dialog"></div>');
            modalDialog.append(modalContent);
            var modalTop = $('<div class="modal fade" id="' + opts.modalId + '" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>');
            modalTop.append(modalDialog);
            $('#body').append(modalTop);
            $(document).on('click', id + ' [data-submit="modal"]', function () {
                if (typeof spinner !== 'undefined') {
                    spinner.show();
                }
                submit(id, opts.postSubmit);
            });
        }

        function createModalHeader() {
            var header = $('<div class="modal-header">'+
                    '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
                    '<h4 class="modal-title" id="' + opts.modalId + 'Label">' + opts.title + '</h4></div>');
            return header;
        }

        function createModalBody() {
            var body = $('<div class="modal-body"></div>');
            return body;
        }

        function createModalFooter() {
            var footer = $('<div class="modal-footer"></div>');
            for (var i = 0; i < opts.buttons.length; i++) {
                var btn = $('<button class="btn ' + opts.buttons[i].cssClass + '" ' + opts.buttons[i].attributes + '>' + opts.buttons[i].name + '</button>');
                if (typeof opts.buttons[i].id !== 'undefined') {
                    btn.attr("id", opts.buttons[i].id);
                }
                footer.append(btn);
            }
            return footer;
        }
    };

    var submit = function (modalId, postSubmit) {
        var form = $(modalId).find('form');
        $.post(form.attr('action'), form.serialize(), function (data) {
            if (typeof spinner !== 'undefined') {
                spinner.hide();
            }
            if (data === null || data.length === 0) {
                $(modalId).modal('hide');
                if (typeof postSubmit !== 'undefined') {
                    postSubmit();
                }
            }
            else {
                // Set or clear the error markers for each widget
                $(modalId).find(".form-control,.ui-widget").each(function (i, widget) {
                    var message = "";
                    $.each(data, function (j, err) {
                        if (widget.id === err["field"]) {
                            message = err["defaultMessage"];
                        }
                    });
                    var fg = $(widget).closest(".form-group");
                    if (message === "") {
                        fg.removeClass("has-error")
                                .find(".help-block").remove();
                    }
                    else {
                        fg.addClass("has-error");
                        var hb = fg.find(".help-block");
                        if (hb.length) {
                            hb.text(message);
                        }
                        else {
                            $("<span class='help-block'>" + message + "</span>").insertAfter("#" + widget.id);
                        }
                    }
                });
            }
        })
                .fail(function (data) {
                    // Nuke the whole page and replace with the response. Makes
                    // internal server errors and whatnot appear correctly.
                    document.open();
                    document.write(data.responseText);
                    document.close();
                });
    };

})(jQuery);