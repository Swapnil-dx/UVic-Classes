function [ rerror ] = relativeerror (true, approx)
% Function to find relative error
    rerror = abs(1-(approx/true));
    
end

