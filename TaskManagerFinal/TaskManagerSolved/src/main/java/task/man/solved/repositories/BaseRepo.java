package task.man.solved.repositories;

import java.util.List;

import javax.persistence.MappedSuperclass;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import task.man.solved.entities.BaseEntity;
import task.man.solved.helper.ReflectionHelper;

@MappedSuperclass
public class BaseRepo<T extends BaseEntity> {

	@Autowired
	private SessionFactory sessionFactory;

	public long add(T item) {
		return (Long) sessionFactory.getCurrentSession().save(item);
	}

	public T edit(T item) {
		sessionFactory.getCurrentSession().update(item);
		return item;
	}

	public void delete(long id) throws InstantiationException, IllegalAccessException {
		T item = ReflectionHelper.CreateInstance(this.getClass(), 1);
		item.setId(id);
		sessionFactory.getCurrentSession().delete(item);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() throws InstantiationException, IllegalAccessException {
		T item = ReflectionHelper.CreateInstance(this.getClass(), 1);
		return sessionFactory.getCurrentSession().createQuery(" FROM " + item.getClass().getName()).list();
	}

	@SuppressWarnings("unchecked")
	public T getById(long id) throws InstantiationException, IllegalAccessException {
		T item = ReflectionHelper.CreateInstance(this.getClass(), 1);
		return (T) sessionFactory.getCurrentSession().get(item.getClass(), id);
	}

	@SuppressWarnings("unchecked")
	public T getByString(String searchName) throws InstantiationException, IllegalAccessException {
		T item = ReflectionHelper.CreateInstance(this.getClass(), 1);
		return (T) sessionFactory.getCurrentSession().get(item.getClass(), searchName);
	}
}
