<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
<!--     <meta http-equiv="X-UA-Compatible" content="edge" /> -->
    <title>日志管理</title>
    <script type="text/javascript">
        var dataGrid;
        $(function() {
            dataGrid = $('#dataGrid').datagrid({
                url : '${path }' + '/sysLog/dataGrid',
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
                    title : 'id',
                    field : 'id',
                    sortable : true,
                    hidden:true
                }, {
                    width : '80',
                    title : '操作人',
                    field : 'loginName',
                    sortable : true
                } , {
                    width : '120',
                    title : 'IP地址',
                    field : 'clientIp',
                    sortable : true
                } , {
                    width : '100',
                    title : '操作命令',
                    field : 'command',
                    sortable : true
                } , {
                    width : '160',
                    title : '操作对象',
                    field : 'operationobj',
                    sortable : true
                }, {
                    width : '600',
                    title : '操作内容',
                    field : 'optContent',
                    sortable : true
                }, {
                    width : '140',
                    title : '操作时间',
                    field : 'createTime'                        
                } ] ],
              onDblClickRow:function(index, row){
                 editFun(row.id);
            },
                toolbar : '#toolbar'
            });
        });
      function editFun(id) {
        if (id == undefined) {
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.modalDialog({
            title : '查看日志详细信息',
            width : 500,
            height : 410,
            href :'${path }/sysLog/editPage?id='+id
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
     <form id="searchForm">
            <table>
                <tr>
                    <th>关键字:</th>
                    <td><input name="loginName" placeholder="请输入查询条件"/></td>                    
                    <td>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>