select 
  number, 
  timeinmillis, 
  distance, 
  modtime, 
  geom
from 
  routing
where
  number = ?