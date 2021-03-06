<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>操作员维护</title>
    <script type="text/javascript">
    var dataGrid;
    $(function() {
        dataGrid = $('#dataGrid').datagrid({
            url : '${path }' + '/t_airport_employee/dataGrid',
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
                width : '100',
                title : '操作员编号',
                field : 'code',
                sortable : true
            } , {
                width : '100',
                title : '操作员姓名',
                field : 'name',
                sortable : true
            }, {
                width : '100',
                title : '所属单位',
                field : 'corpation',
                sortable : true
            }, {
                width : '100',
                title : '所属部门',
                field : 'department',
                sortable : true
            }, {
                width : '100',
                title : '所属岗位',
                field : 'station',
                sortable : true
            }, {
                width : '100',
                title : '状态',
                field : 'status',
                sortable : true
            }, {
                width : '100',
                title : 'APP登陆用户',
                field : 'loginusr',
                sortable : true
            }, {
                width : '100',
                title : 'APP登陆密码',
                field : 'loginpwd',
                sortable : true
            },{
                width : '100',
                title : '权限级别',
                field : 'rightlevel',
                sortable : true
            },{
                width : '120',
                title : '备注',
                field : 'remark',
                sortable : true,
            }, {
                field : 'action',
                title : '操作',
                width : 150,
                formatter : function(value, row, index) {
                    var str = '';
                        <%--<shiro:hasPermission name="/t_airport_employee/edit">--%>
                            str += '&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="t_airport_employee-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
                        <%--</shiro:hasPermission>--%>
                        <%--<shiro:hasPermission name="/t_airport_employee/delete">--%>
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="t_airport_employee-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
                        <%--</shiro:hasPermission>--%>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){

                $('.t_airport_employee-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                $('.t_airport_employee-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
            },
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
            width : 400,
            height : 430,
            href : '${path }/t_airport_employee/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#t_airport_employeeAddForm');
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
            width : 400,
            height : 430,
            href : '${path }/t_airport_employee/editPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#t_airport_employeeEditForm');
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
        parent.$.messager.confirm('询问', '您是否要删除当前操作员信息？', function(b) {
            if (b) {
                progressLoad();
                $.post('${path }/t_airport_employee/delete', {
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
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',fit:true,border:false">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
        <%--<shiro:hasPermission name="/t_airport_employee/add">--%>
            <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        <%--</shiro:hasPermission>--%>
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