/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TipoUsuario;
import model.Usuario;

/**
 * @author lber
 */

@WebFilter(urlPatterns = "/logado/funcionario/*")
public class FilterAreaFun implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        Usuario u = null; 
        
        HttpSession sesison = ((HttpServletRequest) req).getSession(false);
        
        if (sesison != null) {
            u = (Usuario) sesison.getAttribute("usuario-logado");
        }
        
        if (u.getTipo() == TipoUsuario.administrador) { 
            String contextPath = ((HttpServletRequest) req).getContextPath();
            ((HttpServletResponse) res).sendRedirect(contextPath + "/logado/admin/home.xhtml");
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {}
}
