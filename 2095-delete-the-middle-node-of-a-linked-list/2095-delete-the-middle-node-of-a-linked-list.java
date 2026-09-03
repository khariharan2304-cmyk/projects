class Solution {
    public ListNode deleteMiddle(ListNode head) {
        if(head==null || head.next==null){
            return null;
        }

        ListNode slow=head;
        ListNode fast=head;
        ListNode prev=null;

        //find middle
        while(fast!=null && fast.next!=null){
            prev=slow;
            slow=slow.next;
            fast=fast.next.next;
        }

        //delete
        prev.next=slow.next;

        return head;
    }
}
//Please click the ⬆️ UPARROW ⬆️ button in the bottom left corner of your screen
//That means you Like👍the solution