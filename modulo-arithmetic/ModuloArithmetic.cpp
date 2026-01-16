class ModuloArithmetic
{
    public:
        static int add(int a, int b, int mod){
            return ( (a % mod) + (b % mod) ) % mod;
        }
        static int subtract(int a, int b, int mod){
            return ( (a % mod) - (b % mod) + mod ) % mod;
        }
        static int multiply(int a, int b, int mod){
            return ( (a % mod) * (b % mod) ) % mod;
        }
        static int power(int base, int exponent, int mod){
            int result = 1;
            base = base % mod;
            while (exponent > 0) {
                if (exponent % 2 == 1) {
                    result = (result * base) % mod;
                }
                exponent = exponent >> 1; // exponent /= 2
                base = (base * base) % mod;
            }
            return result;
        }
        static int modInverse(int a, int mod){
            return power(a, mod - 2, mod);
        }
        static int divide(int a, int b, int mod){
            return (a % mod * modInverse(b, mod) % mod) % mod;
        }
};