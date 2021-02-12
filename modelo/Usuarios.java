package modelo;

public class Usuarios {

	 private int id;
	 private String usuario;
	 private String password;
	 private String last_session;
	 private int id_tipo;
                 private int id_empleado;

    public Usuarios(int id, String usuario, String password, String last_session, int id_tipo, int id_empleado) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.last_session = last_session;
        this.id_tipo = id_tipo;
        this.id_empleado = id_empleado;
    }
	
                 
                 
	 public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

	public String getLast_session() {
		return last_session;
	}
	public void setLast_session(String last_session) {
		this.last_session = last_session;
	}
	public int getId_tipo() {
		return id_tipo;
	}
	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
	}
	 

	

	
}
