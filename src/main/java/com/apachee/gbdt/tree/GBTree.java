package com.apachee.gbdt.tree;

import java.util.List;

/**
 * @author chen lu
 * @date 2017/1/10
 * @time 14:15
 */
public class GBTree {
    protected final List<RegTree> regTrees;
    protected final int treeNums;
    protected final int featureNums;

    public GBTree(List<RegTree> regTrees, int treeNums,
                  int featureNums) {
        this.regTrees = regTrees;
        this.treeNums = treeNums;
        this.featureNums = featureNums;
    }

    public double predict(double[] features) {
        double score = 0.0;
        for (RegTree regTree: regTrees) {
            double singleTreeScore = regTree.predict(features);
            score += singleTreeScore;
        }

        return 1.0 / (1.0 + Math.exp(-1 * score));
    }

    public int getTreeNums() {
        return this.treeNums;
    }

    public int getFeatureNums() {
        return this.featureNums;
    }
}
