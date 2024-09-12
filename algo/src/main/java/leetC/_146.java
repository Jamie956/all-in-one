package leetC;

import java.util.HashMap;
import java.util.Map;

public class _146 {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        int v1 = lruCache.get(1);
        lruCache.put(3,3);
        int v2 = lruCache.get(2);
        lruCache.put(4,4);
        int v3 = lruCache.get(1);
        int v4 = lruCache.get(3);
        int v5 = lruCache.get(4);

    }

    static class LRUCache {
        public int capacity;
        //为了移除最旧的节点，需要size 知道实际元素的个数
        public int size;
        //dummy 节点是为了快速访问 head，tail 和方便增删，当作指针用
        public Node dummyHead;
        public Node dummyTail;

        //Hash 根据 key 查询节点，O1复杂度
        Map<Integer, Node> cache;
        //增删O1 复杂度，使用双向链表
        class Node {
            public Node next;
            public Node pre;
            public int key;
            public int val;
            public Node() {}
            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>(capacity);
            this.dummyHead = new Node();
            this.dummyTail = new Node();
            this.size = 0;
            dummyHead.next = dummyTail;
            dummyTail.pre = dummyHead;
        }

        public int get(int key) {
            Node node = cache.get(key);
            if (node == null) {
                return -1;
            }
            unlink(node);
            mvHead(node);
            return node.val;
        }

        public void put(int key, int value) {
            Node node = cache.get(key);
            if (node != null) {
                //节点值已经存在，只需要更新值
                node.val = value;
                unlink(node);
                mvHead(node);
                return;
            }
            node = new Node(key, value);
            if (size < capacity) {
                //容量没满，新节点移到最前
                mvHead(node);
                size++;
            } else {
                //remove tail
                //容量满了，移除最后一个节点
                Node tail = dummyTail.pre;
                Node newTail = tail.pre;
                newTail.next = dummyTail;
                dummyTail.pre = newTail;
                cache.remove(tail.key);
                //add to head
                mvHead(node);
            }
            cache.put(key, node);
        }

        //移除节点且把前后节点连接起来
        public void unlink(Node node) {
            Node tempPre = node.pre;
            Node tempNext = node.next;
            tempPre.next = tempNext;
            tempNext.pre = tempPre;
        }

        //节点移到最前
        public void mvHead(Node node) {
            Node head = dummyHead.next;
            dummyHead.next = node;
            node.pre = dummyHead;
            node.next = head;
            head.pre = node;
        }
    }
}
