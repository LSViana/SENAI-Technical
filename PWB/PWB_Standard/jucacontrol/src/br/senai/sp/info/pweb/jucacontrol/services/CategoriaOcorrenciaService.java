package br.senai.sp.info.pweb.jucacontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import br.senai.sp.info.pweb.jucacontrol.dao.CategoriaOcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.exceptions.ValidationException;
import br.senai.sp.info.pweb.jucacontrol.models.CategoriaOcorrencia;

@Service
public class CategoriaOcorrenciaService {
	
	@Autowired
	private CategoriaOcorrenciaDAO dao;
	
	public CategoriaOcorrencia cadastrar(CategoriaOcorrencia valor, BindingResult br) throws ValidationException {
		if(br.hasErrors()) {
			throw new ValidationException("Erro de Validação");
		}
		CategoriaOcorrencia mesmoNome = dao.buscarPorNome(valor.getNome());
		if(mesmoNome != null) {
			br.addError(new FieldError("categoriaOcorrencia", "nome", "Nome já está em uso"));
			throw new ValidationException("Nome já está em uso");
		}
		dao.persistir(valor);
		return valor;
	}

}
