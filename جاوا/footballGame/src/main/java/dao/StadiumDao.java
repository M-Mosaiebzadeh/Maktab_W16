package dao;

import entities.Stadium;

import javax.persistence.EntityManager;

public class StadiumDao extends AbstractDao<Integer, Stadium>{

    private final CompetitionDao competitionDao = new CompetitionDao(entityManager);

    public StadiumDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Stadium> getEntityClass() {
        return Stadium.class;
    }

    @Override
    public void delete(Stadium entity) {
        entity.getMatches().forEach(competitionDao::delete);
        super.delete(entity);
    }
}
