/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.45
 * Generated at: 2020-01-07 08:15:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.pages.systemlog;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class systemlog_005fview_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<div>\r\n");
      out.write("\t<div class=\"panel-header panel-header-noborder\"\r\n");
      out.write("\t\tstyle=\"width: 100% px; height: 40px\">\r\n");
      out.write("\t\t<div\r\n");
      out.write("\t\t\tstyle=\"display: inline-block; font-size: 20px; height: 40px; padding: 2px 2px; margin-left: 5px;\">\r\n");
      out.write("\t\t\t<span id=\"paneltile\" style=\"font-size: 16px; margin-left: 6px\"></span>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"pull-right\" style=\"padding-right: 20px; margin-top: 3px;\">\r\n");
      out.write("\t\t\t<a class=\"mbtn mbtn-white icon_next\"\r\n");
      out.write("\t\t\t\tstyle=\"width: 80px; height: 28px; background-image: url('../../images/icons/icon_prev.png');\"\r\n");
      out.write("\t\t\t\tonclick=\"domoveUp()\">上一条</a> <a class=\"mbtn mbtn-white icon_next\"\r\n");
      out.write("\t\t\t\tstyle=\"width: 80px; height: 28px; background-image: url('../../images/icons/icon_next.png');\"\r\n");
      out.write("\t\t\t\tonclick=\"domovedown()\">下一条</a> <a class=\"mbtn btn-blue\"\r\n");
      out.write("\t\t\t\tstyle=\"width: 70px; height: 28px; background-image: url('../../images/icons/delete-white.png');\"\r\n");
      out.write("\t\t\t\tonclick=\"closeview()\">关闭</a>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<form id=\"entityviewForm\" class=\"customForm\" method=\"post\">\r\n");
      out.write("\t\t<table class=\"table-width\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td class=\"t\" style=\"width: 80px;\">用户：</td>\r\n");
      out.write("\t\t\t\t<td class=\"tsblue\" style=\"width: 120px;\"><span\r\n");
      out.write("\t\t\t\t\tname=\"userAccount_view\" id=\"userAccount_view\"></span></td>\r\n");
      out.write("\t\t\t\t<td class=\"t\" style=\"width: 80px;\">操作时间：</td>\r\n");
      out.write("\t\t\t\t<td class=\"tsblue\" style=\"width: 120px;\"><span name=\"operTimeStr_view\"\r\n");
      out.write("\t\t\t\t\tid=\"operTimeStr_view\" style=\"width: 120px;\"></span></td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<td class=\"t\" style=\"width: 80px;\">IP：</td>\r\n");
      out.write("\t\t\t\t<td class=\"tsblue\" style=\"width: 120px;\"><span name=\"userIP_view\"\r\n");
      out.write("\t\t\t\t\tid=\"userIP_view\" style=\"width: 120px;\"></span></td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td class=\"t\">操作内容</td>\r\n");
      out.write("\t\t\t\t<td class=\"tsblue\" ><span name=\"description_view\"\r\n");
      out.write("\t\t\t\t\tid=\"description_view\" ></span></td>\r\n");
      out.write("\t\t\t\t<td class=\"t\">耗时(ms)：</td>\r\n");
      out.write("\t\t\t\t<td class=\"tsblue\"><span name=\"exeTime_view\" id=\"exeTime_view\"></span></td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<td class=\"t\">结果：</td>\r\n");
      out.write("\t\t\t\t<td class=\"tsblue\"><span name=\"sucessStr_view\" id=\"sucessStr_view\"></span></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td class=\"t\">请求参数</td>\r\n");
      out.write("\t\t\t\t<td class=\"tsblue\" colspan=\"5\"><span name=\"arguments_view\"\r\n");
      out.write("\t\t\t\t\tid=\"arguments_view\" style=\"width: 500px;\"></span></td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td class=\"t\">返回结果</td>\r\n");
      out.write("\t\t\t\t<td class=\"tsblue\" colspan=\"5\"><span name=\"returnObject_view\"\r\n");
      out.write("\t\t\t\t\tid=\"returnObject_view\" style=\"width: 500px;\"></span></td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</form>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
