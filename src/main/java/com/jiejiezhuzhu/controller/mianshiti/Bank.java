package com.jiejiezhuzhu.controller.mianshiti;
import java.util.ArrayList;
import java.util.Iterator;

public class Bank {
    int num;
    ArrayList list = new ArrayList<>();

    public Bank(int num) {
        this.num = num;
    }

    public void goodbye(int i){

        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).equals(i)){
                list.remove(j);
            }
        }

    }
    public int hello(){
        int set = 0;
        int maxi = 0;
        int maxj = 0;

        if (list.isEmpty()){ //判断是否是第一个人
            list.add(set);
            return set;
        }

        for (int j = num-1; j > 0; j--) {
            int flag = 0;//判断位置是否有人
//            for (int k = 0; k < list.size(); k++) {
//                if (list.get(k).equals(j)){
//                    flag = 1;
//                }
//            }
            if(list.contains((Integer)j)){ //优化
                flag = 1;
            }
            //没人就开始选位置了
            if (flag == 0){
                int maxii = 0;
                int maxjj = num+1;

//                for (int k = 0; k < list.size(); k++) {
//
//
//                }
                Iterator iterator1 = list.iterator();
                while(iterator1.hasNext()){
                    int ite = (int)iterator1.next();
                    maxii += Math.abs(ite-j);//在题目的基础上做了一点点修改，为了防疫更加有效不是选择数字最小的而是远离群体的
                    if (maxjj>Math.abs(ite-j)){
                        maxjj = Math.abs(ite-j);//找出离最近的人最远的位置
                    }
                }
                if (maxjj>maxj){
                    maxi=maxii;
                    set = j;
                    maxj=maxjj;
                } else if (maxjj==maxj&&maxii>=maxi) { //删除这个判断就是题目要求的最小数字了
                    maxi=maxii;
                    set = j;
                    maxj=maxjj;
                }
            }

        }
        list.add(set);
        return set;
    }
}
