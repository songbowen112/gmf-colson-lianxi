package com.solson.dal;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 2、求没有发生故障的机器
 * 机器相互依赖，如果A依赖B，那么B发生故障的时候，A也会发生故障，现在输入规则第一行为机器的依赖关系，如A-B,B-C用，进行分隔
 * 输出顺序要和输入顺序保持一致
 * 例：
 * 输入:
 * a1-a2,a2-a3,a4-a6
 * a2,a4
 * 输出：
 * a3,a6
 *
 * 如果没有输出，则用一个，即可
 * 例：
 * 输入:
 * a1-a2,a2-a3
 * a3
 * 输出:
 * ,
 */
public class MachineTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			System.out.println();
			System.out.println("请输入依赖关系:");
			String[] relations = scan.nextLine().split(",");
			Set<String> set = new HashSet();


			System.out.println("请输入故障机器:");
			Set<String> breakdowns = new HashSet<>(Arrays.asList(scan.nextLine().split(",")));

			Map<String,String> map = new HashMap();
			for (String relation : relations) {
				String[] machine = relation.split("-");
				map.put(machine[0], machine[1]);
				set.add(machine[0]);
				set.add(machine[1]);
			}

			ListIterator<Map.Entry<String, String>> li = new ArrayList<Map.Entry<String, String>>(map.entrySet()).listIterator(map.size());

			//map倒序找出故障机器
			while(li.hasPrevious()) {
				Map.Entry<String, String> entry = li.previous();
				if (breakdowns.contains(entry.getValue())) {
					breakdowns.add(entry.getKey());
				}
			}
			//map正序找出故障机器
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (breakdowns.contains(entry.getValue())) {
					breakdowns.add(entry.getKey());
				}
			}

			System.out.println("故障机器:");
			breakdowns.forEach(i->{
				System.out.print(i + "	");
			});

			set.removeAll(breakdowns);
			System.out.println();
			System.out.println("好机器:");
			if (CollectionUtils.isEmpty(set)) {
				System.out.println(",");
			} else {
				set.forEach(i->{
					System.out.print(i + "	");
				});
			}
		}
	}
}
