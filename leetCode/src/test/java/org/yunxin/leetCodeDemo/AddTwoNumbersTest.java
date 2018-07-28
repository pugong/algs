package org.yunxin.leetCodeDemo;

import junit.framework.TestCase;

/**
 * Created by lpug on 2018/7/27.
 */
public class AddTwoNumbersTest extends TestCase {

    public void testAddTwoNumbers() throws Exception {
        // Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
        // Output: 7 -> 0 -> 8

        int[] firstArray = {2,4,3};
        ListNode firstNode = BuildListNode(firstArray);

        int[] secondArray = {5, 6, 4};
        ListNode secondNode = BuildListNode(secondArray);


        ListNode tmpNode = new AddTwoNumbers().addTwoNumbers(firstNode, secondNode);

        int[] resultArray = {7, 0, 8};
        assertTrue(VerifyListNode(tmpNode, resultArray));
    }

    ListNode BuildListNode(int[] array) {
        ListNode dummyNode = new ListNode(0);
        ListNode currNode = dummyNode;
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                currNode.next = new ListNode(array[i]);
                currNode = currNode.next;
            }
        }
        return  dummyNode.next;
    }

    boolean VerifyListNode(ListNode tmpNode, int[] array) {
        boolean result = true;
        if(tmpNode == null)
            return false;
        if(array == null || array.length == 0)
            return false;

        for(int i = 0; i< array.length; i++) {
            if(tmpNode.val != array[i])
                return false;
            tmpNode = tmpNode.next;
        }
        return result;
    }
}