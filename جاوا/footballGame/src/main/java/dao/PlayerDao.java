package dao;

import entities.Player;
import entities.User;

import javax.persistence.EntityManager;

public class PlayerDao extends UserDao{

    private final CompetitionDao competitionDao = new CompetitionDao(entityManager);
    private final MatchEventDao matchEventDao = new MatchEventDao(entityManager);

    public PlayerDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void delete(User entity) {
        Player player = (Player) entity;
        player.getAwayCompetition().forEach(competitionDao::delete);
        player.getHomeCompetition().forEach(competitionDao::delete);
        player.getMatchEvents().forEach(matchEventDao::delete);
        super.delete(entity);
    }
}
