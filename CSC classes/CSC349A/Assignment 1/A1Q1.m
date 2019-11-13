%% Swapnil Daxini (V00861672) CSC 349A Assignment 1 

%% Question 1 Part A
type Euler.m
%% Question 1 Part B
% Use Euler Equation with constants m = 86.2, c = 12.5, v0 = 0
% t0=0, tn = 12, n = 15, g = 9.81

Euler(86.2, 12.5, 9.81, 0, 0, 12, 15)

%% Question 1 Part C

Euler(86.2, 12.5, 3.71, 0, 0, 12, 15)

%% Question 1 Part D

true_v = velocity(86.2, 9.81, 12.5, 12)

% Relative error is given by abs(1-approx_v/true_v)

approx_v = 57.0088
relative_error = abs(1-(approx_v/true_v)) 

%% Question 2 Part A

type Euler2.m

%% Question 2 Part B

Euler2(73.5, 0.234, 9.81, 0, 0, 18, 72)

%% Question 2 Part C

    approx_v = 55.3583
    true_v = velocity2nd(73.5, 9.81, 0.234, 18)
    
    relative_error = abs(1-(approx_v/true_v))
    
%% Question 3

% For this question, we can use the matlab function taylor which accepts a
% function and a value and expands it to a given order.

syms x;

f1 = exp(-x)
f2 = exp(x)

% Taylor series expansion of exp(-x) for n=1,2,3,4,5
t1 = taylor(f1, x, 'Order', 1);
t2 = taylor(f1, x, 'Order', 2);
t3 = taylor(f1, x, 'Order', 3);
t4 = taylor(f1, x, 'Order', 4);
t5 = taylor(f1, x, 'Order', 5);
t6 = taylor(f1, x, 'Order', 6);

% Taylor series expansion of exp(x) for n=1,2,3,4,5
g1 = taylor(f2, x, 'Order', 1);
g2 = taylor(f2, x, 'Order', 2);
g3 = taylor(f2, x, 'Order', 3);
g4 = taylor(f2, x, 'Order', 4);
g5 = taylor(f2, x, 'Order', 5);
g6 = taylor(f2, x, 'Order', 6);

% The taylor series are evaluated at x = 2 then convert to double
approx_1_1 = double(subs(t1, x, 2));
approx_1_2 = double(subs(t2, x, 2));
approx_1_3 = double(subs(t3, x, 2));
approx_1_4 = double(subs(t4, x, 2));
approx_1_5 = double(subs(t5, x, 2));
approx_1_6 = double(subs(t6, x, 2));

% The taylor series are evaluated at x = 2. The reciprocal of this result
% was calculated to approximate exp(-2)
approx_2_1 = double(1/subs(g1, x, 2));
approx_2_2 = double(1/subs(g2, x, 2));
approx_2_3 = double(1/subs(g3, x, 2));
approx_2_4 = double(1/subs(g4, x, 2));
approx_2_5 = double(1/subs(g5, x, 2));
approx_2_6 = double(1/subs(g6, x, 2));


true_value = exp(-2)

err_1_1 = relativeerror(true_value, approx_1_1);
err_1_2 = relativeerror(true_value, approx_1_2);
err_1_3 = relativeerror(true_value, approx_1_3);
err_1_4 = relativeerror(true_value, approx_1_4);
err_1_5 = relativeerror(true_value, approx_1_5);
err_1_6 = relativeerror(true_value, approx_1_6);

err_2_1 = relativeerror(true_value, approx_2_1);
err_2_2 = relativeerror(true_value, approx_2_2);
err_2_3 = relativeerror(true_value, approx_2_3);
err_2_4 = relativeerror(true_value, approx_2_4);
err_2_5 = relativeerror(true_value, approx_2_5);
err_2_6 = relativeerror(true_value, approx_2_6);


T = table([true_value;true_value;true_value;true_value;true_value;true_value], [approx_1_1;approx_1_2;approx_1_3;approx_1_4;approx_1_5;approx_1_6], [err_1_1;err_1_2;err_1_3;err_1_4;err_1_5;err_1_6], [approx_2_1;approx_2_2;approx_2_3;approx_2_4;approx_2_5;approx_2_6], [err_2_1;err_2_2;err_2_3;err_2_4;err_2_5;err_2_6]);
%%
% Note that Approximation1 represents the taylor expansion of exp(-2) while Approximation2 represents the taylor 
% expansion of 1/exp(x).
T.Properties.VariableNames = {'TrueValue';'Approximation1';'RelativeError1';'Approximation2';'RelativeError2'};

T
%% 
% From the table we can conclude that using the approximation of 1/exp(2) is 
% much better than the taylor approximation of exp(-2). The approximation
% with exp(-2) approaches the true more slower than 1/exp(2).


 

