package com.apachee.gbdt;

import com.apachee.gbdt.parse.ModelParser;
import com.apachee.gbdt.tree.GBTree;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;

/**
 * @author chen lu
 * @date 2017/1/13
 * @time 17:16
 */
public class GBTreeTest {

    private static GBTree gbTree;
    private static double[] features;
    static {
        ModelParser parser = new ModelParser();
        String filePath = Thread.currentThread()
                .getContextClassLoader()
                .getResource("models/model_log_click_0.1")
                .getPath();
        try {
            gbTree = parser.parse(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        features = getFeatures(356, 0.1);
    }

    public static double[] getFeatures(int featureNum, double val) {
        double[] features = new double[featureNum];
        for(int i = 0; i < featureNum; i++) {
            features[i] = val;
        }
        return features;
    }

    @Benchmark
    public void wellHelloThere() {
        // this method was intentionally left blank.
        gbTree.predict(features);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(GBTreeTest.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
