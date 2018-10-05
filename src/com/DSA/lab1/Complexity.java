package com.DSA.lab1;


import java.lang.management.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Complexity {

    public static long now() {
        return System.nanoTime();
    }

    public static long nowMillis() {
        return System.currentTimeMillis();
//        return TimeUnit.NANOSECOND‌​S.toMillis(now());
    }

    public static long mem() {
        MemoryUsage heap
                = ManagementFactory
                .getMemoryMXBean()
                .getHeapMemoryUsage();
        return heap.getUsed();
    }

    public static long mem2() {
        Runtime rt = Runtime.getRuntime();
        return rt.totalMemory() - rt.freeMemory();
    }

    /************************/

    public static void bubble(int[] arr) {
        int tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void countSort(int[] array) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, idx;
        for (int i = 0; i < array.length; i++) {
            max = Math.max(max, array[i]);
            min = Math.min(min, array[i]);
        }
        int[] counts = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            idx = array[i] - min;
            counts[idx]++;
        }
        idx = 0;
        for (int j = 0; j < counts.length; j++) {
            for (int k = counts[j]; k > 0; k--) {
                array[idx++] = j + min;
            }
        }
    }

    public static void merge(int[] arr) {
        //Timsort adaptation
        Arrays.sort(arr);
    }

    public static void naivetree(int[] arr) {
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            Integer v = tree.get(arr[i]);
            v = v == null ? 1 : v + 1;
            tree.put(arr[i], v);
        }
        int idx = 0;
        for (Integer key : tree.keySet()) {
            for (int j = 0; j < tree.get(key); j++) {
                arr[idx++] = key;
            }
        }
    }

    public static void main(String[] args) {
        int ITERS = 10;
        for (int LEN = 10; LEN < 1e9; LEN *= 10) {
            long time = 0, tx = 0, memory = 0, mx = 0;
            for (int iter = 0; iter < ITERS; iter++) {
                System.gc();
                int[] arr = new int[LEN];
                for (int i = 0; i < LEN; i++) {
                    arr[i] = (int) (now() % 1123213);
                }

                mx = mem();
                tx = now();
                // MEASURING THIS
                naivetree(arr);
                time += now() - tx;
                memory += mem() - mx;
            }
            // here we lose variance information
            memory /= ITERS;
            time /= ITERS;
            System.out.format("%.0f\t%d\t%d\n",
                    Math.log10(LEN),
                    time, memory);

        }
    }

}