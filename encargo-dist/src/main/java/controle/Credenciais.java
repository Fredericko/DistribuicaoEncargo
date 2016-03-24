package controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.DocenteDAO;
import util.Encrypter;
import vo.DocenteVO;

@ManagedBean
@RequestScoped
public class Credenciais {

	private String usuario, senha;

	public String deslogar() {
		SessionContext.getInstance().removeAttribute("usuario");
		SessionContext.getInstance().encerrarSessao();
		return "/login.xhtml?faces-redirect=true";
	}

	public String logar() {
		if (getUsuario() != null && getSenha() != null && !getUsuario().isEmpty() && !getSenha().isEmpty()) {
			setUser(DocenteDAO.logar(getUsuario().trim(), Encrypter.criptografar(getSenha())));
			if (getUser() != null) {
				return "/usuario/perfil.xhtml?faces-redirect=true";
			}
		}
		return null;
	}

	public DocenteVO getUser() {
		return (DocenteVO) SessionContext.getInstance().getAttribute("usuario");
	}

	public void setUser(DocenteVO user) {
		SessionContext.getInstance().setAttribute("usuario", user);
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
