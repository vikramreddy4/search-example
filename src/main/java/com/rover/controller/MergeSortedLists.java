package com.rover.controller;

import java.util.LinkedList;
import java.util.ListIterator;

public class MergeSortedLists {
	public static void main(String[] args) {
		LinkedList<Integer> listA = new LinkedList<Integer>(), listB = new LinkedList<Integer>(); 
		listA.add(1);
		listA.add(3);
		listA.add(5);
		listA.add(7);
		listA.add(9);
		listA.add(10);
		listA.add(11);
		listA.add(17);
		listA.add(19);
		listA.add(20);
		
		listB.add(2);
		listB.add(13);
		listB.add(14);
		listB.add(15);
		listB.add(16);
		listB.add(18);
		System.out.println(mergeLists(listA,listB));
	}

	public static LinkedList<Integer> mergeLists(LinkedList<Integer> listA, LinkedList<Integer> listB) {
		LinkedList<Integer> bigList = null, smallList = null;
		if(listA.size() > listB.size()) {
			bigList = listA;
			smallList = listB;
		}else {
			bigList = listB;
			smallList = listA;
		}
		int ctr = 0;
		merge(bigList, smallList, ctr);
		return bigList;
	}
 	
	private static void merge(LinkedList<Integer> bigList, LinkedList<Integer> smallList, int ctr) {
		if(!smallList.isEmpty()) {
			Integer value = smallList.poll();
			if(ctr < bigList.size()-1) {
				while(value > bigList.get(ctr) && ctr < bigList.size()-1) {
					ctr++;
				}
	 			bigList.add(ctr, value);
	 			merge(bigList, smallList, ctr++);
			}else {
	 			bigList.add(value);
	 			bigList.addAll(smallList);
			}
		}
	}
}