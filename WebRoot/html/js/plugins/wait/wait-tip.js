/*
 * wait - tip 2.0, jQuery plugin
 *
 * Copyright(c) 2015, gbcom
 * http://www.gbcom.com
 *
 * 2.0 Rewrite by doujun
 * http://www.gbcom.com
 *
 * tip info , in process wait.
 */
(function ($) {
	$.fn.waiting = function (method, options) {
		var $this = $(this);
		//对于modal层，仅对 .modal-content进行遮罩
		if ($this.hasClass('modal')) {
			$this = $this.find('.modal-content');
		}
		if (method == null || method == 'undefined') {
			method = 'show';
		}
		if (method != 'hide' && method != 'show') {
			if (options == null) {
				options = { msg: method };
			}
			method = 'show';
		}
		if (method == 'hide') {
			$this.find('.waiting').remove();
			$this.css('position', '');
			$("body").css('overflow','auto');
			return;
		}
		if (method == 'show') {
			var opts = $.extend({}, $.fn.waiting.defaults, options);
			//遮盖层
			var bg = $('<div class="waiting"></div>').css(opts.bgStyle);
			//消息层
			var msgBg = $('<div></div>').css(opts.style);
			//图标
			var icon = $('<i class="dynamic-icon"></i>');
			//消息
			var msg = $('<div>&nbsp;&nbsp; ' + opts.msg + '</div>').css(opts.friendly);
			$this.css('position', 'relative');
			$this.append(bg.append(msgBg.append(icon).append(msg)));
			$("body").css('overflow','hidden');
		}
	};
	$.fn.waiting.defaults = {
		bgStyle: {
			position: 'absolute',
			background: 'rgba(0,0,0,.5)',
			top: '0px',
			left: '0px',
			right: '0px',
			bottom: '-2px',
			'border-radius': '4px',
			'z-index': 3200,
			'overflow-x': 'auto',
			'overflow-y': 'scroll'
		},
		style: {
			position: 'absolute',
			background: '#fff',
			color: '#000',
			padding: '5px 15px',
			top: '50%',
			left: '50%',
			'border': '2px solid #4CC1EC',
			'font-weight': 'bold',
			'border-radius': '4px',
			'margin-top': '-25px',
			'margin-left': '-100px',
			'box-shadow': '2px 2px 10px #999',
			'font-size': '12px',
			'text-align': 'center'
		},
		friendly: {
			height: '32px',
			float: 'left',
			padding: '8px 3px'
		},
		msg: '<i class="fa fa-spinner fa-pulse fa-lg fa-fw"></i> &nbsp;玩命加载中，请稍候 ...'
	};
})(jQuery);