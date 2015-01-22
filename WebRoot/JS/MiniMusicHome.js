/**
 * 
 */

$(load);


function load(){
	
	try {
		$(".user_message_left").hover(function(){
			$(".user_message_left ul").stop().slideDown(300);	
		}, function(){
			$(".user_message_left ul").stop().slideUp(300);	
		});
		$("#exit_login").on("click", exitLogin);
	} catch (e) {}
	function exitLogin(){
		$.ajax({
			url:"/myHome/exitLogin",
			method : 'get',
			success:function(text){
				if(text=="exit is ok"){
					window.location.href=window.location.href;
				}
			}
		});
	};
	 window.myAngle=0;
	 window.searchMessage={
		name:null,
		index:1,
		flag:false
	 };
	 window.searchFlag=false;
	if(viewInner().height<626){
		var temp=145-(626-viewInner().height);
		$(".mini_music_lyric").css({height: temp<0 ? 0:temp+"px"});
	}else{
		$(".mini_music_lyric").css({height:150+(viewInner().height-626)+"px"});
	}
	 $(".mini_music_content_right").css({
		width:viewInner().width-338+"px" ,
		height:viewInner().height-40+"px"
	 });
	$("#searchText").on("keypress", keySearch);
	$("#searchButton").on("click", search);
	function keySearch(evt){
		if(evt.keyCode==13||evt.charCode==13){
			search();
		}
	}
	function search(){
		window.searchMessage.index=1;
		if(window.searchFlag===false){
			window.searchFlag=true;
			window.searchMessage.flag=false;
			searchMusic(1,$("#searchText").val(),window.searchMessage.index);	
		}	
	}
	$("#cursor").on("mousedown", click);
	$("#volume_bar_cursor").on("mousedown", click2);
	$("#volume_music").on("click", function(evt){
		var node=this;
		if(node.id!="volume_bar_cursor"){
			var className=$("#volume_music").get(0).className;
			var volume=100;
			if(className=="volume_music"){
				$("#volume_music").addClass("volume_music_mute");
				volume=0;
			}else{
				$("#volume_music").addClass("volume_music");
				volume=parseInt($("#volume_bar").css("width"))/60;
			}
			try {
				document.getElementById("Externa").volume(volume);		
			} catch (e) {
				document.getElementById("Externa1").volume(volume);
			}
		}
	});
	$(".progress").on("click", function(evt){
		$("#play_music").addClass("pause_music");
		$("#currTimeBar #bar").css({width:evt.clientX/320*100+"%"});
		try {
			document.getElementById("Externa").position(evt.clientX/320*100);
		} catch (e) {
			document.getElementById("Externa1").position(evt.clientX/320*100);
		}
	});
	$("#music_img_bg").css({
		height:viewInner().height+"px",
		width:viewInner().width+"px"
	});	
	$(window).on("resize", function(){
	
		if(viewInner().height<626){
			var temp=145-(626-viewInner().height);
			$(".mini_music_lyric").css({height: temp<0 ? 0:temp+"px"});
		}else{
			$(".mini_music_lyric").css({height:150+(viewInner().height-626)+"px"});
		}
		 $(".mini_music_content_right").css({
				width:viewInner().width-338+"px" ,
				height:viewInner().height-40+"px"
			 });
		if(viewInner().height<500||viewInner().width<700){
			return;
		}	

		$("#music_img_bg").css({
			height:viewInner().height+"px",
			width:viewInner().width+"px"
		});	
	});	
	
	
	$("#play_music").on("click", function(){
		if(this.className=="play_music"){
			this.className="pause_music";
			try {
				document.getElementById("Externa").playMusic();
			} catch (e) {
				document.getElementById("Externa1").playMusic();
			}
			window.songTime=setInterval(function(){
				if( window.myAngle==360){
					window.myAngle=0;
				}
				$("#singer_photo").css("tranforms","rotate("+ (window.myAngle++)+"deg)");
			}, 30);
		}else{
			this.className="play_music";
			miniMusic.pause();
			clearInterval(window.songTime);
		}
	});
	var miniMusic=new MiniMusicBox();
	function MiniMusicBox(){
		var timeStr="00:00";
		var BufferBar=0;
		var progressBar=0;
		var volume=1;
		var position=0;
		this.getPosiction=function(){
			return position;
		};
		this.setPosiction=function(num){
			try {
				document.getElementById("Externa").posiction(num);
			} catch (e) {
				document.getElementById("Externa1").posiction(num);
			}
		};
		this.getVolume=function(){
			return volume;
		};
		this.setVolume=function(num){
			try {
				document.getElementById("Externa").volume(num);
			} catch (e) {
				document.getElementById("Externa1").volume(num);
			}
		};
		this.play=function(url){
			try {
				document.getElementById("Externa").playMusic(url);
			} catch (e) {
				document.getElementById("Externa1").playMusic(url);
			}
		};
		this.pause=function (){
			try {
				document.getElementById("Externa").pauseMusic();
			} catch (e) {
				document.getElementById("Externa1").pauseMusic();
			}
		};
		this.previous=function (){
			
			
		};
		this.next=function (){
				
		};
	}
}
function click(e) {
	var that = this;
	var diffX =this.offsetLeft;
	var percent=0;
	function stop() {// 松开按键 停止默认行为
		$(document).off( 'mousemove', move);
		$(document).off( 'mouseup', stop);
		if (typeof that.releaseCapture != 'undefined') {
			that.releaseCapture();
		}
		try {
			document.getElementById("Externa").position(percent);
		} catch (e) {
			document.getElementById("Externa1").position(percent);
		}
	}
	function move(evt) { // 鼠标移动
		var left = evt.clientX;
		if (left < 0)
			left = 0;
		else if (left > 330)
			left = 330;
		percent=left/330*100 ;
		$("#currTimeBar #bar").css({width:percent+ "%"});
	}
	$(document).on('mousemove', move);
	$(document).on( 'mouseup', stop);
}
function click2(e) {
	var that = this;
	var diffX =e.clientX-this.offsetLeft-13;
	var percent=0;
	var flag=false;
	function stop() {// 松开按键 停止默认行为
		$(document).off('mousemove', move);
		$(document).off( 'mouseup', stop);
		if (typeof that.releaseCapture != 'undefined') {
			that.releaseCapture();
		}
		try {
			document.getElementById("Externa").volume(flag ? 0:percent/100);
			
		} catch (e) {
			document.getElementById("Externa1").volume(flag ? 0:percent/100);
		}
	}
	function move(evt) { // 鼠标移动
		var left = evt.clientX-diffX;
		if(left<9){
			flag=true;
		}
		if (left < 9)
			left = 9;
		else if (left > 60)
			left = 60;
		percent=left/60*100 ;
		$Base("#volume_bar").css({width:percent+ "%"});
	}
	$(document).on( 'mousemove', move);
	$(document).on( 'mouseup', stop);
}
function resultScroll(evt){
	var clientHeight =parseInt($(".search_result").css("height")) ;
	var scrollTop = this.scrollTop;
	var scrollHeight =parseInt($(".search_result ul").css("height")) ;
	if(clientHeight+scrollTop+30>=scrollHeight){
		if(window.searchFlag===false&&window.searchMessage.flag===false){
			window.searchMessage.index+=1;
			window.searchFlag===true;
			searchMusic(window.searchMessage.index,window.searchMessage.name);
			this.scrollTop=scrollTop;
		}
	}
}
function playMusic(evt){
	var _that=getEvtObj(evt);
	if(_that.className=="song_append_ico"){
		return false;
	}
	$("#play_music").get(0).className="pause_music";
	$("#singer_photo").attr("src", "/myHome/load/download_singerPhoto?singerName="+encodeURI(encodeURI(this.getElementsByTagName("span")[7].innerHTML)));
	try {
		document.getElementById("Externa").playMusic(this.getAttribute("alt"));	
	} catch (e) {
		document.getElementById("Externa1").playMusic(this.getAttribute("alt"));
	}
	clearInterval(window.songTime);
	window.songTime=setInterval(function(){
		if( window.myAngle==360){
			window.myAngle=0;
		}
		console.log(window.myAngle)
		$("#singer_photo").css("tranforms","rotate("+ (window.myAngle++)+"deg)");
	}, 30);
	$("#p_songName").html(this.getElementsByTagName("span")[6].innerHTML);
	$("#p_singerName").html(this.getElementsByTagName("span")[7].innerHTML);
}
function searchMusic(index,name,method){
	$.ajax({
		url:"/myHome/search/search_music",
		method : 'post',
		data : {
			pageIndex:index,searchName:name,pageSize:20},
		success:function(text){
			var json;
			json=JSON.parse(text);
			if(json.length<=1){
				window.searchMessage.flag=true;
			}
			window.searchMessage.name=name;
			if(method==1){
				$(".mini_music_content_right").remove();
				var contentRight=document.createElement("div");
				contentRight.className="mini_music_content_right";
				var searchTitle=document.createElement("h2");
				searchTitle.innerHTML=name+"-"+"搜索结果 （共"+json[json.length-1]["pagecount"]+")条记录";
				var searchResults=document.createElement("div");
				searchResults.className="search_result";
				var searchResultsUl=document.createElement("ul");
				searchResults.appendChild(searchResultsUl);
				contentRight.appendChild(searchTitle);
				contentRight.appendChild(searchResults);
				$(".mini_music_component").append(contentRight);
				$(searchResults).on("scroll", resultScroll);
				$(contentRight).css({
						width:viewInner().width-338+"px" ,
						height:viewInner().height-40+"px"
					 });
			}
			var parent=$(".search_result ul");
			for ( var i = 0; i < json.length-1; i++) {
			    var rowResults=document.createElement("li");
			    var rowControll=document.createElement("a");
			    rowControll.className="search_result_li_area_hover";
			    var stateIco=document.createElement("span");
			    stateIco.setAttribute("id","search_result_state");
			    var likeIco=document.createElement("span");
			    likeIco.className="song_like_ico";
			    var appendIco=document.createElement("span");
			    appendIco.className="song_append_ico";
			    $(appendIco).click(addMusic);
			    var downloadIco=document.createElement("span");
			    downloadIco.className="song_download_ico";
			    rowControll.appendChild(stateIco);
			    rowControll.appendChild(likeIco);
			    rowControll.appendChild(appendIco);
			    rowControll.appendChild(downloadIco);
			    
			    var resultState=document.createElement("span");
			    resultState.className="search_result_state";
		    
			    var resultMessage=document.createElement("span");
			    resultMessage.className="search_result_li_area";
			    var resultSongName=document.createElement("span");
			    resultSongName.className="search_result_songname";
			    resultSongName.innerHTML=json[i].song;
			    resultSongName.setAttribute("alt",json[i].special);
			    var resultSingerName=document.createElement("span");
			    resultSingerName.className="search_result_singername";
			    resultSingerName.innerHTML=json[i].singer;
			    resultSingerName.setAttribute("alt",json[i].sex);
				resultMessage.appendChild(resultSongName);
				resultMessage.appendChild(resultSingerName);
				
				rowResults.setAttribute("alt",json[i].musicId);
				rowResults.appendChild(rowControll);
				rowResults.appendChild(resultState);
				rowResults.appendChild(resultMessage);
				$(rowResults).on("click", playMusic);	
				parent.append(rowResults);
			}
			window.searchFlag=false;
	}});
}
function addMusic(){
	var that=this;
	var $parent=$(that.parentNode.parentNode);
	var musicId=$parent.attr("alt");
	var songerName=$(".search_result_songname",$parent).text();
	var singername=$(".search_result_singername",$parent).text();
	$("<li></li>").text(songerName+"--"+singername).attr("alt",musicId).click(function(){
		$("#play_music").get(0).className="pause_music";
		$("#singer_photo").attr("src", "/myHome/load/download_singerPhoto?singerName="+singername);
		var musicId=this.getAttribute("alt");
		try {
			document.getElementById("Externa").playMusic(musicId);	
		} catch (e) {
			document.getElementById("Externa1").playMusic(musicId);
		}
		clearInterval(window.songTime);
		window.songTime=setInterval(function(){
			if( window.myAngle==360){
				window.myAngle=0;
			}
			$("#singer_photo").css("tranforms","rotate("+ (window.myAngle++)+"deg)");
		}, 30);
		$("#p_songName").html(songerName);
		$("#p_singerName").html(singername);
	}).appendTo(".list_ul");
}