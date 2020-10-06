"""
Copyright (c) 2020 ISP RAS.
109004, A. Solzhenitsina, 25, Moscow, Russia.
All rights reserved.

Created on Sep 13, 2020
"""
class GCD:
    def gcd(self, x:int, y:int)->int:
        t:int;

        if(x < 0): x = -x
        if(y < 0): y = -y

        while(y != 0):
            if(y > x):
                x = y-x;
                y = y-x;
                x = x+y;
            if(y == 0): return x
            t = y
            y = x % y
            x = t
        return x