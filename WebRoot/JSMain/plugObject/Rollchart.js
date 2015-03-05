/**
 * 
 */
function Rollchart() {
    //私有滚图JQ对象
    var rollcharts = null;
    this.handle = {};
  };


  Rollchart.prototype = {
  init: function(images, element) {
    //初始化节点属性
    var that = this;
    this.images = {};
    this.images.width = (document.documentElement.scrollWidth || document.body.scrollWidth || window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth);
    if (parseInt(images.width) < 1000) {
      this.images.width = 1000;
    }
    this.images.height = (document.documentElement.scrollheight || document.body.scrollheight || window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight);;
    this.images.alts = [];
    this.images.image=[];
    this.images.urls = [];
    this.images.hrefs = [];
    this.images.car=true;
    for (var i = 0; i < images.image.length; i++) {
      this.images.urls[i] = images.image[i].src;
      this.images.hrefs[i] = "#";
      this.images.alts[i] = "";
      this.images.image[i]=images.image[i];
    }
    this.images.select={
      over:{"background":"#EA5D52"},
      out:{"background":"#FFFFFF"}
    };
    this.images.count = 0;
    this.images.times = 5000;
    $.extend(true, this.images, images);
    //创建包裹滚轮
    var rollchart = $("<div class='rollchart-global-control'></div>").css({
      "width": that.images.width,
      "overflow": "hidden",
      "height": that.images.height,
      "background": "#fff",
      position: "relative",
      "z-index": 1
    }).hover(function() {
      clearInterval(that.time);
      that.time=null;
    }, function(){
      that.images.car&&timeOpen.call(that);
    });
    //创建图片列表
    var rollchartUl = $("<ul></ul>").css({
      width: that.images.width * that.images.urls.length,
      display: "block",
      height: that.images.height ,
      position: "absolute",
      top: "0px",
      left: "0",
      "list-style": "none"
    });
    //如果支持手机拖动事件 进行绑定
    if(this.images.phone){
      phoneDrag.call(this,rollchartUl.get(0));
    }
    var selectUl = $("<ul style='width:" + (that.images.urls.length * 28+19) + "px;' class='rollchart_selectUl_gf'></ul>").css({
      "list-style": "none",
      display: "block",
      height: "30px",
      margin: "0 auto",
      zoom: 1,
      position: "static"
    });
    this.ul={
      rollchartUl:rollchartUl,
      selectUl:selectUl
    };
    for (var i = 0; i < that.images.urls.length; i++) {
      (function(n) {
        $("<li class='rollchart-global-control'></li>").append(
          $("<a class='rollchart-global-control'></a>").attr({
            href: that.images.hrefs[i],
            target: "_blank",
            title: that.images.alts[i],
            alt: that.images.alts[i],
             background:"#F2F2F2"
          }).css({
            width:that.images.width,
            height:that.images.height,
            "display":"block",
            "margin":"auto",
            background:"#F2F2F2"
          }).append(
            $("<img class='rollchart-global-control rollchart-img'/>").attr({
              src: that.images.urls[i],
              title: that.images.alts[i],
              alt: that.images.alts[i]
            }).css({
              width:(that.images.autoScale!=null ? that.images.autoScale:that.images.width),
              height:that.images.height,
              "margin":"auto",
              "margin-left":(that.images.autoScale!=null ? -(that.images.autoScale-that.images.width)/2:0)
            })
          )
        ).css({
          float: "left",
          width: that.images.width,
          height: that.images.height,
          display:"block",
          overflow:"hidden"
        }).appendTo(rollchartUl);
        selectUl.append(
          $("<li></li>")
          .css({
            width: "16px",
            height: "16px",
            background: (i == 0 ? (that.images.select.over.background) : (that.images.select.out.background)),
            "border-radius": "13px",
            margin: (i == 0 ? "6px 15px 0 10px" : "6px 15px 0 0"),
            cursor: "pointer",
            float: "left"
          })
          .hover(function() {
            clearInterval(that.time);
            that.time=null;
            $("li",selectUl).eq(that.images.count).css(that.images.select.out).end().eq(n).css(that.images.select.over);
            that.images.count = n;
            $(rollchartUl).stop().animate({
              left: -(that.images.count * that.images.width)
            }, 500);
          },function(){
            that.images.car&&timeOpen.call(that);
          })
        );
      })(i);
    }
    rollchart.append(rollchartUl).append(
      $("<div></div").css({
        width: "100%",
        height: "30px",
        position: "absolute",
        bottom: "10px",
        left: 0
      }).append(selectUl)
    );
    //定时器开启
    that.images.car&&timeOpen.call(that);
    if (element != null && $(element).get(0) != undefined) {
      $(element).append(rollchart);
    }
    rollcharts = rollchart;
  },
  //Protype获取轮播Dom对象
  getRollchart: function() {
    return rollcharts;
  },
  getSelectUl:function(){
    return  this.ul.selectUl;
  },
  //Protype获取下一张轮播
  nextPage: function() {
    var _this = this;
    handlePage.call(this,function() {
      _this.fire.call(_this, "next");
    },true);
  },
  //Protype获取上一张轮播
  prevPage: function() {
    var _this = this;
    handlePage.call(this,function() {
      _this.fire.call(_this, "prev");
    },false);
  },
  getIndex: function() { //或许当前轮播位置
    return this.images.count;
  },
  getIndexDom:function(index){//或许当前轮播位置ImgDom元素
    return $("li img",rollchartUl).get(index);
  },
  setWidth:function(width){
    this.images.width=width;
    var temp=$(".rollchart-global-control").css("width",width);
    if(this.images.autoScale!=null){
      $(".rollchart-img").css({
        width:this.images.autoScale,
        "margin-left": -(this.images.autoScale-this.images.width)/2
      });
      this.ul.rollchartUl.css({
        left: -(this.images.count * this.images.width),
        width:this.images.width * this.images.urls.length
      });
    }
  },
  setHeight:function(height){
    this.images.height=height;
    $(".rollchart-global-control").css("height",height);
  },
  on: function(type, fn) { //绑定自定义事件
    if (!(this.handle[type] instanceof Array)) {
      this.handle[type] = [];
    };
    !equals(this.handle[type]) && this.handle[type].push(fn);
  },
  off: function(type, fn) { //移除自定义事件
    if (this.handle[type] != null) {
      for (var i in this.handle[type]) {
        if (this.handle[type][i] == fn) {
          delete this.handle[type][i];
        }
      }
    }
  },
  fire: function(type) { //触发定自定义事件
    if (this.handle[type] != null) {
      for (var i in this.handle[type]) {
        this.handle[type][i].call(this);
      }
    }
  }
};
//================工具函数=================================
function handlePage(fn,b) {
  var tempCount=this.images.count ;
  if(b){
    if (this.images.count + 1 > this.images.urls.length - 1) {
      this.images.count = 0;
    } else {
      ++this.images.count;
    }
  }else{
    if (this.images.count - 1 < 0) {
      this.images.count =this.images.urls.length-1;
    } else {
      --this.images.count;
    }
  }
  this.ul.rollchartUl.animate({
    left: -(this.images.count * this.images.width)
  }, fn);
  $("li",this.ul.selectUl).eq(this.images.count).css(this.images.select.over)
  .end().eq(tempCount).css(this.images.select.out);
}
  function equals(es, fn) { // 比较方法是否重复
    for (var i in es) {
      if (es[i] == fn) return true;
    }
    return false;
  };
  function phoneDrag(node){
    node.flag=false;
    var that = this;
    node.move=false;
    try{

    node.addEventListener("touchstart",function(event){
      node.flag=true;
      clearInterval(that.time);
      that.time=null;
      node.mx=event.touches[0].pageX+that.images.width*that.images.count;
    });
    node.addEventListener("touchmove",function(event){
      if(node.flag){
        node.move=true;
        event.preventDefault();
        var speed=event.touches[0].pageX-node.mx;
        if(speed>0||speed<-that.images.width*(that.images.urls.length-1)){
          node.flag=false;
        }else{
          $(node).css({
            left:speed
          });
        }
      }
    });
    node.addEventListener("touchend",function(event){
      if(node.flag){
        if(node.move){
          event.preventDefault();
          node.move=false;
        }
        node.flag=false;
        var realLeft=-(that.images.count * that.images.width);
        var currentLeft=parseInt($(node).css("left"));
        if(realLeft-currentLeft>that.images.width/2){
          that.nextPage();
        }else if(currentLeft-realLeft>that.images.width/2){
          that.prevPage();
        }else{
          $(node).animate({
            left: realLeft
          });
        }
        that.images.car&&timeOpen.call(that);
      }
    });
    }catch(e){
		//TODO handle the exception
	}
  }
  function timeOpen() {
    var that=this;
    if(that.time==null){
      this.time = setInterval(function() {
        that.nextPage();
      }, this.images.times);
    }
  }