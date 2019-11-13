function root = Bisect ( xl , xu , eps , imax, f, enablePlot )

    i = 1;
    
    f_l = f(xl);
    f_u = f(xu);
    
    if enablePlot == 1
        hold on;
    end
    
    fprintf ('iteration approximation \n')
    
    while( i <= imax)
        xr = (xl+xu)/2;
        
        f_r = f(xr);
        
        plotCondition = [1 2 4 6];
        
        y = ismember(i, plotCondition);
        
        if (enablePlot == 1 && y)
            x = [xl: 0.01: xu];
            z = [xl xr xu];
            fz = f(z);
            
            plot(x, f(x), z, fz, '*g');
        end
        
        fprintf (' %6.0f %18.8f \n', i, xr );
        
        if (f_r == 0 || ((xu-xl)/abs(xu+xl))< eps)
            root = xr;
            return
        end
        i = i+1;
        
        if (f_l*f_r < 0)
            xu = xr;
        else
            xl = xr;
            f_l = f_r;
        end
    end
    
    if enablePlot == 1
        hold off
    end 
    
    fprintf ('failed to converge in %g iterations\n', imax)
end
    

    

    