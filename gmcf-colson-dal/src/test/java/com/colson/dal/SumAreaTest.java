package com.colson.dal;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 1、求图形面积
 * 第一行输入数据的个数和终点
 * 如，
 * 4,8
 * 1,1
 * 2,3
 * 4,2
 * 5，-2
 * 其中第二个为y轴的偏移量，到下个坐标的时候发生y轴的偏移，
 * 问最终和x轴的面积为多少？
 */
public class SumAreaTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("开始?(Y/N)");
		while ("Y".equals(scan.nextLine())) {
			Integer area = 0;
			System.out.println("请输入数据的个数和终点:");
			String[] rule = scan.nextLine().split(",");
			Integer number = Integer.valueOf(rule[0]);
			Integer destination = Integer.valueOf(rule[1]);

			Integer[] xArr = new Integer[number];
			Integer[] yArr = new Integer[number];
			Integer ySum = 0;

			for (Integer i = 0; i < number; i++) {
				System.out.println("请输入第" + (i + 1) + "个数据:");
				String[] split = scan.nextLine().split(",");
				int x = Integer.valueOf(split[0]);
				int y = Integer.valueOf(split[1]);
				xArr[i] = x;
				yArr[i] = y;
			}
			System.out.println();

			for (int i = 0; i < xArr.length; i++) {
				ySum += yArr[i];

				if (i < xArr.length - 1) {
					Integer x = xArr[i + 1] - xArr[i];
					Integer y = ySum;
					Integer everyArea = x * y > 0 ? x * y : -x * y;;
					area += everyArea;
				} else {
					Integer x = destination - xArr[i];
					Integer y = ySum;
					Integer everyArea = x * y > 0 ? x * y : -x * y;
					area += everyArea;
				}
			}
			System.out.println("总面积为:" + area);
		}

	}
}
