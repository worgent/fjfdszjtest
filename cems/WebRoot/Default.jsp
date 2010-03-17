<%@page contentType="text/html; charset=gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Untitled Page</title>
    <LINK href="SlideMenu/images/SlideMenu.css" type="text/css" rel="stylesheet"/>
    
    <script language="JavaScript" src="SlideMenu/images/menu.js" type="text/javascript"></script>
    <script type="text/javascript">
function lefthide() {
var fr = parent.document.getElementById("sbCont");
    if(fr.style.display==""){
        fr.style.display="none";
		change('outlookside', 'outlookBarExpand')
    }
    else{
        fr.style.display="";
		change('outlookside', 'outlookBarcollapse')
    }

}
function change(id, newClass) {

identity=document.getElementById(id);

identity.className=newClass;

}
 </script>	
</head>
<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
    <form id="form1" runat="server">
   
      <!--------Start Menu---------->
        <!--------End Menu---------->
        <!--------Start Menu---------->
        <!--------End Menu---------->
        <!--------Start Menu---------->
        <!--------End Menu---------->
        <!--------Start Menu---------->
        <table id="TABLE1" runat="server" border="0" cellpadding="0" cellspacing="0" style="height: 592px">
            <tbody>
                <tr>
                    <td align="left"  colspan="3" dir="ltr" style="height: 40px; font-weight: bold; color: blue;"
                        valign="top" bgcolor="#336699">
                        Outlook Style Menu & collapse Side Menu&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
                <tr style="font-size: 7pt; color: #000000; height: 100%">
                    <td id="sbCont" style="width: 10%; height: 91%; background-color: #e7ebf0" valign="top">
                        <!--------Start Menu 1 ---------->
                    <div class="SMMenu" state="0" style="width: 146px; height: 1px">
                        <div class="SMParent" style="width: 138px">
                            <span style="color: white">
                                <a id="HyperLink3"  href="http://www.yahoo.com" Style="color: white">Home</a>
                            </span>
                        </div>
                    </div>
                        <!--------End Menu---------->
                        <!--------Start Menu 2 ---------->
                        <!--------End Menu---------->
                        <!--------Start Menu 3 ---------->
                        <!--------End Menu---------->
                        <!--------Start Menu 4 ---------->
                        <!--------End Menu---------->
                        <!--------Start Menu 5 ---------->
                        <!--------End Menu---------->
                    <div class="SMMenu" state="0" style="width: 146px">
                        <div class="SMParent" onmouseover="Init(this);" style="width: 138px">
                            ÎÒµÄÕ¾µã</div>
                        <div class="SMChildrenBox">
                            <div class="subMenu" state="0">
                                <span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000">
                                    <br />
                                    <img class="SMChildImage" src="SlideMenu/Icon/user1_mobilephone.png" /><br />
                                     <a ID="HyperLink1"  href="http://www.yahoo.com">Code Project</a>
                                </span>
                                <br />
                                <span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000">
                                    <br />
                                    <img class="SMChildImage" src="SlideMenu/Icon/help_earth.png" /><br />
                                    <asp:HyperLink ID="HyperLink2" runat="server" NavigateUrl="http://www.ASP.net" >Asp.net</asp:HyperLink>
                                </span>
                                <br />
                                <span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000">
                                    <br />
                                    <img class="SMChildImage" src="SlideMenu/Icon/help2.png" /><br />
                                    <asp:HyperLink ID="HyperLink4" runat="server" NavigateUrl="UnderConstruction.aspx"
                                        > My Drive</asp:HyperLink>
                                    <br />
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="SMMenu" state="0" style="width: 146px">
                        <div class="SMParent" onmouseover="Init(this);" style="width: 138px">
                            Contacts
                        </div>
                        <div class="SMChildrenBox">
                            <div class="subMenu" state="0">
                                <span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000">
                                    <br />
                                    <img class="SMChildImage" src="SlideMenu/Icon/help.png" /><br />
                                    <asp:HyperLink ID="HyperLink7" runat="server" NavigateUrl="http://www.yahoo.com"
                                        >New Customer</asp:HyperLink>
                                </span>
                                <br />
                                <span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000">
                                    <br />
                                    <img class="SMChildImage" src="SlideMenu/icon/id_card_ok.png" /><br />
                                    <asp:HyperLink ID="HyperLink8" runat="server" NavigateUrl="http://www.codeproject.com"
                                        > Customer Contacts</asp:HyperLink>
                                </span>
                                <br />
                            </div>
                        </div>
                    </div>
                    <div class="SMMenu" state="0" style="width: 146px">
                        <div class="SMParent" onmouseover="Init(this);" style="width: 138px">
                            Settings
                        </div>
                        <div class="SMChildrenBox">
                            <div class="subMenu" state="0">
                                <span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000">
                                    <br />
                                    <img class="SMChildImage" src="SlideMenu/Icon/help2.png" /><br />
                                    <asp:HyperLink ID="HyperLink5" runat="server" NavigateUrl="http://www.yahoo.com"
                                        >General Settings</asp:HyperLink>
                                </span>
                                <br />
                                <span class="SMChild" classout="SMChild" classover="SMChild" style="color: #000000">
                                    <br />
                                    <img class="SMChildImage" src="SlideMenu/Icon/user1_mobilephone.png" /><br />
                                    <asp:HyperLink ID="HyperLink6" runat="server" NavigateUrl="http://www.yahoo.com" > Admin Settings</asp:HyperLink>
                                </span>
                                <br />
                            </div>
                        </div>
                    </div>
                    </td>
                    <td align="center" background="images/collapse_side_bg.gif" style="width: 6px;
                        height: 91%; background-color: #e7ebf0" valign="middle">
                        <img id="outlookside" class="outlookBarCollapse" height="45" onclick="lefthide(this);"
                            src="images/slidbtn.JPG" style="cursor: pointer" width="5" /></td>
                    <td style="width: 100%; height: 91%; font-weight: bold; font-family: Tahoma;" valign="top">
                        <strong><span style="font-size: 9.7pt; font-family: Verdana; background-color: #f8fbfd">
                            <table border="0" cellpadding="0" cellspacing="0" style="width: 100%;
                                height: 160px">
                                <tr>
                                    <td align="center" colspan="2" style="height: 29px">
                                        &nbsp;Home Youssef Saad -- Software Developer
                                        <br />
                                        - c#/VB.NEt/Web Designer&nbsp; - Egypt - Cairo NileNetworks Co. | yousfysaady@gmail.com</td>
                                </tr>
                            </table>
                        </span></strong>&nbsp;&nbsp;<br />
                        <br />
                     </td>
                </tr>
            </tbody>
        </table>
      
    </form>
</body>
</html>
