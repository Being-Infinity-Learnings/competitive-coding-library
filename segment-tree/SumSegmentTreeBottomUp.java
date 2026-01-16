class SumSegmentTreeBottomUp {
    private int size;
    private long[] tree;

    public SumSegmentTreeBottomUp(int n) {
        size = 1;
        while (size < n) {
            size *= 2;
        }
        tree = new long[2 * size];
    }
    public SumSegmentTreeBottomUp(int a[]) {
        this(a.length);
        for (int i = 0; i < a.length; i++) {
            tree[size + i] = a[i];
        }
        for (int i = size - 1; i > 0; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    public void update(int index, long value) {
        index += size;
        tree[index] = value;
        while (index > 1) {
            index /= 2;
            tree[index] = tree[2 * index] + tree[2 * index + 1];
        }
    }

    public long query(int left, int right) {
        left += size;
        right += size;
        long sum = 0;
        while (left < right) {
            if ((left & 1) == 1) {
                sum += tree[left++];
            }
            if ((right & 1) == 1) {
                sum += tree[--right];
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }   
}
