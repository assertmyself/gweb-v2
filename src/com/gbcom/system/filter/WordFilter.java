package com.gbcom.system.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * 非法字符过滤，自定义MyRequest 继承HttpServletRequestWrapper
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-11,下午02:22:18
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.system.filter.WordFilter
 */
public class WordFilter implements Filter {
	@Override
    public void init(FilterConfig config) throws ServletException {
    }
	@Override
    public void destroy() {
    }

	@Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        //传入自己定义的ServletRequest对象
        MyRequest req = new MyRequest((HttpServletRequest) request);
        chain.doFilter(req, response);
    }

    //请求参数都是通过getParameter()和getParameterValues()方法得到的，复写这2个方法
    //HttpServletRequestWrapper是ServletRequest的实现类
    public class MyRequest extends HttpServletRequestWrapper {

        private String[] words = {"<script>", "</script>", "%3Cscript%3E", "%3C/script%3E"};

        /**
         * 过滤操作doFilter
         * @param content String
         * @return String
         */
        public String doFilter(String content) {
            if (content != null) {
                for (int i = 0; i < words.length; i++) {
                    if (content.indexOf(words[i]) != -1) {
                        content = content.replace(words[i], "*");
                    }
                }
            }
            return content;
        }

        /**
         * MyRequest
         * @param request HttpServletRequest
         */
        public MyRequest(HttpServletRequest request) {
            super(request);
        }

        @Override
        public String getParameter(String name) {
            String content = super.getParameter(name);
            return doFilter(content);
        }

        @Override
        public String[] getParameterValues(String name) {
            String[] temp = super.getParameterValues(name);
            if (temp != null) {
                String[] contents = new String[temp.length];
                for (int i = 0; i < temp.length; i++) {
                    String content = temp[i];
                    contents[i] = doFilter(content);
                }
                return contents;
            } else {
                return null;
            }
        }
    }
}


