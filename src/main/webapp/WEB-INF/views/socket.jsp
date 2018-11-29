<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<head>
    <%@ include file="/commons/basejs.jsp"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>WebSocket测试页面</title>
</head>
<body>
    <div id="msg"></div>
    <input type="text" id="text">
    <button onclick="send()">发送</button>
</body>
<script>
    var msg = document.getElementById("msg");
//    var wsServer = 'ws://127.0.0.1:80';
    var websocket = new WebSocket('ws://192.168.0.187:80');

    //连接发生错误的回调方法
    websocket.onerror = function(){
        msg.innerHTML = "error";
    };

    //监听连接打开
    websocket.onopen = function (evt) {
        console.log("WebSocket监听连接打开");
        msg.innerHTML = "WebSocket监听连接打开";
    };

    //监听服务器数据推送
    websocket.onmessage = function (evt) {
        msg.innerHTML += "<br>" + evt.data;
    };

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function(){
        websocket.close();
    };

    //监听连接关闭
    websocket.onclose = function (evt) {
        console.log("WebSocket监听连接已关闭");
        alert("连接关闭");
    };

    function send() {
        var text = document.getElementById("text").value
        websocket.send(text);
    }
    function aa() {
        $.getJSON("${path }/checkcenter/aa", function (data) { })
    }

    //关闭连接
    function closeWebSocket(){
        websocket.close();
    }



</script>
<%--<script>--%>
    <%--var socket;--%>
    <%--if(typeof(WebSocket) == "undefined") {--%>
        <%--console.log("您的浏览器不支持WebSocket");--%>
    <%--}else{--%>
        <%--console.log("您的浏览器支持WebSocket");--%>
        <%--//实现化WebSocket对象，指定要连接的服务器地址与端口 建立连接--%>
        <%--// 等同于 socket = new WebSocket("ws://localhost:8083/checkcentersys/websocket/20");--%>
        <%--socket = new WebSocket("${basePath}websocket/${cid}".replace("http","ws"));--%>
        <%--//打开事件--%>
        <%--socket.onopen = function() {--%>
            <%--console.log("Socket 已打开");--%>
            <%--//socket.send("这是来自客户端的消息" + location.href + new Date());--%>
        <%--};--%>
        <%--// 获得消息事件--%>
        <%--socket.onmessage = function(msg) {--%>
            <%--console.log(msg.data);--%>
            <%--//发现消息进入 开始处理前端触发逻辑--%>

        <%--};--%>

        <%--// 关闭事件--%>
        <%--socket.onclose = function() {--%>
            <%--console.log("Socket已关闭");--%>
        <%--};--%>

        <%--//发生了错误事件--%>
        <%--socket.onerror = function() {--%>
            <%--alert("Socket发生了错误");--%>
            <%--//此时可以尝试刷新页面--%>
        <%--}--%>
    <%--}--%>
<%--</script>--%>
</html>
