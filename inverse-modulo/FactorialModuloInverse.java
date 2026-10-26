class FactorialModuloInverse{
    long fact[];
    long ifact[];
    long mod;
    static final long DEFAULT_MOD = 1000000007;
    public FactorialModuloInverse(int n){
        this(n, DEFAULT_MOD);
    }
    public FactorialModuloInverse(int n, long m){
        this.mod = m;
        fact = new long[n + 1];
        fact[0] = 1;
        for(int i = 1; i <= n; i++){
            fact[i] = (fact[i - 1] * i) % mod;
        }
        ifact = new long[n + 1];
        ifact[n] = modInverse(fact[n], mod);
        for(int i = n - 1; i >= 0; i--){
            ifact[i] = (ifact[i + 1] * (i + 1)) % mod;
        }
    }

    // Extended Euclidean Algorithm to find modular inverse
    // O(log m)
    private long modInverse(long a, long m) {
        if (m == 1) return 0;
        a %= m;
        if (a < 0) a += m;
        long m0 = m, x = 1, y = 0;
        while (m != 0) {
            long q = a / m;
            long t = m; m = a % m; a = t;
            t = y; y = x - q * y; x = t;
        }
        if (a != 1) return -1;   // gcd != 1, no inverse
        return ((x % m0) + m0) % m0;
    }

    public long factorial(int n){
        if(n < 0 || n >= fact.length) throw new IllegalArgumentException("n out of bounds");
        return fact[n];
    }
    public long inversefactorial(int n){
        if(n < 0 || n >= ifact.length) throw new IllegalArgumentException("n out of bounds");
        return ifact[n];
    }
}
