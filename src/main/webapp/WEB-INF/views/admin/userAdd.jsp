<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#organizationId').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto',
                onClick: function(node){
    				$("#postId").combobox({
    	                url:'${path }/postinfo/dataGrid', 
    	                queryParams:{code:node.pid}    
                    });//载入子表
    			}
        });
        //加载岗位
        $('#postId').combobox({
            valueField: 'deptid',
            textField:'postname',
            required: true,
            panelHeight : 'auto',
        });
        
        $('#roleIds').combotree({
            url: '${path }/role/tree',
            multiple: true,
            required: true,
            panelHeight : 'auto'
        });

        $('#userAddForm').form({
            url : '${path }/user/add',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('提示', result.msg, 'warning');
                }
            }
        });
        
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="userAddForm" method="post">
            <div class="light-info" style="overflow: hidden;padding: 3px;">
                <div>密码初始为：123</div>
            </div>
            <table class="grid">
                <tr>
                    <td style="text-align: right;">登录名
                   <input name="loginname" type="text" placeholder="请输入登录名称" class="easyui-validatebox" data-options="required:true" value=""></td>
                    <td style="text-align: right;">姓名
                    <input name="name" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td style="text-align: right;">密码
                    <input name="password" type="password" placeholder="请输入密码" class="easyui-validatebox" data-options="required:true" value="123"></td>
                    <td style="text-align: right;">性别
                        <select name="sex" class="easyui-combobox" data-options="width:158,height:29,editable:false,panelHeight:'auto'">
                            <option value="0" selected="selected">男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">年龄
                 <input type="text" name="age" class="easyui-numberbox"/></td>
                    <td style="text-align: right;">用户类型             
                        <select name="usertype" class="easyui-combobox" data-options="width:158,height:29,editable:false,panelHeight:'auto'">
                            <option value="0">管理员</option>
                            <option value="1" selected="selected">用户</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">部门
                    <select id="organizationId" name="organizationId" style="width: 158px; height: 29px;" class="easyui-combotree" data-options="required:true"></select></td>
                    <td style="text-align: right;">角色
                   <select id="roleIds" name="roleIds" style="width: 158px; height: 29px;"></select></td>
                </tr>
                <tr>
                   <td style="text-align: right;">岗位
                <select id="postId" name="postId" style="width: 158px; height: 29px;"></select></td>
                    <td style="text-align: right;">用户状态                  
                        <select id="status" name="status" class="easyui-combobox" data-options="width:158,height:29,editable:false,panelHeight:'auto'">
                                <option value="0">正常</option>
                                <option value="1">停用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">电话
                        <input type="text" name="phone" class="easyui-numberbox"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>