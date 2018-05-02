package todo.dao.jpa;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import todo.dao.NotaDAO;
import todo.models.Nota;

@Repository
@SuppressWarnings("unchecked")
@Transactional
public class NotaJPA implements NotaDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Nota primeiroOuNulo(List<Nota> notas) {
		return (notas.isEmpty()) ? null : notas.get(0);
	}

	@Override
	public Nota buscar(Long id) {
		return primeiroOuNulo(sessionFactory.getCurrentSession().createQuery("FROM Nota n WHERE n.id = :id").setParameter("id", id).list());
	}

	@Override
	public List<Nota> buscar() {
		return sessionFactory.getCurrentSession().createQuery("FROM Nota n").list();
	}

	@Override
	public void alterar(Nota obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void deletar(Nota obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public void inserit(Nota obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public Nota buscarPorTitulo(String titulo) {
		return primeiroOuNulo(sessionFactory.getCurrentSession().createQuery("FROM Nota n WHERE n.titulo = :titulo").setParameter("titulo", titulo).list());

	}

}
