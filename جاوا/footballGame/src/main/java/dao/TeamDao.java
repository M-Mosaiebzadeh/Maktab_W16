package dao;

import entities.Team;

import javax.persistence.EntityManager;

public class TeamDao extends AbstractDao<Integer, Team>{

    private final ContractDao contractDao = new ContractDao(entityManager);
    private final CompetitionDao competitionDao = new CompetitionDao(entityManager);

    public TeamDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Team> getEntityClass() {
        return Team.class;
    }

    @Override
    public void delete(Team entity) {
        entity.getContracts().forEach(contractDao::delete);
        entity.getAwayCompetition().forEach(competition -> competitionDao.delete(competition));
        entity.getHomeCompetition().forEach(competitionDao::delete);
        super.delete(entity);
    }
}
