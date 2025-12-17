package bridge.controller;

import bridge.domain.BridgeGame;
import bridge.domain.BridgeMaker;
import bridge.factory.BridgeFactory;
import bridge.util.NumberParser;
import bridge.view.InputView;
import bridge.view.OutputView;
import bridge.view.PrintMessage;
import java.util.List;
import java.util.function.Supplier;

public class BridgeController {
    private final BridgeMaker bridgeMaker;

    public BridgeController(BridgeMaker bridgeMaker) {
        this.bridgeMaker = bridgeMaker;
    }

    public void run() {
        OutputView.printPrompt(PrintMessage.GAME_BEGIN);

        BridgeGame game = createBridge();
        
    }

    private BridgeGame createBridge() {
        return retryOnError(() -> {
            OutputView.printPrompt(PrintMessage.INPUT_BRIDGE_SIZE);
            String inputBridgeSize = InputView.readBridgeSize();
            int bridgeSize = NumberParser.parse(inputBridgeSize);

            List<String> bridge = bridgeMaker.makeBridge(bridgeSize);
            return BridgeFactory.createGame(bridge);
        });
    }

    private <T> T retryOnError(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
