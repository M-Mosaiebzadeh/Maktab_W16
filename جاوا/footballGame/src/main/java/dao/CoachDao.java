package dao;

import entities.Coach;
import entities.Player;
import entities.User;

import javax.persistence.EntityManager;

public class CoachDao extends UserDao{

    public CoachDao(EntityManager entityManager) {
        super(entityManager);
    }

}
