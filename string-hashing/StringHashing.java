class StringHashing{
    private static final int DEFAULT_BASE = 131;
    private static final long DEFAULT_MOD = 1_000_000_007L;
    private int base = 131;
    private long mod = 1_000_000_007L;
    private long[] prefixHash;
    private long[] power;

    public StringHashing(String s) {
        this(s, DEFAULT_BASE, DEFAULT_MOD);
    }

    public StringHashing(String s, int base, long mod) {
        int n = s.length();
        this.base = base;
        this.mod = mod;
        prefixHash = new long[n + 1];
        power = new long[n + 1];
        power[0] = 1;
        for (int i = 0; i < n; i++) {
            prefixHash[i + 1] = (prefixHash[i] * this.base + s.charAt(i)) % this.mod;
            power[i + 1] = (power[i] * this.base) % this.mod;
        }
    }

    // 0-based indexing, both inclusive
    public long hash(int left, int right) {
        long hashValue = (prefixHash[right + 1] - (prefixHash[left] * power[right - left + 1]) % this.mod + this.mod) % this.mod;
        return hashValue;
    }   
}
