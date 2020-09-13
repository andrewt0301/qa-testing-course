/**
 * Copyright (c) 2009 ISP RAS.
 * 109004, A. Solzhenitsina, 25, Moscow, Russia.
 * All rights reserved.
 *
 * $Id$
 * Created on Dec 22, 2015
 *
 */

package root.gcd;

/**
 * @author Victor Kuliamin
 *
 */
public class GCD
{
    public int gcd(int x, int y)
    {
        int t;

        if(x < 0) x = -x;
        if(y < 0) y = -y;

        while(y != 0)
        {
            if(y > x)
            {
                x = y-x;
                y = y-x;
                x = x+y;
            }

            if(y == 0) return x;

            t = y;
            y = x%y;
            x = t;
        }
        return x;
    }


}
