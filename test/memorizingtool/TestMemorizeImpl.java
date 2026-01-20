package memorizingtool;

/**
 * Concrete implementation of MemorizeBase for testing purposes.
 * Uses String as the generic type for simplicity and flexibility.
 */
public final class TestMemorizeImpl extends MemorizeBase<String> {

    public TestMemorizeImpl() {
        super();
    }

    @Override
    protected void sort(String way) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    protected void compare(int i, int j) {
        throw new UnsupportedOperationException("Not supported.");
    }

}
