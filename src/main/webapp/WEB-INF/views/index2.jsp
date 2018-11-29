<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/commons/basejs.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>测试页面</title>
</head>
<body>
<div id="loading" style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #fff;z-index: 100;overflow: hidden;">
	<img src="${staticPath }/static/style/images/ajax-loader.gif" style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;" />
</div>
<div id="index_layout">
	<div data-options="region:'center'" style="overflow: hidden;">
		<div id="index_tabs" style="overflow: hidden;">
			<div title="测试页面" data-options="border:false,closable : true" style="overflow: hidden;">
				<div id="container" style="border-radius:10px;border-style:outset;width: 80%;height: 310px;position:fixed; left:150px; bottom:368px;"></div>
				<div id="containerz" style="border-radius:10px;border-style:outset;width: 80%;height: 310px;position:fixed; left:150px; bottom:40px;"></div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
    var index_layout;
    var index_tabs;
    var interval;

    var websocket = new WebSocket('ws://192.168.0.187:80');

    //连接发生错误的回调方法
    websocket.onerror = function(){
        msg.innerHTML = "error";
    };

    //监听连接打开
    websocket.onopen = function (evt) {
        console.log("WebSocket监听连接打开");
    };

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function(){
        websocket.close();
    };

    //监听连接关闭
    websocket.onclose = function () {
        console.log("WebSocket监听连接已关闭");
    };

    var myChart = echarts.init(document.getElementById('container'));

    var option = {
        title: {
            text: '动态数据',
            subtext: '纯属虚构'
        },
        tooltip: {
            trigger: 'container',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#283b56'
                }
            }
        },
        legend: {
            data:['最新成交价', '预购队列']
        },
        toolbox: {
            show: true,
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        dataZoom: {
            show: false,
            start: 0,
            end: 100
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: true,
                data: (function (){
                    var now = new Date();
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
                        now = new Date(now - 2000);
                    }
                    return res;
                })()
            },
            {
                type: 'category',
                boundaryGap: true,
                data: (function (){
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.push(10 - len - 1);
                    }
                    return res;
                })()
            }
        ],
        yAxis: [
            {
                type: 'value',
                scale: true,
                name: '价格',
                max: 30,
                min: 0,
                boundaryGap: [0.2, 0.2]
            },
            {
                type: 'value',
                scale: true,
                name: '预购量',
                max: 1200,
                min: 0,
                boundaryGap: [0.2, 0.2]
            }
        ],
        series: [
            {
                name:'预购队列',
                type:'bar',
                xAxisIndex: 1,
                yAxisIndex: 1,
                data:(function (){
                    var res = [];
                    var len = 10;
                    while (len--) {
                        res.push(Math.round(Math.random() * 1000));
                    }
                    return res;
                })()
            },
            {
                name:'最新成交价',
                type:'line',
                data:(function (){
                    var res = [];
                    var len = 0;
                    while (len < 10) {
                        res.push((Math.random()*10 + 5).toFixed(1) - 0);
                        len++;
                    }
                    return res;
                })()
            }
        ]
    };

    app.count = 11;
    setInterval(function (){
        var axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');

        var data0 = option.series[0].data;
        var data1 = option.series[1].data;
        data0.shift();
        data0.push(Math.round(Math.random() * 1000));
        data1.shift();
        data1.push((Math.random() * 10 + 5).toFixed(1) - 0);

        option.xAxis[0].data.shift();
        option.xAxis[0].data.push(axisData);
        option.xAxis[1].data.shift();
        option.xAxis[1].data.push(app.count++);

        myChart.setOption(option);
    }, 2100);

    $(function() {

        //setInterval(show,5000);
        var x_arr= ['01:00','02:00','03:00','04:00','05:00','06:00','07:00', '08:00','09:00', '10:00', '11:00','12:00',
            '13:00','14:00','15:00', '16:00','17:00', '18:00', '19:00','20:00','21:00','22:00','23:00','24:00'];


        //监听服务器数据推送
        websocket.onmessage = function (evt) {
            var series = this.series[0];
            var series1 = this.series[1];
            if(evt.data.indexOf("databz") != -1){
                var datas = evt.data.substring(evt.data.indexOf("[")+1, evt.data.indexOf("]"));
                var databz = datas.split(",") ;
                for (var i = 0; i < databz.length; i++) {
                    var x = x_arr[i];
                    var y = parseInt(databz[i]);
                    series.addPoint([x,y], true);
                }
            }
            if(evt.data.indexOf("dataybz") != -1){
                var datas = evt.data.substring(evt.data.indexOf("[")+1, evt.data.indexOf("]"));
                var dataybz = datas.split(",") ;
                for (var i = 0; i < dataybz.length; i++) {
                    var x = x_arr[i];
                    var y = parseInt(dataybz[i]);
                    series1.addPoint([x,y], true);
                }
            }
        }




</script>
</body>
</html>