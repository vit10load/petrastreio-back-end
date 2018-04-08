package br.ifms.cx.petrastreio.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import br.ifms.cx.petrastreio.domain.Coordenada;

@Component
public class CoordenadaDAO extends HibernateDAO<Coordenada>{

	public CoordenadaDAO() {
		super(Coordenada.class);
	}
	
	public Coordenada getUltimaCoordenada(){
		Query query = super.createQuery("FROM Coordenada ORDER BY id DESC");
		query.setMaxResults(1);
		return (Coordenada) query.uniqueResult();
	}
	
	
	public void persistirCoordenada(Coordenada c){
		super.save(c);
	}
	
	@SuppressWarnings("unchecked")
	public List<Coordenada> listarCoordenadas(Integer qtde){
		Query query = super.createQuery("FROM Coordenada ORDER BY id DESC");
		query.setMaxResults(qtde);
		return query.list();
	}
 
}
