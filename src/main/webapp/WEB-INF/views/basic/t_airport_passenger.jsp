<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>旅客值机查询</title>
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
            url : '${path }' + '/t_airport_passenger/dataGrid',
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'id',
            sortName : 'id',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            frozenColumns : [ [ {
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
            }, {
                width : '80',
                title : '旅客姓名',
                field : 'name',
                sortable : true
            }, {
                width : '80',
                title : '身份证号',
                field : 'code',
                sortable : true
            },{
                width : '80',
                title : '仓位',
                field : 'cw',
                sortable : true,
            },{
                width : '120',
                title : '办理值机序号',
                field : 'chknum',
                sortable : true,
            },{
                width : '120',
                title : '办理值机时间',
                field : 'chktm',
                sortable : true,
                formatter: formatDatebox
            },{
                width : '80',
                title : '备注',
                field : 'remartk',
                sortable : true,
            }] ],
            toolbar : '#toolbar',
			remoteFilter:true
        });
    	dataGrid.datagrid('enableFilter',[{
			field:'action',
			hidden:true
		}]);
    });
    
     function searchFun() {
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    }
     function cleanFun() {
        $('#searchForm input').val('');
        dataGrid.datagrid('load', {});
    }
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',fit:true,border:false">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
        <form id="searchForm">
            <table>
                <tr>
                    <th>关键字:</th>
                    <td><input name="CODE" placeholder="请输入查询条件"/></td>                    
                    <td>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>