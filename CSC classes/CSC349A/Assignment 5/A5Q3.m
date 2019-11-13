%% Swapnil Daxini (V00861672) Assignment 5

%% Question 3

%% Part A

X = [0 2*pi/3 4*pi/3 2*pi];

% Note the first and last entries are 0 which are our clamped boundary 
% conditions
Y = [0 0 0.75 0.75 0 0];

pp = spline(X, Y);

format short;

[b, c] = unmkpp( pp )


%% Part B

hold on;
x = linspace(0, 2*pi, 150);
y = sin(x).^2;
plot(x, y)

X1 = linspace(0, 2*pi/3, 50);
Y1 = c(1,1)*X1.^3 + c(1,2)*X1.^2 + c(1,3)*X1 + c(1,4);
plot(X1, Y1, ':')

X2 = linspace(2*pi/3, 4*pi/3, 50);
Y2 = c(2,1)*(X2 - 2*pi/3).^3 + c(2,2)*(X2 - 2*pi/3).^2 + c(2,3)*(X2 - 2*pi/3) + c(2,4);
plot(X2, Y2, '-')

X3 = linspace(4*pi/3, 2*pi, 50);
Y3 = c(3,1)*(X3 - 4*pi/3).^3 + c(3,2)*(X3 - 4*pi/3).^2 + c(3,3)*(X3 - 4*pi/3) + c(3,4);
plot(X3, Y3, ':')

legend({'f(x)', 'S_0(x)', 'S_1(x)', 'S_2(x)'})
title('Plot of function with cubic spline function')

hold off;