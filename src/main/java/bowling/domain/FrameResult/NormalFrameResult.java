package bowling.domain.FrameResult;

import bowling.domain.FrameStatus;
import bowling.domain.NumberOfHitPin;
import bowling.domain.exceptions.InvalidNumberOfHitPinException;

import java.util.Objects;

public class NormalFrameResult implements FrameResult {
    private static final NumberOfHitPin MAX_NUMBER_OF_FIRST_HIT_PIN = new NumberOfHitPin(9);

    private NumberOfHitPin firstNumberOfHitPin;
    private NumberOfHitPin secondNumberOfHitPin;

    public NormalFrameResult(NumberOfHitPin firstNumberOfHitPin, NumberOfHitPin secondNumberOfHitPin) {
        this.firstNumberOfHitPin = firstNumberOfHitPin;
        this.secondNumberOfHitPin = secondNumberOfHitPin;
    }

    public static NormalFrameResult firstBowl(int numberOfHitPin) {
        NumberOfHitPin firstNumberOfHitPin = new NumberOfHitPin(numberOfHitPin);
        validateFirstThrow(firstNumberOfHitPin);

        return new NormalFrameResult(firstNumberOfHitPin, null);
    }

    public NormalFrameResult secondBowl(int numberOfHitPin) {
        NumberOfHitPin secondNumberOfHitPin = new NumberOfHitPin(numberOfHitPin);
        validateSecondThrow(secondNumberOfHitPin);

        return new NormalFrameResult(this.firstNumberOfHitPin, secondNumberOfHitPin);
    }

    private static void validateFirstThrow(NumberOfHitPin numberOfHitPin) {
        if (numberOfHitPin.compareTo(MAX_NUMBER_OF_FIRST_HIT_PIN) > 0) {
            throw new InvalidNumberOfHitPinException("맞춘 핀의 수가 0 ~ 9를 벗어났습니다.");
        }
    }

    private void validateSecondThrow(NumberOfHitPin secondNumberOfHitPin) {
        this.firstNumberOfHitPin.plus(secondNumberOfHitPin);
    }

    @Override
    public boolean isStrikeResult() {
        return false;
    }

    @Override
    public boolean isCompleted() {
        return (this.firstNumberOfHitPin != null && this.secondNumberOfHitPin != null);
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    @Override
    public FrameStatus calculateCurrentStatus() {
        // TODO: FrameStatus에서 NumberOfHitPin으로 처리하도록 변경 후 다시 구현
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrameResult that = (NormalFrameResult) o;
        return Objects.equals(firstNumberOfHitPin, that.firstNumberOfHitPin) &&
                Objects.equals(secondNumberOfHitPin, that.secondNumberOfHitPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNumberOfHitPin, secondNumberOfHitPin);
    }
}
