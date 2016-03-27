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

import vo.DocenteVO;

@WebFilter(filterName="FiltroCredenciais")
public class FiltroCredenciais implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		DocenteVO usuario = (DocenteVO) session.getAttribute("usuario");
		if (usuario == null) {
			if (!req.getRequestURI().endsWith("login.xhtml")) {
				redireciona("login.xhtml", response);
				return;
			}
		} 
		chain.doFilter(request, response);
		/*else {
			if(req.getRequestURI().matches(".+/adm/.+")){
				if (usuario.getCargo() != null && (usuario.getCargo().equals(Cargos.COORDENADOR) || usuario.getCargo().equals(Cargos.CHEFE_DEPARTAMENTO))) {
					if(req.getRequestURI().endsWith("cursos.xhtml") && !usuario.getCargo().equals(Cargos.CHEFE_DEPARTAMENTO)){
						redireciona("usuario/perfil.xhtml", response);
					}
					chain.doFilter(request, response);
				} else {
					redireciona("usuario/perfil.xhtml", response);
				}
			}
			if (req.getRequestURI().endsWith("login.xhtml")) {
				redireciona("usuario/perfil.xhtml", response);
			}
			chain.doFilter(request, response);
		}*/
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	private void redireciona(String url, ServletResponse response) throws IOException {
		((HttpServletResponse) response).sendRedirect("/encargo-dist/" + url);
	}

}
