package dao;

import entities.Competition;

import javax.persistence.EntityManager;

public class CompetitionDao extends AbstractDao<Integer, Competition>{
    private final MatchEventDao matchEventDao = new MatchEventDao(entityManager);
    public CompetitionDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Competition> getEntityClass() {
        return Competition.class;
    }

    @Override
    public void delete(Competition entity) {
        entity.getMatchEvents().forEach(matchEventDao::delete);
        super.delete(entity);
    }
}
