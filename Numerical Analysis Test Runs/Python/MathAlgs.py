import math

##! To call the functions, please go to line 230 !##


# ----------------------------#
# General tools and functions #
# ----------------------------#
# Used to print cleaner exponents, for example 2⁸ instead of 2^8
def superscript(x):
    regular = "-0123456789"
    superscript = "⁻⁰¹²³⁴⁵⁶⁷⁸⁹"
    map = x.maketrans(''.join(regular), ''.join(superscript))
    return x.translate(map)

# Used to print indices
def subscript(x):
    regular = "0123456789"
    subscript = "₀₁₂₃₄₅₆₇₈₉"
    map = x.maketrans(''.join(regular), ''.join(subscript))
    return x.translate(map)

# Returns a numbers sign, or 0
def sign(value):
    match value:
        case _ if value < 0:
            return -1
        case _ if value == 0:
            return 0
        case _ if value > 0:
            return 1


# ---------------#
# Numeral System #
# ---------------#
# Print all numbers in a base 2 numeral system with the given parameters
def printNumbers(digits, min, max):
    power = min
    # calculate the largest significand
    max_num = 2**(digits) - 1 # for example if digits = 4, max_num = 16 - 1 = 15. This is the same as (1111) in base 2.
    num = 1 # Starting number. same as (00...001) in base 2
    positive_numbers = []
    negative_numbers = []
    # formatting rule to print leading and trailing zeroes. 0 means print the leading zeroes,
    # str(digits) tells it how many digits to include in each number, and b means convert it to binary.
    formatting_rule = "0"+str(digits)+"b" 

    while power <= max:
        while num <= max_num:
            # Go through numbers from 1 to max_num and add the numbers in binary format.
            positive_numbers.append("(0."+format(num, formatting_rule)+")"+"×"+"2"+superscript(str(power)))
            num += 1
        power += 1
        num = 1
    # Reverse the positive numbers, and add a minus sign behind them.
    negative_numbers = ["-" + x for x in positive_numbers]
    negative_numbers = reversed(negative_numbers)
    # Print negative numbers and then positive numbers.
    for i in negative_numbers:
        print(i)
    for i in positive_numbers:
        print(i)


# -----------------#
# Bisection Method #
# -----------------#
def Bisection(function, start, end, error, mode):
    
    # Take function as input, in python format
    f = lambda x: eval(function)
    a = start
    b = end
    x = (a+b)/2
    fx = f(x)
    ax = 0
    xb = 0
    counter = 0

    # Check if starting conditions are the solution.
    if f(a) == 0:
        print("The solution is exactly x = {}.".format(a))
        exit()
    elif f(b) == 0:
        print("The solution is exactly x = {}.".format(b))
        exit()
    elif fx == 0:
        print("The solution is exactly x = {}.".format(x))
        exit()

    # Display values of variables on each iteration.
    print("n = {}: x = {:.8f}, a = {:.8f}, b = {:.8f}, f(x) = {:.8f}\n".format(counter,x,a,b,fx))

    # Main loop
    while True:
        ax = sign(f(a)*fx)
        xb = sign(f(b)*fx)

        # Check which sub-interval the root belongs to,
        # and then set the endpoints to the new values.
        # If there are multiple roots in the given interval,
        # find the smallest one as default.
        if ax == -1:
            b = x
            old_x = x
            x = (a+b)/2
        elif xb == -1:
            a = x
            old_x = x
            x = (a+b)/2
        else:
            print("The function f has no roots in the specified interval.")
            exit()

        # Update the value of f(x), and display updated info
        fx = f(x)
        counter += 1
        print("n = {}: x = {:.8f}, a = {:.8f}, b = {:.8f}, f(x) = {:.8f}, |x{}-x{}| = {:.8f}\n".format(counter,x,a,b,fx,subscript(str(counter)),subscript(str(counter-1)),abs(x-old_x)))

        # Check if solution is found based on given error criteria
        if mode == 1:
            if abs(fx) < error:
                print("The solution is approximately x = {:.8f}, with f(x) = {:.8f}. \nThe solution was found after {} iterations.".format(x,fx,counter))
                exit()
        elif mode == 2:
            abs_error = abs(x-old_x)
            if abs_error < error:
                print("The solution is approximately x = {:.8f}, with |x{}-x{}| = {:.8f}. \nThe solution was found after {} iterations.".format(x,subscript(str(counter)),subscript(str(counter-1)),abs_error,counter))
                exit()


# ----------------------#
# False Position Method #
# ----------------------#
def FalsePosition(function, start, end, error, mode):

    # Take function as input, in python format
    f = lambda x: eval(function)
    a = start
    b = end
    x = (a*f(b)-b*f(a))/(f(b)-f(a))
    fx = f(x)
    ax = 0
    xb = 0
    counter = 0


    # Check if starting conditions are the solution.
    if f(a) == 0:
        print("The solution is exactly x = {}.".format(a))
        exit()
    elif f(b) == 0:
        print("The solution is exactly x = {}.".format(b))
        exit()
    elif fx == 0:
        print("The solution is exactly x = {}.".format(x))
        exit()

    # Display values of variables on each iteration.
    print("n = {}: x = {:.8f}, a = {:.8f}, b = {:.8f}, f(x) = {:.8f}\n".format(counter,x,a,b,fx))

    # Main loop
    while True:
        ax = sign(f(a)*fx)
        xb = sign(f(b)*fx)

        # Check which sub-interval the root belongs to,
        # and then set the endpoints to the new values.
        # If there are multiple roots in the given interval,
        # find the smallest one as default.
        if ax == -1:
            b = x
            old_x = x
            x = (a*f(b)-b*f(a))/(f(b)-f(a))
        elif xb == -1:
            a = x
            old_x = x
            x = (a*f(b)-b*f(a))/(f(b)-f(a))
        else:
            print("The function f has no roots in the specified interval.")
            exit()

        # Update the value of f(x), and display updated info
        fx = f(x)
        counter += 1
        print("n = {}: x = {:.8f}, a = {:.8f}, b = {:.8f}, f(x) = {:.8f}, |x{}-x{}| = {:.8f}\n".format(counter,x,a,b,fx,subscript(str(counter)),subscript(str(counter-1)),abs(x-old_x)))

        # Check if solution is found based on given error criteria
        if mode == 1:
            if abs(fx) < error:
                print("The solution is approximately x = {:.8f}, with f(x) = {:.8f}. \nThe solution was found after {} iterations.".format(x,fx,counter))
                exit()
        elif mode == 2:
            abs_error = abs(x-old_x)
            if abs_error < error:
                print("The solution is approximately x = {:.8f}, with |x{}-x{}| = {:.8f}. \nThe solution was found after {} iterations.".format(x,subscript(str(counter)),subscript(str(counter-1)),abs_error,counter))
                exit()


# --------------#
# Secant Method #
# --------------#
def Secant(function, p0, p1, error, mode):

    # Take function as input, in python format
    f = lambda x: eval(function)
    counter = 0
    # Set initial values
    pn_2 = p0
    pn_1 = p1
    print("n = 0: p{} = {}, f(p{}) = {:.8f}\n".format(subscript(str(0)),p0,subscript(str(0)),f(p0)))
    print("n = 1: p{} = {}, f(p{}) = {:.8f}\n".format(subscript(str(1)),p1,subscript(str(1)),f(p1)))
    # Main Loop
    while True:
        pn = pn_1 - (f(pn_1)*(pn_1-pn_2))/(f(pn_1)-f(pn_2))

        counter += 1

        # Display values of variables on each iteration.
        print("n = {}: p{} = {:.8f}, f(p{}) = {:.8f}, |p{}-p{}| = {:.8f}\n".format(counter+1,subscript(str(counter+1)),pn,subscript(str(counter+1)),f(pn),subscript(str(counter+1)),subscript(str(counter)),abs(pn-pn_1)))

        # Check if solution is found based on given error criteria
        if mode == 1:
            fx = f(pn)
            if abs(fx) < error:
                print("The solution is approximately x = {:.8f}, with f(x) = {:.8f}. \nThe solution was found after {} iterations.".format(pn,fx,counter))
                exit()
        elif mode == 2:
            abs_error = abs(pn-pn_1)
            if abs_error < error:
                print("The solution is approximately x = {:.8f}, with |p{}-p{}| = {:.8f}. \nThe solution was found after {} iterations.".format(pn,subscript(str(counter+1)),subscript(str(counter)),abs_error,counter))
                exit()

        # Update pn_2 and pn_1
        pn_2 = pn_1
        pn_1 = pn


# -------------------#
# Functions Showcase #
# -------------------#

# Above each function you will see a guide for the input parameters
# "1:" refers to the first arguement, "2:" refers to the second arguement, and so on
# Uncomment only one function at a time to see output more clearly

# -- printNumbers(digits, min, max) -- #
# 1: digits of the significand
# 2: minimum power of 2
# 3: maximum power of 2
printNumbers(5,-5,5)

# -- Bisection(function, start, end, error, mode) -- #
# 1: function entered in python format, surrounded by ""
# 2: start of interval
# 3: end of interval
# 4: error cutoff, you can enter numbers using scientific notation
# for example 1e-2 is read as 10**(-2)
# 5: error criteria, choosing "1" will use |f(x)| < error and choosing "2" will use |xₙ-xₙ₋₁| < error
# Bisection("x**3 - x - 2",1,2,1e-2,1)
# Bisection("x**3 - x - 2",1,2,1e-2,2)

# -- FalsePosition(function, start, end, error, mode) -- #
# 1: function entered in python format, surrounded by ""
# 2: start of interval
# 3: end of interval
# 4: error cutoff, you can enter numbers using scientific notation
# for example 1e-2 is read as 10**(-2)
# 5: error criteria, choosing "1" will use |f(x)| < error_cutoff and choosing "2" will use |xₙ-xₙ₋₁| < error_cutoff
# FalsePosition("x**3 - x - 2",1,2,1e-2,1)
# FalsePosition("x**3 - x - 2",1,2,1e-2,2)

# -- Secant(function, p0, p1, error, mode) -- #
# 1: function entered in python format, surrounded by ""
# 2: first initial approximation
# 3: second initial approximation
# 4: error cutoff, you can enter numbers using scientific notation
# for example 1e-2 is read as 10**(-2)
# 5: error criteria, choosing "1" will use |f(x)| < error_cutoff and choosing "2" will use |xₙ-xₙ₋₁| < error_cutoff
# Secant("x**3 - x - 2",1,2,1e-2,1)
# Secant("x**3 - x - 2",1,2,1e-2,2)