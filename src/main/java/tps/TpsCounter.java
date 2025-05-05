package tps;

public class TpsCounter {
    private static final int SAMPLE_SIZE = 20;
    private static final double TPS_BASE = 20.0;
    private static final long[] tickIntervals = new long[SAMPLE_SIZE];
    private static int tickPtr = 0;
    private static long lastWorldTime = -1;
    private static long lastPacketTime = -1;
    private static double tps = TPS_BASE;

    public static void onWorldTimeUpdate(long worldTime) {
        long now = System.nanoTime();
        if (lastWorldTime != -1 && lastPacketTime != -1) {
            long timeDiff = now - lastPacketTime;
            long tickDiff = worldTime - lastWorldTime;
            if (tickDiff > 0) {
                long interval = timeDiff / tickDiff;
                tickIntervals[tickPtr % SAMPLE_SIZE] = interval;
                tickPtr++;
                updateTps();
            }
        }
        lastWorldTime = worldTime;
        lastPacketTime = now;
    }

    private static void updateTps() {
        int count = Math.min(tickPtr, SAMPLE_SIZE);
        if (count == 0)
            return;
        long totalInterval = 0;
        for (int i = 0; i < count; i++) {
            totalInterval += tickIntervals[i];
        }
        double avgTickTime = totalInterval / (double) count;
        double tpsCalc = 1_000_000_000.0 / avgTickTime;
        tps = Math.min(TPS_BASE, tpsCalc);
    }

    public static double getTps() {
        return tps;
    }
}
