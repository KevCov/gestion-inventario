package com.cibertec.gestion.service;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.gestion.dto.User;
import com.cibertec.gestion.entity.Usuario;
import com.cibertec.gestion.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> obtenerTodosClientes(){
		var clientes=repository.findAll();
		if (clientes.isEmpty())
			return Collections.emptyList();
		System.out.println(clientes);
		return clientes;
	}
	
	public Usuario obtenerClienteById(Integer id) {
		var cliente = repository.findById(id);
		if (cliente.isEmpty())
			System.out.println("No se encontro al Usuario");

		return cliente.get();
	}
	
	public void crearCliente(Usuario cliente) {
		repository.save(cliente);
	}

	public void eliminarCliente(Integer id) {
		repository.deleteById(id);
	}
	
	public void actualizarCliente(Integer id, Usuario cliente) {
		var clienteActual = repository.findById(id);

	     clienteActual.ifPresentOrElse(p -> {
			p.setNombre(cliente.getNombre());
			p.setApellido(cliente.getApellido());
			p.setTelefono(cliente.getTelefono());
			p.setDireccion(cliente.getDireccion());
			p.setEmail(cliente.getEmail());
			repository.save(p);
		}, () -> {
			throw new NullPointerException("Usuario no encontrado con ID: " + id);
		});
	}
	
	public boolean verificarUsuario(User user) {
		Long count = repository.findUserToLogin(user.getUsername(), user.getPassword());

	    return count != null && count > 0;
	}
}
