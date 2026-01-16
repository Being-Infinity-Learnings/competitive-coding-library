class ModuloArithmetic:
    @staticmethod
    def add(a: int, b: int, mod: int) -> int:
        return (a + b) % mod

    @staticmethod
    def subtract(a: int, b: int, mod: int) -> int:
        return (a - b) % mod

    @staticmethod
    def multiply(a: int, b: int, mod: int) -> int:
        return (a * b) % mod

    @staticmethod
    def power(base: int, exponent: int, mod: int) -> int:
        result = 1
        base = base % mod
        while exponent > 0:
            if (exponent % 2) == 1:
                result = (result * base) % mod
            exponent = exponent >> 1
            base = (base * base) % mod
        return result
    
    @staticmethod
    def mod_inverse(a: int, mod: int) -> int:   
        return ModuloArithmetic.power(a, mod - 2, mod)
    
    @staticmethod
    def divide(a: int, b: int, mod: int) -> int:
        b_inv = ModuloArithmetic.mod_inverse(b, mod)
        return (a * b_inv) % mod
