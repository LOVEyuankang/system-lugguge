<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<script type=text/javascript>
	$(document).ready(
			function() {
				$("#firstpane .menu_body:eq(0)").show();
				$("#firstpane p.menu_head").click(
						function() {
							$(this).addClass("current").next("div.menu_body")
									.slideToggle(300).siblings("div.menu_body")
									.slideUp("slow");
							$(this).siblings().removeClass("current");
						});
				$("#secondpane .menu_body:eq(0)").show();
				$("#secondpane p.menu_head").mouseover(
						function() {
							$(this).addClass("current").next("div.menu_body")
									.slideDown(500).siblings("div.menu_body")
									.slideUp("slow");
							$(this).siblings().removeClass("current");
						});

			});
</script>
<script type="text/javascript">
	var index_layout;
	var index_tabs;
	var index_tabsMenu;
	var layout_west_tree;
	var bz;
	var ybz;
	var xlzs;
	var zcxl;
	var wsbxl;
	var interval;
	var myDate;
	var time;
	$(function() {
		index_layout = $('#index_layout').layout({
			fit : true
		});
        show();
		function show() {
		//航班总架次
		$.ajax({
			url : "${path }/t_airport_dyndepflt/t_airport_dyndepfltAll",
			type : "POST",
			success : function(data) {
			$("#circle").html(data);
			bz=data;
			}
		});
		//已保障航班架次
		$.ajax({
					url : "${path }/t_airport_dyndepflt/findT_airport_dyndepfltAllbyStatus",
					type : "POST",
					success : function(data) {
						$("#circle1").html(data);
						ybz=data;
					}
				});
		//行李总件数
		$.ajax({
			url : "${path }/t_airport_luggage/findT_airport_luggageAll",
			type : "POST",
			success : function(data) {
				$("#circle4").html(data);
				xlzs=data;
			}
		});
		//已装车
		$.ajax({
			url : "${path }/t_airport_luggage/findT_airport_luggageAllbystutas",
			type : "POST",
			success : function(data) {
				$("#circle2").html(data);
				zcxl=data;
			}
		});
		//未识别
		$.ajax({
			url : "${path }/t_airport_luggage/findT_airport_luggageAllbystutasw",
			type : "POST",
			success : function(data) {
				$("#circle3").html(data);
				wsbxl=data;
			}
		});
		}
	    //setInterval(show,5000);
		var x_arr= ['01:00','02:00','03:00','04:00','05:00','06:00','07:00', '08:00','09:00', '10:00', '11:00','12:00',
					'13:00','14:00','15:00', '16:00','17:00', '18:00', '19:00','20:00','21:00','22:00','23:00','24:00'];
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
       	         	            var y;
                                var y1;
       	                        var series = this.series[0];
       	                        var series1 = this.series[1];
            	                        	$.post("${path }/t_airport_dyndepflt/findT_airport_dyndepfltAllbytime", function(databz) {
            	                        		$.post("${path }/t_airport_dyndepflt/findT_airport_dyndepfltAllbyStatusbytime", function(dataybz) {
             	                        			for (var i = 0; i <databz.length; i++) {
                            	                        	y =databz[i];
                            	                        	y1=dataybz[i];
                           	                                series.addPoint([y], true);
                           	                                series1.addPoint([y1], true);
 													}
            	                        			});
                	                        	});				
       	                        interval=setInterval(function () {
       	             	    	$.post("${path }/t_airport_luggage/aa", function(databz) {
           	             	    	$.post("${path }/t_airport_luggage/bb", function(dataybz) {
        	                        	y = databz;
        	                        	y1= dataybz;
       	                                series.addPoint([y], true);
       	                                series1.addPoint([y1], true);
           	                  	          });
       	                  	          });
       	                        },3600000);
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
       	                categories:x_arr,
       	                max:23,
       	                min:0								
       	            },
       	            yAxis: [{
       	                title: {
       	                  text: '保障航班架次(次)'
       	                }
       	              }],
       	            tooltip: {
       	            	formatter: function() {
       	                    return '<b>'+ this.series.name +'</b><br/>'+
       	                    this.x +': '+ this.y +'(次)';
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
       	                color:'red'
       	              },
       	              {
       	                name: '已保障航班架次',
       	                color:'green'
       	              }
       	              ]
       	        });

					//柱状图表
					$('#containerz') .highcharts({
										chart : {
											type : 'column',
										    animation: Highcharts.svg,
					        	            events: {
					        	                    load: function () {
					           	         	            var y;
					                                    var y1;
					        	                        var series = this.series[0];
					        	                        var series1 = this.series[1];
					           	             	    	$.post("${path }/t_airport_luggage/findT_airport_luggageAllbytime", function(dataxlzs) {
			                   	             	    		for (var i = 0; i <dataxlzs.length; i++) {
						        	                        	y =dataxlzs[i];
							        	                        series.addPoint([y], true);									
															}
					               	             	    	$.post("${path }/t_airport_luggage/findT_airport_luggageAllbystutastime", function(datayzcxl) {
					                   	             	    		for (var i = 0; i <datayzcxl.length; i++) {
								        	                            y1=datayzcxl[i];
									        	                        series1.addPoint([y1], true);										
																	}
					                       	                  	      });
					               	                  	          });
					        	                        interval=setInterval(function () {
					               	             	    	$.post("${path }/t_airport_luggage/cc", function(dataxlzs) {
					                   	             	    	$.post("${path }/t_airport_luggage/dd", function(datayzcxl) {
					                	                        	y = dataxlzs;
					                	                        	y1= datayzcxl;
					               	                                series.addPoint([y], true);
					               	                                series1.addPoint([y1], true);
					                   	                  	          });
					               	                  	          });
					        	                        }, 3600000);
					        	                    }
					        	                }
										},
										title : {
											text : '保障行李件数图表'
										},
										xAxis : {
											title: {
					        	                   enabled: true,
					        	                   text: '时间（小时）'
					        	                },
					        	                categories:x_arr,
					        	                max:23,
					        	                min:0
										},
										yAxis : {
											title : {
												text : '行李件数(件)'
											}
										},
										tooltip : {
											headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
											pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
													+ '<td style="padding:0"><b>{point.y:.1f} (件)</b></td></tr>',
											footerFormat : '</table>',
											shared : true,
											useHTML : true
										},
										plotOptions : {
											column : {
												pointPadding : 0.2,
												borderWidth : 0
											}
										},
										series : [
												{
													name : '行李总件数',
								        	                color:'#00ACF1'
												},
												{
													name : '已装车行李',
								        	                color:'green'
												}]
					});
					//柱状图表结束

		index_tabs = $('#index_tabs').tabs({
					fit : true,
					border : false,
					tools : [
							{
								iconCls : 'icon-home',
								handler : function() {
									index_tabs.tabs('select', 0);
								}
							},
							{
								iconCls : 'icon-refresh',
								handler : function() {
									var index = index_tabs.tabs('getTabIndex',index_tabs.tabs('getSelected'));
									index_tabs.tabs('getTab', index).panel( 'open').panel('refresh');
								}
							},
							{
								iconCls : 'icon-del',
								handler : function() {
									var index = index_tabs.tabs('getTabIndex',index_tabs.tabs('getSelected'));
									var tab = index_tabs.tabs('getTab', index);
									if (tab.panel('options').closable) {
										index_tabs.tabs('close', index);
									}
								}
							}
					]
		});

		//左侧树形菜单
		layout_west_tree = $('#layout_west_tree').tree({
			url : '${path }/resource/tree',
			parentField : 'pid',
			lines : true,
			onClick : function(node) {
				if (node.attributes.indexOf("http") >= 0) {
					var url = node.attributes;
					addTab({
						url : url,
						title : node.text,
						iconCls : node.iconCls
					});
				} else if (node.attributes) {
					var url = '${path }' + node.attributes;
					addTab({
						url : url,
						title : node.text,
						iconCls : node.iconCls
					});
				}
			}
		});
		//绑定tabs的右键菜单
		$("#index_tabs").tabs({
			onContextMenu : function(e, title) {
				e.preventDefault();
				$('#tabsMenu').menu('show', {
					left : e.pageX,
					top : e.pageY
				}).data("tabTitle", title);
			}
		});

		//实例化menu的onClick事件
		$("#tabsMenu").menu({
			onClick : function(item) {
				CloseTab(this, item.name);
			}
		});

		//几个关闭事件的实现
		function CloseTab(menu, type) {
			var curTabTitle = $(menu).data("tabTitle");
			var tabs = $("#index_tabs");
			if (type === "close") {
				tabs.tabs("close", curTabTitle);
				return;
			}
			var allTabs = tabs.tabs("tabs");
			var closeTabsTitle = [];
			$.each(allTabs, function() {
				var opt = $(this).panel("options");
				if (opt.closable && opt.title != curTabTitle
						&& type === "Other") {
					closeTabsTitle.push(opt.title);
				} else if (opt.closable && type === "All") {
					closeTabsTitle.push(opt.title);
				}
			});

			for (var i = 0; i < closeTabsTitle.length; i++) {
				tabs.tabs("close", closeTabsTitle[i]);
			}
		}
	});
	function addTab(params) {
		var iframe = '<iframe src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:99.5%;"></iframe>';
		var t = $('#index_tabs');
		var opts = {
			title : params.title,
			closable : true,
			iconCls : params.iconCls,
			content : iframe,
			border : false,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			t.tabs('select', opts.title);
		} else {
			t.tabs('add', opts);
		}
	}
	function logout() {
		$.messager.confirm('提示', '确定要退出吗?', function(r) {
			if (r) {
                window.location.href ='${path }/logout';
			}
		});
	}

	function editUserPwd() {
		parent.$.modalDialog({
			title : '修改密码',
			width : 300,
			height : 250,
			href : '${path }/user/editPwdPage',
			buttons : [ {
				text : '确定',
				handler : function() {
					var f = parent.$.modalDialog.handler.find('#editUserPwdForm');
					f.submit();
				}
			} ]
		});
	}
</script>
</head>
<body>
	<div id="loading" style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #fff;z-index: 100;overflow: hidden;">
		<img src="${staticPath }/static/style/images/ajax-loader.gif" style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;" />
	</div>
	<div id="index_layout">
		<div data-options="region:'north',border:false"
			style=" overflow: hidden; ">
			<div>
				<span style="float: right; padding-right: 20px; margin-top: 15px; color: #333">欢迎
					<b><shiro:principal></shiro:principal></b>
					&nbsp;&nbsp;<a href="javascript:void(0)" onclick="editUserPwd()" class="easyui-linkbutton" plain="true" icon="icon-edit">修改密码</a>
					&nbsp;&nbsp;<a href="javascript:void(0)" onclick="logout()" class="easyui-linkbutton" plain="true" icon="icon-clear">安全退出</a>
				</span> <span class="header"></span>
			</div>
		</div>
		<div data-options="region:'west',split:true" title="菜单" style="width: 205px; overflow: hidden;overflow-y:auto; padding:0px">
			<div class="well well-small" style="padding: 5px 5px 5px 5px;">
				<ul id="layout_west_tree"></ul>
			</div>
		</div>
		<div data-options="region:'center'" style="overflow: hidden;">
			<div id="index_tabs" style="overflow: hidden;">
				<div title="首页" data-options="border:false,closable : true" style="overflow: hidden;">
					<div style="width: 410px;height: 110px;margin-top: 20px;margin-left: 10px;">
						<p style="line-height:70px; border-radius:20px;font-size: 20px;border-style:outset;">
							<img src="${staticPath }/static/style/images/fj1.jpg" style="width: 70px;height: 50px;">保障航班架次:<span id="circle" style="font-size: 50px;color: red;"></span>(次)
						</p>
					</div>
					<div style="width: 410px;height: 110px;margin-top: 20px;margin-left: 10px;">
						<p style="line-height:70px;border-radius:20px;font-size: 20px;border-style:outset;">
							<img src="${staticPath }/static/style/images/fj2.jpg" style="width: 70px;height: 50px;">已保障航班架次:<span id="circle1" style="font-size: 50px;color: green;"></span>(次)
						</p>
					</div>
					<div style="width: 410px;height: 110px;margin-top: 20px;margin-left: 10px;">
						<p style="line-height:70px;border-radius:20px;font-size: 20px;border-style:outset;">
							<img src="${staticPath }/static/style/images/xl3.jpg" style="width: 70px;height: 50px;">行李总数:<span id="circle4" style="font-size: 50px;color: #00ACF1;"></span>(件)
						</p>
					</div>
					<div style="width: 410px;height: 110px;margin-top: 20px;margin-left: 10px;">
						<p style="line-height:70px;border-radius:20px;font-size: 20px;border-style:outset;">
							<img src="${staticPath }/static/style/images/xl1.jpg" style="width: 70px;height: 50px;">已装车行李数:<span id="circle2" style="font-size: 50px;color: green;"></span>(件)
						</p>
					</div>
					<div style="width: 410px;height: 110px;margin-top: 20px;margin-left: 10px;">
						<p style="line-height:70px;border-radius:20px;font-size: 20px;border-style:outset;">
							<img src="${staticPath }/static/style/images/xl2.jpg" style="width: 70px;height: 50px;">未识别行李数:<span id="circle3" style="font-size: 50px;color: red;"></span>(件)
						</p>
					</div>
					<div id="container" style="border-radius:10px;border-style:outset;width: 930px;height: 310px;position:fixed; left:650px; bottom:368px;"></div>
					<div id="containerz" style="border-radius:10px;border-style:outset;width: 930px;height: 310px;position:fixed; left:650px; bottom:40px;"></div>
				</div>
			</div>
		</div>
		<div data-options="region:'south',border:false" style="height: 30px;line-height:30px; overflow: hidden;text-align: center;background-color: #eee">
			Copyright © 2015 power by <a href="http://www.brytx.com/" target="_blank">博睿英特</a>
		</div>
	</div>
	<div id="tabsMenu" class="easyui-menu" style="width:120px;">
		<div name="close">关闭</div>
		<div name="Other">关闭其他</div>
		<div name="All">关闭所有</div>
	</div>
	<!--[if lte IE 7]>
    <div id="ie6-warning"><p>您正在使用 低版本浏览器，在本页面可能会导致部分功能无法使用。建议您升级到 <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">Internet Explorer 8</a> 或以下浏览器：
    <a href="http://www.mozillaonline.com/" target="_blank">Firefox</a> / <a href="http://www.google.com/chrome/?hl=zh-CN" target="_blank">Chrome</a> / <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> / <a href="http://www.operachina.com/" target="_blank">Opera</a></p></div>
    <![endif]-->
	<style>
/*ie6提示*/
#ie6-warning {
	width: 100%;
	position: absolute;
	top: 0;
	left: 0;
	background: #fae692;
	padding: 5px 0;
	font-size: 12px
}

#ie6-warning p {
	width: 960px;
	margin: 0 auto;
}
</style>
</body>
</html>