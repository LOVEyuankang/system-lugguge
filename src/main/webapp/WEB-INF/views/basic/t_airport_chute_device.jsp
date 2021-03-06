<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
    <!DOCTYPE html>
    <html>
    <head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>滑槽对应设备维护</title>
    <script type="text/javascript">
    var dataGrid;
    var subdataGrid;
    $(function() {
    	//滑槽表
        dataGrid = $('#dataGrid').datagrid({
            url : '${path }' + '/t_airport_chute/dataGrid',
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
                $("#subdataGrid").datagrid({url:'${path }/t_airport_chute_device/dataGrid',  
                    queryParams:{CHUTECODE:rowData.code}
                }); //载入子表
                subdataGrid.datagrid('enableFilter', [{
                    field:'action',
                    hidden:true
                }]); // 过滤查询
            },
            frozenColumns : [ [ {
                width : '120',
                title : '滑槽编号',
                field : 'code',
                sortable : true
            }, {
                width : '120',
                title : '滑槽类型',
                field : 'type',
                sortable : true
            },
            ] ],
			remoteFilter:true
        });

    	
    	//设备对应表
		subdataGrid = $('#subdataGrid').datagrid({
			url : '',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			sortName : 'id',
			sortOrder : 'asc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50],			
			columns : [ [ {
                width : '120',
                title : '滑槽编号',
                field : 'chutecode',
                sortable : true,
                hidden:true
            },{
                width : '120',
                title : '设备类型',
                field : 'devicetype',
                sortable : true
            }, {
                width : '120',
                title : '设备编号',
                field : 'devicecode',
                sortable : true
            },{
                width : '120',
                title : '备注',
                field : 'remark',
                sortable : true,
            },
            {
                field : 'action',
                title : '操作',
                width : 150,
                formatter : function(value, row, index) {
                    var str = '';
                        <%--<shiro:hasPermission name="/t_airport_chute_device/edit">--%>
                            str += '&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="t_airport_chute_device-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
                        <%--</shiro:hasPermission>--%>
                        <%--<shiro:hasPermission name="/t_airport_chute_device/delete">--%>
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="t_airport_chute_device-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
                        <%--</shiro:hasPermission>--%>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.t_airport_chute_device-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                $('.t_airport_chute_device-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
            },
            toolbar : '#subtoolbar',
			remoteFilter:true
		});
		subdataGrid.datagrid('enableFilter', [{
            field:'action',
            hidden:true
        }]); // enable filter
    });

    function addFun() {
   	 var rowInfo = $("#dataGrid").datagrid('getSelected');
     if(!rowInfo){
      $.messager.alert('提示','请选中要添加的对应滑槽！');
      }else{  
    	  var rows = $("#dataGrid").datagrid('getSelections');
    	  var row1=rows[0].id;
          parent.$.modalDialog({
              title : '添加',
              width : 400,
              height : 220,
              href : '${path }/t_airport_chute_device/addPage?id='+row1,
              buttons : [ {
                  text : '确定',
                  handler : function() {
                      parent.$.modalDialog.openner_dataGrid = subdataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                      var f = parent.$.modalDialog.handler.find('#t_airport_chute_deviceAddForm');
                      f.submit();
                  }
              } ]
          });  
      }
    }

    function editFun(id) {
        if (id == undefined) {
            var rows = subdataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
        	subdataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.modalDialog({
            title : '编辑',
            width : 400,
            height : 220,
            href : '${path }/t_airport_chute_device/editPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = subdataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#t_airport_chute_deviceEditForm');
                    f.submit();
                }
            } ]
        });
    }

    function deleteFun(id) {
        if (id == undefined) {//点击右键菜单才会触发这个
            var rows = subdataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {//点击操作里面的删除图标会触发这个
        	subdataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.messager.confirm('询问', '您是否要删除当前滑槽对应设备信息？', function(b) {
            if (b) {
                progressLoad();
                $.post('${path }/t_airport_chute_device/delete', {
                    id : id
                }, function(result) {
                    if (result.success) {
                        parent.$.messager.alert('提示', result.msg, 'info');
                        subdataGrid.datagrid('reload');
                    }
                    progressClose();
                }, 'JSON');
            }
        });
    }
    
     function searchFun() {
    	 subdataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    }
     function cleanFun() {
        $('#searchForm input').val('');
        subdataGrid.datagrid('load', {});
    }
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:false,border:true,collapsible:false,split:true,title:'设备对应信息'"  style="width:400px">
        <table id="subdataGrid" data-options="fit:true,border:false"></table>
    </div>
	<div data-options="region:'west',fit:false,border:true,collapsible:false,split:true,title:'滑槽信息'" style="width:500px">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
    <div id="subtoolbar" style="display: none;">
        <%--<shiro:hasPermission name="/t_airport_chute_device/add">--%>
            <a  onclick="addFun();"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        <%--</shiro:hasPermission>--%>
        <form id="searchForm">
            <table>
                <tr>
                    <th>关键字:</th>
                    <td><input name="CHUTECODE" placeholder="请输入查询条件"/></td>                    
                    <td>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>