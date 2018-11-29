<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<!-- <meta http-equiv="X-UA-Compatible" content="edge" /> -->
<title>部门管理</title>
<script type="text/javascript">
    var treeGrid;
    var dataGrid;
    var deptid;
    $(function() {
        treeGrid = $('#treeGrid').treegrid({
            url : '${path }/organization/treeGrid',
            idField : 'id',
            treeField : 'name',
            parentField : 'pid',
            fit : true,
            fitColumns : false,
            singleSelect : true,
            striped : true,
            border : false,
            frozenColumns : [ [ {
                title : 'id',
                field : 'id',
                width : 40,
                hidden : true
            } ] ],
            columns : [ [ {
                field : 'code',
                title : '部门编号',
                width : 60
            },{
                field : 'name',
                title : '部门名称',
                width : 180
            }, {
                field : 'seq',
                title : '排序',
                width : 40
            }, {
                field : 'iconCls',
                title : '图标',
                width : 100
            },  {
                width : '130',
                title : '创建时间',
                field : 'createdate'
            },{
                field : 'pid',
                title : '上级资源ID',
                width : 150,
                hidden : true
            }, {
                field : 'address',
                title : '地址',
                width : 120
            } , {
                field : 'action',
                title : '操作',
                width : 130,
                formatter : function(value, row, index) {
                    var str = '';
                        <%--<shiro:hasPermission name="/organization/edit">--%>
                            str += $.formatString('<a href="javascript:void(0)" class="organization-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
                        <%--</shiro:hasPermission>--%>
                        <%--<shiro:hasPermission name="/organization/delete">--%>
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="organization-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
                        <%--</shiro:hasPermission>--%>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.organization-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                $('.organization-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
            },
		   	onClickRow:function(row){  
		        var rowIndex = row.code;
		      	dataGrid.datagrid({
	     		   	   url:'${path }/postinfo/dataGrid?code='+rowIndex	   	   
	     		    });
		       },
            toolbar : '#toolbar'
        });
        
        dataGrid = $('#dataGrid').datagrid({
            fit : true,
            striped : true,
            rownumbers : true,
            columns : [ [ {
                title : 'id',
                field : 'id',
                width : 40,
                hidden : true
            },{
                width : '80',
                title : '岗位名称',
                field : 'postname',
                sortable : true
            }, {
                width : '80',
                title : '备注',
                field : 'postremark',
                sortable : true
            },{
                width : '130s',
                title : '创建时间',
                field : 'createdate',
                sortable : true
            },{
                field : 'action',
                title : '操作',
                width : 130,
                formatter : function(value, row, index) {
                    var str = '';
                        <%--<shiro:hasPermission name="/postinfo/edit">--%>
                            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editPostinfoFun(\'{0}\');" >编辑</a>', row.id);
                        <%--</shiro:hasPermission>--%>
                        <%--<shiro:hasPermission name="/postinfo/delete">--%>
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deletePostinfoFun(\'{0}\');" >删除</a>', row.id);
                        <%--</shiro:hasPermission>--%>
                    return str;
                }
            }] ],
            onLoadSuccess:function(data){
                $('.user-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                $('.user-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
            },
            toolbar : '#toolbar1'
        });
    });
    
    function editFun(id) {
        if (id != undefined) {
            treeGrid.treegrid('select', id);
        }
        var node = treeGrid.treegrid('getSelected');
        if (node) {
            parent.$.modalDialog({
                title : '编辑',
                width : 500,
                height : 230,
                href : '${path }/organization/editPage?id=' + node.id,
                buttons : [ {
                    text : '编辑',
                    handler : function() {
                        parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#organizationEditForm');
                        f.submit();
                    }
                } ]
            });
        }
    }
    
    function deleteFun(id) {
        if (id != undefined) {
            treeGrid.treegrid('select', id);
        }
        var node = treeGrid.treegrid('getSelected');
        if (node) {
            parent.$.messager.confirm('询问', '您是否要删除当前部门？删除当前部门会连同部门下岗位一起删除!', function(b) {
                if (b) {
                    progressLoad();
                    $.post('${path }/organization/delete', {
                        id : node.id
                    }, function(result) {
                        if (result.success) {
                            parent.$.messager.alert('提示', result.msg, 'info');
                            treeGrid.treegrid('reload');
                        }else{
                            parent.$.messager.alert('提示', result.msg, 'info');
                        }
                        progressClose();
                    }, 'JSON');
                }
            });
        }
    }
    
    function addFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 500,
            height : 230,
            href : '${path }/organization/addPage',
            buttons : [ {
                text : '添加',
                handler : function() {
                    parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#organizationAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
        ///////添加岗位////////
        function addPostinfoFun() {
        var rowInfos = $("#treeGrid").treegrid('getSelected');
        if(!rowInfos){
        	parent.$.messager.alert('提示', '请先选中部门！', 'info');
        }       
        else{
        	var codes=$("#treeGrid").treegrid('getSelections')[0].code;
             parent.$.modalDialog({
                 title : '添加',
                 width : 400,
                 height : 180,
                 href : '${path }/postinfo/addPage?code='+codes,
                 buttons : [ {
                     text : '添加',
                     handler : function() {
                         parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                         var f = parent.$.modalDialog.handler.find('#postinfoAddForm');
                         f.submit();
                     }
                 } ]
             });
        }
      }
     /////////////////////////////
     function editPostinfoFun(id){
       if (id == undefined) {
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.modalDialog({
            title : '编辑',
            width : 400,
            height : 180,
            href : '${path }/postinfo/editPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#postinfoEditForm');
                    f.submit();
                }
            } ]
        });
     }

     /////////////////////////////
     function deletePostinfoFun(id){
       if (id == undefined) {//点击右键菜单才会触发这个
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {//点击操作里面的删除图标会触发这个
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.messager.confirm('询问', '您是否要删除当前岗位？', function(b) {
            if (b) {
                progressLoad();
                $.post('${path}/postinfo/delete', {
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
     
    </script>
</head>
<body>
    <div class="easyui-layout" data-options="fit:true,border:false">
        <div data-options="region:'center',border:false"  style="overflow: hidden;">        
	        <div id="toolbar" style="display: none;">
	            <%--<shiro:hasPermission name="/organization/add">--%>
	                <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
	            <%--</shiro:hasPermission>--%>
	        </div>
            <table id="treeGrid"></table>
        </div>
        <div data-options="region:'east',border:true,split:false,title:'岗位列表'"  style="width:50%;overflow: hidden; ">
            <div id="toolbar1" style="display: none;">
	            <%--<shiro:hasPermission name="/postinfo/add">--%>
	                <a onclick="addPostinfoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
	            <%--</shiro:hasPermission>--%>
	        </div>
            <table id="dataGrid" data-options="fit:true,border:false"></table>
        </div>
    </div>
</body>
</html>