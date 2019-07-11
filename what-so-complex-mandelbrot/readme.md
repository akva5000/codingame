# Goal

The Mandelbrot Set is the most well known set of complex numbers with fractal properties.

Members of the set are the complex numbers `c` such that the absolute value of the equation  
`f(n) = f(n-1)^2 + c` does not diverge as n approaches infinity, with `f(0) = 0`.

One property of this equation is that if its absolute value ever becomes larger than 2,  
we can be confident that it will diverge and therefore conclude that c is not in the set.  

However, an absolute value less than 2 does not guarantee that it will not diverge.  
Only additional iterations of the equation can help determine that.

Since the equation will never diverge for numbers in the set, we would run an  
infinite number of iterations if we only stopped based on the absolute value.  
Therefore, we select another number, `m`, and give up after running `m` iterations of the equation.  
Higher values of `m` could have given us greater confidence that our number is in the set,  
but we don't have infinite time so we have to draw a line somewhere.

For this puzzle, you will need to determine how many iterations are necessary to decide  
if a given complex number `c` is in the Mandelbrot set, using the absolute value heuristic  
described above, and given a maximum number of iterations `m`.

# Practice

f(0) = 0  
f(1) = c  
f(2) = f(1) + c = c + c = 2*c  
f(3) = c + c + c = 3*c  
f(4) = f(3) + c = 4*c  
f(N) = N*c


f(0) = 0  
f(1) = x + yi  
f(2) = f(1)^2 + x + yi  
     = (x+yi)^2 + x + yi  
     = x^2 + 2ixy - y^2 + x + iy  
     = x^2 - y^2 + x + (2xy + y)i
     
f(1) = 4.5 + 2i
f(2) =  (4.5 + 2i)^2 + 4.5 + 2i


x^2 + y^2 > 4


z{n} = x{n} + iy{n}
c = p + qi

x{n+1} = x{n}^2 - y{n}^2 + p,
y{n+1} = 2*x{n}*y{n} + q

    