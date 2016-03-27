package filtro;

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

import enums.Cargos;
import vo.DocenteVO;

@WebFilter(filterName="FiltroAdmin")
public class FiltroAdmin implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		DocenteVO usuario = (DocenteVO) session.getAttribute("usuario");
		/*if(usuario != null && (usuario.getCargo().equals(Cargos.COORDENADOR) || usuario.getCargo().equals(Cargos.CHEFE_DEPARTAMENTO))){
			chain.doFilter(request, response);
		}else{
			redireciona("perfil.xhtml", response);
		}*/
		if(usuario == null){ redireciona("login.xhtml", response); return;}
		if (usuario.getCargo() != null && (usuario.getCargo().equals(Cargos.COORDENADOR) || usuario.getCargo().equals(Cargos.CHEFE_DEPARTAMENTO))) {
			if(req.getRequestURI().endsWith("cursos.xhtml") && !usuario.getCargo().equals(Cargos.CHEFE_DEPARTAMENTO)){
				redireciona("usuario/perfil.xhtml", response);
				return;
			}
			chain.doFilter(request, response);
		} else {
			redireciona("usuario/perfil.xhtml", response);
			return;
		}
	}

	@Override
	public void destroy() {
		
	}
	
	private void redireciona(String url, ServletResponse response) throws IOException{
		((HttpServletResponse) response).sendRedirect("/encargo-dist/"+url);
	}
}
