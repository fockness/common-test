package com.example.demo.cases.algorithm;

/**
* @author shibin
* @version ����ʱ�䣺2019��4��18�� ����10:16:55
* 	�ϲ�������������
*/

public class MergeTwoSortedLists {

	public static void main(String[] args) {
		ListNode list1 = new ListNode(1);
		ListNode list1_2 = new ListNode(2);
		ListNode list1_3 = new ListNode(3);
		ListNode list2 = new ListNode(1);
		ListNode list2_2 = new ListNode(2);
		ListNode list2_3 = new ListNode(4);
		list1.next = list1_2;
		list1_2.next = list1_3;
		list2.next = list2_2;
		list2_2.next = list2_3;
		ListNode result = mergeTwoLists(list1, list2);
		while(result != null) {
			System.out.println(result.value);
			result = result.next;
		}
	}
	
	private static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode head =  new ListNode(0);
		ListNode cur = head;
		while(list1 != null && list2 != null) {
			if(list1.value < list2.value) {
				cur.next = list1;
				cur = cur.next;
				list1 = list1.next;
			}else {
				cur.next = list2;
				cur = cur.next;
				list2 = list2.next;
			}
		}
		if(list1 == null && list2 != null) {
			cur.next = list2;
		}
		if(list2 == null && list1 != null) {
			cur.next = list1;
		}
		return head.next;
	}

	static class ListNode{
		private ListNode next;
		private Integer value;
		public ListNode(Integer value) {
			this.value = value;
		}
	}
}


