<%@page contentType="text/html; charset=UTF-8"%>

					  <table border="0" cellpadding="0" style="border-collapse: collapse" width="248" id="table38" background="images/left333.jpg" height="210">
						<tr>
							<td>
							 <table cellpadding="0"  cellspacing="0">
							 <tr>
							 <td>
							 <a href="/news.do?action=FrontNews&search.artype=3">
							 <img border="0" src="images/left221.jpg"  height="45"></a>
							 </td>
							 <td>
							 <a href="/news.do?action=FrontNews&search.artype=4">
							 <img border="0" src="images/left222.jpg"  height="45"></a>
							 </td>
							 <td>
							 <a href="/news.do?action=FrontNews&search.artype=5">
							 <img border="0" src="images/left223.jpg"  height="45"></a>
							 </td>
							 </tr>
							 </table>
							 </td>
						</tr>
						
						<tr>
							<td>
							<table border="0" cellpadding="0" style="border-collapse: collapse;" width="248" id="table39" height="191">
								<tr>
									<td valign="top">
									
										<table id="table40" style="width: 238px; border-collapse: collapse" cellPadding="0" border="0">
										
										
											<s:iterator id="teight" value="%{topEightNews.objectList}">	
								
											<tr>
												<td valign="top">
												<table height="23" cellSpacing="0" cellPadding="0" width="238" align="center" background="images/listbg.jpg" border="0" id="table41">
													<tr>
														<td align="left" width="22" Command="ImageManager">
														<font color="#FFFFFF">
														<span style="font-size: 9pt">
														<img alt="" src="images/jiantou1.gif" border="0" width="5" height="9"></span></font></td>
														<td align="left">
														<font color="#FFFFFF">
														<span style="font-size: 9pt; text-decoration: none">
													    <a href="/news.do?action=newsDetail&search.newsID=<s:property value="#teight.NEWSID"/>"  target="_blank">
													    <s:property value="#teight.TITLE"/></a></span></font><span id="TabSkinContainer34"><span id="TabSkinContainer35"><span id="TabSkinContainer36"><span style="font-size: 9pt; text-decoration: none" id="TabSkinContainer37"><font color="#FFFFFF">…</font></span></span></span></span></td>
													</tr>
												</table>
												</td>
											</tr>
											
									</s:iterator>
											
											
										</table>
									
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
	