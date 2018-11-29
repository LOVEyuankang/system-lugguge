<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <style type="text/css" media="print">
    @page 
    {
        size:  auto;   /* auto is the initial value */
        margin: 0mm;  /* this affects the margin in the printer settings */
    }

    html
    {
        background-color: #FFFFFF; 
        margin: 0px;  /* this affects the margin on the html before sending to printer */
    }

    </style>
<!-- [jQuery] -->
<script type="text/javascript" src="${staticPath }/static/easyui/jquery.min.js" charset="utf-8"></script>
    
<script type="text/javascript">
$(function(){
var data="";
  if(window.localStorage){
    data=localStorage.getItem('print_data');
    data=JSON.parse(data);
  }else{
  }
  var flightno="",xl_total="",cl_total="",time="2016-07-09 15:30:00",map="";
  if(data["flightno"]){
    flightno=data["flightno"];
  }
  if(data["xl_total"]){
    xl_total=data["xl_total"];
  }
  if(data["cl_total"]){
    cl_total=data["cl_total"];
  }
  if(data["time"]){
    time=data["time"];
  }
  if(data["map"]){
    map=data["map"];
    var str="";
    if(map!=""){
      for(var key in map){
        var list=map[key];
           str+="<div style='text-align:center;'><h1>行李装车报表<h1></div>"+
                "<div style='text-align:center;'><table style='width:90%;margin-left:5%;'>"+
                "<tr>"+
                "<td>航班号:<u>"+flightno+"</u></td>"+
                "<td>行李总数:<u>"+xl_total+"</u></td>"+
                "<td>装车总数:<u>"+cl_total+"</u></td>"+
                "<td>当前拖车:<u>"+(key=='空'?'&nbsp;&nbsp;':key)+"</u></td>"+
                "<td>装车时间:<u>"+time+"</u></td>"+
                "</tr>"+
                "</table>"+"</div>"+
                //行李信息
                "<div style='text-align:center;'>"+
                "<table style='width:90%;margin-left:5%;margin-top:20px;'>";
           var qsize=parseInt(list.length/4);
           var modsize=list.length%4;
           for(var i=0;i<qsize;i++){
              str+="<tr>"+
                   "<td style='border:solid 2px #000000;width:25%;'>"+list[i*4].bgcode+"</td>"+
                   "<td style='border:solid 2px #000000;width:25%;'>"+list[i*4+1].bgcode+"</td>"+
                   "<td style='border:solid 2px #000000;width:25%;'>"+list[i*4+2].bgcode+"</td>"+
                   "<td style='border:solid 2px #000000;width:25%;'>"+list[i*4+3].bgcode+"</td>"+
                   "</tr>";
           }   
           if(modsize>0){
             if(modsize==1){
              str+="<tr>"+
                   "<td style='border:solid 2px #000000;width:25%;'>"+list[list.length-1].bgcode+"</td>"+
                   "<td style='border:solid 2px #000000;width:25%;'> </td>"+
                   "<td style='border:solid 2px #000000;width:25%;'> </td>"+
                   "<td style='border:solid 2px #000000;width:25%;'> </td>"+
                   "</tr>";
             }
             if(modsize==2){
              str+="<tr>"+
                   "<td style='border:solid 2px #000000;width:25%;'>"+list[list.length-1].bgcode+"</td>"+
                   "<td style='border:solid 2px #000000;width:25%;'>"+list[list.length-2].bgcode+"</td>"+
                   "<td style='border:solid 2px #000000;width:25%;'> </td>"+
                   "<td style='border:solid 2px #000000;width:25%;'> </td>"+
                   "</tr>";          
             }
             if(modsize==3){
              str+="<tr>"+
                   "<td style='border:solid 2px #000000;width:25%;'>"+list[list.length-1].bgcode+"</td>"+
                   "<td style='border:solid 2px #000000;width:25%;'>"+list[list.length-2].bgcode+"</td>"+
                   "<td style='border:solid 2px #000000;width:25%;'>"+list[list.length-3].bgcode+"</td>"+
                   "<td style='border:solid 2px #000000;width:25%;'> </td>"+
                   "</tr>";          
             }
           }  
           str+="</table>"+
                "</div>"+
                //签字
                "<div style='text-align:center;'>"+
                "<table style='width:80%;margin-left:5%;margin-top:20px;'>"+
                "<tr>"+
                "<td style='width:33%;'>查询员:<hr style='border:none;border-top:1px solid #000000;width:45%;margin-left:65%;margin-top:0.5%;'></hr></td>"+
                "<td style='width:33%;'>分拣员:<hr style='border:none;border-top:1px solid #000000;width:45%;margin-left:65%;margin-top:0.5%;'></hr></td>"+
                "<td style='width:33%;'>班长:<hr style='border:none;border-top:1px solid #000000;width:45%;margin-left:65%;margin-top:0.5%;'></hr></td>"+
                "</tr>"+
                "</table>"+
                "</div>"+
                //换页
                "<div style='page-break-after:always;'></div>";  
      }
             document.getElementById("print").innerHTML=str; 
    }
  }
});

function perview(){
bdhtml=window.document.body.innerHTML; 

sprnstr="<!--startprint-->"; //开始打印标识字符串有17个字符

eprnstr="<!--endprint-->"; //结束打印标识字符串

prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17); //从开始打印标识之后的内容

prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr)); //截取开始标识和结束标识之间的内容

window.document.body.innerHTML=prnhtml; //把需要打印的指定内容赋给body.innerHTML

window.print(); //调用浏览器的打印功能打印指定区域

window.document.body.innerHTML=bdhtml; // 最后还原页面

}
</script>   
  </head>
  <body>
    <div><button style="width:100;" onclick="perview()">打印</button></div>
	<!--startprint-->
	<div id="print">
	</div>
	<!--endprint-->
  </body>
</html>
