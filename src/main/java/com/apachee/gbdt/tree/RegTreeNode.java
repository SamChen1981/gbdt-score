package com.apachee.gbdt.tree;

/**
 * @author chen lu
 * @date 2017/1/11
 * @time 15:54
 */
public class RegTreeNode {
    private int left;
    private int right;

    //指向的特征维度
    private int pointer;
    private int index;
    private double value;

    private boolean defaultLeft;

    public int getDefaultNode() {
        return defaultLeft ? left: right;
    }

    public void setDefaultLeft(boolean defaultLeft) {
        this.defaultLeft = defaultLeft;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("value:").append(value).append(";");
        sb.append("left:").append(left).append(";");
        sb.append("right:").append(right).append(";");
        sb.append("index:").append(index).append(";");
        sb.append("pointer:").append(pointer).append(";");
        return sb.toString();
    }
}
