package todo.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import todo.dao.NotaDAO;
import todo.exceptions.EntidadeNaoEncontradaException;
import todo.exceptions.ValidacaoException;
import todo.models.Nota;

@Service
public class NotaService {
	
	@Autowired
	private NotaDAO notaDAO;
	
	public Nota buscar(Long id) throws EntidadeNaoEncontradaException {
		
		//Busca a nota do banco
		Nota notaBuscada = notaDAO.buscar(id);
		
		//Verifica se nota existe
		if(notaBuscada == null) {
			throw new EntidadeNaoEncontradaException();
		}
		
		return notaBuscada;
	}
	
	public List<Nota> buscarTodas(){
		return notaDAO.buscar();
	}
	
	public Nota cadastrar(Nota nota, BindingResult bindingResult) throws ValidacaoException {
		
		//Verificando integridade de campos
		if(bindingResult.hasErrors()) {
			throw new ValidacaoException();
		}
		
		//Verificar se já existe o titulo
		if(notaDAO.buscarPorTitulo(nota.getTitulo()) != null) {
			bindingResult.addError(new FieldError("nota", "titulo", "O título já está sendo utilizado"));
			throw new ValidacaoException();
		}
		
		notaDAO.inserit(nota);
		
		return nota;
	}
	
	public void deletar(Long id) throws EntidadeNaoEncontradaException {
		//Busca a nota do banco
		Nota notaBuscada = notaDAO.buscar(id);
		
		//Verifica se nota existe
		if(notaBuscada == null) {
			throw new EntidadeNaoEncontradaException();
		}
		
		notaDAO.deletar(notaBuscada);
	}
	
	public Nota editar(Long id, Nota nota, BindingResult bindingResult) throws ValidacaoException, EntidadeNaoEncontradaException {
		
		if(bindingResult.hasErrors()) {
			throw new ValidacaoException();
		}
		
		//Busca a nota do banco
		Nota notaBuscada = notaDAO.buscar(id);
		if(notaBuscada == null) {
			throw new EntidadeNaoEncontradaException();
		}
				
		if(notaDAO.buscarPorTitulo(nota.getTitulo()) != null) {
			bindingResult.addError(new FieldError("nota", "titulo", "O título já está sendo utilizado"));
			throw new ValidacaoException();
		}
		
		//Alterando a nota
		BeanUtils.copyProperties(nota, notaBuscada, "id");		
		notaDAO.alterar(notaBuscada);
		
		return notaBuscada;
		
	}

}
