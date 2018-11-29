<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>航班动态查询</title>
    <script type="text/javascript">
    var dataGrid;
    $(function() {
        function formatDatebox(value) {  
            if (value == null || value == '') {  
                return '';  
            }  
            var dt;  
            if (value instanceof Date) {  
                dt = value;  
            } else {  
                dt = new Date(value);  
            }  
          
            return dt.format("yyyy-MM-dd hh:mm"); //扩展的Date的format方法(上述插件实现)  
        }
        dataGrid = $('#dataGrid').datagrid({
            url : '${path }' + '/t_airport_dyndepflt/dataGrid',
        	striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
            idField : 'id',
            sortName : 'id',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
        	onClickRow:function(rowIndex,rowData){
                $("#subdataGrid").datagrid({url:'${path }/t_airport_luggage/dataGrid',  
                    queryParams:{fltno:rowData.fltno}
                }); //载入子表
                subdataGrid.datagrid('enableFilter', [{
                    field:'action',
                    hidden:true
                }]); // 过滤查询
            },
            columns : [ [ {
                width : '80',
                title : '航班日期',
                field : 'plandate',
                sortable : true
            } , {
                width : '80',
                title : '航空公司',
                field : 'aircorp',
                sortable : true
            } ,{
                width : '80',
                title : '航班号',
                field : 'fltno',
                sortable : true
            } ,{
                width : '80',
                title : '航班状态',
                field : 'fstatus',
                sortable : true
            },{
                width : '80',
                title : '机号',
                field : 'planeno',
                sortable : true
            },{
                width : '80',
                title : '机型',
                field : 'planemdl',
                sortable : true
            },{
                width : '80',
                title : '机位号',
                field : 'portno',
                sortable : true
            },{
                width : '80',
                title : '航班属性',
                field : 'fltattr',
                sortable : true
            }, {
                width : '80',
                title : '航班性质',
                field : 'flttype',
                sortable : true
            }, {
                width : '80',
                title : '起飞地',
                field : 'tkfp',
                sortable : true
            }, {
                width : '80',
                title : '经停地1',
                field : 'pass1',
                sortable : true
            },{
                width : '80',
                title : '经停地2',
                field : 'pass2',
                sortable : true,
            },{
                width : '80',
                title : '到达地',
                field : 'arrp',
                sortable : true,
            },{
                width : '120',
                title : '计划起飞时间',
                field : 'tkftm',
                sortable : true,
                formatter: formatDatebox
            },{
                width : '120',
                title : '计划落地时间',
                field : 'arrtm',
                sortable : true,
                formatter: formatDatebox
            },{
                width : '120',
                title : '预计起飞时间',
                field : 'ptkftm',
                sortable : true,
                formatter: formatDatebox
            },{
                width : '120',
                title : '预计落地时间',
                field : 'parrtm',
                sortable : true,
                formatter: formatDatebox
            },{
                width : '120',
                title : '实际起飞时间',
                field : 'rtkftm',
                sortable : true,
                formatter: formatDatebox
            },{
                width : '120',
                title : '实际落地时间',
                field : 'rarrtm',
                sortable : true,
                formatter: formatDatebox
            },{
                width : '80',
                title : '航站楼',
                field : 'build',
                sortable : true,
            },{
                width : '80',
                title : '备注',
                field : 'remartk',
                sortable : true,
            }] ],
            toolbar : '#toolbar',
			remoteFilter:true
        });
    	dataGrid.datagrid('enableFilter', [{
            field:'action',
            hidden:true
        }]); // enable filter
    	
    	 //子表
    	subdataGrid = $('#subdataGrid').datagrid({
    		url : '${path }/t_airport_luggage/dataGrid',
    		striped : true,
    		rownumbers : true,
    		pagination : true,
    		singleSelect : true,
    		idField : 'id',
    		sortName : 'id',
    		sortOrder : 'asc',
    		pageSize : 50,
    		pageList : [ 10, 20, 30, 40, 50],		
    		columns : [ [   {
                width : '80',
                title : '航班日期',
                field : 'plandate',
                sortable : true
            } , {
                width : '80',
                title : '航空公司',
                field : 'aircorp',
                sortable : true
            } ,{
                width : '80',
                title : '航班号',
                field : 'fltno',
                sortable : true
            } , {
    			width : '80',
    			title : '身份证号',
    			field : 'percode'
    		} , {
    			width : '80',
    			title : '行李标签号',
    			field : 'bgcode'
    		}, {
    			width : '80',
    			title : '行李类型',
    			field : 'type'
    		} , {
    			width : '80',
    			title : '行李属性',
    			field : 'attr'
    		}, {
    			width : '80',
    			title : '重量',
    			field : 'bgweight'
    		} , {
    			width : '80',
    			title : '行李照片路劲',
    			field : 'bgphoto'
    		}, {
    			width : '80',
    			title : '行李状态',
    			field : 'bgstatus'
    		} , {
    			width : '80',
    			title : '当前对应拖车编号',
    			field : 'trailercode'
    		}, {
    			width : '80',
    			title : '当前对应操作员编号',
    			field : 'personcode'
    		} , {
    			width : '80',
    			title : '当前对应滑槽编号',
    			field : 'chutecode'
    		}, {
    			width : '80',
    			title : '装车时间',
    			field : 'trailer_time',
    			formatter: formatDatebox
    		} , {
    			width : '80',
    			title : '出库时间',
    			field : 'out_time',
    			formatter: formatDatebox
    		}, {
    			width : '80',
    			title : '进舱时间',
    			field : 'in_cabin_time',
    			formatter: formatDatebox
    		} , {
    			width : '80',
    			title : '进舱操作员编号',
    			field : 'in_cabin_employee_id'
    		} , {
    			width : '80',
    			title : '进舱分区',
    			field : 'cation_area'
    		} , {
    			width : '80',
    			title : '出舱时间',
    			field : 'out_cabin_time',
    			formatter: formatDatebox
    		} , {
    			width : '80',
    			title : '出舱操作员编号',
    			field : 'out_cabin_employee_id'
    		} , {
    			width : '80',
    			title : '行李级别',
    			field : 'bglevel'
    		} , {
    			width : '80',
    			title : '值机柜台号',
    			field : 'chkdesk'
    		} , {
    			width : '80',
    			title : '值机时间',
    			field : 'in_chk_time',
    			formatter: formatDatebox
    		}, {
    			width : '80',
    			title : '备注',
    			field : 'remartk'
    		} ] ],  	
    	});
    });
    
     function searchFun() {
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
        subdataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    }
     function cleanFun() {
        $('#searchForm input').val('');
        dataGrid.datagrid('load', {});
        subdataGrid.datagrid('load', {});
    }
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
   	<div data-options="region:'west',fit:false,border:false,collapsible:false,split:true,title:'航班动态'" style="width: 500px">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
    <div data-options="region:'center',fit:false,border:true,collapsible:false,split:true,title:'旅客行李信息'"  style="width: 300px">
        <table id="subdataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
        <form id="searchForm">
            <table>
                <tr>
                    <th>关键字:</th>
                    <td><input name="FLTNO" placeholder="请输入查询条件"/></td>                    
                    <td>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>