package bridge.factory;

import bridge.controller.BridgeController;
import bridge.domain.BridgeMaker;
import bridge.domain.BridgeNumberGenerator;
import bridge.domain.numbergenerator.BridgeRandomNumberGenerator;

public class ApplicationFactory {
    public BridgeController controller() {
        return new BridgeController(bridgeMaker());
    }

    public BridgeMaker bridgeMaker() {
        return new BridgeMaker(numberGenerator());
    }

    public BridgeNumberGenerator numberGenerator() {
        return new BridgeRandomNumberGenerator();
    }
}
