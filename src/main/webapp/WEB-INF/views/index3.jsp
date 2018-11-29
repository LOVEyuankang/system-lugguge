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

    //发送消息
    function send(message) {
        websocket.send(message);
    }

    function monitor(series, series1){
        websocket.onmessage = function (evt) {
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

            if(evt.data.indexOf("dataxlzs") != -1){
                var datas = evt.data.substring(evt.data.indexOf("[")+1, evt.data.indexOf("]"));
                var dataxlzs = datas.split(",") ;
                for (var i = 0; i < dataxlzs.length; i++) {
                    var x = x_arr[i];
                    var y = parseInt(dataxlzs[i]);
                    series.addPoint([x,y], false, true);
                }
            }
            if(evt.data.indexOf("datayzcxl") != -1){
                var datas = evt.data.substring(evt.data.indexOf("[")+1, evt.data.indexOf("]"));
                var datayzcxl = datas.split(",") ;
                for (var i = 0; i < datayzcxl.length; i++) {
                    var x = x_arr[i];
                    var y = parseInt(datayzcxl[i]);
                    series1.addPoint([x,y], false, true);
                }
            }
        };
    }

    $(function() {

        //setInterval(show,5000);
        var x_arr = ['01:00', '02:00', '03:00', '04:00', '05:00', '06:00', '07:00', '08:00', '09:00', '10:00', '11:00', '12:00',
            '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00', '23:00', '24:00'];

        //线图表
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });

        $('#container').highcharts({
            chart: {
                type: 'spline',
                animation: Highcharts.svg,
                events: {
                    load: function () {
                        var series = this.series[0];
                        var series1 = this.series[1];
                        //监听服务器数据推送
                        websocket.onmessage = function (evt) {
//                            this.showLoading();
                            if (evt.data.indexOf("databz") != -1) {
                                var datas = evt.data.substring(evt.data.indexOf("[") + 1, evt.data.indexOf("]"));
                                var databz = datas.split(",");
                                for (var i = 0; i < databz.length; i++) {
                                    var x = x_arr[i];
                                    var y = parseInt(databz[i]);
                                    series.addPoint([x, y], true);
                                }
                            }
                            if (evt.data.indexOf("dataybz") != -1) {

                                var datas = evt.data.substring(evt.data.indexOf("[") + 1, evt.data.indexOf("]"));
                                var dataybz = datas.split(",");
                                for (var i = 0; i < dataybz.length; i++) {
                                    var x = x_arr[i];
                                    var y = parseInt(dataybz[i]);
                                    series1.addPoint([x, y], true);
                                }
                            }
                            this.hideLoading();
                        }
                    }
                }
            },
            title: {
                text: '保障航班架次图表'
            },
            xAxis: {
                title: {
                    enabled: true,
                    text: '时间（小时）'
                },
                categories: x_arr,
                max: 23,
                min: 0
            },
            yAxis: [{
                title: {
                    text: '保障航班架次(次)'
                }
            }],
            tooltip: {
                formatter: function () {
                    return '<b>' + this.series.name + '</b><br/>' + this.x + ': ' + this.y + '(次)';
                }
            },
            legend: {
                enabled: true
            },
            exporting: {
                enabled: true
            },
            series: [{
                name: '保障航班架次',
                color: 'red'
            },
                {
                    name: '已保障航班架次',
                    color: 'green'
                }
            ]
        });

        //柱状图表
//        $('#containerz').highcharts({
//            chart : {
//                type : 'column',
//                animation: Highcharts.svg,
//                events: {
//                    load: function () {var y;
//                        var y1;
//                        var series = this.series[0];
//                        var series1 = this.series[1];
//        monitor(series, series1);//监听
//                        //监听服务器数据推送
//                        websocket.onmessage = function (evt) {
//                            var split = evt.data.split(":");
//
//                            if ("dataxlzs" == split[0]) {
//                                var dataxlzs = split[1].split(",");
//                                for (var i=0; i < dataxlzs.length; i++){
//                                    y = dataxlzs[i];
//                                    series.addPoint(y);
//                                }
//                            }
//
//                            if ("datayzcxl" == split[0]) {
//                                var datayzcxl = split[1].split(",");
//                                for (var i = 0; i < datayzcxl.length; i++) {
//                                    y1 = datayzcxl[i];
//                                    series1.addPoint(y1);
//                                }
//                            }
//                        };
//                    }
//                }
//            },
//            title : {
//                text : '保障行李件数图表'
//            },
//            xAxis : {
//                title: {
//                    enabled: true,
//                    text: '时间（小时）'
//                },
//                categories:x_arr,
//                max:23,
//                min:0
//            },
//            yAxis : {
//                title : {
//                    text : '行李件数(件)'
//                }
//            },
//            tooltip : {
//                headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
//                pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
//                + '<td style="padding:0"><b>{point.y:.1f} (件)</b></td></tr>',
//                footerFormat : '</table>',
//                shared : true,
//                useHTML : true
//            },
//            plotOptions : {
//                column : {
//                    pointPadding : 0.2,
//                    borderWidth : 0
//                }
//            },
//            series : [
//                {
//                    name : '行李总件数',
//                    color:'#00ACF1'
//                },
//                {
//                    name : '已装车行李',
//                    color:'green'
//                }]
//        });
        //柱状图表结束


    });

</script>
</body>
</html>