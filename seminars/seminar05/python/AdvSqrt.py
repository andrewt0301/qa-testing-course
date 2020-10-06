"""
Copyright (c) 2020 ISP RAS.
109004, A. Solzhenitsina, 25, Moscow, Russia.
All rights reserved.

Created on Sep 13, 2020
"""
import math
import doub
class AdvSqrt:
    eps: doub = 2.25e-16
    dgmask: int = 0x7FF0000000000000
    mtmask: int = 0x000FFFFFFFFFFFFF
    dgshift: int = 52
    sqdgadd: int = 0x1FF
    dnrbnd: doub = doub.longBitsToDouble(0x0010000000000000)
    odddeg: int = 0x3FF0000000000000
    evndeg: int = 0x3FE0000000000000
    def sqrt(self, x:doub)->doub:
        if(doub.isnan(x) or x < 0):
            return doub.NAN
        elif(x == 0 or x == 1 or doub.isinf(x)):
            return x
        else:
            dnr: bool = False
            if(x < AdvSqrt.dnrbnd):
                x = x*math.pow(2, AdvSqrt.dgshift)
                dnr = True
            b: int = doub.doubleToRawLongBits(x)
            d: int = (b & AdvSqrt.dgmask) >> AdvSqrt.dgshift
            res: doub = 0
            tmp: doub = 0
            i: int = 0
            
            if((d & 1) != 0):
                b = (b & AdvSqrt.mtmask) | AdvSqrt.odddeg
            else:
                b = (b & AdvSqrt.mtmask) | AdvSqrt.evndeg
            
            x = doub.longBitsToDouble(b)
            res = x
            
            while(abs(x-res*res)/x > AdvSqrt.eps):
                i += 1
                tmp = res
                res = (tmp+x/tmp)/2
            
            b = doub.doubleToRawLongBits(res)
            d = int(math.ceil(d/2))+AdvSqrt.sqdgadd
            if(dnr):
                d -= AdvSqrt.dgshift/2
            
            b = (b & AdvSqrt.mtmask) | int(d << AdvSqrt.dgshift)
            res = doub.longBitsToDouble(b)
            return res