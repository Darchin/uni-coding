import time
import numpy as np
from sympy import *
x = Symbol('x')
from random import randrange

# --------------------------------------------------------------------- #
# Algoithm implementation using symbolic math and lagrange coefficients #
# --------------------------------------------------------------------- # 
def HermiteInterpolation(x_list,f,d):
    n = len(x_list)
    def LagrangeCoefficient(j):
        # Lagrange Coefficient
        LC = 1
        for i in range(0,n):
            if i!=j:
                LC = LC * (x-x_list[i])/(x_list[j]-x_list[i])
        return LC
        
    LC_list = [LagrangeCoefficient(i) for i in range(0,n)]

    # Instead of lambdifying Lagrange coefficient derivatives,
    # we lambdify the lagrange coefficients themselves in order to use them in the above formula.
    LC_lambda_list = [lambdify(x,LC,"numpy") for LC in LC_list]


    def CentralDifference(LC,x_0):
        # The value chosen for h here seems to be sweetspot for this specific calculation,
        # as choosing smaller h tends to result in signifiantly large errors
        # as we reach the computers floating point accuracy.
        h = 10**-9
        return (LC(x_0+h) - LC(x_0-h))/(2*h)
    
    # Calculating and storing the values of the derivatives at each point
    LCD_value_list = [CentralDifference(LC_lambda_list[i],x_list[i]) for i in range(n)]

    H_list = [(1-2*(x-x_list[j])*LCD_value_list[j])*(LC_list[j]**2) for j in range(0,n)]

    HH_list = [(x-x_list[j])*(LC_list[j]**2) for j in range(0,n)]

    hermite_polynomial = 0
    for j in range(0,n):
        hermite_polynomial = hermite_polynomial + f[j]*H_list[j] + d[j]*HH_list[j]
    return hermite_polynomial

n = np.arange(1,30).tolist() # 1,2,...,13
run_times = []
for i in n:
    x_list = []
    for j in range(i):
        x_list.append(randrange(10**8,10**9))
        x_list.append(randrange(-10**9,-10**8))
    x_list = [x/(10**7) for x in x_list]

    f_list = []
    for j in range(i):
        f_list.append(randrange(10**8,10**9))
        f_list.append(randrange(-10**9,-10**8))
    f_list = [x/(10**7) for x in x_list]

    d_list = []
    for j in range(i):
        d_list.append(randrange(10**8,10**9))
        d_list.append(randrange(-10**9,-10**8))
    d_list = [x/(10**7) for x in x_list]

    start_time = time.time()
    HermiteInterpolation(x_list,f_list,d_list)
    run_times.append(time.time() - start_time)

print(n)
print(run_times)

# p = np.poly1d(np.polyfit(n,run_times,3))
# t = np.linspace(1, 20, 200)
# mpl.plot(n, run_times, 'o', t, p(t), '-')
# mpl.xticks(np.arange(min(t),max(t)+1,1))
# mpl.ylim(0,10)
# mpl.show()