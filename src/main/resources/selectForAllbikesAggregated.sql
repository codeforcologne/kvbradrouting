select number, sum(timeinmillis) timeinmillis, sum(distance) distance, count(number) countdata
from routing 
group by number