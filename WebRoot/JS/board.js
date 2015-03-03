/**
 * 
 */

$(function(){
	 	var ue = UM.getEditor('myEditor');

	    function createEditor() {
	        enableBtn();
	        UM.getEditor('myEditor');
	    }
	    function getAllHtml() {
	        alert(UM.getEditor('myEditor').getAllHtml())
	    }
	    function getContent() {
	        return UM.getEditor('myEditor').getContent();
	    }
	    function getPlainTxt() {
	        return UM.getEditor('myEditor').getPlainTxt();
	    }
	    function setContent(isAppendTo) {
	        //使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容
	        UM.getEditor('myEditor').setContent('欢迎使用ueditor', isAppendTo); 
	    }
	    function setDisabled() {
	        UM.getEditor('myEditor').setDisabled('fullscreen');
	        disableBtn("enable");
	    }

	    function setEnabled() {
	        UM.getEditor('myEditor').setEnabled();
	        enableBtn();
	    }

	    function getContentTxt() {
	        //使用editor.getContentTxt()方法可以获得编辑器的纯文本内容
	        return UM.getEditor('myEditor').getContentTxt();
	    }
	    function hasContent() {
	    	return UM.getEditor('myEditor').hasContents();
	    }
	    function setFocus() {
	        UM.getEditor('myEditor').focus();
	    }
	    function deleteEditor() {
	        disableBtn();
	        UM.getEditor('myEditor').destroy();
	    }

	    function getLocalData () {
	        alert(UM.getEditor('myEditor').execCommand( "getlocaldata" ));
	    }

	    sendDiary=function (draft,evt,url_u){
	    	if(!hasContent()){			
	    		$.notice("viki提醒您！","文章内容不能为空");
	    		return
	    	}
	    	$.ajax({
	    		url:BASE_PATH+"/user/function_"+url_u,
	    		method : 'post',
	    		data : {
	    			'userlog.logName':$("#title").val(),
	    			'userlog.logContent':getContent(),
	    			'userlog.visible':($("#visible").text().isEmpty()=="所有人可见"),
	    			'userlog.type':$("#type").val(),
	    			token:$("#token").val(),
	    			'userlog.id':document.getElementById("token").getAttribute("title"),
	    			'userlog.noHtmlLog':getPlainTxt()
	    		},
	    		success:function(msg){
	    			if(msg.isEmpty()=='save user log ok'){			
	    				$.notice("Viki提醒您",draft ? '草稿保存成功' :"发布成功,即将跳转！",1500,function(){						
	    					if(draft){
	    						 window.location.href=BASE_PATH+"/user/user_draft";
	    					}else{
	    						window.location.href=BASE_PATH+"/user/space/"+evt.getAttribute("alt")+"/diary";
	    					}
	    				});
	    			}else{
	    				$.notice("Viki提醒您"," ");
	    			}
	    		}
	    	});	
	    }
	    $(".select").on("click", function(evt){	
			var temp=$("ul",this);		
			if(temp.css("display")=="none"){
				$(".select ul").hide(300);
				$(".colorPanel").hide(300);
				temp.show(300);
			}else{
				temp.hide(300);
			}
		});	
	    $(".select").hover(function(){
			this.style.border="1px solid #02ABE3";
		}, function(){
			this.style.border="1px solid #CCCCCC";
		});
	    $(".select ul li").on("click", clickSelect);
});

function clickSelect() {
	$(this.parentNode).prev().html(this.innerHTML);
	try {
		window.iframeDocument.execCommand(this.parentNode.parentNode.id, false,this.getAttribute("title"));
	} catch (e) {}
	
	$(this.parentNode).hide(300);
}