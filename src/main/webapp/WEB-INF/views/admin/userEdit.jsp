<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        var roleIds = ${roleIds };
        $('#organizationId').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto',
            value : '${user.organizationId}',
            onClick: function(node){
				$("#postId").combobox({
	                url:'${path }/postinfo/dataGrid', 
	                queryParams:{code:node.pid}    
                });//载入子表
			}
        });

        //加载岗位
        $('#postId').combobox({
            url: '${path }/postinfo/dataGrid?deptid='+${user.organizationId},
            valueField: 'postno',
            textField:'postname',
            required: true,
            panelHeight : 'auto',
            value : '${user.postId}'
        });
        
        $('#roleIds').combotree({
            url : '${path }/role/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto',
            multiple : true,
            required : true,
            cascadeCheck : false,
            value : roleIds
        });

        $('#userEditForm').form({
            url : '${path }/user/edit',
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
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
        $("#sex").val('${user.sex}');
        $("#usertype").val('${user.usertype}');
        $("#status").val('${user.status}');
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="userEditForm" method="post">
            <div class="light-info" style="overflow: hidden;padding: 3px;">
                <div>密码不修改请留空。</div>
            </div>
            <table class="grid">
                <tr>
                    <td style="text-align: right;">登录名</td>
                    <td><input name="id" type="hidden"  value="${user.id}">
                    <input name="loginname" type="text" placeholder="请输入登录名称" class="easyui-validatebox" data-options="required:true" value="${user.loginname}"></td>
                    <td style="text-align: right;">姓名</td>
                    <td><input name="name" type="text" placeholder="请输入姓名" class="easyui-validatebox" data-options="required:true" value="${user.name}"></td>
                </tr>
                <tr>
                    <td style="text-align: right;">密码</td>
                    <td><input type="text" name="password"/></td>
                    <td style="text-align: right;">性别</td>
                    <td><select name="sex" id="sex"  class="easyui-combobox" data-options="width:145,height:29,editable:false,panelHeight:'auto'">
                            <option value="0">男</option>
                            <option value="1">女</option>
                    </select></td>
                </tr>
                <tr>
                    <td style="text-align: right;">年龄</td>
                    <td><input type="text" name="age" value="${user.age}" class="easyui-numberbox"/></td>
                    <td style="text-align: right;">用户类型</td>
                    <td><select id="usertype" name="usertype"  class="easyui-combobox" data-options="width:145,height:29,editable:false,panelHeight:'auto'">
                            <option value="0">管理员</option>
                            <option value="1">用户</option>
                    </select></td>
                </tr>
                <tr>
                    <td style="text-align: right;">部门</td>
                    <td><select id="organizationId" name="organizationId" style="width: 145px; height: 29px;" class="easyui-validatebox" data-options="required:true"></select></td>
                    <td style="text-align: right;">角色</td>
                    <td><input  id="roleIds" name="roleIds" style="width: 145px; height: 29px;"/></td>
                </tr>
                <tr>
                    <td style="text-align: right;">岗位</td>
                    <td><select id="postId" name="postId" style="width: 158px; height: 29px;"></select></td>
                    <td style="text-align: right;">用户状态</td>
                    <td><select id="state" name="status" value="${user.status}" class="easyui-combobox" data-options="width:145,height:29,editable:false,panelHeight:'auto'">
                            <option value="0">正常</option>
                            <option value="1">停用</option>
                    </select></td>
                </tr>
                <tr>
                <td style="text-align: right;">电话</td>
                    <td>
                        <input type="text" name="phone" class="easyui-numberbox" value="${user.phone}"/>
                    </td>
                <td></td>
                <td></td>
                </tr>
            </table>
        </form>
    </div>
</div>