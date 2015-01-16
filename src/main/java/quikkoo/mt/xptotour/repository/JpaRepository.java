package quikkoo.mt.xptotour.repository;

import java.io.Serializable;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
public abstract class JpaRepository<Entity, Id> implements Repository<Entity, Id>, Serializable {

	private static final long serialVersionUID = -2523099629693312719L;

	private static final String MSG_ERROR_NOT_FOUND = 
			"Not found an etity %s with id = %s";

	@PersistenceContext
	protected EntityManager manager;

	protected Class<Entity> clazz;

	public JpaRepository(Class<Entity> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void save(Entity entity) {
		if (manager.contains(entity)) {
			update(entity);
		}
		else {
			insert(entity);
		}
	}

	@Override
	public void insert(Entity entity) {
		manager.persist(entity);
	}

	@Override
	public void update(Entity entity) {
		manager.merge(entity);
	}

	@Override
	public void remove(Entity entity) {
		manager.remove(entity);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Entity load(Id id) {
		Entity entity = manager.find(clazz, id);
		if (entity != null) {
			return entity;
		}
		else {
			throw new EntityNotFoundException(String.format(MSG_ERROR_NOT_FOUND, clazz.getName(), id));
		}
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public TypedQuery<Entity> createNamedQuery(String query) {
		return manager.createNamedQuery(query, clazz);
	}

}
