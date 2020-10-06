# doub.py
# Copyright (C) 2006, 2007 Martin Jansche
#
# Permission is hereby granted, free of charge, to any person obtaining
# a copy of this software and associated documentation files (the
# "Software"), to deal in the Software without restriction, including
# without limitation the rights to use, copy, modify, merge, publish,
# distribute, distribute with modifications, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be
# included in all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
# EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
# MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
# IN NO EVENT SHALL THE ABOVE COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
# DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
# OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR
# THE USE OR OTHER DEALINGS IN THE SOFTWARE.
#
# Except as contained in this notice, the name(s) of the above copyright
# holders shall not be used in advertising or otherwise to promote the
# sale, use or other dealings in this Software without prior written
# authorization.

"""
Support for IEEE 754 double-precision floating-point numbers.  The
purpose is to work around the woefully inadequate built-in
floating-point support in Python.  Functionality is a blend of the
static members of java.lang.Double and bits of <float.h> and <math.h>
from C99.
"""

from __future__ import division
from math import fabs as _fabs
import struct as _struct


def doubleToRawLongBits(value):
    """
    @type  value: float
    @param value: a Python (double-precision) float value

    @rtype: long
    @return: the IEEE 754 bit representation (64 bits as a long integer)
             of the given double-precision floating-point value.
    """
    # pack double into 64 bits, then unpack as long int
    return _struct.unpack('Q', _struct.pack('d', value))[0]


def longBitsToDouble(bits):
    """
    @type  bits: long
    @param bits: the bit pattern in IEEE 754 layout

    @rtype:  float
    @return: the double-precision floating-point value corresponding
             to the given bit pattern C{bits}.
    """
    return _struct.unpack('d', _struct.pack('Q', bits))[0]


NAN               = longBitsToDouble(0x7ff8000000000000)
POSITIVE_INFINITY = longBitsToDouble(0x7ff0000000000000)
MAX_VALUE         = longBitsToDouble(0x7fefFfffFfffFfff)
MIN_NORMAL        = longBitsToDouble(0x0010000000000000)
MIN_VALUE         = longBitsToDouble(0x0000000000000001)
NEGATIVE_ZERO     = longBitsToDouble(0x8000000000000000)
NEGATIVE_INFINITY = longBitsToDouble(0xFff0000000000000)

# same as DBL_EPSILON in <float.h>
EPSILON = longBitsToDouble(doubleToRawLongBits(1.0) + 1) - 1.0


def fpclassify(value):
    """
    @type  value: float
    @param value: a Python (double-precision) float value

    @rtype:  str
    @return: a string indicating the classification of the given value as
             one of 'NAN', 'INFINITE', 'ZERO', 'SUBNORMAL', or 'NORMAL'.
    """
    bits = doubleToRawLongBits(value)
    exponent = (bits >> 52) & 0x7ff
    if exponent == 0x7ff:
        # INFINITE or NAN
        mantissa = bits & 0x000fFfffFfffFfff
        if mantissa == 0:
            return 'INFINITE'
        else:
            return 'NAN'
    elif exponent == 0:
        # ZERO or SUBNORMAL
        mantissa = bits & 0x000fFfffFfffFfff
        if mantissa == 0:
            return 'ZERO'
        else:
            return 'SUBNORMAL'
    else:
        assert exponent > 0 and exponent < 0x7ff
        return 'NORMAL'


def isnan(value):
    """
    @type  value: float
    @param value: a Python (double-precision) float value

    @rtype:  bool
    @return: C{True} if given value is not a number;
             C{False} if it is a number.
    """
    return value != value


def isinf(value):
    """
    @type  value: float
    @param value: a Python (double-precision) float value

    @rtype:  bool
    @return: C{True} if the given value represents positive or negative
             infinity; C{False} otherwise.
    """
    return value == POSITIVE_INFINITY or value == NEGATIVE_INFINITY


def isfinite(value):
    """
    @type  value: float
    @param value: a Python (double-precision) float value

    @rtype:  bool
    @return: C{True} if the given value is a finite number;
             C{False} if it is NaN or infinity.
    """
    return (value == value
            and value != POSITIVE_INFINITY
            and value != NEGATIVE_INFINITY)


def isnormal(value):
    """
    @type  value: float
    @param value: a Python (double-precision) float value

    @rtype:  bool
    @return: C{True} if the given value is a normal floating-point number;
             C{False} if it is NaN, infinity, or a denormalized
             (subnormal) number.
    """
    if value != value:
        return False
    value = abs(value)
    return value >= MIN_NORMAL and value <= MAX_VALUE


def doubleToLongBits(value):
    """
    Similar to L{doubleToRawLongBits}, but standardize NaNs.

    @type  value: float
    @param value: a Python (double-precision) float value

    @rtype:  long
    @return: the IEEE 754 bit representation (64 bits) of the given
             floating-point value if it is a number, or the bit
             representation of L{NAN} if it is not a number.
    """
    if value != value:
        # value is NaN, standardize to canonical non-signaling NaN
        return 0x7ff8000000000000 # NaN bit pattern
    else:
        return doubleToRawLongBits(value)


def signbit(value):
    """
    Test whether the sign bit of the given floating-point value is
    set.  If it is set, this generally means the given value is
    negative.  However, this is not the same as comparing the value
    to C{0.0}.  For example:

    >>> NEGATIVE_ZERO < 0.0
    False

    since negative zero is numerically equal to positive zero.  But
    the sign bit of negative zero is indeed set:

    >>> signbit(NEGATIVE_ZERO)
    True
    >>> signbit(0.0)
    False

    @type  value: float
    @param value: a Python (double-precision) float value

    @rtype:  bool
    @return: C{True} if the sign bit of C{value} is set;
             C{False} if it is not set.
    """
    return (doubleToRawLongBits(value) >> 63) == 1


def copysign(x, y):
    """
    Return a floating-point number whose absolute value matches C{x}
    and whose sign matches C{y}.  This can be used to copy the sign of
    negative zero, as follows:

    >>> copysign(1, NEGATIVE_ZERO)
    -1.0

    @type  x: float
    @param x: the floating-point number whose absolute value is to be copied

    @type  y: number
    @param y: the number whose sign is to be copied

    @rtype:  float
    @return: a floating-point number whose absolute value matches C{x}
             and whose sign matches C{y}.

    @postcondition: (isnan(result) and isnan(x)) or abs(result) == abs(x)
    @postcondition: signbit(result) == signbit(y)
    """
    if y < 0 or (y == 0 and signbit(y)):
        result = - _fabs(x)
    else:
        result = _fabs(x)
    assert (isnan(result) and isnan(x)) or _fabs(result) == _fabs(x)
    assert signbit(result) == signbit(y)
    return result


def fdiv(x, y):
    """
    Divide two numbers according to IEEE 754 floating-point semantics.

    Division by zero does not raise an exception, but produces
    negative or positive infinity or NaN as a result.

    >>> fdiv(+1, +0.0) == POSITIVE_INFINITY
    True
    >>> fdiv(-1, +0.0) == NEGATIVE_INFINITY
    True
    >>> fdiv(+1, -0.0) == NEGATIVE_INFINITY
    True
    >>> fdiv(-1, -0.0) == POSITIVE_INFINITY
    True
    >>> isnan(fdiv(0.0, 0.0))
    True

    @type  x: number
    @param x: the dividend

    @type  y: number
    @param y: the divisor

    @rtype:  float
    @return: the quotient C{x/y} with division carried out according
             to IEEE 754 semantics.
    """
    if x != x or y != y:
        return NAN
    elif y == 0:
        # treat y==0 specially to avoid raising a ZeroDivisionError
        if x == 0:
            # 0/0
            return NAN
        elif (x < 0) == signbit(y):
            # signs cancel, result is positive
            return POSITIVE_INFINITY
        else:
            # opposite signs, result is negative
            return NEGATIVE_INFINITY
    elif x == 0:
        # this case is treated specially to handle e.g. fdiv(0, 1<<1024)
        if signbit(x) == (y < 0):
            # signs cancel, result is positive
            return 0.0
        else:
            # opposite signs, result is negative
            #return -0.0
            #^^^^^^^^^^^ this doesn't work in Python 2.5 due to a bug
            return NEGATIVE_ZERO
    else:
        try:
            # NB: __future__.division MUST be in effect.  Otherwise
            # integer division will be performed when x and y are both
            # integers.  Unfortunately the current (Python 2.4, 2.5)
            # behavior of __future__.division is weird: 1/(1<<1024)
            # (both arguments are integers) gives the expected result
            # of pow(2,-1024), but 1.0/(1<<1024) (mixed integer/float
            # types) results in an overflow error.  The surrounding
            # try/except block attempts to work around this issue.
            return x / y
        except OverflowError:
            # only necessary to handle big longs: scale them down
            # until they fit into a double
            assert x == x and x != 0
            assert y == y and y != 0
            negative = (x < 0) == (y > 0)
            x = abs(x)
            y = abs(y)
            n = 0
            s = 1
            while n < 1024:
                n += 1
                s <<= 1
                x,q = divmod(x, s)
                y,r = divmod(y, s)
                #print 'n=%d s=%d x=%g q=%g y=%g r=%g' % (n, s, x, q, y, r)
                try:
                    result = (x+q/s) / (y+r/s)
                except OverflowError:
                    continue
                if negative:
                    return -result
                else:
                    return result
            # scaling didn't work, so attempt to carry out division
            # again, which will result in an exception
            return x / y


if __name__ == '__main__':
    # run some basic sanity checks
    assert doubleToRawLongBits(1.0) == 0x3ff0000000000000

    NONSTD_NAN = longBitsToDouble(0x7ff8000000000001)
    assert isnan(NONSTD_NAN)
    assert doubleToLongBits(NONSTD_NAN) == doubleToLongBits(NAN)
    assert isnan(POSITIVE_INFINITY + NEGATIVE_INFINITY)
    assert isnan(0 * POSITIVE_INFINITY)
    assert isnan(0 * NEGATIVE_INFINITY)

    BIG = 1 << 1024
    for x in (NAN,   BIG,  MAX_VALUE,  1.0, 0.0,
              -NAN, -BIG, -MAX_VALUE, -1.0, NEGATIVE_ZERO):
        assert isnan(fdiv(x, NAN))
        assert isnan(fdiv(NAN, x))

    assert fdiv(BIG, 0) == POSITIVE_INFINITY
    assert fdiv(-BIG, 0) == NEGATIVE_INFINITY
    assert fdiv(0, BIG) == 0
    assert fdiv(0, -BIG) == 0
    assert signbit(fdiv(0, -BIG))
    assert fdiv(NEGATIVE_ZERO, BIG) == 0
    assert signbit(fdiv(NEGATIVE_ZERO, BIG))

    assert fdiv(-1e-200, +1e200) == NEGATIVE_ZERO
    assert fdiv(+1e-200, -1e200) == NEGATIVE_ZERO
    assert fdiv(-1e-200, +(1<<1024)) == NEGATIVE_ZERO
    assert fdiv(+1e-200, -(1<<1024)) == NEGATIVE_ZERO

    assert fdiv(+BIG, 0.5) == POSITIVE_INFINITY
    assert fdiv(-BIG, 0.5) == NEGATIVE_INFINITY

    assert copysign(0, -1) == NEGATIVE_ZERO

    import doctest
    doctest.testmod()

# eof
