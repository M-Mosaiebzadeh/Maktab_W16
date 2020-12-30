import com.github.javafaker.Faker;
import dao.*;
import entities.*;

import javax.persistence.EntityManager;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static entities.enums.MatchEventType.INJURY;
import static entities.enums.MatchEventType.YELLOW_CARD;
import static entities.enums.MatchEventType.RED_CARD;
import static util.EntityManagerFactoryUtil.createEntityManagerFactory;
import static util.EntityManagerFactoryUtil.shutdown;


public class App {
    static UserDao userDao;
    static PlayerDao playerDao;
    static CoachDao coachDao;
    static CityDao cityDao;
    static CompetitionDao competitionDao;
    static ContractDao contractDao;
    static MatchEventDao matchEventDao;
    static StadiumDao stadiumDao;
    static TeamDao teamDao;
    static Faker faker = new Faker();

    public static void main(String[] args) {
        EntityManager entityManager = createEntityManagerFactory().createEntityManager();
        daoInitialization(entityManager);
        entityManager.getTransaction().begin();

        //create data
//        createCompetition();


        //update match event
//        MatchEvent matchEvent = matchEventDao.load(1);
//        matchEvent.setType(RED_CARD);
//        matchEventDao.update(matchEvent);


        //delete matchEvent <<not dependent>> to other entities
//        matchEventDao.delete(matchEventDao.load(1));


        //delete competition <<dependent>> to match events
//        competitionDao.delete(competitionDao.load(1));


        //delete stadium <<dependent>> to competition
//        stadiumDao.delete(stadiumDao.load(1));


        //delete contract <<not dependent>> to other entities
//        contractDao.delete(contractDao.load(1));


        //delete team <<dependent>> to competition and contract
//        teamDao.delete(teamDao.load(1));


        //delete city <<dependent>> to team and stadium
//        cityDao.delete(cityDao.load(1));


        //delete user directly <<dependent>> to contract && player <<dependent>> to competition and match event
//        userDao.delete(userDao.load(1));
//        userDao.delete(userDao.load(3));

        entityManager.getTransaction().commit();
        entityManager.close();
        shutdown();
    }

    public static void daoInitialization(EntityManager entityManager) {
        userDao = new UserDao(entityManager);
        playerDao = new PlayerDao(entityManager);
        coachDao = new CoachDao(entityManager);
        competitionDao = new CompetitionDao(entityManager);
        cityDao = new CityDao(entityManager);
        contractDao = new ContractDao(entityManager);
        matchEventDao = new MatchEventDao(entityManager);
        stadiumDao = new StadiumDao(entityManager);
        teamDao = new TeamDao(entityManager);
    }
    public static void createCompetition() {

        //team 1
        Player player = new Player();
        player.setFirstname(faker.name().firstName());
        player.setLastname(faker.name().lastName());
        player.setPosition("AB");
        playerDao.save(player);

        Player player2 = new Player();
        player2.setFirstname(faker.name().firstName());
        player2.setLastname(faker.name().lastName());
        player2.setPosition("CD");
        playerDao.save(player2);

        Coach coach = new Coach();
        coach.setFirstname(faker.name().firstName());
        coach.setLastname(faker.name().lastName());
        coachDao.save(coach);

        City city = new City();
        city.setCityName(faker.address().cityName());
        cityDao.save(city);

        Team team = new Team();
        team.setTeamName(faker.team().name());
        team.setCity(city);
        teamDao.save(team);

        Contract playerContract = new Contract();
        playerContract.setUser(player);
        playerContract.setTeam(team);
        playerContract.setSalary(1_000_000_000D);
        playerContract.setSeason(new Date(2020L));
        contractDao.save(playerContract);

        Contract player2Contract = new Contract();
        player2Contract.setUser(player2);
        player2Contract.setTeam(team);
        player2Contract.setSalary(1_200_000_000D);
        player2Contract.setSeason(new Date(2020L));
        contractDao.save(player2Contract);

        Contract coachContract = new Contract();
        coachContract.setUser(coach);
        coachContract.setTeam(team);
        coachContract.setSalary(2_200_000_000D);
        coachContract.setSeason(new Date(2020L));
        contractDao.save(coachContract);

        //match playing in stadium
        //====================================
        Stadium stadium = new Stadium();
        stadium.setStadiumName("Azadi");
        stadium.setCapacity(65000L);
        stadium.setCity(city);
        stadiumDao.save(stadium);
        //====================================

        //team 2
        Player player21 = new Player();
        player21.setFirstname(faker.name().firstName());
        player21.setLastname(faker.name().lastName());
        player21.setPosition("AB");
        playerDao.save(player21);

        Player player22 = new Player();
        player22.setFirstname(faker.name().firstName());
        player22.setLastname(faker.name().lastName());
        player22.setPosition("CD");
        playerDao.save(player22);

        Coach coach2 = new Coach();
        coach2.setFirstname(faker.name().firstName());
        coach2.setLastname(faker.name().lastName());
        coachDao.save(coach2);

        City city2 = new City();
        city2.setCityName(faker.address().cityName());
        cityDao.save(city2);

        Team team2 = new Team();
        team2.setTeamName(faker.team().name());
        team2.setCity(city2);
        teamDao.save(team2);

        Contract player21Contract = new Contract();
        player21Contract.setUser(player21);
        player21Contract.setTeam(team2);
        player21Contract.setSalary(900_000_000D);
        player21Contract.setSeason(new Date(2020L));
        contractDao.save(player21Contract);

        Contract player22Contract = new Contract();
        player22Contract.setUser(player22);
        player22Contract.setTeam(team2);
        player22Contract.setSalary(1_500_000_000D);
        player22Contract.setSeason(new Date(2020L));
        contractDao.save(player22Contract);

        Contract coach2Contract = new Contract();
        coach2Contract.setUser(coach2);
        coach2Contract.setTeam(team2);
        coach2Contract.setSalary(1_900_000_000D);
        coach2Contract.setSeason(new Date(2020L));
        contractDao.save(coach2Contract);

        //====================================

        //competition team1 => home && team2 => away
        Competition competition = new Competition();
        competition.setSeason(new Date(2020L));
        competition.setStadium(stadium);
        competition.setHomeTeam(team);
        competition.setAwayTeam(team2);
        //players of Participates in competition
        Set<Player> homePlayers = new HashSet<>();
        homePlayers.add(player);
        homePlayers.add(player2);

        Set<Player> awayPlayers = new HashSet<>();
        awayPlayers.add(player21);
        awayPlayers.add(player22);

        competition.setAwayPlayers(awayPlayers);
        competition.setHomePlayers(homePlayers);
        competitionDao.save(competition);

        //====================================

        //Events in match
        MatchEvent matchEvent1 = new MatchEvent();
        matchEvent1.setPlayer(player);
        matchEvent1.setType(YELLOW_CARD);
        matchEvent1.setTime(new Date());
        matchEvent1.setCompetition(competition);
        matchEventDao.save(matchEvent1);

        MatchEvent matchEvent2 = new MatchEvent();
        matchEvent2.setPlayer(player22);
        matchEvent2.setType(INJURY);
        matchEvent2.setTime(new Date());
        matchEvent2.setCompetition(competition);
        matchEventDao.save(matchEvent2);
    }
}
