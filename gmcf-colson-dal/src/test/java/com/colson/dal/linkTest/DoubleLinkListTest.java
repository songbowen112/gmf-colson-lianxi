package com.colson.dal.linkTest;

import com.colson.dal.util.DoubleLinkList;

/**
 * Created by Administrator on 2019/6/22.
 */
public class DoubleLinkListTest {

    public static void main(String[] args) {
        DoubleLinkList doubleLinkList = new DoubleLinkList();
        System.out.println(doubleLinkList);
//        doubleLinkList.add(12);
//        doubleLinkList.add(5);
//        doubleLinkList.add(4);
//        doubleLinkList.add(5);
        doubleLinkList.add(2);
        doubleLinkList.add(6);
        doubleLinkList.add(3);
        System.out.println(doubleLinkList);

        doubleLinkList.addFrom(0,11);
        doubleLinkList.addFrom(3,99);
        doubleLinkList.addFrom(doubleLinkList.size(),1);
        doubleLinkList.addFrom(doubleLinkList.size(),2);
        doubleLinkList.addFrom(doubleLinkList.size(),3);
        doubleLinkList.addFrom(doubleLinkList.size(),4);
        doubleLinkList.addFrom(doubleLinkList.size(),5);

        System.out.println(doubleLinkList);


        System.out.println(doubleLinkList.indexOf(99));
        System.out.println(doubleLinkList);

    }
}
