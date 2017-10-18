package task.man.solved.services;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import task.man.solved.entities.BaseEntity;
import task.man.solved.repositories.BaseRepo;

@MappedSuperclass
@Transactional
public class BaseService<T extends BaseEntity> {
	@Autowired
	private BaseRepo<T> baseRepo;

	public long add(T item) {
		return baseRepo.add(item);
	}

	public T edit(T item) {
		return baseRepo.edit(item);
	}

	public void delete(long id) throws InstantiationException, IllegalAccessException {
		baseRepo.delete(id);
	}

	public List<T> getAll() throws InstantiationException, IllegalAccessException {
		return baseRepo.getAll();
	}

	public T getById(long id) throws InstantiationException, IllegalAccessException {
		return baseRepo.getById(id);
	}

	public T getByString(String searchName) throws InstantiationException, IllegalAccessException {
		return baseRepo.getByString(searchName);
	}
}
