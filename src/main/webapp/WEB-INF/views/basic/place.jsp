<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>地名维护</title>
    <script type="text/javascript">
    var dataGrid;
    $(function() {
        dataGrid = $('#dataGrid').datagrid({
            url : '${path }' + '/place/dataGrid',
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'place_no',
            sortName : 'place_no',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            frozenColumns : [ [ {
                width : '120',
                title : '地名编号',
                field : 'place_no',
                sortable : true,
                hidden: true
            } , {
                width : '120',
                title : '三字码',
                field : 'esname',
                sortable : true
            }, {
                width : '120',
                title : '四字码',
                field : 'icao',
                sortable : true
            }, {
                width : '120',
                title : '地名英文全称',
                field : 'elname',
                sortable : true
            }, {
                width : '120',
                title : '中文名称',
                field : 'csname',
                sortable : true
            }, {
                width : '120',
                title : '航管名称',
                field : 'clname',
                sortable : true
            }, {
                width : '120',
                title : '中文简称',
                field : 'cssname',
                sortable : true
            }, {
                width : '150',
                title : '国际标志(是/否)',
                field : 'gjbz',
                sortable : true,
                formatter : function(value, row, index) {
                    if(value=="是")
                    {
                      return "<font color='green'>是</font>";
                    }
                    if(value=="否"){            
                      return "<font color='red'>否</font>";
                    }
                }
            }, {
                field : 'action',
                title : '操作',
                width : 150,
                formatter : function(value, row, index) {
                    var str = '';
                        <%--<shiro:hasPermission name="/place/edit">--%>
                            str += '&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="place-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.place_no);
                        <%--</shiro:hasPermission>--%>
                        <%--<shiro:hasPermission name="/place/delete">--%>
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="place-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.place_no);
                        <%--</shiro:hasPermission>--%>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){

                $('.place-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                $('.place-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
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
            height : 340,
            href : '${path }/place/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#placeAddForm');
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
            height : 340,
            href : '${path }/place/editPage?place_no=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#placeEditForm');
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
        parent.$.messager.confirm('询问', '您是否要删除当前地名？', function(b) {
            if (b) {
                progressLoad();
                $.post('${path }/place/delete', {
                	place_no : id
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
        <%--<shiro:hasPermission name="/place/add">--%>
            <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        <%--</shiro:hasPermission>--%>
        <form id="searchForm">
            <table>
                <tr>
                    <th>关键字:</th>
                    <td><input name="ESNAME" placeholder="请输入查询条件"/></td>                    
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                    </td>
                </tr>
            </table>

        </form>
    </div>
</body>
</html>