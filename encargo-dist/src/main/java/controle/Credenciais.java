package controle;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.DocenteDAO;
import vo.DocenteVO;

@ManagedBean
@SessionScoped
public class Credenciais {

	private DocenteVO user = null;
	private String usuario, senha;

	public void deslogar() {
		user = null;
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logar() {
		try {
			if (getUsuario() != null && getSenha() != null && !getUsuario().isEmpty() && !getSenha().isEmpty()) {
				user = DocenteDAO.logar(getUsuario().trim(), criptografar(getSenha()));
				if (user != null) {
					FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/usuario/perfil.xhtml");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cadastrar(String usuario, String senha) {

	}

	public DocenteVO getUser() {
		return user;
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
