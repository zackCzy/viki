$(function() {
	var g = 1;
	var d = $.Rollchart();
	$.ajaxSetup({
		accepts : {
			"Accept-Charset" : "UTF-8"
		}
	});
	$(".search_user_i input").on(
			"keypress",
			function(e) {
				if (e.keyCode == 13 || e.charCode == 13) {
					window.open(BASE_PATH
							+ "/searchSpace/search_user?searchName="
							+ this.value + "&page=1&pageSize=15", "_blank");
				}
			});
	$(".search_user_i span").on(
			"click",
			function() {
				window.open(BASE_PATH + "/searchSpace/search_user?searchName="
						+ $(this).prev().val() + "&page=1&pageSize=15",
						"_blank");
			});
	$("body", "html").scrollTop(0);
	window.authority = document.getElementById("authority");
	try {
		$(".view_com").on("click", comClick);
	} catch (c) {
	}
	try {
		$(".remove_log").on("click", removeLog);
	} catch (c) {
	}
	try {
		$(".cut_out").on("click", cutClick);
	} catch (c) {
	}
	try {
		$(".sendComment").on("click", sendClick);
	} catch (c) {
	}
	try {
		$(".nav-box").get(0).addEventListener("touchstart", function() {
			if (this.count == undefined) {
				this.count = 0;
			}
			var e = $(".head-nav ul");
			if (this.count++ % 2 == 0) {
				e.css("height", "auto");
			} else {
				e.css("height", "0");
			}
		});
	} catch (c) {
	}
	var a = $(".home_list_display").height();
	var f = document.documentElement.scrollTop == 0 ? document.body.clientWidth
			: document.documentElement.clientWidth;
	if (f >= 1100) {
		$(".Home_page_nav").width(1100).css("left", (f - 1100) / 2);
	} else {
		$(".Home_page_nav").width("100%").css("left", 0);
	}
	$(window)
			.resize(
					function() {
						var e = document.documentElement.scrollTop == 0 ? document.body.clientWidth
								: document.documentElement.clientWidth;
						if (e >= 1200) {
							$(".Home_page_nav").width(1100).css("left",
									(e - 1100) / 2);
						} else {
							$(".Home_page_nav").width("100%").css("left", 0);
						}
					});
	$(window)
			.scroll(
					function() {
						var j = document.documentElement.scrollTop == 0 ? document.body.clientWidth
								: document.documentElement.clientWidth;
						var e = document.documentElement.scrollTop == 0 ? document.body.clientHeight
								: document.documentElement.clientHeight;
						var i = document.documentElement.scrollTop == 0 ? document.body.scrollTop
								: document.documentElement.scrollTop;
						var h = document.documentElement.scrollTop == 0 ? document.body.scrollHeight
								: document.documentElement.scrollHeight;
						$return = $("#returnHead");
						if (i > e * 1.3) {
							if ($return.attr("alt") != "false") {
								$return.attr("alt", "false").stop(true)
										.animate({
											right : "0px"
										}, 300);
							}
						} else {
							if ($return.attr("alt") != "true") {
								$return.attr("alt", "true").stop(true).animate(
										{
											right : "-100px"
										}, 300);
							}
						}
						if (e + i + 50 >= h) {
							if (!window.scrollflag) {
								$(".point_load_status").show(200);
								window.scrollflag = true;
								b();
							}
						}
					});
	$("#returnHead").on("click", function(e) {
		$("html,body").animate({
			scrollTop : "0px"
		}, 800);
	});
	function b() {
		$.ajax({
			url : BASE_PATH + "/json/content_getContent",
			type : "get",
			dataType : "json",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			data : {
				page : ++g,
				userId : document.getElementById("space_user_id").value,
				newSport : document.getElementById("type").value
			},
			success : function(e) {
				$(".point_load_status").hide(200);
				if (e.length > 0) {
					createUserCon(e);
					window.scrollflag = false;
				}
			}
		});
	}
});
function comClick() {
	var k = $(this.parentNode).next();
	var n = this.getAttribute("alt");
	var z, c, o, w, b, x, l, v, y, y, s;
	var a, p, e, j, h, h, f, m, d, u, q, t, i;
	var g = document;
	if (parseInt($("b", this).text()) > 0 && k.attr("alt") != "true") {
		var r = $(".load_box", k).stop(true).show(200);
		$.ajax({
			url : BASE_PATH + "/json/com_com",
			type : "get",
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			data : {
				logId : n
			},
			dataType : "json",
			success : function(C) {
				k.attr("alt", "true");
				var A = C;
				r.stop(true).hide(200);
				var D = k.children(3).get(0);
				for ( var B = 0; B < A.length; B++) {
					z = g.createElement("div");
					z.className = "smallCom";
					c = g.createElement("span");
					o = g.createElement("img");
					o.setAttribute("src", BASE_PATH
							+ "/load/download_getSmallPhoto?id=" + A[B].id);
					c.appendChild(o);
					c.className = "userPhoto";
					w = g.createElement("a");
					$(w).text(A[B].name + ":");
					w.setAttribute("href", BASE_PATH + "/user/space/"
							+ A[B].account + "/");
					w.setAttribute("target", "blank");
					b = g.createElement("div");
					$(b).text(A[B].comText);
					x = g.createElement("strong");
					small_date = g.createElement("span");
					$(small_date).text(A[B].date);
					x.appendChild(small_date);
					l = g.createElement("span");
					l.style.background = "url('" + BASE_PATH
							+ "/image/reply_ico.png') no-repeat";
					l.style.width = "19px";
					l.style.height = "17px";
					l.style.marginTop = "5px";
					l.setAttribute("title", "回复");
					l.setAttribute("alt", A[B].id);
					x.appendChild(l);
					v = g.createElement("div");
					v.className = "small_reply_area";
					y = g.createElement("div");
					y.setAttribute("contenteditable", "true");
					y.className = "small_reply_input";
					$(l).click(replyClick);
					v.appendChild(y);
					s = g.createElement("input");
					s.setAttribute("type", "button");
					s.className = "small_reply_bt";
					s.value = "评论";
					s.setAttribute("alt", A[B].commentId);
					$(s).click(sendReplyCom);
					v.appendChild(s);
					if (A[B].comId != undefined) {
						p = g.createElement("a");
						p.setAttribute("alt", A[B].comId);
						p.className = "remove_com";
						$(p).text("删除");
						$(p).click(removeClick);
						x.appendChild(p);
					}
					z.appendChild(c);
					z.appendChild(w);
					z.appendChild(b);
					z.appendChild(x);
					for ( var E = 0; E < A[B].reviewewCom.length; E++) {
						a = g.createElement("div");
						a.className = "reply";
						e = g.createElement("span");
						e.className = "userPhoto";
						j = g.createElement("img");
						j.setAttribute("src", BASE_PATH
								+ "/load/download_getSmallPhoto?id="
								+ A[B].reviewewCom[E].id);
						e.appendChild(j);
						h = g.createElement("a");
						$(h).text(
								A[B].reviewewCom[E].comName + "回复"
										+ A[B].reviewewCom[E].name);
						f = g.createElement("div");
						$(f).text(A[B].reviewewCom[E].comText);
						m = g.createElement("strong");
						d = g.createElement("span");
						$(d).text(A[B].reviewewCom[E].date);
						u = g.createElement("span");
						u.style.background = "url('" + BASE_PATH
								+ "/image/reply_ico.png') no-repeat";
						u.style.width = "19px";
						u.style.height = "17px";
						u.style.marginTop = "5px";
						u.setAttribute("title", "回复");
						u.setAttribute("alt", A[B].reviewewCom[E].id);
						$(u).click(replyClick);
						m.appendChild(d);
						m.appendChild(u);
						q = g.createElement("div");
						q.className = "small_reply_area";
						t = g.createElement("div");
						t.className = "small_reply_input";
						t.setAttribute("contenteditable", "true");
						i = g.createElement("input");
						i.setAttribute("type", "button");
						i.className = "small_reply_bt";
						i.value = "评论";
						i.setAttribute("alt", A[B].commentId);
						$(i).click(function() {
							sendReplyCom.call(this, 1);
						});
						q.appendChild(t);
						q.appendChild(i);
						a.appendChild(e);
						a.appendChild(h);
						a.appendChild(f);
						a.appendChild(m);
						a.appendChild(q);
						z.appendChild(a);
					}
					z.appendChild(v);
					D.appendChild(z);
				}
			}
		});
	}
	if (k.css("display") == "none") {
		k.slideDown(500);
	} else {
		k.slideUp(500);
	}
}
function replyClick() {
	var b = $(this.parentNode.parentNode.lastChild);
	if (b.css("display") != "none") {
		b.find(".small_reply_input").focus();
		return false;
	}
	$(".small_reply_area").hide(200);
	var a = b.show(200);
	a.attr("alt", this.getAttribute("alt"));
}
function cutClick() {
	var a = $(this.parentNode);
	a.slideUp(500);
}
function sendReplyCom(b) {
	var c = this;
	if ($(c).prev().text().isEmpty().length <= 0) {
		$(c).prev().shake();
		return false;
	}
	c.disabled = true;
	var a = new Date();
	$
			.ajax({
				url : BASE_PATH + "/user/comment_saveReviewewComment",
				type : "post",
				contentType : "application/x-www-form-urlencoded;charset=UTF-8",
				data : {
					"rc.content" : $(c).prev().text().isEmpty(),
					commentID : c.getAttribute("alt"),
					userId : $(c.parentNode).attr("alt")
				},
				success : function(f) {
					c.disabled = false;
					if (f == "you login has expired") {
						login();
						return
					}
					try {
						var q = JSON.parse(f);
						var p = document.createElement("div");
						p.className = "reply";
						var j = document.createElement("span");
						var k = document.createElement("img");
						k.setAttribute("src", BASE_PATH
								+ "/load/download_getSmallPhoto?id="
								+ q.comUserID);
						j.appendChild(k);
						j.className = "userPhoto";
						var o = document.createElement("a");
						$(o).text(q.userName + " 回复 " + q.replyName);
						var m = document.createElement("div");
						$(m).text($(c).prev().text());
						var r = document.createElement("strong");
						var d = document.createElement("span");
						$(d).text(
								a.getFullYear() + "-" + (a.getMonth() + 1)
										+ "-" + a.getDate() + "  "
										+ a.getHours() + ":" + a.getMinutes());
						var n = document.createElement("span");
						n.style.background = "url('" + BASE_PATH
								+ "/image/reply_ico.png') no-repeat";
						n.style.width = "19px";
						n.style.height = "17px";
						n.style.marginTop = "5px";
						n.setAttribute("title", "回复");
						n.setAttribute("alt", q.comUserID);
						$(n).click(replyClick);
						r.appendChild(d);
						r.appendChild(n);
						var i = document.createElement("div");
						i.className = "small_reply_area";
						var g = document.createElement("div");
						g.className = "small_reply_input";
						g.setAttribute("contenteditable", "true");
						var l = document.createElement("input");
						l.setAttribute("type", "button");
						l.className = "small_reply_bt";
						l.value = "评论";
						l.setAttribute("alt", q.commentId);
						$(l).click(function() {
							sendReplyCom.call(this, 1);
						});
						i.appendChild(g);
						i.appendChild(l);
						p.appendChild(j);
						p.appendChild(o);
						p.appendChild(m);
						p.appendChild(r);
						p.appendChild(i);
						try {
							$(p).insertBefore(
								$(".small_reply_area",
								(b == 1 ? c.parentNode.parentNode.parentNode
								: c.parentNode.parentNode)).eq(-1));
						} catch (h) {
						}
						$(c.parentNode).hide(300);
					} catch (h) {
						$.notice("viki提醒你!", "评论失败!");
					}
				}
			});
}
function sendClick() {
	var c = this;
	var d = $(c).prev();
	if (d.text().isEmpty().length <= 0) {
		var a = d.css({
			border : "1px solid #ff4700"
		}).shake(function() {
			a.css({
				border : "1px solid #DEDEDE"
			});
		}, 1);
		return
	}
	c.disabled = true;
	var b = new Date();
	$.ajax({
		url : BASE_PATH + "/user/comment_save",
		type : "post",
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data : {
			"c.content" : d.text().isEmpty(),
			id : $(c).attr("alt")
		},
		success : function(s) {
			c.disabled = false;
			if (s == "you login has expired") {
				login();
				return
			}
			try {
				var t = JSON.parse(s);
				var j = document.createElement("div");
				j.setAttribute("class", "smallCom");
				j.style.display = "none";
				var p = document.createElement("span");
				var k = document.createElement("img");
				k.src = BASE_PATH + "/load/download_getSmallPhoto?id=" + t.id;
				p.appendChild(k);
				p.className = "userPhoto";
				j.appendChild(p);
				var f = document.createElement("a");
				$(f).text(t.spaceName + ":");
				f.setAttribute("target", "blank");
				f.setAttribute("href", BASE_PATH + "/user/space/" + t.name
						+ "/");
				j.appendChild(f);
				var q = document.createElement("div");
				$(q).text(d.text().isEmpty());
				j.appendChild(q);
				var r = document.createElement("strong");
				small_date = document.createElement("span");
				$(small_date).text(
						b.getFullYear() + "-" + (b.getMonth() + 1) + "-"
								+ b.getDate() + "  " + b.getHours() + ":"
								+ b.getMinutes());
				r.appendChild(small_date);
				var h = document.createElement("span");
				h.style.background = "url('" + BASE_PATH
						+ "/image/reply_ico.png') no-repeat";
				h.style.width = "19px";
				h.style.height = "17px";
				h.style.marginTop = "5px";
				h.setAttribute("title", "回复");
				h.setAttribute("alt", t.id);
				r.appendChild(h);
				var i = document.createElement("div");
				i.className = "small_reply_area";
				var g = document.createElement("div");
				g.setAttribute("contenteditable", "true");
				g.className = "small_reply_input";
				$(h).click(replyClick);
				i.appendChild(g);
				var m = document.createElement("input");
				m.setAttribute("type", "button");
				m.className = "small_reply_bt";
				m.value = "评论";
				m.setAttribute("alt", t.commentId);
				$(m).click(sendReplyCom);
				i.appendChild(m);
				if (t.commentId != undefined) {
					var l = document.createElement("a");
					l.setAttribute("alt", t.commentId);
					l.className = "remove_com";
					$(l).text("删除");
					$(l).click(removeClick);
					r.appendChild(l);
				}
				j.appendChild(r);
				j.appendChild(i);
				$("h5", c.parentNode).append(j);
				var n = $(c.parentNode).prev().find(".view_com b");
				n.text(parseInt(n.text()) + 1);
				$(j).slideDown(300);
			} catch (o) {
				$.notice("viki提醒你!", "评论失败!");
			}
		}
	});
}
function removeLog() {
	var a = this;
	var b = {
		title : "<strong class='font-szie:13px;font-weight:300;color:#FB662E' >VIKI提醒您</strong>",
		body : "<p >您真的要删除该文章吗？</p>",
		bt : {
			okVal : "确定",
			noVal : "取消",
			okFn : function() {
				removeLogT(a);
			},
			noFn : function() {
				return false;
			}
		}
	};
	$.texi(b);
}
function removeLogT(a) {
	a.disabled = true;
	$.ajax({
		url : BASE_PATH + "/user/function_removeDiary",
		type : "get",
		timeout : 5000,
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data : {
			userId : a.getAttribute("alt")
		},
		success : function(c) {
			if (c == "you login has expired") {
				login();
				return
			}
			a.disabled = false;
			if (c == "removeDiary is ok") {
				var b = $(a.parentNode.parentNode).slideUp(300, function() {
					this.remove();
				});
			} else {
				$.notice("viki提醒你!", "删除日记失败!");
			}
		},
		error : function() {
			$.notice("viki提醒你!", "删除日记失败!");
		}
	});
}
function removeClick() {
	var a = this;
	a.disabled = true;
	$.ajax({
				url : BASE_PATH + "/user/comment_remove",
				type : "get",
				timeout : 5000,
				contentType : "application/x-www-form-urlencoded;charset=UTF-8",
				data : {
					id : this.getAttribute("alt")
				},
				success : function(c) {
					if (c == "you login has expired") {
						login();
						return
					}
					if (c == "remove is ok") {
						a.disabled = false;
						var b = $(a.parentNode.parentNode)
								.slideUp(
										300,
										function() {
											var d = $(
													a.parentNode.parentNode.parentNode.parentNode)
													.prev().find(".view_com b")
													.eq(0);
											d.html(parseInt(d.html()) - 1);
											b.remove();
										});
					} else {
						$.notice("viki提醒你!", "删除评论失败!");
					}
				},
				error : function() {
					$.notice("viki提醒你!", "删除日记失败!");
				}
			});
}
function sendSmallSpeak(c) {
	var b = $("#shuoshuo");
	var a = b.text().isEmpty();
	if (a.length <= 0) {
		b.css({
			border : "1px solid #FF4700"
		}).shake(function() {
			b.css({
				border : "1px solid #dedede"
			});
		});
		return
	}
	c.disabled = true;
	$.ajax({
		url : BASE_PATH + "/user/function_saveSpeak",
		type : "post",
		timeout : 5000,
		contentType : "application/x-www-form-urlencoded;charset=UTF-8",
		data : {
			"userlog.logName" : "微说",
			"userlog.noHtmlLog" : a,
			"userlog.visible" : true,
			token : c.getAttribute("alt")
		},
		success : function(f) {
			c.disabled = false;
			try {
				json = JSON.parse(f);
				try {
					if ($(".userNotice").remove()) {
					}
				} catch (d) {
				}
				createUserCon(JSON.parse(f), $("home_content_display"));
			} catch (d) {
				$.notice("viki提醒你!", "发布失败!");
			}
		},
		error : function() {
			$.notice("viki提醒你!", "发布失败!");
		}
	});
}
function createUserCon(y, d) {
	var n = $(".home_content_display");
	var u = window.authority != 0;
	var l, a, v, A, i, e, B, x, q, r, w, p, o, b, g, h, c, z, m, s, k;
	var f = document;
	for ( var t = 0; t < y.length; t++) {
		l = f.createElement("div");
		l.className = "con_user_box";
		l.style.display = "none";
		a = f.createElement("img");
		a.src = BASE_PATH + "/load/download_getSmallPhoto?id="
				+ y[t].user[0].id;
		a.className = "user_photo";
		a.width = "40";
		a.height = "40";
		B = $("<a></a>");
		B.href = BASE_PATH + "/user/space/" + y[t].user[0].account + "/";
		B.title = y[t].user[0].name;
		$(a).appendTo(B);
		v = f.createElement("span");
		$(v).text(y[t].modifyDate);
		A = f.createElement("a");
		$(A).text(y[t].user[0].name);
		A.className = "user_name";
		A.setAttribute("href", BASE_PATH + "/user/space/"
				+ y[t].user[0].account + "/");
		A.setAttribute("target", "_blank");
		i = f.createElement("div");
		i.className = "content_user_li";
		if (y[t].logName != decodeURIComponent("微说")) {
			$(i).html(
					"<strong class='logTitle'>《" + y[t].logName
							+ "》</strong><br>");
		}
		e = f.createElement("div");
		$(e).text(y[t].noHtmlLog);
		i.appendChild(e);
		x = f.createElement("a");
		x.appendChild(i);
		if (y[t]["smallSpeak"] == "false") {
			x.setAttribute("href", BASE_PATH
					+ "/user/function_r_readDiary?logId=" + y[t].id);
		}
		x.setAttribute("target", "_blank");
		q = f.createElement("div");
		q.className = "comment_box";
		if (u) {
			r = f.createElement("span");
			$(r).text("删除");
			r.setAttribute("alt", y[t].id);
			$(r).click(removeLog);
			q.appendChild(r);
			if (y[t]["smallSpeak"] == "false") {
				w = f.createElement("span");
				p = f.createElement("a");
				$(p).text("编辑");
				p.setAttribute("target", "_blank");
				p.setAttribute("href", BASE_PATH
						+ "/user/function_modifyDiary?logId=" + y[t].id);
				w.appendChild(p);
				q.appendChild(w);
			}
		}
		o = f.createElement("span");
		$(o).text("浏览(" + y[t].visibleNum + ")");
		b = f.createElement("span");
		b.innerHTML = "评论(<b>" + y[t].commentNum + "</b>)";
		b.setAttribute("alt", y[t].id);
		b.className = "view_com";
		q.appendChild(b);
		q.appendChild(o);
		$(b).click(comClick);
		g = f.createElement("div");
		g.className = "comment";
		h = f.createElement("h5");
		$(h).text("评论：");
		c = f.createElement("div");
		c.setAttribute("contenteditable", "true");
		c.className = "commentArea";
		z = f.createElement("input");
		z.value = "评论";
		z.setAttribute("type", "button");
		z.className = "sendComment";
		z.setAttribute("alt", y[t].id);
		$(z).click(sendClick);
		m = f.createElement("div");
		m.className = "displayComment";
		s = f.createElement("b");
		s.className = "cut_out";
		$(s).text("↑  收起");
		$(s).click(cutClick);
		k = f.createElement("div");
		$(k).text("正在拼了命的,为您加载!");
		k.className = "load_box";
		g.appendChild(h);
		g.appendChild(c);
		g.appendChild(z);
		g.appendChild(m);
		g.appendChild(k);
		g.appendChild(s);
		l.appendChild(a);
		l.appendChild(v);
		l.appendChild(A);
		l.appendChild(x);
		l.appendChild(q);
		l.appendChild(g);
		if (d != null) {
			$(l).insertAfter($("#addContent")).slideDown(300);
		} else {
			$(l).appendTo(".home_content_display").slideDown(300);
		}
	}
};