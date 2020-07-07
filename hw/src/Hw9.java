import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/*
Time RecursiveTask : 48

Time Stream Api: 110

Time parallelStream Api: 176

Time simple findmax: 44
 */


public class Hw9 {


    static class MillionsMax extends RecursiveTask<Integer> {

        private int[] arr;
        private int end;
        private int start;

        public MillionsMax(int start, int end, int[] arr) {
            this.start = start;
            this.end = end;
            this.arr = arr;
        }

        @Override
        protected Integer compute() {
            int run = end - start;
            if (run < 200000) {
                int max = Integer.MIN_VALUE;
                for (int i = start; i < end; i++) {
                    if (arr[i] > max) {
                        max = arr[i];
                    }
                }
                return max;
            }
            int slice = run / 2 + start;

            MillionsMax newStart = new MillionsMax(start, slice, arr);
            MillionsMax newEnd = new MillionsMax(slice + 1, end, arr);

            ForkJoinTask.invokeAll(newStart, newEnd);

            int l = newStart.getRawResult();
            int r = newEnd.getRawResult();
            return Math.max(l, r);
        }


    }


    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[100_000_000];
        List<Integer> arr2 = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            int tmp = random.nextInt(100000);
            arr[i] = tmp;
            arr2.add(tmp);
        }

        long time1 = System.currentTimeMillis();
        int ans = ForkJoinPool.commonPool().invoke(new MillionsMax(0, 100_000_000, arr));
        System.out.println("Time RecursiveTask : " + (System.currentTimeMillis() - time1));
        System.out.println("max: " + ans);

        long time2 = System.currentTimeMillis();
        System.out.println(Arrays.stream(arr).max());
        System.out.println("Time Stream Api: " + (System.currentTimeMillis() - time2));

        long time3 = System.currentTimeMillis();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (max > arr[i]) max = arr[i];
        }
        System.out.println("Time simple findmax: " + (System.currentTimeMillis() - time3));


        long time4 = System.currentTimeMillis();
        System.out.println(arr2.parallelStream().max(Integer::compareTo));
        System.out.println("Time parallelStream Api: " + (System.currentTimeMillis() - time4));


    }
}
