function y = your_function(x)
    g = 9.81;
    Q = 20;
    
    y = g*(3*x+x.^2/2).^3-Q^2*(3+x);
end