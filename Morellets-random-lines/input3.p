

# Ax + By + C = 0
# y = - (C + Ax) / B

# y(x2-x1) - x(y2-y1) + x1*y2 - x2*y1 = 0
# y(x2-x1) = x(y2-y1) - x1*y2 + x2*y1

# y = ( x*(y2-y1) + x2*y1 - x1*y2 ) / (x2-x1)



f1(x) = - (1 + 1*x) / 1

#  0,-1 :2,-1 
fP(x) = y = ( x*(-1+1) + 2 * -1 - 2*1 ) / (2+2)

set term qt size 1200,1200
set xtics 0.5
set ytics 0.5
#set multiplot
set grid
show grid
set mouse zoomjump
set xrange [-10:10]
#set yrange [0:0.1]
plot f1(x), fP(x)

