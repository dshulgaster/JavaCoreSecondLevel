package gbjc1.les01;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

// из разбора урока

public class Competition {
    public static Random random = new Random();
    private Obstacle[] obstacles;
    private Participant[] participants;
    private final String competitionTitle;
    private List<Participant> lastWinner = new LinkedList<>();

    public static void main(String[] args) {
        System.out.println("Готовимся к соревнованию...");
        Competition competition = addNewCompetition();
        System.out.println("Начинаем соревнование!");
        competition.startCompetition();
        System.out.println("Соревнование закончено!\n");
        System.out.println("Победители: ");
        for (Participant winner : competition.getLastWinner()) {
            System.out.println(winner);
        }
    }

    public Competition(String competitionTitle) {
        this.competitionTitle = competitionTitle;
    }

    public void setObstacles(Obstacle... obstacles) {
        this.obstacles = obstacles;
    }

    public void setParticipants(Participant... participants) {
        this.participants = participants;
    }

    public void startCompetition() {
        System.out.println(competitionTitle);
        lastWinner.clear();
        for (Participant participant : participants) {
            boolean success = passAllObstacles(participant);
            if (!success) {
                System.out.println("Участник " + participant + " покинул данное испытание...");
            } else {
                lastWinner.add(participant);
            }
        }
    }

    public List<Participant> getLastWinner() {
        return lastWinner;
    }

    private boolean passAllObstacles(Participant participant) {
        for (Obstacle obstacle : obstacles) {
            if (!participant.doIt(obstacle)) {
                return false;
            }
            System.out.println();
        }
        return true;
    }

    private static Competition addNewCompetition() {
        Participant[] man = new Participant[2];
        man[0] = new Man(1500, 2, "Gleb");
        man[1] = new Man(2000, 1, "Ivan");

        Participant[] cat = new Participant[3];
        cat[0] = new Cat(200, 3, "Barsik");
        cat[1] = new Cat(150, 2, "Pushok");
        cat[2] = new Cat(15000, 2000, "SuperCAT");

        Participant[] robot = new Participant[3];
        robot[0] = new Robot(100, 0, "Rob Dell 318");
        robot[1] = new Robot(80, 0, "Rob Int 41-2");
        robot[2] = new Robot(10000, 10000, "SUPERKIBORG");

        Treadmill treadmill = new Treadmill(random.nextInt(1500));
        Wall wall = new Wall(random.nextInt(5));

        Competition competition = new Competition("--новое соревнование--\n");
        competition.setObstacles(wall, treadmill);
        competition.setParticipants(man[0], man[1], cat[0], cat[2], robot[2]);

        return competition;
    }

}
