class StringHashingMultiple
{
    static final long BASE = 31;
    long[] hashPrimes = {
        1000000007L, 1000000009L
    };
    int primes;
    int n; 
    String s;
    long[][] powersOfBase;
    long[][] inversesOfPowersOfBase;
    long[][] prefixHashes;

    public StringHashingMultiple(String s){
        this.s = s;
        n = s.length();
        primes = hashPrimes.length;
        powersOfBase = new long[primes][n + 1];
        inversesOfPowersOfBase = new long[primes][n + 1];
        prefixHashes = new long[primes][n + 1];

        for(int i = 0; i < primes; i++){
            powersOfBase[i][0] = 1;
            inversesOfPowersOfBase[i][0] = 1;
            prefixHashes[i][0] = 0;
            for(int j = 1; j <= n; j++){
                powersOfBase[i][j] = (powersOfBase[i][j - 1] * BASE) % hashPrimes[i];
            }
            inversesOfPowersOfBase[i][n] = ModuloArithmetic.modularInverse(powersOfBase[i][n], hashPrimes[i]);
            for(int j = n - 1; j >= 0; j--){
                inversesOfPowersOfBase[i][j] = (inversesOfPowersOfBase[i][j + 1] * BASE) % hashPrimes[i];
            }
        }

        for(int i = 0; i < primes; i++){
            for(int j = 1; j <= n; j++){
                prefixHashes[i][j] = (prefixHashes[i][j - 1] + (s.charAt(j - 1) - 'a' + 1) * powersOfBase[i][j - 1]) % hashPrimes[i];
            }
        }
    }

    long[] getHash(int l, int r){
        long[] hashValues = new long[primes];
        for(int i = 0; i < primes; i++){
            hashValues[i] = ModuloArithmetic.modSubtract(prefixHashes[i][r + 1], prefixHashes[i][l], hashPrimes[i]);
            hashValues[i] = (hashValues[i] * inversesOfPowersOfBase[i][l]) % hashPrimes[i];
        }
        return hashValues;
    }
}

class ModuloArithmetic{
    public static long binExponentiation(long a, long b, long m) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = (res * a) % m;
            }
            a = (a * a) % m;
            b >>= 1;
        }
        return res;
    }
    public static long modularInverse(long a, long m) {
        return binExponentiation(a, m - 2, m);
    }
    public static long modSubtract(long a, long b, long m) {
        return ((a % m) - (b % m) + m) % m;
    }
}
