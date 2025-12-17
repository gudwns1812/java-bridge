package bridge.domain;

import static bridge.exception.ErrorMessage.NOT_VALID_MOVE;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {
    private final Bridge bridge;
    private final int latestMove;

    public BridgeGame(Bridge bridge) {
        this(bridge, 0);
    }

    public BridgeGame(Bridge bridge, int latestMove) {
        this.bridge = bridge;
        this.latestMove = latestMove;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public GameResult move(String move, GameResult result) {
        validateMove(move);

        if (bridge.isSameAs(latestMove, move)) {
            return result.add(move, true);
        }

        return result.add(move, false);
    }

    private void validateMove(String move) {
        if (!move.equals("U") && !move.equals("D")) {
            throw new IllegalArgumentException(NOT_VALID_MOVE.getMessage());
        }
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
    }
}
