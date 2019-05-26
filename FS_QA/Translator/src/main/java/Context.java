class Context {

    private Strategy strategy;

    void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    Strategy getStrategy(){
        return this.strategy;
    }

    String executeStrategy(String text) {
        return strategy.translate(text);
    }
}
