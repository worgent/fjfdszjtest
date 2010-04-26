<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
	<body>
		<!-- 悬赏问题-->
		<form name="rewardform" method="post" action="reward.do">
			<div>
				<div style="margin-top:9px">
					<div>
					<s:hidden name="action" value="solvehide"></s:hidden>
					<s:textfield name="search.ptitle" id="searchptitle" value="%{ptitlehide}" label="关键字"></s:textfield>
					<s:submit value="提问"></s:submit>
					</div>
				</div>
			</div>
		</form>
		<div id="s_main">
			<!-- 页面导航位置 -->
			<div>
				<a href="/selfconfig/reward.do">悬赏首页</a><span>&gt;</span>提问
			</div>
			<!-- 揭榜提示信息 -->
			<div>
				<div>
					<div>
						提问
					</div>
					<div>
						<div>
							<div>
								请查看下面的问题是否解决了您的疑问(搜索条)：
							</div>
					        <!-- 叠代显示提示信息 -->
							   <div>
								<table style="height: 90px; width: 990px;">
									<tr>
										<td>标题</td>
										<td width="20%" align="center">回复</td>
										<td width="20%" align="center">提问时间</td>
									</tr>
									<s:iterator id="rewardsolveList" value="%{pageList.objectList}">
									<tr>
										<td>
										<a href="<%=path%>/selfconfig/reward.do?action=solvequestion&search.pid=<s:property value="#rewardsolveList.ID" />" target="_self">
										<s:property value="#rewardsolveList.TITLE" />
										</a>
										</td>
										<td width="20%" align="center"><s:property value="#rewardsolveList.CONTENT"/></td>
										<td width="20%" align="center"><s:property value="#rewardsolveList.SCREATTIME"/></td>
									</tr>
									</s:iterator>
									<tr class="bgColor3">
										<td colspan="4">
											分页:
											<qzgf:pages value="%{pageList.pages}" />
										</td>
									</tr>
					            </table>
							</div>
						</div>
						<!-- 引入树型结构 -->
						<script type='text/javascript' src='<%=basePath%>js/SimpleHtmlTree.js'></script>
						<script type='text/javascript' src='<%=basePath%>js/SimpleHtmlAjax.js'></script>	
						<!-- 采用AJAX取得数据写入id为"SolvedResultFrame"  data:"action=ajaxsolvejson&search.ptitle=" + para,-->
						<script type="text/javascript">
						var searchTitle='XXX';
						//<!--
						function getSolvedResultFrame(){  
						    			$.ajax({
										    url: "reward.do",
										    data:"action=ajaxsolvejson&search.ptitle="+$("#searchptitle").val(), 
										    type: "post",
										    dataType:"json",
										    timeout: 2000,
										    error: function(XMLHttpRequest, textStatus, errorThrown){//返回失败后
										   		  $("#load").show();
										   		 alert('加载失败');
										    },
										    complete :function(){
										    	$("#load").hide();
										    },//AJAX请求完成时隐藏loading提示
										    
								            success: function(msg){//msg为返回的数据，在这里做数据绑定
												$(".repeat").remove();		                
								                $.each(msg, function(i, n){
								                    var row = $("#templaterepeat").clone();
								                    row.removeClass("#templaterepeat");
								                    row.addClass("repeat");
								                    row.find("#question").text(n.ID);
								                    row.find("#solve").text(n.CONTENT);
								                    row.attr("id","ready");//改变绑定好数据的行的id
								                    row.appendTo("#datas");//添加到模板的容器中
								                });
								             }
										});
						}
				
//商家分类树型结构						
function treesort(){ 
    if ($("#dialogalert").length == 0) { 
        $("body").append(' <div id="dialogalert"><div id="treepanel1"></div></div>'); 
        $("#dialogalert").dialog({ 
            autoOpen: false, 
            title: '消息框', 
            modal: true, 
            resizable:false, 
            overlay: { 
                opacity: 0.5, 
                background: "black" 
            }, 
            buttons: { 
                "确定": function(){ 
                    $(this).dialog("close"); 
                } 
            } 
        }); 
    } 
    $("#dialogalert").html("test"); 
    $("#dialogalert").dialog("open"); 
} 


      /*
      	    //定义树型对象 
        var simpleTree = new SimpleHtmlTree("treepanel1","<%=basePath%>js/images/mn/","<%=basePath%>js/images/style0/");
        //新增根结点
        var noderoot=simpleTree.createTreeNode("0","商家类别","D");//参数1：节点ID，参数2：节点文本  
        simpleTree.add(noderoot);
        //动态加载xml文件生成树形结构
  		simpleTree.loadXmlFromFile("<%=basePath%>selfconfig/mapcard.do?action=getTree",true,noderoot,"",load,false);

  		function load(){alert("auto loading...");}
     */						
						
						//getSolvedResultFrame();
				//-->
				</script>
						<!-- 让用户继续提问选项，及一些隐藏域信息,转到提问界面 -->
						<div>
							<div>
								如果没有解决您的疑问，请继续提问：
							</div>
							<form method="post" action="/selfconfig/reward.do?action=insertreward">
								<table>
			 					<tr>
							      <td colspan="2" style="font-size:14px;"><b>2.如果您没有找到相关的答案，请继续提问</b></td>
							    </tr>
							 					
							    <tr>
							      <td style="font-size:14px;">您的悬赏标题：</td>
							      <td><s:textfield name="search.ptitle" id="searchptitle" value="%{ptitlehide}" label="关键字"></s:textfield></td>
							    </tr><tr>
							      <td style="font-size:14px;">悬赏的详细描述：</td>
							      <td align="left"><div style=""><textarea name="search.pcontent" cols="55" rows="7" id="bodytext"></textarea></div><div id="texts" style="height:25px;padding-lef:100px;position:relative;"></div></td>
							    </tr>
							    <tr>
							      <td style="font-size:14px;" height="25">悬赏类别：</td>
							      <td>
							      	<select id="pbelong" name="search.pbelong" onchange="changeCombo('ptype','pbelong','combomenu')">
									</select>
									<select id="ptype" name="search.ptype">
									</select>
							      </td>
							    </tr>
							    <tr>
							    	<td>位置标点：</td>
							    	<td> &nbsp;&nbsp;(<span id="markFlag">未标注</span>)&nbsp;&nbsp;给悬赏标上位置？</td>
							    </tr>
							    <tr>
							      <td style="font-size:14px;">悬赏积分：</td>
							      <td>
							        <input type="text" name="search.pintegral" id="bindpoints" size="4" value="5" maxlength="4" >分	您目前剩余<font size="0" color="red">152</font> 个积分			       					        
							      </td>
							    </tr>			  
							    <tr>
							      <td style="font-size:14px;">匿名设定：</td>
							      <td><input type="checkbox" name="search.puserid" id="hiddenflag" value="Y"><label for="hiddenflag">您必须为此付出10个积分</label></td>
							    </tr>
							    <tr><td></td><td><s:submit  id="subBtn" value=" 提交 "></s:submit>&nbsp;&nbsp;<s:reset id="resetBtn" value="重置"></s:reset></td></tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
			
<script type="text/javascript">
//2009-12-01
//省,地,市连动
//参数说明:chCombo变化控件的id      被动方
//		  srcCombo引起变化的控件id  主动方
//		  action事件类型
function changeCombo(chCombo,srcCombo,action)
{
	var cdsales=new ActiveXObject("Microsoft.XMLDOM"); //创建XmlDom对象
    cdsales.async=false; //使用异步加载
    var parmid='';
    if(srcCombo!='')
    {
      parmid=document.getElementById(srcCombo).value
    }else
    {
    	parmid=0;
    }
    cdsales.load("/selfconfig/mapcardsort.do?action="+action+"&search.pbelongid="+parmid);
     var bi;
     if(cdsales.documentElement!=null)
         bi=cdsales.documentElement.selectNodes("NODE");
    if(bi!=null&&bi.length>0)
    {
       for(var i=0;i<bi.length;i++){     
                document.getElementById(chCombo).length=i+1; 
	   			document.getElementById(chCombo).options[i].value = bi[i].selectSingleNode("ID").text;//隐藏值
	   			document.getElementById(chCombo).options[i].text =  bi[i].selectSingleNode("SORTNAME").text;//显示值
       }
    }else{
       document.getElementById(chCombo).options.length = 1;   
       document.getElementById(chCombo).options[0].value ="0";//隐藏值
	   document.getElementById(chCombo).options[0].text = "请选择";//显示值
    }
}
//初始化信息
changeCombo('pbelong','','combomenu');
//document.getElementById('pprovince').options[12].selected;
//默认福建省
/*
var tmp=<s:property value="%{user.PROVINCE}"/>;
if(tmp=="")
{
 tmp=350000;
}
document.getElementById('pprovince').value=tmp;
*/
changeCombo('ptype','pbelong','combomenu');		
</script>		
	</body>
</html>