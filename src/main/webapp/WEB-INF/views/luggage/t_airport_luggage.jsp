<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>旅客行李维护</title>
    <script type="text/javascript">
    var dataGrid;
    $(function() {
        dataGrid = $('#dataGrid').datagrid({
            url : '${path }' + '/t_airport_luggage/dataGrid',
        	striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
            idField : 'id',
            sortName : 'id',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            columns : [ [ {
                width : '100',
                title : '航班日期',
                field : 'plandate',
                sortable : true
            } , {
                width : '60',
                title : '航空公司',
                field : 'aircorp',
                sortable : true
            }, {
                width : '50',
                title : '航班号',
                field : 'fltno',
                sortable : true
            },{
                width : '120',
                title : '身份证号',
                field : 'percode',
                sortable : true
            },{
                width : '120',
                title : '行李标签号',
                field : 'bgcode',
                sortable : true
            },{
                width : '120',
                title : '行李类型',
                field : 'type',
                sortable : true
            },{
                width : '60',
                title : '行李属性',
                field : 'attr',
                sortable : true
            },{
                width : '60',
                title : '重量(kg)',
                field : 'bgweight',
                sortable : true
            },{
                width : '120',
                title : '行李状态',
                field : 'bgstatus',
                sortable : true
            },{
                width : '80',
                title : '拖车编号',
                field : 'trailercode',
                sortable : true
            },{
                width : '60',
                title : '操作员',
                field : 'personcode',
                sortable : true
            },{
                width : '60',
                title : '滑槽号',
                field : 'chutecode',
                sortable : true
            },{
                width : '120',
                title : '装车时间',
                field : 'trailerTime',               
                sortable : true
            },{
                width : '120',
                title : '出库时间',
                field : 'outTime',
                sortable : true
            },{
                width : '120',
                title : '进舱时间',
                field : 'inCabinTime',
                sortable : true
            },{
                width : '120',
                title : '进舱操作员',
                field : 'inCabinEmployeeId',
                sortable : true
            },{
                width : '120',
                title : '进舱分区',
                field : 'cationArea',
                sortable : true
            },{
                width : '120',
                title : '出舱时间',
                field : 'outCabinTime',
                sortable : true
            },{
                width : '120',
                title : '出舱操作员',
                field : 'outCabinEmployeeId',
                sortable : true
            },{
                width : '120',
                title : '行李级别',
                field : 'bglevel',
                sortable : true
            },{
                width : '120',
                title : '值机柜台',
                field : 'chkdesk',
                sortable : true
            },{
                width : '120',
                title : '值机时间',
                field : 'inChkTime',
                sortable : true
            },{
                width : '120',
                title : '备注',
                field : 'remartk',
                sortable : true
            }
             ] ],
            toolbar : '#toolbar',
			remoteFilter:true
        });
    	dataGrid.datagrid('enableFilter',[{
			field:'action',
			hidden:true
		}]);
    });

   function addFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 700,
            height : 630,
            href : '${path }/t_airport_luggage/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#t_airport_luggageAddForm');
                    f.submit();
                }
            } ]
        });
    }

    function editFun(id) {
        if (id == undefined) {
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.modalDialog({
            title : '编辑',
            width : 700,
            height : 630,
            href : '${path }/t_airport_luggage/editPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#t_airport_luggageEditForm');
                    f.submit();
                }
            } ]
        });
    }

    function deleteFun(id) {
        if (id == undefined) {//点击右键菜单才会触发这个
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {//点击操作里面的删除图标会触发这个
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.messager.confirm('询问', '您是否要删除当前行李？', function(b) {
            if (b) {
                progressLoad();
                $.post('${path }/t_airport_luggage/delete', {
                    id : id
                }, function(result) {
                    if (result.success) {
                        parent.$.messager.alert('提示', result.msg, 'info');
                        dataGrid.datagrid('reload');
                    }
                    progressClose();
                }, 'JSON');
            }
        });
    }
    
     function searchFun() {
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    }
     function cleanFun() {
        $('#searchForm input').val('');
        dataGrid.datagrid('load', {});
    }
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false" >
 	<div data-options="region:'west',fit:false,border:false" style="overflow:hidden">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
    <div id="toolbar" style="display: none;">
        <%--<shiro:hasPermission name="/t_airport_luggage/add">--%>
            <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
            <a onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">修改</a>
            <a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
        <%--</shiro:hasPermission>--%>
        <form id="searchForm">
            <table>
                <tr>
                    <th>关键字:</th>
                    <td><input name="remartk" placeholder="请输入查询条件"/></td>                    
                    <td>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>