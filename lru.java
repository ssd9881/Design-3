class LRUCache {
    class Node{
        int key; int val;
        Node next; Node prev;
        public Node(int key, int value){
            this.key = key;
            this.val = value;     
        }
    }
    private HashMap<Integer, Node> map;
    private Node head; private Node tail;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        removeHead(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            removeHead(node);
            addToHead(node);
        }else{
            if(map.size()==capacity){
                Node tailPrev = this.tail.prev;
                removeHead(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key,value);
            addToHead(newNode);
            map.put(key,newNode);
        }
    }

    private void removeHead(Node node){
        node.next.prev =  node.prev;
        node.prev.next = node.next;
    }

    private void addToHead(Node node){
        node.next = this.head.next;
        node.prev = this.head;
        this.head.next = node;
        node.next.prev = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */