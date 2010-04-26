package com.qzgf.utils.taglib;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.components.Component;
import com.opensymphony.xwork2.util.ValueStack;
import com.qzgf.context.Pages;

/**
 * 分页逻辑Bean
 * 继承Component类是为了从Struts2中的ValueStack中获得相对应的值
 * @author lsr
 *
 */
public class Page extends Component {
	private static final Log logger = LogFactory.getLog(Page.class);

	protected String styleClass = ""; //样式
	protected String argPage = "page"; //当前页
	protected String argTotal = "total"; //总页数
	protected int pageSep = 20; //每行大小~~与内部核心不致，com.qzgf.context.Pages.perPageNum
	protected String javaScript = ""; //要返回调用的JavaScript
	private String value; //%{pageList.pages}

	public Page(ValueStack arg0) {
		super(arg0);
	}

	public boolean start(Writer writer) {
		boolean result = super.start(writer);

		if (value == null) {
			value = "top";
		} else if (altSyntax()) { //是否使用alt键语法
			if (value.startsWith("%{") && value.endsWith("}")) {
				value = value.substring(2, value.length() - 1);
			}
		}

		//从ValueStack中取出数值
		Pages pages = (Pages) this.getStack().findValue(value);

		// 一行显示页数,因为是用类似google的1,2,3,4,...这样显示
		int pagesep = this.pageSep;
		// 总行数
		int allpagesep = (int) Math.ceil((pages.getAllPage() + pagesep - 1)
				/ pagesep);
		// 当前行数
		int cpagesep = (int) Math.ceil((pages.getCpage() + pagesep - 1)
				/ pagesep);
		// 当前一行显示页数
		int cnum;
		if (pages.getAllPage() > 0) {
			// 在页中
			if (cpagesep != allpagesep) {
				cnum = pagesep;
			}
			// 页末
			else {
				cnum = pages.getAllPage() % pagesep;
				// 正好整除
				if (cnum == 0) {
					cnum = pagesep;
				}
			}
		} else {
			cnum = 0;
		}
		// System.out.println("cpage:" + pages.getCpage() + " cpagesep:" +
		// cpagesep + " allpagesep:" + allpagesep);

		String fileName = pages.getFileName();

		StringBuffer sb = new StringBuffer();

		// 不使用URL Rewrite
		if (fileName.indexOf("?") == -1) {
			fileName = fileName + "?";
		} else {
			if (!fileName.endsWith("&")) {
				fileName = fileName + "&";
			}
		}

		if (StringUtils.isBlank(this.javaScript)) {

			/**
			 * previous butten ,append to <a href='xxx.lt?page=x&t=x'><img></a>
			 */
			sb.append("<span");

			if (StringUtils.isNotBlank(this.styleClass)) {
				sb.append(" class=\"");
				sb.append(this.styleClass);
				sb.append("\"");
			}
			sb.append(">");

			if (cpagesep > 1) {
				sb.append("<a href=\"");
				sb.append(fileName);
				sb.append(this.getArgPage());
				sb.append("=1&");
				sb.append(this.argTotal);
				sb.append("=");
				sb.append(pages.getTotalNum());
				sb.append("\">");
				// sb.append("|&lt;");
				sb.append("&laquo;");
				sb.append("</a>");

				sb.append(" <a href=\"");
				sb.append(fileName);
				sb.append(this.getArgPage());
				sb.append("=");
				// 往前一行
				int previous;
				if (pages.getCpage() <= 1) {
					previous = 1;
				} else {
					previous = pages.getCpage() - 1;
				}
				sb.append(previous);
				// sb.append("&total=");
				sb.append("&");
				sb.append(this.argTotal);
				sb.append("=");
				sb.append(pages.getTotalNum());
				sb.append("\">");
				// sb.append("&lt;");
				sb.append("&#8249;");
				sb.append("</a>");

			}

			/**
			 * middle butten ,append to <a href='xxx.lt?page=x&t=x'><img></a>
			 */
			for (int i = 0; i < cnum; i++) {
				sb.append(" <a href=\"");

				sb.append(fileName);
				sb.append(this.getArgPage());
				sb.append("=");

				sb.append(((i + 1) + ((cpagesep - 1) * pagesep)));
				sb.append("&");
				sb.append(this.argTotal);
				sb.append("=");
				sb.append(pages.getTotalNum());
				sb.append("\">");
				if (pages.getCpage() == (i + 1) + ((cpagesep - 1) * pagesep)) {
					sb.append("<strong>");
					sb.append((i + 1) + ((cpagesep - 1) * pagesep));
					sb.append("</strong>");
				} else {
					sb.append(((i + 1) + ((cpagesep - 1) * pagesep)));
				}
				sb.append("</a>");
			}
			/**
			 * next butten ,append to <a href='xxx.lt?page=x&t=x'><img></a>
			 */

			if (cpagesep < allpagesep) {

				sb.append(" <a href=\"");
				// sb.append(pages.getFileName());
				sb.append(fileName);
				sb.append(this.getArgPage());
				sb.append("=");
				// sb.append("page=");
				int next;
				if (pages.getCpage() >= pages.getAllPage()) {
					next = (int) pages.getAllPage();
				} else {
					next = pages.getCpage() + 1;
				}
				sb.append(next);
				sb.append("&");
				sb.append(this.argTotal);
				sb.append("=");
				// sb.append("&t=");
				sb.append(pages.getTotalNum());
				sb.append("\">");
				// sb.append("&gt;");
				sb.append("&#8250;");
				sb.append("</a> ");

				sb.append(" <a href=\"");
				// sb.append(pages.getFileName());
				sb.append(fileName);
				sb.append(this.getArgPage());
				sb.append("=");
				// sb.append("page=");
				sb.append(pages.getAllPage());
				sb.append("&");
				sb.append(this.argTotal);
				sb.append("=");
				// sb.append("&t=");
				sb.append(pages.getTotalNum());
				sb.append("\">");
				// sb.append("&gt;|");
				sb.append("&raquo;");
				sb.append("</a>");
			}
			sb.append("</span>");

		} else {

			/**
			 * previous butten ,append to <a href='xxx.lt?page=x&t=x'><img></a>
			 */
			sb.append("<span");

			if (StringUtils.isNotBlank(this.styleClass)) {
				sb.append(" class=\"");
				sb.append(this.styleClass);
				sb.append("\"");
			}
			sb.append(">");

			if (cpagesep > 1) {
				sb.append("<a href=\"javascript:;\" onclick=\"");
				sb.append(this.javaScript);
				sb.append("('");
				sb.append(fileName);
				sb.append(this.getArgPage());
				sb.append("=1&");
				sb.append(this.argTotal);
				sb.append("=");
				sb.append(pages.getTotalNum());
				sb.append("');\">");
				// sb.append("|&lt;");
				sb.append("&laquo;");
				sb.append("</a>");

				// sb.append(" <a href=\"");
				sb.append(" <a href=\"javascript:;\" onclick=\"");
				sb.append(this.javaScript);
				sb.append("('");

				sb.append(fileName);
				sb.append(this.getArgPage());
				sb.append("=");
				// 往前一行
				int previous;
				if (pages.getCpage() <= 1) {
					previous = 1;
				} else {
					previous = pages.getCpage() - 1;
				}
				sb.append(previous);
				// sb.append("&total=");
				sb.append("&");
				sb.append(this.argTotal);
				sb.append("=");
				sb.append(pages.getTotalNum());
				// sb.append("\">");
				sb.append("');\">");
				// sb.append("&lt;");
				sb.append("&#8249;");
				sb.append("</a>");

			}

			/**
			 * middle butten ,append to <a href='xxx.lt?page=x&t=x'><img></a>
			 */
			for (int i = 0; i < cnum; i++) {
				// sb.append(" <a href=\"");
				sb.append(" <a href=\"javascript:;\" onclick=\"");
				sb.append(this.javaScript);
				sb.append("('");
				sb.append(fileName);
				sb.append(this.getArgPage());
				sb.append("=");

				sb.append(((i + 1) + ((cpagesep - 1) * pagesep)));
				sb.append("&");
				sb.append(this.argTotal);
				sb.append("=");
				sb.append(pages.getTotalNum());
				// sb.append("\">");
				sb.append("');\">");
				if (pages.getCpage() == (i + 1) + ((cpagesep - 1) * pagesep)) {
					sb.append("<strong>");
					sb.append((i + 1) + ((cpagesep - 1) * pagesep));
					sb.append("</strong>");
				} else {
					sb.append(((i + 1) + ((cpagesep - 1) * pagesep)));
				}
				sb.append("</a>");
			}
			/**
			 * next butten ,append to <a href='xxx.lt?page=x&t=x'><img></a>
			 */

			if (cpagesep < allpagesep) {

				// sb.append(" <a href=\"");
				// sb.append(pages.getFileName());
				sb.append(" <a href=\"javascript:;\" onclick=\"");
				sb.append(this.javaScript);
				sb.append("('");

				sb.append(fileName);
				sb.append(this.getArgPage());
				sb.append("=");
				// sb.append("page=");
				int next;
				if (pages.getCpage() >= pages.getAllPage()) {
					next = (int) pages.getAllPage();
				} else {
					next = pages.getCpage() + 1;
				}
				sb.append(next);
				sb.append("&");
				sb.append(this.argTotal);
				sb.append("=");
				// sb.append("&t=");
				sb.append(pages.getTotalNum());
				// sb.append("\">");
				sb.append("');\">");

				// sb.append("&gt;");
				sb.append("&#8250;");
				sb.append("</a> ");

				// sb.append("<a href=\"");

				// sb.append(pages.getFileName());
				sb.append(" <a href=\"javascript:;\" onclick=\"");
				sb.append(this.javaScript);
				sb.append("('");

				sb.append(fileName);
				sb.append(this.getArgPage());
				sb.append("=");
				// sb.append("page=");
				sb.append(pages.getAllPage());
				sb.append("&");
				sb.append(this.argTotal);
				sb.append("=");
				// sb.append("&t=");
				sb.append(pages.getTotalNum());
				// sb.append("\">");
				sb.append("');\">");
				// sb.append("&gt;|");
				sb.append("&raquo;");
				sb.append("</a>");
			}
			sb.append("</span>");

		}

		try {
			writer.write(sb.toString());
		} catch (IOException e) {
			logger.error(e);
		}

		return result;
	}

	public String replacePage(String txt, int page) {
		return txt.replaceAll("\\{page\\}", String.valueOf(page));
	}

	public String replaceTotal(String txt, long total) {
		return txt.replaceAll("\\{total\\}", String.valueOf(total));
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getArgPage() {
		return argPage;
	}

	public void setArgPage(String argPage) {
		this.argPage = argPage;
	}

	public String getArgTotal() {
		return argTotal;
	}

	public void setArgTotal(String argTotal) {
		this.argTotal = argTotal;
	}

	public int getPageSep() {
		return pageSep;
	}

	public void setPageSep(int pageSep) {
		this.pageSep = pageSep;
	}

	public String getJavaScript() {
		return javaScript;
	}

	public void setJavaScript(String javaScript) {
		this.javaScript = javaScript;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
