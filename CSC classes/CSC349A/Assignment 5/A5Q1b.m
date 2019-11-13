%% Swapnil Daxini (V00861672) Assignment 5

%% Question 1 Part b

x = [0:0.1:2*pi];
y = sin(x).^2;

% Define interpolating polynomial
P = (-27/(32*pi^2))*x.^2+(27/(16*pi))*x;

hold on

plot(x, y, 'Red', x, P, 'Blue')
title('Plot of function with interpolating polynomial')
legend({'f(x)', 'P(x)'})

hold off

