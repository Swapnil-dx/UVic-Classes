function xsol = ForwardSub(A, b)

% Solve for x_1 and x_2
xsol(1) = b(1)/A(1,1);
xsol(2) = (b(2)-A(2,1)*xsol(1))/A(2,2);

% Determine size of array A
[n, m] = size(A);

% Perform back-substitution for the terms 3 to n.
if( n > 2)
    for i = 3:n
        xsol(i) = (b(i)-A(i, i-1)*xsol(i-1)-A(i, i-2)*xsol(i-2))/A(i,i);
    end
end
end