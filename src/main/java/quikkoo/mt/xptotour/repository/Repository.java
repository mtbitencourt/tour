package quikkoo.mt.xptotour.repository;

import java.util.List;

public interface Repository<Entity, Id> {

	public void save(Entity entity);

	public void insert(Entity entity);

	public void update(Entity entity);

	public void remove(Entity entity);

	public Entity load(Id id);

	public List<Entity> find();
}