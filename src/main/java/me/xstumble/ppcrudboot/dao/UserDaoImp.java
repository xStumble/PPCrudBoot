package me.xstumble.ppcrudboot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import me.xstumble.ppcrudboot.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(long id, User updatedUser) {
        if (getUser(id) != null) {
            updatedUser.setId(id);
            em.merge(updatedUser);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void deleteUser(long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public User getUser(long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            return user;
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}
