package finalPOO;

public class Main {

	public static void main(String[] args) {
		Cajero cajero = new Cajero();
        
        Admin admin = new Admin("Yannick", "Raijman", "1234", "1234");
        Usuario.getUsuarios().add(admin);
        
        Empleado empleado = new Empleado("Juan", "Lopez", "4321", "4321");
        Usuario.getUsuarios().add(empleado);
        
        Cliente cliente = new Cliente("Sofia", "Martinez", "5678", "5678");
        Usuario.getUsuarios().add(cliente);
        
        Usuario usuario = Usuario.login();
        while(usuario != null) {
            if (usuario != null) {
                usuario.menu(cajero); 
            } 
            usuario = Usuario.login();
        }

	}

}
