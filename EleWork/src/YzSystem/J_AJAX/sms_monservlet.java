package YzSystem.J_AJAX;

import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class sms_monservlet extends HttpServlet {

	  /**
	   * Updates Cart, and outputs XML representation of contents
	   */
	  public void doPost(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {

	    String action = req.getParameter("CHECK_SMS"); 
	    String resultXml="0";
	    
	    if (action.equals("1")) {
	      resultXml=Frmsms_submit.GetBillNoAudit();
	    }
	    res.setContentType("text/xml");
	    res.getWriter().write(resultXml);
	  }

	  public void doGet(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException {
	    // Bounce to post, for debugging use
	    // Hit this servlet directly from the browser to see XML
	    doPost(req,res);
	  }
	}
