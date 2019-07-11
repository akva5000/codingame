

# Ax + By + C = 0
# y = - (C + Ax) / B

# y(x2-x1) - x(y2-y1) + x1*y2 - x2*y1 = 0
# y(x2-x1) = x(y2-y1) - x1*y2 + x2*y1

# y = ( x*(y2-y1) + x2*y1 - x1*y2 ) / (x2-x1)



f1(x) = - (-9 + -7*x) / 6
f2(x) = - (-7 + -6*x) / 10
f3(x) = - (-2 + -3*x) / -4
f4(x) = - (-3*x)
f5(x) = - (3 + -3*x) / 8
f6(x) = - (-4 + 1*x) / -5
f7(x) = - (10 + 3*x) / -8
f8(x) = - (-5 + 3*x) / 9
f9(x) = - (-2 + 8*x) / 3
f10(x) = - (-3 + 10*x) / 4

#  -5,3 : 4,2
fP(x) = ( x*(2-3) + 4*3 + 5*2 ) / (4+5)

set term qt size 1200,1200
set xtics 0.5
set ytics 0.5
#set multiplot
set grid
show grid
set mouse zoomjump
set xrange [-5:4]
#set yrange [0:0.1]
plot f1(x), f2(x), f3(x), f4(x), f5(x), f6(x), f7(x), f8(x), f9(x), f10(x), fP(x)

