package cf.wellod.Intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 登录拦截器
public class LoginIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
        if(request.getSession().getAttribute("eid") == null && !request.getRequestURL().toString().equals(tempContextUrl+"admin/login.html")){
            response.sendRedirect(tempContextUrl+"admin/login.html");
            return false;
        }
        if(request.getSession().getAttribute("eid") != null && request.getRequestURI().equals("/admin/login.html")){
            response.sendRedirect(tempContextUrl+"admin/index.html");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
