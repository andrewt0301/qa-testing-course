class Account:
    def __init__(self):
        self.__blocked: bool = False
        self.__bound: int = 1000000
        self.__balance: int = 0
        self.__max_credit: int = -1000

    def deposit(self, _sum: int) -> bool:
        if self.__blocked :
            return False 
        if _sum < 0 or _sum > self.__bound:
            return False
        self.__balance += _sum
        return True

    def withdraw(self, _sum: int) -> bool:
        if self.__blocked :
            return False
        if _sum < 0 or _sum > self.__bound :
            return False
        if self.__balance <= self.__max_credit + _sum:
            return False
        self.__balance -= _sum
        return True

    def get_balance(self) -> int:
        return self.__balance

    def get_max_credit(self) -> int:
        return -self.__max_credit

    def is_blocked(self) -> bool:
        return self.__blocked

    def block(self) -> None:
        self.__blocked = True

    def unblock(self) -> bool:
        if self.__balance < self.__max_credit:
            return False
        self.__blocked = False
        return True

    def set_max_credit(self, mc: int) -> bool:
        if abs(mc) > self.__bound:
            return False
        self.__max_credit = -mc
        return True
