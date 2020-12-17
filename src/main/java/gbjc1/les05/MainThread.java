package gbjc1.les05;

public class MainThread {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        calcSingleThread();
        calcMultiThread();
    }

    public static void calcSingleThread() {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = calc(arr[i], i);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

    private static float calc(float v, int i) {
        return (float) (v * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }

    public static void calcMultiThread() {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long startTime = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a2[i] = calc(a2[i], i + h);
                }
            }
        });
        thread.start();
        for (int i = 0; i < h; i++) {
            a1[i] = calc(a1[i], i);
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
