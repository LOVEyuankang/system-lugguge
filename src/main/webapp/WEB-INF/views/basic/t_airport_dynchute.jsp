<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>滑槽分配查询</title>
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
            url : '${path }' + '/t_airport_dynchute/dataGrid',
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
            {
                field : 'action',
                title : '操作',
                width : 100,
                formatter : function(value, row, index) {
                    var str = '';
                        <%--<shiro:hasPermission name="/t_airport_chute/edit">--%>
                            str += $.formatString('<a href="javascript:void(0)" class="t_airport_chute-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >装车结束</a>', index);
                        <%--</shiro:hasPermission>--%>
                    return str;
                }},            
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
                title : '滑槽编号',
                field : 'code',
                sortable : true
            }, {
                width : '80',
                title : '滑槽状态',
                field : 'status',
                sortable : true
            },{
                width : '80',
                title : '滑槽属性',
                field : 'attr',
                sortable : true,
            },{
                width : '120',
                title : '计划开放时间',
                field : 'stm',
                formatter: formatDatebox
            },{
                width : '120',
                title : '计划结束时间',
                field : 'etm',
                sortable : true,
                formatter: formatDatebox
                    
            },{
                width : '120',
                title : '预计开放时间',
                field : 'pstm',
                sortable : true,
                formatter:formatDatebox
            },{
                width : '120',
                title : '预计结束时间',
                field : 'petm',
                sortable : true,
                formatter:formatDatebox
            },{
                width : '120',
                title : '实际开放时间',
                field : 'rstm',
                sortable : true,
                formatter:formatDatebox
            },{
                width : '120',
                title : '实际结束时间',
                field : 'retm',
                sortable : true,
                formatter:formatDatebox
            },{
                width : '120',
                title : '当前对应拖车编号',
                field : 'trainlercode',
                sortable : true,
            },{
                width : '120',
                title : '当前对应操作员编号',
                field : 'personcode',
                sortable : true,
            },{
                width : '80',
                title : '备注',
                field : 'remartk',
                sortable : true,
            }] ],
            onLoadSuccess:function(data){
               $('.t_airport_chute-easyui-linkbutton-edit').linkbutton({text:'装车结束',plain:true,iconCls:'icon-edit'});
            },
            toolbar : '#toolbar',
			remoteFilter:true
        });
    	dataGrid.datagrid('enableFilter',[{
			//field:'action',
			//hidden:true
		}]);
    });
     
     function editFun(index){
        dataGrid.datagrid('selectRow',index);// 关键在这里  
        var row = dataGrid.datagrid('getSelected');      
        if(row){
           parent.$.messager.confirm('询问', '您是否要对航班'+row.aircorp+row.fltno+'进行<font color="red">装车结束</font>操作', function(b){
              if(row.plandate==new Date().format('yyyy-MM-dd')){
              if(row.by1=='装车结束'){
                alert("航班已经装车结束！");
              }else{
                var date =new Date(row.stm);
                //console.log(new Date().format('yyyy-MM-dd'));
                //console.log(new Date);
                if(date>new Date()){
                  alert("航班还未开始装车，不能进行装车结束操作！");
                }else{
                
	                $.ajax({
	                url:'${ path}/t_airport_dynchute/endloading',
	                type:'post',
	                data:{plandate:row.plandate,aircorp:row.aircorp,fltno:row.fltno,attr:row.attr},
	                dataType:'json',
	                success:function(data) { 
	                  if(data=='1'){
	                    alert("操作成功！");
	                  }
	                }
	              });
                }
              }            
             }else{
               alert("非当日航班！");
             }
           });
        }
     }
    
     function searchFun() {
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    }
     function cleanFun() {
        $('#searchForm input').val('');
        dataGrid.datagrid('load', {});
    }
    
    
    
   //对Date的扩展，将 Date 转化为指定格式的String   
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
// 例子：   
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.Format = function(fmt)   
{ //author: meizz   
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}  
    
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false" >
 	<div data-options="region:'west',fit:false,border:false" style="overflow:hidden">
		<table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>
    <div id="toolbar" style="display: none;">
        <form id="searchForm">
            <table>
                <tr>
                    <th>航班日期:</th>
                    <td><input name="PLANDATE" type="text" style="width:105px" class="easyui-datebox" data-options="required:true"/></td>
                    <th>&nbsp;&nbsp;&nbsp;航班号:</th>
                    <td><input name="FLTNO" placeholder="请输入航班号"/></td>   
                    <th>&nbsp;&nbsp;&nbsp;关键字:</th>
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