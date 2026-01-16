class ModuloArithmetic{
    public static long add(long a, long b, long mod){
        return ((a % mod) + (b % mod)) % mod;
    }
    public static long subtract(long a, long b, long mod){
        return ((a % mod) - (b % mod) + mod) % mod;
    }
    public static long multiply(long a, long b, long mod){
        return ((a % mod) * (b % mod)) % mod;
    }
    public static long power(long a, long b, long mod){
        long result = 1;
        a = a % mod;
        while(b > 0){
            if((b & 1) == 1){
                result = (result * a) % mod;
            }
            a = (a * a) % mod;
            b = b >> 1;
        }
        return result;
    }
    public static long modInverse(long a, long mod){
        return power(a, mod - 2, mod);
    }
    public static long divide(long a, long b, long mod){
        return (a % mod * modInverse(b, mod) % mod) % mod;
    }
}
