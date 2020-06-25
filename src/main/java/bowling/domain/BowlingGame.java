package bowling.domain;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private final Player player;
    private List<BowlingGameResult> bowlingGameResults;

    public BowlingGame(Player player, List<BowlingGameResult> bowlingGameResults) {
        this.player = player;
        this.bowlingGameResults = new ArrayList<>(bowlingGameResults);
    }

    public static BowlingGame play(Player player) {
        return new BowlingGame(player, new ArrayList<>());
    }

    public static BowlingGame start(String playerName) {
        return new BowlingGame(Player.createByName(playerName), new ArrayList<>());
    }

    public int checkWhereFrame() {
        return this.bowlingGameResults.size();
    }

    public List<BowlingGameResult> bowlFirst(int numberOfHitPin) {
        Frame frame = player.bowlFirstRefactor(numberOfHitPin);
        BowlingGameResult bowlingGameResult = new BowlingGameResult(frame.calculateCurrentResults());

        this.bowlingGameResults.add(bowlingGameResult);

        return new ArrayList<>(this.bowlingGameResults);
    }

    public List<BowlingGameResult> bowlCurrentFrame(int numberOfHitPin) {
        Frame frame = player.bowlCurrentFrameRefactor(numberOfHitPin);
        BowlingGameResult bowlingGameResult = new BowlingGameResult(frame.calculateCurrentResults());

        this.bowlingGameResults.set(lastIndexOfBowlingGameResults(), bowlingGameResult);

        return new ArrayList<>(this.bowlingGameResults);
    }

    public List<BowlingGameResult> toNextFrame(int numberOfHitPin) {
        Frame frame = player.toNextFrameRefactor(numberOfHitPin);
        BowlingGameResult bowlingGameResult = new BowlingGameResult(frame.calculateCurrentResults());

        this.bowlingGameResults.add(bowlingGameResult);

        return new ArrayList<>(this.bowlingGameResults);
    }

    public boolean isCurrentFrameCompleted() {
        return this.player.isCurrentFrameCompleted();
    }

    public String getPlayerName() {
        return this.player.getName();
    }

    private int lastIndexOfBowlingGameResults() {
        return this.bowlingGameResults.size() - 1;
    }
}
