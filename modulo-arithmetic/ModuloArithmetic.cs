class ModuloArithmetic
{
    public static long Add(long a, long b, long mod){
        return ((a % mod) + (b % mod)) % mod;
    }
    public static long Subtract(long a, long b, long mod){
        return ((a % mod) - (b % mod) + mod) % mod;
    }
    public static long Multiply(long a, long b, long mod){
        return ((a % mod) * (b % mod)) % mod;
    }
    public static long Power(long a, long b, long mod){
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
    public static long ModInverse(long a, long mod){
        return Power(a, mod - 2, mod);
    }
    public static long Divide(long a, long b, long mod){
        return (a % mod * ModInverse(b, mod) % mod) % mod;
    }
}