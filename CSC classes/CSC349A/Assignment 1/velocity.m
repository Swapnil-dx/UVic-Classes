function [ v ] = velocity( m,g,c,t )
% Function to find the exact value of velocity.

v = (g*m/c)*(1-exp(-c*t/m));
end