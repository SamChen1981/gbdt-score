package com.apachee.gbdt;

import com.apachee.gbdt.parse.ModelParser;
import com.apachee.gbdt.tree.GBTree;

import java.io.IOException;

/**
 * @author chen lu
 * @date 2017/1/11
 * @time 16:16
 */
public class Booster {

    /**
     * 测试demo
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ModelParser parser = new ModelParser(0.0);
        GBTree gbTree = parser.parse("/D:/code/gbdt-score/src/main/resources/models/model_log_click_0.1");

        System.out.println(gbTree.predict(getFeatures(gbTree.getFeatureNums(), 0.5)));
    }

    public static double[] getFeatures(int size, double val) {
        double[] features = new double[size];
        for(int i = 0; i < size; i++) {
            features[i] = val;
        }
        return features;
    }
}
