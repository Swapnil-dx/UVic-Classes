function [ v ] = velocity2nd( m,g,k,t )
% Function to find the exact value of velocity.

v = (g*m/k)^0.5*(tanh((g*k/m)^0.5*t));
end