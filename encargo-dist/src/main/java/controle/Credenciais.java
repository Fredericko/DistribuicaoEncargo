package controle;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.DocenteDAO;
import vo.DocenteVO;

@ManagedBean
@SessionScoped
public class Credenciais {

	private String usuario, senha;

	public String deslogar() {
		SessionContext.getInstance().removeAttribute("usuario");
		SessionContext.getInstance().encerrarSessao();
		return "/login.xhtml?faces-redirect=true";
	}

	public String logar() {
		if (getUsuario() != null && getSenha() != null && !getUsuario().isEmpty() && !getSenha().isEmpty()) {
			setUser(DocenteDAO.logar(getUsuario().trim(), criptografar(getSenha())));
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

	private String criptografar(String senha) {
		try {
			MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algoritmo.digest(senha.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
