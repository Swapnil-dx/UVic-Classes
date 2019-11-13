syms f(x)

f(x) = 1/(1+24.5*x^2)

x5 = linspace(-1, 1, 5)

y5 = f(x5)

[P5, S5, R5] = lagrangepoly(x5, float(y5))

x20 = linspace(-1, 1, 20)

y20 = f(x20)

[P20, S20, R20] = lagrangepoly(x20, y20)
