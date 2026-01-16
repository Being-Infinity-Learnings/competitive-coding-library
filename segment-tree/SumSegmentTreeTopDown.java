class SumSegmentTreeTopDown {
    private int size;
    private long[] tree;
    private long defaultValue = 0;
    private int stRoot;
    private int stLeft;
    private int stRight;

    public SumSegmentTreeTopDown(int n) {
        size = 1;
        while (size < n) {
            size *= 2;
        }
        stLeft = 0;
        stRight = size - 1;
        stRoot = 1;
        tree = new long[2 * size];
    }

    public SumSegmentTreeTopDown(int a[]) {
        this(a.length);
        build(stRoot, stLeft, stRight, a);
    }

    public void pointReplace(int index, long value) {
        pointReplace(stRoot, stLeft, stRight, index, value);
    }

    public long query(int left, int right) {
        return query(stRoot, stLeft, stRight, left, right - 1);
    }   

    private void merge(int stRoot) {
        tree[stRoot] = tree[2 * stRoot] + tree[2 * stRoot + 1];
    }

    private void build(int stRoot, int stLeft, int stRight, int[] a) {
        if (stLeft == stRight) {
            if (stLeft < a.length) {
                tree[stRoot] = a[stLeft];
            }
            return;
        }
        int mid = stLeft + (stRight - stLeft) / 2;
        build(2 * stRoot, stLeft, mid, a);
        build(2 * stRoot + 1, mid + 1, stRight, a);
        merge(stRoot);
    }  

    private void pointReplace(int stRoot, int stLeft, int stRight, int index, long value) {
        if (index < stLeft || index > stRight) {
            return;
        }
        if (stLeft == stRight) {
            tree[stRoot] = value;
            return;
        }
        int mid = stLeft + (stRight - stLeft) / 2;
        if (index <= mid) {
            pointReplace(2 * stRoot, stLeft, mid, index, value);
        } else {
            pointReplace(2 * stRoot + 1, mid + 1, stRight, index, value);
        }
        merge(stRoot);
    }

    private long query(int stRoot, int stLeft, int stRight, int left, int right) {
        if (right < stLeft || left > stRight) {
            return defaultValue;
        }
        if (left <= stLeft && stRight <= right) {
            return tree[stRoot];
        }
        int mid = stLeft + (stRight - stLeft) / 2;
        long sumLeft = query(2 * stRoot, stLeft, mid, left, right);
        long sumRight = query(2 * stRoot + 1, mid + 1, stRight, left, right);
        return sumLeft + sumRight;
    }
}
