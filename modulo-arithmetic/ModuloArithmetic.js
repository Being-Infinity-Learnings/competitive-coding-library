class ModuloArithmetic {
  static add(a, b, mod) {
    return ( (a % mod) + (b % mod) ) % mod;
  }

  static subtract(a, b, mod) {
    return ( (a % mod) - (b % mod) + mod ) % mod;
  }

  static multiply(a, b, mod) {
    return ( (a % mod) * (b % mod) ) % mod;
  }

  static power(base, exponent, mod) {
    let result = 1;
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

  static modInverse(a, mod){
    return this.power(a, mod - 2, mod);
  }

  static divide(a, b, mod){
    return (a % mod * this.modInverse(b, mod) % mod) % mod;
  }
}