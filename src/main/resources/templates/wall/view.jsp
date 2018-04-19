<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<!DOCTYPE>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>留言墙</title>
    <!-- 静态css、js资源 -->
    <%@ include file="/WEB-INF/view/common/common.jsp"%>
    <style>
        body
        {
            font-size: 12px;
            font-family: 微软雅黑;
            margin: 0;
        }
        .main
        {
            width: 100%!important;
            height: 100%!important;
            margin: 0 auto;
            background-image: url(${_cdnStaticResourcesPath}/resources/static/images/wall/wall.gif);
        }
        .note
        {
            width: 235px;
            cursor: move;
            display: none;
            position: absolute;
        }
        .nhead
        {
            width: 240px;
            height: 23px;
            padding: 45px 30px 0px 30px;
        }
        .nbody
        {
            width: 240px;
            height: auto;
            padding: 30px 15px 5px 30px;
            margin: 0px 0px 0px -2px;
            text-indent:25px;
            letter-spacing:1px;
            line-height: 20px;
        }
        .nfoot
        {
            width: 235px;
            height: 70px;
            padding: 20px 20px 10px 30px;
        }
        .moodpic
        {
            width: 20px;
            height: 20px;
            float: left;
        }
        .moodpic img
        {
            width: 20px;
            height: 20px;
        }

        .username
        {
            text-align: right;
            float: right;
        }


        #send {
            float: right;
            display: inline;
            margin-top: 7px;
            margin-right: 20px;
            height: 35px;
            width: 93px;
            background: url(${_cdnStaticResourcesPath}/resources/static/chat/images/ok.gif);
            cursor: pointer;
            overflow: hidden;
            text-indent: -9999px;
        }
    </style>
    <script>
        Number.prototype.NaN0=function(){return isNaN(this)?0:this;}
        var iMouseDown  = false;
        var dragObject  = null;
        var curTarget   = null;
        var pageMaxNotes=50;

        function makeDraggable(item){
            if(!item) return;
            item.onmousedown = function(ev){
                dragObject  = this;
                mouseOffset = getMouseOffset(this, ev);
                return false;
            }
        }

        function getMouseOffset(target, ev){
            ev = ev || window.event;

            var docPos    = getPosition(target);
            var mousePos  = mouseCoords(ev);
            return {x:mousePos.x - docPos.x, y:mousePos.y - docPos.y};
        }

        function getPosition(e){
            var left = 0;
            var top  = 0;
            while (e.offsetParent){
                left += e.offsetLeft + (e.currentStyle?(parseInt(e.currentStyle.borderLeftWidth)).NaN0():0);
                top  += e.offsetTop  + (e.currentStyle?(parseInt(e.currentStyle.borderTopWidth)).NaN0():0);
                e     = e.offsetParent;
            }

            left += e.offsetLeft + (e.currentStyle?(parseInt(e.currentStyle.borderLeftWidth)).NaN0():0);
            top  += e.offsetTop  + (e.currentStyle?(parseInt(e.currentStyle.borderTopWidth)).NaN0():0);

            return {x:left, y:top};

        }

        function mouseCoords(ev){
            if(ev.pageX || ev.pageY){
                return {x:ev.pageX, y:ev.pageY};
            }
            return {
                x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
                y:ev.clientY + document.body.scrollTop  - document.body.clientTop
            };
        }

        function mouseDown(ev){
            ev         = ev || window.event;
            var target = ev.target || ev.srcElement;

            if(target.onmousedown || target.getAttribute('DragObj')){
                return false;
            }
        }

        function mouseUp(ev){

            dragObject = null;

            iMouseDown = false;
        }

        function mouseMove(ev){
            ev         = ev || window.event;

            /*
            We are setting target to whatever item the mouse is currently on

            Firefox uses event.target here, MSIE uses event.srcElement
            */
            var target   = ev.target || ev.srcElement;
            var mousePos = mouseCoords(ev);


            if(dragObject){
                dragObject.style.position = 'absolute';
                dragObject.style.top      = mousePos.y - mouseOffset.y;
                dragObject.style.left     = mousePos.x - mouseOffset.x;
                if(dragObject.style.zIndex!=pageMaxNotes)
                {
                    pageMaxNotes++;
                    dragObject.style.zIndex=pageMaxNotes;
                }
            }

            // track the current mouse state so we can compare against it next time
            lMouseState = iMouseDown;

            // this prevents items on the page from being highlighted while dragging
            if(curTarget || dragObject) return false;
        }

        document.onmousemove = mouseMove;
        document.onmousedown = mouseDown;
        document.onmouseup   = mouseUp;

        /**
         * @author Mr.Think
         * @author blog http://mrthink.net/
         * @2011.01.27
         * 可自由转载及使用,但请注明版权归属
         */
        function fadeIn(elem, speed, opacity){
            //底层共用
            var iBase = {
                Id: function(name){
                    return document.getElementById(name);
                },
                //设置元素透明度,透明度值按IE规则计,即0~100
                SetOpacity: function(ev, v){
                    ev.filters ? ev.style.filter = 'alpha(opacity=' + v + ')' : ev.style.opacity = v / 100;
                }
            }

            speed = speed || 20;
            opacity = opacity || 100;
            //显示元素,并将元素值为0透明度(不可见)
            elem.style.display = 'block';
            iBase.SetOpacity(elem, 0);
            //初始化透明度变化值为0
            var val = 0;
            //循环将透明值以5递增,即淡入效果
            (function(){
                iBase.SetOpacity(elem, val);
                val += 5;
                if (val <= opacity) {
                    setTimeout(arguments.callee, speed)
                }
            })();
        }

        /*
         *生成随机数
         */
        function GetRandomNum(Min,Max)
        {
            var Range = Max - Min;
            var Rand = Math.random();
            return(Min + Math.round(Rand * Range));
        }

        /*
         *当文档加载完毕时将对留言条进行初始化
         */
        window.onload = function() {

            //取得所有的note类留言条
            var notes=document.getElementsByName("note");

            //此时note所在层最小为49，最高层为49+note数量
            pageMaxNotes=49+notes.length;

            //得到此时文档宽度
            var bodyWidthMain = document.body.offsetWidth;
            var baseOffsetLeft = 0;
            var baseOffsetTop = 0;
            //留言条出现的最低位置
            var maxHeight = $(document.body).height()-225;
            //留言条出现的最右位置
            var maxWidth = $(document.body).width()-235;
            for(var i=0;i<notes.length;i++){
                makeDraggable(notes[i]);
                //随机出现位置
                notes[i].style.top = baseOffsetTop + GetRandomNum(0 , maxHeight);
                notes[i].style.left =baseOffsetLeft + GetRandomNum(0 ,maxWidth);

                //位置确定后淡入
                fadeIn(notes[i]);
            }
        }
    </script>
    <script>
        $(function(){
            $("#message").focus();
            $("#send").click(function () {
                var content = $("#message").val();

                if(content == "" || content.trim() == ""){
                    alert("请输入您要说的内容");
                    $("#message").focus();
                    return false;
                }
                if(content.length > 50){
                    alert("最多只能输入50个字符");
                    $("#message").focus();
                    return false;
                }
                var userName = $("#userName").val();
                if(userName != "" && userName.trim() != ""){
                    if(userName.length > 10){
                        alert("称呼最多只能输入10个字符");
                        $("#userName").focus();
                        return false;
                    }
                }
                $.ajax({
                    type: "POST",
                    url: $("#addMessageUrl").val(),
                    data: {"content":content,"userName" : userName},
                    dataType: "json",
                    success: function(data){
                        if(data.code == 200){
                            alert("提交成功");
                        }else{
                            alert(data.msg);
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<div style="background-color: #FFF7C4; color: lightseagreen;font-weight: bold; height: 50px;line-height: 50px;position: fixed;top: 0px;border-bottom: 1px solid #dddddd;width: 100%;font-size: 14px;padding-left: 20px;">
    <span>hello，如果您对此项目有什么想说的或者建议的话，不妨在这里留个言吧~ ^_^</span>
</div>
<div class="main">
    <c:forEach items="${messageList}" var="msg" varStatus="s">
        <div name="note" class="note">
            <div class="nhead" style="background-image: url(${_cdnStaticResourcesPath}/resources/static/images/wall/a${nums}_1.gif);"><fmt:formatDate value="${msg.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/> </div>
            <div class="nbody" style="background-image: url(${_cdnStaticResourcesPath}/resources/static/images/wall/a${nums}_2.gif);">${msg.content}</div>
            <div class="nfoot" style="background-image: url(${_cdnStaticResourcesPath}/resources/static/images/wall/a${nums}_3.gif);">
                <%--<div class="moodpic">
                    <img src="${_cdnStaticResourcesPath}/resources/static/images/wall/17.gif"/>
                </div>--%>
                <div class="username">${msg.userName}</div>
            </div>
        </div>
    </c:forEach>
</div>
.
<div style="background-color: #76D1C4; color: rgb(0, 0, 0); height: 50px;line-height: 50px;position: fixed;bottom: 0px;border-bottom: 1px solid #dddddd;width: 100%;">
    <textarea  maxlength="50" id="message" style="padding-left: 5px !important; padding-right: 5px !important; height: 45px;line-height:45px!important;vertical-align: middle; width: 65%;
            resize: none;border-color: mediumseagreen;!important;margin-left:5px;font-size: 14px;padding-top: 0px;!important;margin-top: -4px;" placeholder="请在这里输入你要说的内容，最多50个字符"></textarea>

    <textarea  maxlength="50" id="userName" style="margin-left:20px!important;padding-left: 5px !important; padding-right: 5px !important; height: 45px;line-height:45px!important;vertical-align: middle; width: 15%;
            resize: none;border-color: mediumseagreen;!important;margin-left:5px;font-size: 14px;padding-top: 0px;!important;margin-top: -4px;" placeholder="请输入您的称呼"></textarea>
    <div id="send">发送</div>
</div>

<input type="hidden" des="静态资源服务器地址" name="cdnStaticResourcesPath" id="cdnStaticResourcesPath" value="${_cdnStaticResourcesPath}" />
<input type="hidden" des="新增留言消息" id="addMessageUrl" value="${ctx}/message/wall/add" />
</body>
</html>
