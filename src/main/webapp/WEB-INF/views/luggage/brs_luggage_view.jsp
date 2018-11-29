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
    var fdataGrid;
    $(function() {
        dataGrid = $('#dataGrid').datagrid({
            //url : '${path }' + '/brs_luggage_view/dataGrid',
            url:'',
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
            } ,  {
                width : '80',
                title : '航班号',
                field : 'flightno'
            },{
                width : '80',
                title : '旅客姓名',
                field : 'lkname'
            },{
                width : '120',
                title : '身份证号',
                field : 'percode'
            },{
                width : '120',
                title : '行李标签号',
                field : 'bgcode'
            },{
                width : '80',
                title : '行李类型',
                field : 'type'
            },{
                width : '60',
                title : '行李属性',
                field : 'attr'
            },{
                width : '50',
                title : '重量(kg)',
                field : 'bgweight'
            },{
                width : '60',
                title : '行李状态',
                field : 'bgstatus'
            },{
                width : '80',
                title : '拖车编号',
                field : 'trailercode'
            },{
                width : '60',
                title : '操作员',
                field : 'opname'
            },{
                width : '60',
                title : '滑槽号',
                field : 'chutecode'
            },{
                width : '120',
                title : '备注',
                field : 'remartk'
            }
             ] ],
            toolbar : '#toolbar',
			remoteFilter:true
        });
    	dataGrid.datagrid('enableFilter',[{
			field:'action',
			hidden:true
		}]);
        fdataGrid= $('#fdataGrid').datagrid({
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
            columns : [ [
            //{
            //    field : 'action',
            //    title : '操作',
            //    width : 100,
            //    formatter : function(value, row, index) {
            //        var str = '';
            <%--//            <shiro:hasPermission name="/t_airport_chute/edit">--%>
            //                str += $.formatString('<a href="javascript:void(0)" class="t_airport_chute-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >装车结束</a>', row);
            <%--//            </shiro:hasPermission>--%>
            //        return str;
            //    }},
                {
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
            },{
                width : '120',
                title : '计划落地时间',
                field : 'arrtm',
                sortable : true,
            },{
                width : '120',
                title : '预计起飞时间',
                field : 'ptkftm',
                sortable : true,
            },{
                width : '120',
                title : '预计落地时间',
                field : 'parrtm',
                sortable : true,
            },{
                width : '120',
                title : '实际起飞时间',
                field : 'rtkftm',
                sortable : true,
            },{
                width : '120',
                title : '实际落地时间',
                field : 'rarrtm',
                sortable : true,
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
            onClickRow:function(index, row){
              dataGrid.datagrid({url:'${path }' + '/brs_luggage_view/dataGrid',queryParams:{plandate:row.plandate,flightno:row.aircorp+row.fltno}});
              $('#flightno').val(row.aircorp+row.fltno);
              $('#plandate').datebox('setValue', row.plandate);
              $('#flightnom').val(row.aircorp+row.fltno);
              $('#plandatem').datebox('setValue', row.plandate);
            },
            //onLoadSuccess:function(data){
            //    $('.t_airport_chute-easyui-linkbutton-edit').linkbutton({text:'装车结束',plain:true,iconCls:'icon-edit'});
            //},
			remoteFilter:true
        });
    	fdataGrid.datagrid('enableFilter',[{
			field:'action',
			hidden:true
		}]);
    });
    
     function editFun(row){
       
     }
   
     function searchFun() {
        dataGrid.datagrid({url:'${path }' + '/brs_luggage_view/dataGrid',queryParams:$.serializeObject($('#searchForm'))});
        //dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    }
     function cleanFun() {
        //$('#searchForm input').val('');
        $('#remartk').val('');
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    }
    
    function searchDFun() {
        dataGrid.datagrid('load', $.serializeObject($('#searchDForm')));
    }
     function cleanDFun() {
        //$('#searchDForm input').val('');
        $('#lkname').val('');
        $('#percode').val('');
        $('#bgcode').val('');
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    }
    
        
    function printFun() {
        var plandate=$('#plandate').datebox('getValue');
        var flightno=document.getElementById("flightno").value;
        if(plandate.trim()==""||flightno.trim()==""){
           alert("请选择要打印的航班！");
           return;
        }
        $.ajax({
        type:'POST',
        url:'${path }' + '/brs_luggage_view/printSearch',
        data:{plandate:plandate,flightno:flightno},
        dataType:'json',  
		success:function(data) {  
		    //for(var code in data){
		    //console.log(data[code]);
		    //}
		    if(window.localStorage){
		    localStorage['print_data']=JSON.stringify(data);
		    }else{
		    }
		    
		    window.open("${staticPath }/static/print.jsp");
		 },  
		 error : function() {  
		      // view("异常！");  
		      alert("异常！");  
		 }  
        
        });
    }
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',width:500,border:true,title:'行李维护'">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div data-options="region:'west',width:450,border:false,title:'航班信息'">
        <table id="fdataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display: none;">
        <form id="searchForm">
            <table>
                <tr>
                    <th>关键字:</th>
                    <td><input id="remartk" name="remartk" placeholder="请输入查询条件"/></td>                                        
			        <td  style="display:none;">航班日期:</td><td  style="display:none;"><input id="plandatem" name="plandate" class="easyui-datebox"  style="width:200px;"/></td>
			        <td style="display:none;">航班号:</td><td style="display:none;"><input id="flightnom" name="flightno"/></td>
			        <td>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">模糊查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
        <form id="searchDForm">
        <table>
        <tr>
        <td  style="display:none;">航班日期:</td><td  style="display:none;"><input id="plandate" name="plandate" class="easyui-datebox"  style="width:200px;"/></td>
        <td style="display:none;">航班号:</td><td style="display:none;"><input id="flightno" name="flightno"/></td>
        <td>旅客姓名:</td><td><input id="lkname" name="lkname"/></td>
        <td>身份证:</td><td><input id="percode" name="percode"/></td>
        <td>行李标签:</td><td><input id="bgcode" name="bgcode"/></td>
        <td>
        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchDFun();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanDFun();">清空</a>
        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="printFun();">打印</a>
        </td>
        </tr>
        </table>
        </form>
      </div>
</body>
</html>