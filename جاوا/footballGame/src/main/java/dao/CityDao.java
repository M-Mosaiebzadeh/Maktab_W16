package dao;

import entities.City;

import javax.persistence.EntityManager;

public class CityDao extends AbstractDao<Integer, City> {

    private final TeamDao teamDao = new TeamDao(entityManager);
    private final StadiumDao stadiumDao = new StadiumDao(entityManager);

    public CityDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<City> getEntityClass() {
        return City.class;
    }

    @Override
    public void delete(City entity) {
        entity.getTeams().forEach(teamDao::delete);
        entity.getStadiums().forEach(stadiumDao::delete);
        super.delete(entity);
    }
}
