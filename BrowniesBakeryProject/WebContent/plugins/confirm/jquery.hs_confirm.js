/**
 * current version 1.1.1 release 28/5/2015 edit error css
 * 
 * current version 1.1 release 5/5/2015 can change width and height of dialog,
 * can't button confirm
 * 
 * version 1.0 release 20/12/2014
 * 
 * @author Haric Silver
 * @email haric.silver@gmail.com
 */
(function($) {
	$.fn.openDialog = function(options) {
		if (typeof options === 'undefined') {
			options = {};
		}

		this.click(function(e) {
			e.preventDefault();

			$.openDialog(options);
		});

		return this;
	};

	/* create buttons confirm */
	function createBtn(settings, popup) {
		if (popup)
			return "";

		var btnHtml = "<div class='dg_confirm'><button class='confirm'>"
				+ settings.confirmText + "</button>";
		if (!settings.warning) {
			btnHtml += "<button class='cancel'>" + settings.cancelText
					+ "</button></div>";
		}
		return btnHtml;
	}
	;

	$.openDialog = function(options) {
		var defaults = {
			title : '',
			text : 'Bạn có muốn tiếp tục?',
			confirmText : 'Có',
			cancelText : 'Không',
			warning : false,
			modal : false,
			popup : false,
			width : '500px',
			maxHeight : '90%',
			duration : 900

		// can defind height of dialog is height attribute
		};
		var settings = $.extend({}, defaults, options);

		var modelHtml = "<div class='over'></div><div class='dialog'><div class='dg_title'><button class='dg_close'>x</button><span>"
				+ settings.title
				+ "</span></div><div class='dg_content'><p>"
				+ settings.text
				+ "</p></div>"
				+ createBtn(settings, settings.popup) + "</div>";

		// add dialog into DOM
		$('body').append(modelHtml);

		// format width and height of dialog is center
		$('.dialog').css('width', settings.width);
		$('.dialog').css('left',
				(($(window).width() - $('.dialog').width()) / 2) + 'px');
		// $('.dialog').css('margin-left', ($('.dialog').width()/-2)+'px');
		$('.dialog').css('height', settings.height);
		if ($('.dialog').height() > $(window).height()) {
			// default 90% or customize
			$('.dialog').css('height', settings.maxHeight);
		}

		// set border background
		$('.dialog').css('border', settings.border);
		$('.dg_title').css('background-color', settings.color);

		// animate fade in down
		var h = ($(window).height() - $('.dialog').height()) / 2;
		$('.over').fadeIn(300);
		$('.dialog').animate({
			top : h,
			opacity : 1
		}, settings.duration, 'swing', function() {
		});

		// default focus
		if (settings.warning) {
			$('.dg_confirm .confirm').focus();
		} else {
			$('.dg_confirm .cancel').focus();
		}

		// set default close function
		var modal;
		if (settings.modal) {
			modal = $('.dg_close, .confirm, .cancel');
		} else {
			modal = $('.over, .dg_close, .confirm, .cancel');

			// set options functions
			$('.over').on('click', options.close);
		}

		// close dialog function
		modal.on('click', function() {
			$('.dialog, .over').clearQueue().fadeOut(300, function() {
				$(this).remove();
			});
		});

		// set options function
		$('.confirm').on('click', options.confirm);
		$('.cancel').on('click', options.cancel);
		$('.dg_close').on('click', options.close);
	};
})(jQuery);